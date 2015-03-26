package sage;

import com.idrsolutions.pdf.parser.PdfStreamDecoder;
import com.idrsolutions.pdf.renderer.DynamicVectorRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.awt.print.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import javax.swing.RepaintManager;
import javax.swing.Timer;
import javax.swing.border.Border;
import org.jpedal.color.ColorSpaces;
import org.jpedal.exception.PdfException;
import org.jpedal.exception.PdfFontException;
import org.jpedal.grouping.PdfGroupingAlgorithms;
import org.jpedal.gui.Hotspots;
import org.jpedal.io.ColorSpaceConvertor;
import org.jpedal.io.ObjectStore;
import org.jpedal.io.PdfObjectReader;
import org.jpedal.io.StatusBar;
import org.jpedal.objects.PageLines;
import org.jpedal.objects.PageLookup;
import org.jpedal.objects.PdfAnnots;
import org.jpedal.objects.PdfData;
import org.jpedal.objects.PdfFileInformation;
import org.jpedal.objects.PdfFormData;
import org.jpedal.objects.PdfPageData;
import org.jpedal.objects.acroforms.AcroRenderer;
import org.jpedal.objects.outlines.OutlineData;
import org.jpedal.utils.LogWriter;
import org.jpedal.utils.Strip;
import org.w3c.dom.Document;

// Referenced classes of package org.jpedal:
//            PdfPanel

public final class PdfDecoder2 extends PdfPanel
    implements Printable, Pageable
{

  public static final String version = "ERF-2.50b02";
  public static boolean isRunningOnMac = false;
  public static boolean isDraft = true;
  private boolean useForms;
  private Graphics2D g2;
  private PdfObjectReader currentPdfFile;
  private boolean hasEmbeddedFonts;
  private String fontsInFile;
  public static int dpi = 72;
  public static boolean embedWidthData = false;
  private PageLookup pageLookup;
  private boolean isBackgroundDecoding;
  private OutlineData outlineData;
  private Object outlineObject;
  private String XMLObject;
  private boolean hasOutline;
  private int start;
  private int end;
  public static final boolean inDemo = true;
  private boolean renderPage;
  public static final int TEXT = 1;
  public static final int RAWIMAGES = 2;
  public static final int FINALIMAGES = 4;
  protected static final int PAGEDATA = 8;
  public static final int RAWCOMMANDS = 16;
  public static final int CLIPPEDIMAGES = 32;
  public static final int TEXTCOLOR = 64;
  public static final int CMYKIMAGES = 128;
  public static final int RENDERTEXT = 1;
  public static final int RENDERIMAGES = 2;
  private String annotObject;
  private boolean isForm;
  private PdfFormData currentAcroFormData;
  private static int extractionMode = 7;
  private static int renderMode = 7;
  private PdfData pdfData;
  private PdfData pdfBackgroundData;
  private int pageCount;
  private Map pagesReferences;
  private Map globalRes;
  private boolean isDecoding;
  public static Map fontSubstitutionTable = null;
  public static Map fontSubstitutionLocation = new Hashtable();
  public static Map fontSubstitutionAliasTable = new Hashtable();
  public static boolean enforceFontSubstitution = false;
  private boolean showImageable;
  public static String defaultFont = null;
  private Map pageFormats;
  private static final String separator = System.getProperty("file.separator");
  private static boolean isXMLExtraction = true;
  private Map globalMediaValues;
  private boolean includeImages;
  private StatusBar statusBar;
  public static boolean use13jPEGConversion = false;
  private boolean imageIsTransparent;
  public boolean usePageScaling;
  private boolean useHiResImageForDisplay;
  private boolean operationSuccessful;
  private String pageErrorMessages;
  private String filename;
  private ObjectStore backgroundObjectStoreRef;
  private static final boolean useXFAformsCheck = false;

  static
  {
      try
      {
          String s = System.getProperty("os.name");
          if(s.equals("Mac OS X"))
              isRunningOnMac = true;
      }
      catch(Exception exception) { }
    }


    class ProgressListener
        implements ActionListener
    {

        public void actionPerformed(ActionEvent actionevent)
        {
            statusBar.setProgress((int)statusBar.percentageDone);
        }

        ProgressListener()
        {
        }
    }


    public void setSubstitutedFontAliases(String s, String as[])
    {
        if(as != null)
        {
            int i = as.length;
            for(int j = 0; j < i; j++)
                fontSubstitutionAliasTable.put(as[j].toLowerCase(), s.toLowerCase());

        }
    }

    private void addTTFonts(String s)
    {
        String s1;
        for(StringTokenizer stringtokenizer = new StringTokenizer(s, ","); stringtokenizer.hasMoreTokens(); addTTDir(s1))
        {
            s1 = stringtokenizer.nextToken();
            if((!s1.endsWith("/")) & (!s1.endsWith("\\")))
                s1 = s1 + separator;
            LogWriter.writeLog("Looking in " + s1 + " for TT fonts");
        }

    }

    public void resetViewableArea()
    {
        viewableArea = null;
        setPageRotation(displayRotation);
        repaint();
    }

    public AffineTransform setViewableArea(Rectangle rectangle)
        throws PdfException
    {
        if(rectangle != null)
        {
            double d = rectangle.getX();
            double d1 = rectangle.getY();
            double d2 = rectangle.getWidth();
            double d3 = rectangle.getHeight();
            double d4 = pageData.getCropBoxX(pageNumber);
            double d5 = pageData.getCropBoxY(pageNumber);
            double d6 = pageData.getCropBoxWidth(pageNumber);
            double d7 = pageData.getCropBoxHeight(pageNumber);
            if(d < 0.0D || d1 < 0.0D || d + d2 > d6 || d1 + d3 > d7)
                throw new PdfException("Viewport is not totally enclosed within displayed panel.");
            if(d6 != d2 || d7 != d3)
            {
                viewableArea = rectangle;
                currentDisplay.setOptimiseDrawing(false);
                setPageRotation(displayRotation);
                repaint();
            }
        } else
        {
            resetViewableArea();
        }
        return viewScaling;
    }

    public void setTTFontDirs(String as[])
    {
        try
        {
            if(as == null)
            {
                LogWriter.writeLog("Null font parameter passed");
            } else
            {
                int i = as.length;
                for(int j = 0; j < i; j++)
                {
                    String s = as[j];
                    if((!s.endsWith("/")) & (!s.endsWith("\\")))
                        s = s + separator;
                    LogWriter.writeLog("Looking in " + s + " for TT fonts");
                    addTTDir(s);
                }

            }
        }
        catch(Exception exception)
        {
            LogWriter.writeLog("Unable to run setTTFontDirs " + exception.getMessage());
        }
    }

    private void addTTDir(String s)
    {
        if(fontSubstitutionTable == null)
            fontSubstitutionTable = new HashMap();
        String as[] = {
            "/TrueType"
        };
        File file = new File(s);
        if(file.exists() && file.isDirectory())
        {
            String as1[] = file.list();
            if(as1 != null)
            {
                int i = as1.length;
                for(int j = 0; j < i; j++)
                {
                    String s1 = as1[j];
                    if(!s1.toLowerCase().endsWith(".ttf"))
                        continue;
                    FileInputStream fileinputstream = null;
                    try
                    {
                        fileinputstream = new FileInputStream(s + s1);
                    }
                    catch(FileNotFoundException filenotfoundexception) { }
                    if(fileinputstream != null)
                    {
                        int k = s1.indexOf(".");
                        String s2;
                        if(k == -1)
                            s2 = s1;
                        else
                            s2 = s1.substring(0, k);
                        fontSubstitutionTable.put(s2.toLowerCase(), as[0]);
                        fontSubstitutionLocation.put(s2.toLowerCase(), s + s1);
                        LogWriter.writeLog("Added truetype font " + s2 + " path=" + s + s1);
                    } else
                    {
                        LogWriter.writeLog("No fonts found at " + s);
                    }
                }

            }
        }
    }

    public final void init(boolean flag)
    {
        embedWidthData = flag;
    }

    public PdfDecoder2(boolean flag)
    {
        useForms = true;
        g2 = null;
        hasEmbeddedFonts = false;
        fontsInFile = "";
        pageLookup = new PageLookup();
        isBackgroundDecoding = false;
        outlineData = null;
        outlineObject = null;
        hasOutline = false;
        start = 0;
        end = -1;
        renderPage = false;
        annotObject = null;
        isForm = false;
        pageCount = 0;
        pagesReferences = new Hashtable();
        isDecoding = false;
        showImageable = false;
        pageFormats = new Hashtable();
        globalMediaValues = new Hashtable();
        statusBar = null;
        imageIsTransparent = true;
        usePageScaling = false;
        useHiResImageForDisplay = false;
        pageErrorMessages = "";
        backgroundObjectStoreRef = new ObjectStore();
        objectStoreRef = new ObjectStore();
        renderPage = flag;
        setLayout(null);
        startup();
    }

    public PdfDecoder2(int i, boolean flag)
    {
        useForms = true;
        g2 = null;
        hasEmbeddedFonts = false;
        fontsInFile = "";
        pageLookup = new PageLookup();
        isBackgroundDecoding = false;
        outlineData = null;
        outlineObject = null;
        hasOutline = false;
        start = 0;
        end = -1;
        renderPage = false;
        annotObject = null;
        isForm = false;
        pageCount = 0;
        pagesReferences = new Hashtable();
        isDecoding = false;
        showImageable = false;
        pageFormats = new Hashtable();
        globalMediaValues = new Hashtable();
        statusBar = null;
        imageIsTransparent = true;
        usePageScaling = false;
        useHiResImageForDisplay = false;
        pageErrorMessages = "";
        backgroundObjectStoreRef = new ObjectStore();
        objectStoreRef = new ObjectStore();
        renderPage = flag;
        setLayout(null);
        startup();
    }

    private void startup()
    {
        String s = System.getProperty("debug");
        if(s != null)
            LogWriter.setupLogFile(true, 1, "", "v", false);
        try
        {
            String s1 = System.getProperty("TTfontMaps");
            if(s1 != null)
            {
                String as[];
                String s4;
                for(StringTokenizer stringtokenizer = new StringTokenizer(s1, ","); stringtokenizer.hasMoreTokens(); setSubstitutedFontAliases(s4, as))
                {
                    String s3 = stringtokenizer.nextToken();
                    StringTokenizer stringtokenizer1 = new StringTokenizer(s3, "=:");
                    int i = stringtokenizer1.countTokens() - 1;
                    as = new String[i];
                    s4 = stringtokenizer1.nextToken();
                    for(int j = 0; j < i; j++)
                        as[j] = stringtokenizer1.nextToken();

                }

            }
        }
        catch(Exception exception)
        {
            LogWriter.writeLog("Unable to read TTfontMaps " + exception.getMessage());
        }
        try
        {
            String s2 = System.getProperty("TTfontDirs");
            if(s2 != null)
                addTTFonts(s2);
        }
        catch(Exception exception1)
        {
            LogWriter.writeLog("Unable to read TTfontDirs " + exception1.getMessage());
        }
        if(renderPage)
        {
            setToolTipText("image preview");
            highlightFont = new Font("Lucida", 1, size);
            setPreferredSize(new Dimension(100, 100));
        }
    }

    public PdfDecoder2()
    {
        useForms = true;
        g2 = null;
        hasEmbeddedFonts = false;
        fontsInFile = "";
        pageLookup = new PageLookup();
        isBackgroundDecoding = false;
        outlineData = null;
        outlineObject = null;
        hasOutline = false;
        start = 0;
        end = -1;
        renderPage = false;
        annotObject = null;
        isForm = false;
        pageCount = 0;
        pagesReferences = new Hashtable();
        isDecoding = false;
        showImageable = false;
        pageFormats = new Hashtable();
        globalMediaValues = new Hashtable();
        statusBar = null;
        imageIsTransparent = true;
        usePageScaling = false;
        useHiResImageForDisplay = false;
        pageErrorMessages = "";
        backgroundObjectStoreRef = new ObjectStore();
        objectStoreRef = new ObjectStore();
        renderPage = true;
        setLayout(null);
        startup();
    }

    public final void closePdfFile()
    {
        if(currentPdfFile != null)
            currentPdfFile.closePdfFile();
        currentPdfFile = null;
        objectStoreRef.flush();
    }

    public final byte[] getPdfBuffer()
    {
        byte abyte0[] = null;
        if(currentPdfFile != null)
            abyte0 = currentPdfFile.getPdfBuffer();
        return abyte0;
    }

    public final PdfData getPdfBackgroundData()
    {
        return pdfBackgroundData;
    }

    public final PdfData getPdfData()
        throws PdfException
    {
        if((extractionMode & 1) == 0)
            throw new PdfException("[PDF] Page data object requested will be empty as text extraction disabled. Enable with PdfDecoder method setExtractionMode(PdfDecoder.TEST | other values");
        else
            return pdfData;
    }

    /**
     * @deprecated Method getPdfBackgroundPageData is deprecated
     */

    public final PdfPageData getPdfBackgroundPageData()
    {
        return pageData;
    }

    public final boolean hasOutline()
    {
        return hasOutline;
    }

    public final Document getOutlineAsXML()
    {
        if(outlineData == null && outlineObject != null)
            try
            {
                outlineData = new OutlineData(pageCount);
                int i = outlineData.readOutlineFileMetadata(outlineObject, currentPdfFile, pageLookup);
            }
            catch(Exception exception)
            {
                System.out.println("Exception " + exception + " accessing outline " + outlineObject);
            }
        return outlineData.getList();
    }

    public final PdfPageData getPdfPageData()
    {
        return pageData;
    }

    public void setPagePrintRange(int i, int j)
    {
        start = i;
        end = j;
    }

    public int print(Graphics g, PageFormat pageformat, int i)
        throws PrinterException
    {
        int j = 0;
        double d;
        //if(useHiResImageForDisplay)
        //    currentDisplay.dumpImagesFromMemory();
        d = 1.0D;
        if(usePageScaling)
            d = scaling;
        if(start > end && end != -1)
            i = end - i;
        else
            i = start + i;
        if(end == -1)
            i++;
        if((i > pageCount) | (end != -1) & (i > end))
            return 1;
        try
        {
            if((end == -1) | (i >= start) & (i <= end))
            {
                operationSuccessful = true;
                pageErrorMessages = "";
                try
                {
                    PdfAnnots pdfannots = null;
                    String s = (String)pagesReferences.get(new Integer(i));
                    if(s != null)
                    {
                        Map map = currentPdfFile.readObject(s, false, null);
                        try
                        {
                            String s1 = (String)map.get("Annots");
                            if(s1 != null)
                            {
                                pdfannots = new PdfAnnots(currentPdfFile);
                                pdfannots.readAnnots(s1);
                            } else
                            {
                                pdfannots = null;
                            }
                        }
                        catch(Exception exception)
                        {
                            LogWriter.writeLog("[PDF] " + exception + " with annotation");
                        }
                        String s2 = (String)map.get("Contents");
                        if(printHotspots != null)
                            printHotspots.flushAnnotationsDisplayed();
                        if(s2 != null)
                        {
                            PdfStreamDecoder pdfstreamdecoder = new PdfStreamDecoder(true);
                            ObjectStore objectstore = new ObjectStore();
                            pdfstreamdecoder.setStore(objectstore);
                            pdfstreamdecoder.optimiseDisplayForPrinting();
                            Map map1 = currentPdfFile.getSubDictionary(map.get("Resources"));
                            try
                            {
                                pdfstreamdecoder.init(true, renderPage, renderMode, 0, pageData, i, null, currentPdfFile, globalRes, map1);
                            }
                            catch(PdfException pdfexception)
                            {
                                throw new PdfFontException(pdfexception.getMessage());
                            }
                            int k = (int)pageformat.getImageableX();
                            int l = (int)pageformat.getImageableY();
                            int i1 = (int)pageformat.getImageableWidth() - 1;
                            int j1 = (int)pageformat.getImageableHeight() - 1;
                            g.setClip(k, l, i1, j1);
                            if(showImageable)
                            {
                                Rectangle rectangle = new Rectangle(k, l, i1, j1);
                                Graphics2D graphics2d = (Graphics2D)g;
                                graphics2d.setColor(Color.green);
                                graphics2d.fill(rectangle);
                                graphics2d.setColor(Color.black);
                                graphics2d.draw(new java.awt.geom.Line2D.Float(k, l, k + i1, l + j1));
                                graphics2d.draw(new java.awt.geom.Line2D.Float(k, l + j1, k + i1, l));
                            }
                            AffineTransform affinetransform = new AffineTransform();
                            int k1 = pageData.getMediaBoxWidth(i);
                            int l1 = pageData.getMediaBoxHeight(i);
                            int i2 = pageData.getMediaBoxX(i);
                            int j2 = pageData.getMediaBoxY(i);
                            int k2;
                            int k3 = k2 = pageData.getCropBoxWidth(i) + 1;
                            int l2;
                            int l3 = l2 = pageData.getCropBoxHeight(i) + 1;
                            int i3;
                            int i4 = i3 = pageData.getCropBoxX(i);
                            int j3;
                            int j4 = j3 = pageData.getCropBoxY(i);
                            int k4 = pageData.getRotation(i);
                            boolean flag = false;
                            if((k4 == 90) | (k4 == 270))
                            {
                                int l4 = i4;
                                i4 = j4;
                                j4 = l4;
                                l4 = k3;
                                k3 = l3;
                                l3 = l4;
                                flag = true;
                            }
                            if(k3 > l3)
                                pageformat.setOrientation(0);
                            else
                                pageformat.setOrientation(1);
                            double d1 = 1.0D;
                            int i5 = (int)((double)k3 * d);
                            int j5 = (int)((double)l3 * d);
                            if((i5 > i1) | (j5 > j1))
                            {
                                i5 = k3;
                                j5 = l3;
                                d = 1.0D;
                            }
                            if((i5 > i1) | (j5 > j1))
                            {
                                double d2 = (double)i1 / (double)i5;
                                double d5 = (double)j1 / (double)j5;
                                if(d2 < d5)
                                    d1 = d2;
                                else
                                    d1 = d5;
                                i5 = (int)((double)i5 * d1);
                                j5 = (int)((double)j5 * d1);
                            }
                            if(!flag)
                                affinetransform.translate(-((double)i4 * d1), (double)j4 * d1);
                            if(k4 == 270)
                            {
                                affinetransform.rotate(-1.5707963267948966D, i5 / 2, j5 / 2);
                                double d3 = affinetransform.getTranslateX();
                                double d6 = affinetransform.getTranslateY();
                                affinetransform.translate(d6 - (double)j5 - (double)l, d3 + (double)k);
                            } else
                            if(k4 == 180)
                            {
                                affinetransform.rotate(3.1415926535897931D, i5 / 2, j5 / 2);
                                affinetransform.translate(-k, j5 - j1 - l);
                            } else
                            if(k4 == 90)
                            {
                                affinetransform.rotate(1.5707963267948966D, i5 / 2, j5 / 2);
                                double d4 = affinetransform.getTranslateX();
                                double d7 = affinetransform.getTranslateY();
                                affinetransform.translate(-d7 + (double)l, (double)i5 - d4 - (double)k);
                            } else
                            {
                                affinetransform.translate(k, l);
                            }
                            if(d1 < 1.0D)
                            {
                                affinetransform.translate(i5, j5);
                                affinetransform.scale(1.0D, -1D);
                                affinetransform.translate(-i5, 0.0D);
                                affinetransform.scale(d1, d1);
                            } else
                            {
                                affinetransform.translate(i5, j5);
                                affinetransform.scale(1.0D, -1D);
                                affinetransform.translate(-i5, 0.0D);
                                affinetransform.scale(d, d);
                            }
                            i4 = i3;
                            j4 = j3;
                            k3 = k2;
                            l3 = l2;
                            RepaintManager repaintmanager = RepaintManager.currentManager(this);
                            repaintmanager.setDoubleBufferingEnabled(false);
                            Graphics2D graphics2d1 = (Graphics2D)g;
                            java.awt.Shape shape = graphics2d1.getClip();
                            graphics2d1.setRenderingHints(ColorSpaces.hints);
                            graphics2d1.transform(affinetransform);
                            if(showImageable)
                            {
                                graphics2d1.setColor(Color.red);
                                graphics2d1.draw(new Rectangle(i2, j2, k1, l1));
                                graphics2d1.drawLine(i2, j2, k1 + i2, l1 + j2);
                                graphics2d1.drawLine(i2, l1 + j2, k1 + i2, j2);
                                graphics2d1.setColor(Color.blue);
                                graphics2d1.draw(new Rectangle(i4, j4, k3, l3));
                                graphics2d1.drawLine(i4, j4, k3 + i4, l3 + j4);
                                graphics2d1.drawLine(i4, l3 + j4, k3 + i4, j4);
                            } else
                            {
                                graphics2d1.clip(new Rectangle(i4, j4, k3, l3));
                            }
                            pdfstreamdecoder.setDirectRendering((Graphics2D)g);
                            try
                            {
                                pdfstreamdecoder.decodePageContent(s2, 0, 0);
                            }
                            catch(PdfException pdfexception1)
                            {
                                pdfexception1.printStackTrace();
                                throw new PrinterException(pdfexception1.getMessage());
                            }
                            graphics2d1.setClip(null);
                            if(pdfannots != null && printHotspots != null)
                                printHotspots.setHotspots(pdfannots);
                            if(printHotspots != null)
                                printHotspots.addHotspotsToDisplay(graphics2d1, userAnnotIcons, i);
                            /*if(currentFormRenderer != null && currentAcroFormData != null)
                            {
                                currentFormRenderer.createDisplayComponentsForPage(i, null, (float)d, k4);
                                currentFormRenderer.removeDisplayComponentsFromScreen(this);
                                currentFormRenderer.renderFormsOntoG2(graphics2d1, i, (float)d, 0);
                                currentFormRenderer.createDisplayComponentsForPage(pageNumber, this, scaling, displayRotation);
                            }*/
                            graphics2d1.setColor(Color.red);
                            graphics2d1.drawLine(i4, j4, k3 + i4, l3 + j4);
                            graphics2d1.drawLine(i4, l3 + j4, k3 + i4, j4);
                            if(useBorder && myBorder != null)
                                myBorder.paintBorder(this, graphics2d1, i4, j4, k3, l3);
                            repaintmanager.setDoubleBufferingEnabled(true);
                            if(!pdfstreamdecoder.isPageSuccessful())
                            {
                                operationSuccessful = false;
                                pageErrorMessages = pageErrorMessages + pdfstreamdecoder.getPageFailureMessage();
                            }
                        }
                    }
                }
                catch(PdfFontException pdffontexception)
                {
                    operationSuccessful = false;
                    pageErrorMessages = pageErrorMessages + "Missing substitute fonts\n";
                }
            }
        }
        catch(Error error)
        {
            operationSuccessful = false;
            pageErrorMessages = pageErrorMessages + "Memory Error on printing\n";
        }
        if(!operationSuccessful)
            j = 1;
        return j;
    }

    public BufferedImage getPageAsImage(int i)
        throws PdfException
    {
        BufferedImage bufferedimage = null;
        if((i > pageCount) | (i < 1))
        {
            LogWriter.writeLog("Page " + i + " not in range");
        } else
        {
            PdfAnnots pdfannots = null;
            String s = (String)pagesReferences.get(new Integer(i));
            if(s != null)
            {
                Map map = currentPdfFile.readObject(s, false, null);
                try
                {
                    String s1 = (String)map.get("Annots");
                    if(s1 != null)
                    {
                        pdfannots = new PdfAnnots(currentPdfFile);
                        pdfannots.readAnnots(s1);
                    } else
                    {
                        pdfannots = null;
                    }
                }
                catch(Exception exception)
                {
                    LogWriter.writeLog("[PDF] " + exception + " with annotation");
                }
                String s2 = (String)map.get("Contents");
                if(printHotspots != null)
                    printHotspots.flushAnnotationsDisplayed();
                if(s2 != null)
                {
                    ObjectStore objectstore = new ObjectStore();
                    PdfStreamDecoder pdfstreamdecoder = new PdfStreamDecoder();
                    pdfstreamdecoder.setName(filename);
                    pdfstreamdecoder.setStore(objectstore);
                    Map map1 = currentPdfFile.getSubDictionary(map.get("Resources"));
                    pdfstreamdecoder.init(true, renderPage, renderMode, 0, pageData, i, null, currentPdfFile, globalRes, map1);
                    AffineTransform affinetransform = setPageParametersForImage(scaling, i);
                    int j = pageData.getMediaBoxWidth(i);
                    int k = pageData.getMediaBoxHeight(i);
                    int l = pageData.getRotation(i);
                    int i1 = pageData.getCropBoxWidth(i);
                    int j1 = pageData.getCropBoxHeight(i);
                    int k1 = pageData.getCropBoxX(i);
                    int l1 = pageData.getCropBoxY(i);
                    boolean flag = false;
                    int i2;
                    int j2;
                    if((l == 90) | (l == 270))
                    {
                        j2 = (int)((float)i1 * scaling);
                        i2 = (int)((float)j1 * scaling);
                        flag = true;
                    } else
                    {
                        i2 = (int)((float)i1 * scaling);
                        j2 = (int)((float)j1 * scaling);
                    }
                    if(imageIsTransparent)
                        bufferedimage = new BufferedImage(i2, j2, 2);
                    else
                        bufferedimage = new BufferedImage(i2, j2, 1);
                    Graphics g = bufferedimage.getGraphics();
                    Graphics2D graphics2d = (Graphics2D)g;
                    graphics2d.setColor(Color.white);
                    graphics2d.fillRect(0, 0, i2, j2);
                    java.awt.Shape shape = graphics2d.getClip();
                    graphics2d.setRenderingHints(ColorSpaces.hints);
                    graphics2d.transform(affinetransform);
                    if(flag)
                        graphics2d.translate(-k1, l1);
                    pdfstreamdecoder.setDirectRendering((Graphics2D)g);
                    pdfstreamdecoder.decodePageContent(s2, 0, 0);
                    graphics2d.setClip(null);
                    if(pdfannots != null && printHotspots != null)
                        printHotspots.setHotspots(pdfannots);
                    if(printHotspots != null)
                        printHotspots.addHotspotsToDisplay(graphics2d, userAnnotIcons, i);
                    /*if(currentFormRenderer != null && currentAcroFormData != null)
                    {
                        currentFormRenderer.createDisplayComponentsForPage(i, this, scaling, 0);
                        currentFormRenderer.renderFormsOntoG2(graphics2d, i, scaling, 0);
                    }*/
                    graphics2d.setColor(Color.red);
                    graphics2d.drawLine(0, 0, j, k);
                    graphics2d.drawLine(0, k, j, 0);
                    objectstore.flush();
                }
            }
            bufferedimage = ColorSpaceConvertor.convertToRGB(bufferedimage);
        }
        return bufferedimage;
    }

    public final void flushObjectValues(boolean flag)
    {
        boolean flag1 = false;
        if(pdfData != null)
            pdfData.flushTextList(flag);
        if(currentAcroFormData != null)
            currentAcroFormData = new PdfFormData(flag1);
    }

    public final PdfAnnots getPdfAnnotsData()
    {
        if(annotsData == null)
            try
            {
                if(annotObject != null)
                {
                    annotsData = new PdfAnnots(currentPdfFile);
                    annotsData.readAnnots(annotObject);
                }
            }
            catch(Exception exception)
            {
                LogWriter.writeLog("[PDF] " + exception + " with annotation");
            }
        return annotsData;
    }

    private final void readAcroForm(Object obj)
    {
        String s = "";
        String s1 = "";
        HashMap hashmap = new HashMap();
        if(!useForms)
            return;
        LogWriter.writeLog("Form data being read");
        Vector vector = new Vector();
        Map map;
        if(obj instanceof String)
            map = currentPdfFile.readObject((String)obj, false, null);
        else
            map = (Map)obj;
        boolean flag = map.get("XFA") != null;
        s = (String)map.get("Fields");
        if(s != null)
        {
            int i = s.indexOf("]");
            if(i != 0)
                s = s.substring(0, i + 1);
            s = Strip.removeArrayDeleminators(s);
            boolean flag1 = true;
            boolean flag2 = false;
            StringTokenizer stringtokenizer = new StringTokenizer(s, "R");
            HashMap hashmap1 = new HashMap();
            hashmap1.put("T", "x");
            hashmap1.put("TM", "x");
            hashmap1.put("TU", "x");
            hashmap1.put("CA", "x");
            hashmap1.put("R", "x");
            hashmap1.put("V", "x");
            hashmap1.put("RC", "x");
            hashmap1.put("DA", "x");
            hashmap1.put("DV", "x");
            if(stringtokenizer.hasMoreTokens())
            {
                s1 = stringtokenizer.nextToken().trim() + " R";
                for(; stringtokenizer.hasMoreTokens(); vector.addElement(stringtokenizer.nextToken().trim() + " R"));
            } else
            {
                flag1 = false;
            }
            do
            {
                if(!flag1)
                    break;
                Object obj1 = currentPdfFile.readObject(s1, false, hashmap1);
                Map map1 = (Map)hashmap.get(s1);
                if(map1 != null)
                {
                    Iterator iterator = map1.keySet().iterator();
                    do
                    {
                        if(!iterator.hasNext())
                            break;
                        Object obj2 = iterator.next();
                        Object obj3 = map1.get(obj2);
                        if(!((Map) (obj1)).containsKey(obj2))
                            ((Map) (obj1)).put(obj2, obj3);
                    } while(true);
                }
                String s2 = (String)((Map) (obj1)).get("Kids");
                String s3 = (String)((Map) (obj1)).get("Type");
                if(s3 != null && s3.equals("/Annot"))
                {
                    if(currentAcroFormData == null)
                        currentAcroFormData = new PdfFormData(flag2);
                    HashMap hashmap2 = new HashMap();
                    currentPdfFile.flattenValuesInObject(((Map) (obj1)), hashmap2, hashmap1, pageLookup, s1);
                    obj1 = hashmap2;
                    try
                    {
                        currentAcroFormData.addFormElement(((Map) (obj1)));
                    }
                    catch(Exception exception)
                    {
                        exception.printStackTrace();
                    }
                } else
                if(s2 != null)
                {
                    String s4 = s2;
                    if(s4.startsWith("["))
                        s4 = s4.substring(1, s4.length() - 1).trim();
                    String s5;
                    for(StringTokenizer stringtokenizer1 = new StringTokenizer(s4, "R"); stringtokenizer1.hasMoreTokens(); hashmap.put(s5, obj1))
                    {
                        s5 = stringtokenizer1.nextToken().trim() + " R";
                        vector.addElement(s5);
                        ((Map) (obj1)).remove("Kids");
                    }

                }
                if(vector.isEmpty())
                    break;
                s1 = (String)vector.firstElement();
                vector.removeElement(s1);
            } while(true);
        }
        //if(currentFormRenderer != null && currentAcroFormData != null)
        //    currentFormRenderer.init(currentAcroFormData.getFormData(), pageCount, insetW, insetH, pageData, currentPdfFile);
    }

    /**
     * @deprecated Method setRenderMode is deprecated
     */

    public final void setRenderMode(int i, String as[])
    {
        setRenderMode(i);
    }

    public final void setRenderMode(int i)
    {
        renderMode = i;
    }

    public final PdfFileInformation getFileInformationData()
    {
        if(currentPdfFile != null)
            return currentPdfFile.readPdfFileMetadata(XMLObject);
        else
            return null;
    }

    public final void setExtractionMode(int i, int j, float f)
    {
        if((double)f < 0.5D)
            f = 0.5F;
        scaling = f;
    }

    public final PdfAnnots decodePageForAnnotations(int i)
    {
        PdfAnnots pdfannots = null;
        if(i > getPageCount())
        {
            LogWriter.writeLog("Page out of bounds");
        } else
        {
            String s1 = (String)pagesReferences.get(new Integer(i));
            if(s1 != null)
            {
                Map map = currentPdfFile.readObject(s1, false, null);
                try
                {
                    String s = currentPdfFile.getValue((String)map.get("Annots"));
                    if(s != null)
                    {
                        pdfannots = new PdfAnnots(currentPdfFile);
                        pdfannots.readAnnots(s);
                    }
                }
                catch(Exception exception)
                {
                    LogWriter.writeLog("[PDF] " + exception + " with annotation");
                }
            }
        }
        return pdfannots;
    }

    public final BufferedImage getPageAsThumbnail(int i, int j)
    {
        BufferedImage bufferedimage = null;
        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        boolean flag4 = false;
        boolean flag5 = false;
        ObjectStore objectstore = new ObjectStore();
        DynamicVectorRenderer dynamicvectorrenderer = new DynamicVectorRenderer(true, 1000, objectstore);
        try
        {
            if(i > getPageCount())
            {
                LogWriter.writeLog("Page " + i + " out of bounds");
            } else
            {
                int k = pageData.getMediaBoxX(i);
                int l = pageData.getMediaBoxY(i);
                int i1 = pageData.getMediaBoxWidth(i);
                int j1 = pageData.getMediaBoxHeight(i);
                String s = (String)pagesReferences.get(new Integer(i));
                if(s != null)
                {
                    Map map = currentPdfFile.readObject(s, false, null);
                    String s1 = (String)map.get("Contents");
                    if(s1 != null)
                    {
                        PdfStreamDecoder pdfstreamdecoder = new PdfStreamDecoder();
                        pdfstreamdecoder.setName(filename);
                        pdfstreamdecoder.setStore(objectstore);
                        Map map1 = currentPdfFile.getSubDictionary(map.get("Resources"));
                        pdfstreamdecoder.init(true, true, renderMode, 0, pageData, i, dynamicvectorrenderer, currentPdfFile, globalRes, map1);
                        int k1 = pageData.getRotation(i);
                        dynamicvectorrenderer.init(i1, j1, k1);
                        pdfstreamdecoder.decodePageContent(s1, k, l);
                        pdfstreamdecoder = null;
                    }
                }
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        objectstore.flush();
        bufferedimage = getImageFromRenderer(j, dynamicvectorrenderer, i);
        Graphics2D graphics2d = bufferedimage.createGraphics();
        graphics2d.setColor(Color.red);
        graphics2d.drawLine(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight());
        graphics2d.drawLine(0, bufferedimage.getHeight(), bufferedimage.getWidth(), 0);
        return bufferedimage;
    }

    public void setStatusBarObject(StatusBar statusbar)
    {
        statusBar = statusbar;
    }

    public final void decodePage(int i)
        throws Exception
    {
        if(isDecoding)
        {
            LogWriter.writeLog("[PDF]WARNING - this file is being decoded already");
        } else
        {
            isDecoding = true;
            cursorBoxOnScreen = null;
            //if(currentFormRenderer != null)
            //    currentFormRenderer.removeDisplayComponentsFromScreen(this);
            currentDisplay.flush();
            screenNeedsRedrawing = true;
            overRideAcceleration = false;
            if(i > getPageCount() || i < 1)
            {
                LogWriter.writeLog("Page out of bounds");
            } else
            {
                Timer timer = null;
                if(statusBar != null)
                {
                    ProgressListener progresslistener = new ProgressListener();
                    timer = new Timer(500, progresslistener);
                    timer.start();
                }
                pageNumber = i;
                String s1 = (String)pagesReferences.get(new Integer(i));
                if(s1 != null)
                {
                    Map map = currentPdfFile.readObject(s1, false, null);
                    annotObject = currentPdfFile.getValue((String)map.get("Annots"));
                    annotsData = null;
                    if(renderPage)
                        annotsData = getPdfAnnotsData();
                    String s = (String)map.get("Contents");
                    if(displayHotspots != null)
                        displayHotspots.flushAnnotationsDisplayed();
                    if(s != null)
                    {
                        PdfStreamDecoder pdfstreamdecoder = new PdfStreamDecoder(useHiResImageForDisplay);
                        pdfstreamdecoder.setName(filename);
                        pdfstreamdecoder.setStore(objectStoreRef);
                        if(includeImages)
                            pdfstreamdecoder.includeImages();
                        pdfstreamdecoder.setStatusBar(statusBar);
                        currentDisplay.setHiResImageForDisplayMode(useHiResImageForDisplay);
                        Map map1 = currentPdfFile.getSubDictionary(map.get("Resources"));
                        if(map1 == null)
                        {
                            for(String s2 = (String)map.get("Parent"); s2 != null && map1 == null;)
                            {
                                Map map2 = currentPdfFile.readObject(s2, false, null);
                                Object obj = map2.get("Resources");
                                if(obj == null)
                                    s2 = (String)map2.get("Parent");
                                else
                                if(obj instanceof String)
                                    map1 = currentPdfFile.getSubDictionary((String)obj);
                                else
                                    map1 = (Map)obj;
                            }

                        }
                        pdfstreamdecoder.init(true, renderPage, renderMode, extractionMode, pageData, i, currentDisplay, currentPdfFile, globalRes, map1);
                        pdfstreamdecoder.setStatusBar(statusBar);
                        int j = pageData.getMediaBoxWidth(pageNumber);
                        int k = pageData.getMediaBoxHeight(pageNumber);
                        int l = pageData.getMediaBoxX(pageNumber);
                        int i1 = pageData.getMediaBoxY(pageNumber);
                        int j1 = pageData.getRotation(pageNumber);
                        currentDisplay.init(j, k, j1);
                        if(g2 != null)
                            pdfstreamdecoder.setDirectRendering(g2);
                        pdfstreamdecoder.decodePageContent(s, 0, 0);
                        hasEmbeddedFonts = pdfstreamdecoder.hasEmbeddedFonts();
                        fontsInFile = pdfstreamdecoder.getFontsInFile();
                        pdfData = pdfstreamdecoder.getText();
                        if(embedWidthData)
                            pdfData.widthIsEmbedded();
                        pdfstreamdecoder = null;
                    }
                }
                if(timer != null)
                {
                    timer.stop();
                    statusBar.setProgress(100);
                }
                /*if(currentFormRenderer != null && currentAcroFormData != null)
                {
                    currentFormRenderer.createDisplayComponentsForPage(i, this, scaling, displayRotation);
                    validate();
                }*/
                if(annotsData != null && displayHotspots != null)
                    displayHotspots.setHotspots(annotsData);
            }
            screenNeedsRedrawing = true;
            isDecoding = false;
        }
    }

    public void useHiResScreenDisplay(boolean flag)
    {
        useHiResImageForDisplay = flag;
    }

    public final void decodePageInBackground(int i)
        throws Exception
    {
        if(isBackgroundDecoding)
        {
            LogWriter.writeLog("[PDF]WARNING - this file is being decoded already in background");
        } else
        {
            isBackgroundDecoding = true;
            if(i > getPageCount())
            {
                LogWriter.writeLog("Page out of bounds");
            } else
            {
                String s1 = (String)pagesReferences.get(new Integer(i));
                if(s1 != null)
                {
                    Map map = currentPdfFile.readObject(s1, false, null);
                    String s = (String)map.get("Contents");
                    if(s != null)
                    {
                        PdfStreamDecoder pdfstreamdecoder = new PdfStreamDecoder();
                        pdfstreamdecoder.setName(filename);
                        pdfstreamdecoder.setStore(backgroundObjectStoreRef);
                        Map map1 = currentPdfFile.getSubDictionary(map.get("Resources"));
                        pdfstreamdecoder.init(true, false, 0, extractionMode, pageData, i, null, currentPdfFile, globalRes, map1);
                        pdfstreamdecoder.decodePageContent(s, 0, 0);
                        pdfBackgroundData = pdfstreamdecoder.getText();
                        if(embedWidthData)
                            pdfBackgroundData.widthIsEmbedded();
                        pdfstreamdecoder = null;
                    }
                }
            }
            isBackgroundDecoding = false;
        }
    }

    public final int getPageCount()
    {
        return pageCount;
    }

    public final boolean isEncrypted()
    {
        if(currentPdfFile != null)
            return currentPdfFile.isEncrypted();
        else
            return false;
    }

    public final boolean isPasswordSupplied()
    {
        if(currentPdfFile != null)
            return currentPdfFile.isPasswordSupplied();
        else
            return false;
    }

    public boolean isFileViewable()
    {
        if(currentPdfFile != null)
            return currentPdfFile.isFileViewable();
        else
            return false;
    }

    public boolean isExtractionAllowed()
    {
        if(currentPdfFile != null)
            return currentPdfFile.isExtractionAllowed();
        else
            return false;
    }

    public final void verifyAccess()
    {
        if(currentPdfFile != null)
            try
            {
                openPdfFile();
            }
            catch(Exception exception)
            {
                LogWriter.writeLog("Exception " + exception + " opening file");
            }
    }

    public final void setDefaultDisplayFont(String s)
        throws PdfFontException
    {
        boolean flag = false;
        String as[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        int i = as.length;
        for(int j = 0; j < i; j++)
            if(as[j].toLowerCase().equals(s.toLowerCase()))
            {
                flag = true;
                defaultFont = as[j];
                j = i;
            }

        if(!flag)
            throw new PdfFontException("Font " + s + " is not available.");
        else
            return;
    }

    public final void setEncryptionPassword(String s)
    {
        currentPdfFile.setEncryptionPassword(s);
    }

    public final void openPdfArray(byte abyte0[])
        throws PdfException
    {
        LogWriter.writeMethod("{openPdfArray}", 0);
        try
        {
            currentPdfFile = new PdfObjectReader();
            currentPdfFile.openPdfFile(abyte0);
            openPdfFile();
            objectStoreRef.storeFileName("r" + System.currentTimeMillis());
        }
        catch(Exception exception)
        {
            throw new PdfException("[PDF] OpenPdfArray generated exception " + exception.getMessage());
        }
    }

    public final void openPdfFile(String s)
        throws PdfException
    {
        LogWriter.writeMethod("{openPdfFile " + s + "}", 0);
        filename = s;
        objectStoreRef.storeFileName(s);
        currentPdfFile = new PdfObjectReader();
        currentPdfFile.openPdfFile(s);
        openPdfFile();
    }

    public final void openPdfFileFromURL(String s)
        throws PdfException
    {
        LogWriter.writeMethod("{openPdfFileFromURL " + s + "}", 0);
        Object obj = null;
        byte abyte0[] = null;
        Object obj1 = null;
        Object obj2 = null;
        try
        {
            URL url = new URL(s);
            InputStream inputstream = url.openStream();
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
            byte abyte1[] = new byte[4096];
            for(int i = 0; (i = inputstream.read(abyte1)) != -1;)
                bytearrayoutputstream.write(abyte1, 0, i);

            abyte0 = bytearrayoutputstream.toByteArray();
            inputstream.close();
            bytearrayoutputstream.close();
        }
        catch(IOException ioexception)
        {
            LogWriter.writeLog("[PDF] Exception " + ioexception + " opening URL " + s);
        }
        currentPdfFile = new PdfObjectReader();
        currentPdfFile.openPdfFile(abyte0);
        openPdfFile();
        objectStoreRef.storeFileName("<raw data>");
    }

    private final void openPdfFile()
        throws PdfException
    {
        LogWriter.writeMethod("{openPdfFile}", 0);
        //if(currentFormRenderer != null)
        //    currentFormRenderer.removeDisplayComponentsFromScreen(this);
        pageData = new PdfPageData();
        String s = currentPdfFile.getType();
        LogWriter.writeLog("Pdf version : " + s);
        if(s.indexOf("1.5") != -1)
            LogWriter.writeLog("Please note Pdf version 1.5  some features not fully supported ");
        else
        if(s.indexOf("1.6") != -1)
            LogWriter.writeLog("Please note Pdf version 1.6  new features not fully supported ");
        LogWriter.writeMethod("{about to read ref table}", 0);
        String s1 = currentPdfFile.readReferenceTable();
        LogWriter.writeMethod("{about to read catalog}", 0);
        Map map = currentPdfFile.readObject(s1, false, null);
        if((!isEncrypted()) | isPasswordSupplied())
        {
            XMLObject = (String)map.get("Metadata");
            String s2 = (String)map.get("Pages");
            LogWriter.writeMethod("{about to read pages}", 0);
            if(s2 != null)
            {
                LogWriter.writeLog("Pages being read " + s2);
                pageNumber = 1;
                pageLookup = new PageLookup();
                readAllPageReferences(s2);
                pageCount = pageNumber - 1;
                pageNumber = 0;
                if(getPageCount() == 0)
                    LogWriter.writeLog("No pages found");
            }
            Object obj = null;
            try
            {
                obj = map.get("Names");
                if(obj != null)
                    currentPdfFile.readNames(obj);
            }
            catch(Exception exception)
            {
                LogWriter.writeLog("Exception reading Names object " + obj + " " + objectStoreRef.fullFileName);
                //System.out.println(map);
                exception.printStackTrace();
            }
            outlineObject = map.get("Outlines");
            outlineData = null;
            if(outlineObject != null)
                hasOutline = true;
            else
                hasOutline = false;
            Object obj1 = map.get("AcroForm");
           // if(currentFormRenderer != null)
           //     currentFormRenderer.removeDisplayComponentsFromScreen(this);
            if(obj1 != null)
            {
                readAcroForm(obj1);
                isForm = true;
            } else
            {
                isForm = false;
                currentAcroFormData = null;
            }
        }
    }

    private final void readAllPageReferences(String s)
    {
        LogWriter.writeMethod("{readAllPageReferences " + s + "}", 0);
        String s1 = "";
        String s3 = "";
        Map map = currentPdfFile.readObject(s, false, null);
        s3 = (String)map.get("Type");
        if(s3 == null)
            s3 = "/Pages";
        s1 = currentPdfFile.getValue((String)map.get("Rotate"));
        if(s1 == null)
            s1 = "0";
        pageData.setPageRotation(s1, pageNumber);
        String s4 = currentPdfFile.getValue((String)map.get("MediaBox"));
        if(s4 != null)
        {
            pageData.setMediaBox(s4);
        } else
        {
            String s5 = s3;
            if(s3.equals("Page"))
            {
                s4 = (String)globalMediaValues.get("Pages");
                if(s4 == null)
                    s5 = "Kids";
            }
            if(s4 == null && s5.equals("Kids"))
            {
                s4 = (String)globalMediaValues.get("Kids");
                if(s4 == null)
                    s5 = "Catalog";
            }
            if(s4 == null && s5.equals("Catalog"))
                s4 = (String)globalMediaValues.get("Catalog");
            if(s4 == null)
                s4 = "0 0 800 800";
        }
        s1 = currentPdfFile.getValue((String)map.get("CropBox"));
        if(s1 != null)
            pageData.setCropBox(s1);
        if(!s3.equals("Page") && s4 != null)
            globalMediaValues.put(s3, s4);
        if(s3.indexOf("/Pages") != -1)
        {
            globalRes = currentPdfFile.getSubDictionary(map.get("Resources"));
            String s2 = Strip.removeArrayDeleminators(currentPdfFile.getValue((String)map.get("Kids")));
            if(s2.length() > 0)
            {
                for(StringTokenizer stringtokenizer = new StringTokenizer(s2, "R"); stringtokenizer.hasMoreTokens(); readAllPageReferences(stringtokenizer.nextToken().trim() + " R"));
            }
        } else
        if(s3.indexOf("/Page") != -1)
        {
            pagesReferences.put(new Integer(pageNumber), s);
            pageLookup.put(s, pageNumber);
            pageData.checkSizeSet(pageNumber);
            pageNumber++;
        }
    }

    protected static ArrayList getDirectoryMatches(String s)
        throws URISyntaxException, IOException
    {
        s = s.replaceAll("\\.", "/");
        URL url = Thread.currentThread().getContextClassLoader().getResource(s);
        ArrayList arraylist = new ArrayList(0);
        String s1 = url.toString();
        if(s1.startsWith("jar:") && s1.endsWith(s))
        {
            int i = s1.lastIndexOf(s);
            s1 = s1.substring(0, i);
            URL url1 = new URL(s1);
            JarURLConnection jarurlconnection = (JarURLConnection)url1.openConnection();
            JarFile jarfile = jarurlconnection.getJarFile();
            Enumeration enumeration = jarfile.entries();
            do
            {
                if(!enumeration.hasMoreElements())
                    break;
                JarEntry jarentry = (JarEntry)enumeration.nextElement();
                if((!jarentry.isDirectory()) & jarentry.getName().startsWith(s))
                {
                    String s2 = jarentry.getName();
                    int j = s2.lastIndexOf("/");
                    s2 = s2.substring(j + 1);
                    arraylist.add(s2);
                }
            } while(true);
        } else
        {
            LogWriter.writeLog("Path: " + s1);
        }
        return arraylist;
    }

    protected static ArrayList getDirectoryMatchesXX(String s)
        throws URISyntaxException, IOException
    {
        s = s.replaceAll("\\.", "/");
        URL url = Thread.currentThread().getContextClassLoader().getResource(s);
        ArrayList arraylist = new ArrayList(0);
        String s1 = url.toString();
        if(s1.startsWith("jar:") && s1.endsWith(s))
        {
            int i = s1.lastIndexOf("!/" + s);
            s1 = s1.substring(4, i);
        }
        if(s1.startsWith("http://"))
        {
            InputStream inputstream = Thread.currentThread().getContextClassLoader().getResourceAsStream(s);
            arraylist = readIndirectValues(inputstream);
        } else
        {
            try
            {
                JarFile jarfile = new JarFile(new File(new URI(s1)));
                Enumeration enumeration = jarfile.entries();
                do
                {
                    if(!enumeration.hasMoreElements())
                        break;
                    JarEntry jarentry = (JarEntry)enumeration.nextElement();
                    if(!jarentry.isDirectory() && jarentry.getName().startsWith(s))
                    {
                        String s2 = jarentry.getName();
                        int j = s2.lastIndexOf("/");
                        s2 = s2.substring(j + 1);
                        arraylist.add(s2);
                    }
                } while(true);
            }
            catch(Exception exception) { }
        }
        return arraylist;
    }

    private static ArrayList readIndirectValues(InputStream inputstream)
        throws IOException
    {
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
        ArrayList arraylist = new ArrayList(0);
        do
        {
            String s = bufferedreader.readLine();
            if(s != null)
            {
                arraylist.add(s);
            } else
            {
                bufferedreader.close();
                return arraylist;
            }
        } while(true);
    }

    public boolean addSubstituteFonts(String s, boolean flag)
    {
        if(fontSubstitutionTable == null)
            fontSubstitutionTable = new HashMap();
        boolean flag1 = false;
        try
        {
            String as[] = {
                "tt", "t1c", "t1"
            };
            String as1[] = {
                "/TrueType", "/Type1C", "/Type1"
            };
            enforceFontSubstitution = flag;
            ClassLoader classloader = getClass().getClassLoader();
            InputStream inputstream = classloader.getResourceAsStream(s);
            LogWriter.writeLog("Looking for root " + s);
            if(inputstream != null)
            {
                LogWriter.writeLog("Adding fonts fonts found in  tt,t1c,t1 sub-directories of " + s);
                flag1 = true;
                for(int i = 0; i < as.length; i++)
                {
                    if(!s.endsWith("/"))
                        s = s + "/";
                    String s1 = s + as[i] + "/";
                    InputStream inputstream1 = classloader.getResourceAsStream(s1);
                    if(inputstream1 != null)
                    {
                        try
                        {
                            ArrayList arraylist;
                            if(inputstream1 instanceof ByteArrayInputStream)
                                arraylist = readIndirectValues(inputstream1);
                            else
                                arraylist = getDirectoryMatches(s1);
                            String s2 = "";
                            int j = arraylist.size();
                            int k = 0;
                            do
                            {
                                if(k >= j)
                                    break;
                                String s3 = (String)arraylist.get(k);
                                if(s3 == null)
                                    break;
                                int l = s3.indexOf(".");
                                String s4;
                                if(l == -1)
                                    s4 = s3;
                                else
                                    s4 = s3.substring(0, l);
                                fontSubstitutionTable.put(s4.toLowerCase(), as1[i]);
                                fontSubstitutionLocation.put(s4.toLowerCase(), s1 + s3);
                                LogWriter.writeLog("Added from jar =" + s4 + " path=" + s1 + s3);
                                k++;
                            } while(true);
                        }
                        catch(Exception exception1)
                        {
                            LogWriter.writeLog("Exception " + exception1 + " reading substitute fonts");
                        }
                    }
                }

            } else
            {
                LogWriter.writeLog("No fonts found at " + s);
            }
        }
        catch(Exception exception)
        {
            LogWriter.writeLog("Exception adding substitute fonts " + exception.getMessage());
        }
        return flag1;
    }

    public void addUserIconsForAnnotations(int i, String s, Image aimage[])
    {
        if(userAnnotIcons == null)
            userAnnotIcons = new Hashtable();
        userAnnotIcons.put(i + "-" + s, aimage);
        if(displayHotspots == null)
        {
            displayHotspots = new Hotspots();
            printHotspots = new Hotspots();
        }
    }

    public void createPageHostspots(String as[], String s)
    {
        displayHotspots = new Hotspots(as, s);
        printHotspots = new Hotspots(as, s);
    }

    public void showImageableArea()
    {
        showImageable = true;
    }

    public int getNumberOfPages()
    {
        return (end - start) + 1;
    }

    public PageFormat getPageFormat(int i)
        throws IndexOutOfBoundsException
    {
        Object obj = pageFormats.get(new Integer(i + start));
        if(obj != null)
            return (PageFormat)obj;
        else
            return new PageFormat();
    }

    public Printable getPrintable(int i)
        throws IndexOutOfBoundsException
    {
        return this;
    }

    public void setPageFormat(int i, PageFormat pageformat)
    {
        pageFormats.put(new Integer(i), pageformat);
    }

    public static boolean isXMLExtraction()
    {
        return isXMLExtraction;
    }

    public void clearScreen()
    {
        currentDisplay.flush();
    }

    public boolean hasEmbeddedFonts()
    {
        return hasEmbeddedFonts;
    }

    public String getFontsInFile()
    {
        if(fontsInFile == null)
            return "No fonts defined";
        else
            return fontsInFile;
    }

    public void includeImagesInStream()
    {
        includeImages = true;
    }

    public PageLines getPageLines()
    {
        return pageLines;
    }

    public void setEnableLegacyJPEGConversion(boolean flag)
    {
        use13jPEGConversion = flag;
    }

    public void setThumbnaiImageTransparency(boolean flag)
    {
        imageIsTransparent = flag;
    }

    public void enableScaledPrinting(boolean flag)
    {
        usePageScaling = flag;
    }

    public float getScaleForPrinting()
    {
        if(usePageScaling && scaling < 1.0F)
            return scaling;
        else
            return 1.0F;
    }

  /*  public void setCurrentFormRenderer(AcroRenderer acrorenderer)
    {
        currentFormRenderer = acrorenderer;
    }*/

    public boolean isPageSuccessful()
    {
        return operationSuccessful;
    }

    public String getPageFailureMessage()
    {
        return pageErrorMessages;
    }

    public BufferedImage getSelectedRectangleOnscreen(int i, int j, int k, int l, int i1)
    {
        int j1 = pageData.getMediaBoxWidth(pageNumber);
        int k1 = pageData.getMediaBoxHeight(pageNumber);
        int l1 = pageData.getMediaBoxX(pageNumber);
        int i2 = pageData.getMediaBoxY(pageNumber);
        int j2 = pageData.getCropBoxWidth(pageNumber);
        int k2 = pageData.getCropBoxHeight(pageNumber);
        int l2 = pageData.getCropBoxX(pageNumber);
        int i3 = pageData.getCropBoxY(pageNumber);
        if(l < i3)
            l = i3;
        if(i < l2)
            i = l2;
        if(j > k2 + i3)
            j = k2 + i3;
        if(k > l2 + j2)
            k = l2 + j2;
        if(k - i < 1 || j - l < 1)
            return null;
        float f = i1 / 100;
        int j3 = k - i;
        int k3 = j - l;
        BufferedImage bufferedimage = new BufferedImage((int)((float)j3 * f), (int)((float)k3 * f), 1);
        Graphics2D graphics2d = bufferedimage.createGraphics();
        if(i3 > 0)
            i3 = k1 - k2 - i3;
        float f1 = f;
        AffineTransform affinetransform = getScalingForImage(0, f1);
        int l3 = -l2;
        int i4 = -i3;
        affinetransform.translate(l3, -i4);
        affinetransform.translate(-(i - l2), k1 - j - i3);
        AffineTransform affinetransform1 = graphics2d.getTransform();
        if(affinetransform != null)
            graphics2d.transform(affinetransform);
        if(currentDisplay.addBackground())
        {
            graphics2d.setColor(currentDisplay.getBackgroundColor());
            graphics2d.fill(new Rectangle(l2, i3, j2, k2));
        }
        currentDisplay.paint(graphics2d, null, null);
       /* if(currentFormRenderer != null && currentAcroFormData != null)
        {
            currentFormRenderer.removeDisplayComponentsFromScreen(this);
            currentFormRenderer.renderFormsOntoG2(graphics2d, pageNumber, i1, 0);
            currentFormRenderer.createDisplayComponentsForPage(pageNumber, this, scaling, displayRotation);
        }*/
        if(annotsData != null && displayHotspots != null)
            displayHotspots.setHotspots(annotsData);
        graphics2d.setTransform(affinetransform1);
        graphics2d.setColor(Color.red);
        graphics2d.drawLine(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight());
        graphics2d.drawLine(0, bufferedimage.getHeight(), bufferedimage.getWidth(), 0);
        graphics2d.dispose();
        return bufferedimage;
    }

    public ObjectStore getObjectStore()
    {
        return objectStoreRef;
    }

    public void setObjectStore(ObjectStore objectstore)
    {
        objectStoreRef = objectstore;
    }

   /* public PdfGroupingAlgorithms2 getGroupingObject()
        throws PdfException
    {
        PdfData pdfdata = getPdfData();
        if(pdfdata == null)
        {
          System.out.println("return null");
          return null;
        }
        else
        {
          System.out.println("return new");
          //return new org.jpedal.grouping.PdfGroupingAlgorithms(pdfdata);
          return new PdfGroupingAlgorithms2(pdfdata);
        }
    }

    public PdfGroupingAlgorithms2 getBackgroundGroupingObject()
        throws PdfException
    {
        PdfData pdfdata = getPdfBackgroundData();

        if(pdfdata == null)
        {
          System.out.println("return null");
          return null;
        }
        else
        {
          System.out.println("return new");
          //return new org.jpedal.grouping.PdfGroupingAlgorithms(pdfdata);
          return new PdfGroupingAlgorithms2(pdfdata);
        }
    }*/
}
