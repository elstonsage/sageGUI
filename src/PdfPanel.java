package sage;

import com.idrsolutions.pdf.renderer.DynamicVectorRenderer;
import java.awt.*;
import java.awt.dnd.DropTarget;
import java.awt.event.MouseEvent;
import java.awt.font.GlyphVector;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.PrintStream;
import java.util.Hashtable;
import javax.swing.*;
import javax.swing.border.Border;
import org.jpedal.gui.Hotspots;
import org.jpedal.io.ObjectStore;
import org.jpedal.objects.PageLines;
import org.jpedal.objects.PdfAnnots;
import org.jpedal.objects.PdfPageData;
import org.jpedal.objects.acroforms.AcroRenderer;
import org.jpedal.objects.acroforms.DefaultAcroRenderer;
import org.jpedal.utils.repositories.Vector_Int;
import org.jpedal.utils.repositories.Vector_Rectangle;
import org.jpedal.utils.repositories.Vector_Shape;
import org.jpedal.utils.repositories.Vector_String;


public class PdfPanel extends JPanel
{

  private int scrollInterval;
  private boolean showCrop;
  private boolean isNewRotationSet;
  protected boolean displayViewportBorder;
  protected int displayRotation;
  private Point current_p;
  protected Rectangle viewableArea;
  protected PdfAnnots annotsData;
  private Vector_Int merge_level;
  private Vector_Shape merge_outline;
  private boolean showDebugLevel[];
  private Color debugColors[];
  private boolean showMerging;
  private int crx;
  private int cry;
  private int crw;
  private int crh;
  AffineTransform demoAf;
  private boolean tooltipsAreInitialised;
  private RepaintManager currentManager;
  protected int pageNumber;
  private AffineTransform displayScaling;
  protected AffineTransform viewScaling;
  //protected AcroRenderer currentFormRenderer;
  protected PdfPageData pageData;
  protected PageLines pageLines;
  private Rectangle lastHighlight;
  protected Rectangle cursorBoxOnScreen;
  protected Rectangle lastCursorBoxOnScreen;
  private boolean drawCrossHairs;
  private int boxContained;
  private Color selectedHandleColor;
  private int handlesGap;
  private Color outlineColor;
  private Rectangle currentHighlightedObject;
  private Color outlineHighlightColor;
  private Color highlightColors[];
  private final int strip = 2;
  private Rectangle2D outlineZone[];
  protected int insetW;
  protected int insetH;
  private boolean highlightedZonesSelected[];
  private int defaultSize;
  private int y_size;
  private int max_y;
  private int volatileWidth;
  private int volatileHeight;
  private int x_size;
  int cx[];
  int cy[];
  protected float scaling;
  private Vector_String toolTipText;
  private Vector_Rectangle toolTipRectangle;
  private Vector_Int ttType;
  private Vector_Int toolTipPage;
  private int tooltipsCount;
  private int highlightMode;
  public static final int SHOW_OBJECTS = 1;
  public static final int SHOW_LINES = 2;
  protected int size;
  protected Font highlightFont;
  protected Border myBorder;
  private static final int OUTLINE_CURRENT = 2;
  private static final int SHOW_SELECTED = 4;
  private static final int TEXT_TOOLTIP = 8;
  private static final int MASTER_TOOLTIP = 16;
  protected DropTarget dropTarget;
  protected ObjectStore objectStoreRef;
  protected DynamicVectorRenderer currentDisplay;
  Hotspots displayHotspots;
  Hotspots printHotspots;
  Hashtable userAnnotIcons;
  protected boolean useBorder;
  private int selectionOrder[];
  private Rectangle areas[];
  private Rectangle rectAreas;
  private Object linkedItems[];
  private int parents[];
  private Shape fragmentShapes[];
  private int x_size_cropped;
  private int y_size_cropped;
  private boolean useAcceleration;
  protected boolean screenNeedsRedrawing;
  protected boolean overRideAcceleration;
  private AffineTransform cursorAf;
  private Rectangle actualBox;
    protected VolatileImage backBuffer;

    public PdfPanel()
    {
        scrollInterval = 10;
        showCrop = false;
        isNewRotationSet = false;
        displayViewportBorder = false;
        displayRotation = 0;
        viewableArea = null;
        showMerging = false;
        demoAf = null;
        tooltipsAreInitialised = false;
        currentManager = RepaintManager.currentManager(this);
        viewScaling = null;
        //currentFormRenderer = new DefaultAcroRenderer();
        pageData = new PdfPageData();
        lastHighlight = null;
        cursorBoxOnScreen = null;
        lastCursorBoxOnScreen = null;
        drawCrossHairs = false;
        boxContained = -1;
        selectedHandleColor = Color.red;
        handlesGap = 5;
        currentHighlightedObject = null;
        outlineZone = null;
        insetW = 0;
        insetH = 0;
        highlightedZonesSelected = null;
        defaultSize = 100;
        y_size = defaultSize;
        x_size = defaultSize;
        cx = null;
        cy = null;
        scaling = 1.0F;
        tooltipsCount = 0;
        highlightMode = 0;
        size = 20;
        highlightFont = null;
        myBorder = null;
        dropTarget = null;
        objectStoreRef = new ObjectStore();
        currentDisplay = new DynamicVectorRenderer(objectStoreRef);
        useBorder = true;
        useAcceleration = true;
        overRideAcceleration = false;
        backBuffer = null;
    }

    private void createBackBuffer()
    {
        if(backBuffer != null)
        {
            backBuffer.flush();
            backBuffer = null;
        }
        try
        {
            if(volatileWidth > 0 && volatileHeight > 0)
                if((displayRotation == 0) | (displayRotation == 180))
                    backBuffer = createVolatileImage((int)((float)volatileWidth * scaling + (float)(insetW * 2)), (int)((float)volatileHeight * scaling + (float)(insetH * 2)));
                else
                if((displayRotation == 90) | (displayRotation == 270))
                    backBuffer = createVolatileImage((int)((float)volatileHeight * scaling + (float)(insetH * 2)), (int)((float)volatileWidth * scaling + (float)(insetW * 2)));
        }
        catch(Error error)
        {
            overRideAcceleration = true;
        }
    }

    public Rectangle getCombinedAreas(Rectangle rectangle, boolean flag)
    {
        if(currentDisplay != null)
            return currentDisplay.getCombinedAreas(rectangle, flag);
        else
            return null;
    }

    protected final void flushToolTips()
    {
        toolTipText = new Vector_String(100);
        toolTipRectangle = new Vector_Rectangle(100);
        ttType = new Vector_Int(100);
        toolTipPage = new Vector_Int(100);
        tooltipsCount = 0;
    }

    public final void addMergingDisplayForDebugging(Vector_Int vector_int, Vector_Shape vector_shape, int i, Color acolor[])
    {
        merge_level = vector_int;
        merge_outline = vector_shape;
        showDebugLevel = new boolean[i];
        debugColors = acolor;
    }

    public final void setDebugView(int i, boolean flag)
    {
        if(showDebugLevel != null)
            showDebugLevel[i] = flag;
    }

    public final BufferedImage getPageAsThumbnail(int i)
    {
        BufferedImage bufferedimage = getImageFromRenderer(i, currentDisplay, pageNumber);
        //Graphics2D graphics2d = bufferedimage.createGraphics();
        //graphics2d.setColor(Color.red);
        //graphics2d.drawLine(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight());
        //graphics2d.drawLine(0, bufferedimage.getHeight(), bufferedimage.getWidth(), 0);
        return bufferedimage;
    }

    protected BufferedImage getImageFromRenderer(int i, DynamicVectorRenderer dynamicvectorrenderer, int j)
    {
        int k = pageData.getMediaBoxWidth(j);
        int l = pageData.getMediaBoxHeight(j);
        int i1 = pageData.getMediaBoxX(j);
        int j1 = pageData.getMediaBoxY(j);
        int k1 = pageData.getCropBoxWidth(j);
        int l1 = pageData.getCropBoxHeight(j);
        int i2 = pageData.getCropBoxX(j);
        int j2 = pageData.getCropBoxY(j);
        if(j2 > 0)
            j2 = l - l1 - j2;
        float f = (float)i / (float)l1;
        int k2 = pageData.getRotation(j);
        byte byte0 = -1;
        if((k2 == 90) | (k2 == 270))
        {
            int l2 = k1;
            k1 = l1;
            l1 = l2;
            byte0 = 1;
            l2 = i2;
            i2 = j2;
            j2 = l2;
        }
        AffineTransform affinetransform = getScalingForImage(k2, f);
        int i3 = i1 - i2;
        int j3 = j1 - j2;
        affinetransform.translate(i3, byte0 * j3);
        BufferedImage bufferedimage = dynamicvectorrenderer.getPageAsImage(f, i2, j2, k1, l1, j, affinetransform, 1);
        return bufferedimage;
    }

    public final void setHighlightedZones(int i, int ai[], int ai1[], Shape ashape[], Object aobj[], int ai2[], Rectangle2D arectangle2d[],
            boolean aflag[], Color acolor[], int ai3[])
    {
        cx = ai;
        cy = ai1;
        fragmentShapes = ashape;
        linkedItems = aobj;
        parents = ai2;
        outlineZone = arectangle2d;
        highlightedZonesSelected = aflag;
        highlightMode = i;
        highlightColors = acolor;
        selectionOrder = ai3;
    }

    private final void createToolTip(int i, String s, Rectangle rectangle, int j)
    {
        if(!tooltipsAreInitialised)
        {
            tooltipsAreInitialised = true;
            toolTipText = new Vector_String(100);
            toolTipRectangle = new Vector_Rectangle(100);
            ttType = new Vector_Int(100);
            toolTipPage = new Vector_Int(100);
        }
        toolTipRectangle.addElement(rectangle);
        toolTipText.addElement(s);
        ttType.addElement(i);
        toolTipPage.addElement(j);
        tooltipsCount++;
    }

    public void setDebugDisplay(boolean flag)
    {
        showMerging = flag;
    }

    public final void setInset(int i, int j)
    {
        insetW = i;
        insetH = j;
    }

    public void ensurePointIsVisible(Point point)
    {
        super.scrollRectToVisible(new Rectangle(point.x, y_size - point.y, scrollInterval, scrollInterval));
    }

    public final Dimension getMaximumSize()
    {
        if((displayRotation == 90) | (displayRotation == 270))
            return new Dimension(y_size_cropped + insetW + insetW, x_size_cropped + insetH + insetH);
        else
            return new Dimension(x_size_cropped + insetW + insetW, y_size_cropped + insetH + insetH);
    }

    public final Dimension getMinimumSize()
    {
        return new Dimension(100 + insetW, 100 + insetH);
    }

    public final Dimension getPreferredSize()
    {
        return getMaximumSize();
    }

    public void setFoundTextAreas(Rectangle rectangle)
    {
        rectAreas = rectangle;
    }

    public void setHighlightedAreas(Rectangle arectangle[])
    {
        double d = 0.0D;
        double d1 = 0.0D;
        double d2 = 0.0D;
        double d3 = 0.0D;
        boolean flag = true;
        if(arectangle != null)
        {
            Rectangle arectangle1[] = new Rectangle[arectangle.length];
            for(int i = 0; i < arectangle.length; i++)
            {
                if(arectangle[i] == null)
                    continue;
                if(flag)
                {
                    d = arectangle[i].getMinX();
                    d1 = arectangle[i].getMinY();
                    d2 = arectangle[i].getMaxX();
                    d3 = arectangle[i].getMaxY();
                } else
                {
                    double d4 = arectangle[i].getMinX();
                    double d5 = arectangle[i].getMinY();
                    double d6 = arectangle[i].getMaxX();
                    double d7 = arectangle[i].getMaxY();
                    if(d4 < d)
                        d = d4;
                    if(d5 < d1)
                        d1 = d5;
                    if(d6 > d2)
                        d2 = d6;
                    if(d7 > d3)
                        d3 = d7;
                }
                arectangle1[i] = new Rectangle(arectangle[i].x, arectangle[i].y, arectangle[i].width, arectangle[i].height);
            }

            areas = arectangle1;
            currentManager.addDirtyRegion(this, 0, 0, x_size, y_size);
        } else
        {
            areas = null;
        }
    }

    public final String getToolTipText(MouseEvent mouseevent)
    {
        String s = null;
        Point point = mouseevent.getPoint();
        current_p = new Point((int)((point.getX() - (double)insetW) / (double)scaling), (int)(((double)(y_size + insetH) - point.getY()) / (double)scaling));
        if(displayHotspots != null)
            s = displayHotspots.getTooltip(current_p, userAnnotIcons, pageNumber);
        return s;
    }

    public final void updateCursorBoxOnScreen(Rectangle rectangle, Color color)
    {
        if(rectangle != null)
            cursorBoxOnScreen = new Rectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        else
            cursorBoxOnScreen = null;
        outlineColor = color;
        byte byte0 = 30;
        if(lastCursorBoxOnScreen != null)
        {
            if(displayRotation == 0 || displayRotation == 180)
                currentManager.addDirtyRegion(this, insetW, insetH, x_size, y_size);
            else
                currentManager.addDirtyRegion(this, insetH, insetW, y_size, x_size);
            lastCursorBoxOnScreen = null;
        }
        if(cursorBoxOnScreen != null)
            currentManager.addDirtyRegion(this, (int)((float)cursorBoxOnScreen.x * scaling) - byte0, (int)((float)(max_y - cursorBoxOnScreen.y - cursorBoxOnScreen.height) * scaling) - byte0, (int)((float)cursorBoxOnScreen.width * scaling) + byte0 + byte0, (int)((float)cursorBoxOnScreen.height * scaling) + byte0 + byte0);
        if(viewScaling != null)
            currentManager.markCompletelyDirty(this);
    }

    public void repaintArea(Rectangle rectangle, int i)
    {
        int j = 10;
        if(j < insetH)
            j = insetH;
        if(j < insetW)
            j = insetW;
        currentManager.addDirtyRegion(this, (int)((float)rectangle.x * scaling) - j, (int)((float)(i - rectangle.y - rectangle.height) * scaling) - j, (int)((float)(rectangle.x + rectangle.width) * scaling) + j + j, (int)((float)(rectangle.y + rectangle.height) * scaling) + j + j);
    }

    public final void removeHiglightedObject()
    {
        if(lastHighlight != null)
        {
            currentManager.addDirtyRegion(this, lastHighlight.x - 2, lastHighlight.y - 2, lastHighlight.width + 2 + 2, lastHighlight.height + 2 + 2);
            currentHighlightedObject = null;
        }
    }

    public final void addHiglightedObject(Rectangle rectangle, Color color)
    {
        currentHighlightedObject = rectangle;
        outlineHighlightColor = color;
        if(currentHighlightedObject != null && currentHighlightedObject != lastHighlight)
        {
            currentManager.addDirtyRegion(this, currentHighlightedObject.x - 2, currentHighlightedObject.y - 2, currentHighlightedObject.width + 2 + 2, currentHighlightedObject.height + 2 + 2);
            lastHighlight = currentHighlightedObject;
        }
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        if(demoAf != null)
        {
            Graphics2D graphics2d = (Graphics2D)g;
            AffineTransform affinetransform = graphics2d.getTransform();
            graphics2d.setTransform(demoAf);
            //graphics2d.setColor(Color.red);
            //graphics2d.drawLine(crx, cry, crx + crw, cry + crh);
            //graphics2d.drawLine(crx, crh + cry, crw + crx, cry);
            graphics2d.setTransform(affinetransform);
        }
        if(cursorBoxOnScreen != null)
        {
            Graphics2D graphics2d1 = (Graphics2D)g;
            AffineTransform affinetransform1 = graphics2d1.getTransform();
            if(displayRotation == 0 || displayRotation == 180)
                graphics2d1.setClip(insetW, insetH, (int)((float)pageData.getCropBoxWidth(pageNumber) * scaling), (int)((float)pageData.getCropBoxHeight(pageNumber) * scaling));
            else
                graphics2d1.setClip(insetH, insetW, (int)((float)pageData.getCropBoxHeight(pageNumber) * scaling), (int)((float)pageData.getCropBoxWidth(pageNumber) * scaling));
            if(cursorAf != null)
            {
                graphics2d1.setTransform(cursorAf);
                paintRectangle(graphics2d1);
                graphics2d1.setTransform(affinetransform1);
            }
        }
    }

    public void paintComponent(Graphics g)
    {
        if(DynamicVectorRenderer.debugPaint)
            System.err.println("PaintComponent called ");
        if(SwingUtilities.isEventDispatchThread())
        {
            threadSafePaint(g);
        } else
        {
            final Graphics g2 = g;
            Runnable runnable = new Runnable() {

                public void run()
                {
                    threadSafePaint(g2);
                }
            };
            SwingUtilities.invokeLater(runnable);
        }
    }

    private void threadSafePaint(Graphics g)
    {
        if(DynamicVectorRenderer.debugPaint)
            System.err.println("threadsafePaint called ");
        super.paintComponent(g);
        Graphics2D graphics2d = (Graphics2D)g;
        AffineTransform affinetransform = graphics2d.getTransform();
        Rectangle rectangle = null;
        Object obj = null;
        double d = (float)pageData.getCropBoxX(pageNumber) * scaling;
        double d1 = (float)pageData.getCropBoxY(pageNumber) * scaling;
        double d2 = (float)pageData.getCropBoxWidth(pageNumber) * scaling;
        double d3 = (float)pageData.getCropBoxHeight(pageNumber) * scaling;
        double d4 = (float)pageData.getMediaBoxX(pageNumber) * scaling;
        double d5 = (float)pageData.getMediaBoxY(pageNumber) * scaling;
        double d6 = (float)pageData.getMediaBoxWidth(pageNumber) * scaling;
        double d7 = (float)pageData.getMediaBoxHeight(pageNumber) * scaling;
        crx = (int)((double)insetW + d);
        cry = (int)(((double)insetH + d7) - d3 - d1);
        crw = (int)d2;
        crh = (int)d3;
        if((displayRotation == 90) | (displayRotation == 270))
        {
            int i = crw;
            crw = crh;
            crh = i;
            i = crx;
            crx = cry;
            cry = i;
        }
        graphics2d.translate(insetW - crx, insetH - cry);
        AffineTransform affinetransform1 = graphics2d.getTransform();
        Shape shape = graphics2d.getClip();
        if(!showCrop)
            graphics2d.clip(new Rectangle(crx, cry, crw, crh));
        if(displayScaling != null)
        {
            graphics2d.transform(displayScaling);
            if(DynamicVectorRenderer.debugPaint)
                System.err.println("accelerate or redraw");
            if(useAcceleration && !overRideAcceleration)
            {
                if(DynamicVectorRenderer.debugPaint)
                    System.err.println("acceleration called ");
                if(backBuffer == null)
                    createBackBuffer();
                if(backBuffer != null)
                    do
                    {
                        int j = backBuffer.validate(getGraphicsConfiguration());
                        if(j != 1 && j == 2)
                            createBackBuffer();
                        if(screenNeedsRedrawing && backBuffer != null)
                        {
                            screenNeedsRedrawing = false;
                            Graphics2D graphics2d1 = (Graphics2D)backBuffer.getGraphics();
                            graphics2d1.setColor(Color.white);
                            graphics2d1.fill(new Rectangle(0, 0, backBuffer.getWidth(), backBuffer.getHeight()));
                            graphics2d1.setTransform(displayScaling);
                            float f = (float)crx / scaling;
                            float f1 = (float)cry / scaling;
                            if(displayRotation == 0)
                                graphics2d1.translate(-f, f1);
                            else
                            if(displayRotation == 90)
                                graphics2d1.translate(-f1, -f);
                            else
                            if(displayRotation == 180)
                                graphics2d1.translate(f, -f1);
                            else
                            if(displayRotation == 270)
                                graphics2d1.translate(f1, f);
                            rectangle = currentDisplay.paint(graphics2d1, null, viewScaling);
                            graphics2d1.dispose();
                        }
                        AffineTransform affinetransform3 = graphics2d.getTransform();
                        graphics2d.setTransform(affinetransform1);
                        graphics2d.translate(crx, cry);
                        graphics2d.drawImage(backBuffer, 0, 0, this);
                        graphics2d.setTransform(affinetransform3);
                    } while(backBuffer.contentsLost());
            }
            else
            {
                if(DynamicVectorRenderer.debugPaint)
                    System.err.println("standard paint called ");
                rectangle = currentDisplay.paint(graphics2d, areas, viewScaling);
            }
            if(rectAreas != null)
            {
                java.awt.Composite composite = graphics2d.getComposite();
                graphics2d.setColor(Color.blue);
                graphics2d.setComposite(AlphaComposite.getInstance(3, 0.7F));
                if(rectAreas != null)
                    graphics2d.fillRect(rectAreas.x, rectAreas.y, rectAreas.width, rectAreas.height);
                graphics2d.setComposite(composite);
            }
            if(currentHighlightedObject == null && lastHighlight != null)
                lastHighlight = null;
            AffineTransform affinetransform2 = graphics2d.getTransform();
            if(viewScaling != null)
                graphics2d.transform(viewScaling);
            if(highlightedZonesSelected != null)
                paintHighlights(graphics2d);
            if(displayHotspots != null)
                displayHotspots.addHotspotsToDisplay(graphics2d, userAnnotIcons, pageNumber);
            if(merge_level != null && showMerging)
                paintMergingInfo(graphics2d);
            if(cursorBoxOnScreen != null)
            {
                cursorAf = graphics2d.getTransform();
                actualBox = rectangle;
            }
            graphics2d.setTransform(affinetransform1);
            if(currentHighlightedObject != null)
            {
                graphics2d.setColor(outlineHighlightColor);
                graphics2d.draw(currentHighlightedObject);
            }
            graphics2d.setClip(shape);
            if(showCrop)
            {
                graphics2d.setColor(Color.orange);
                graphics2d.drawLine(crx, cry, crx + crw, cry + crh);
                graphics2d.drawLine(crx, cry + crh, crx + crw, cry);
            }
            demoAf = graphics2d.getTransform();
            if(crw > 0 && crh > 0 && myBorder != null)
                myBorder.paintBorder(this, graphics2d, crx - 1, cry - 1, crw + 2, crh + 2);
        }
        graphics2d.setTransform(affinetransform);
    }

    private void paintMergingInfo(Graphics2D graphics2d)
    {
        int i = merge_outline.size() - 1;
        for(int j = 0; j < i; j++)
        {
            Area area = merge_outline.elementAt(j);
            int k = merge_level.elementAt(j);
            if(showDebugLevel[k] & (area != null))
            {
                graphics2d.setColor(debugColors[k]);
                graphics2d.setComposite(AlphaComposite.getInstance(3, 0.1F));
                graphics2d.draw(area.getBounds());
                graphics2d.setComposite(AlphaComposite.getInstance(3, 0.3F));
                graphics2d.fill(area);
            }
        }

    }

    public void setDrawCrossHairs(boolean flag, int i, Color color)
    {
        drawCrossHairs = flag;
        boxContained = i;
        selectedHandleColor = color;
    }

    private void paintRectangle(Graphics2D graphics2d)
    {
        java.awt.Stroke stroke = graphics2d.getStroke();
        BasicStroke basicstroke = new BasicStroke(1.0F / scaling);
        graphics2d.setStroke(basicstroke);
        graphics2d.setColor(outlineColor);
        graphics2d.draw(cursorBoxOnScreen);
        if(drawCrossHairs)
        {
            int i = cursorBoxOnScreen.x;
            int j = cursorBoxOnScreen.y;
            int l = i + cursorBoxOnScreen.width;
            int j1 = j + cursorBoxOnScreen.height;
            int l1 = pageData.getMediaBoxWidth(pageNumber);
            int j2 = pageData.getMediaBoxHeight(pageNumber);
            int k2 = pageData.getMediaBoxX(pageNumber);
            int i3 = pageData.getMediaBoxY(pageNumber);
            graphics2d.setStroke(new BasicStroke(3F / scaling, 1, 1, 0.0F, new float[] {
                0.0F, 6F / scaling, 0.0F, 6F / scaling
            }, 0.0F));
            graphics2d.drawLine(i, j, k2, j);
            graphics2d.drawLine(i, j, i, i3);
            graphics2d.drawLine(l, j, l1, j);
            graphics2d.drawLine(l, j, l, i3);
            graphics2d.drawLine(i, j1, k2, j1);
            graphics2d.drawLine(i, j1, i, j2);
            graphics2d.drawLine(l, j1, l1, j1);
            graphics2d.drawLine(l, j1, l, j2);
            Rectangle arectangle[] = new Rectangle[8];
            arectangle[0] = new Rectangle(i - handlesGap, (j + Math.abs(j1 - j) / 2) - handlesGap, handlesGap * 2, handlesGap * 2);
            arectangle[1] = new Rectangle((i + Math.abs(l - i) / 2) - handlesGap, j - handlesGap, handlesGap * 2, handlesGap * 2);
            arectangle[2] = new Rectangle((i + Math.abs(l - i) / 2) - handlesGap, j1 - handlesGap, handlesGap * 2, handlesGap * 2);
            arectangle[3] = new Rectangle(l - handlesGap, (j + Math.abs(j1 - j) / 2) - handlesGap, handlesGap * 2, handlesGap * 2);
            arectangle[4] = new Rectangle(i - handlesGap, j - handlesGap, handlesGap * 2, handlesGap * 2);
            arectangle[5] = new Rectangle(i - handlesGap, j1 - handlesGap, handlesGap * 2, handlesGap * 2);
            arectangle[6] = new Rectangle(l - handlesGap, j - handlesGap, handlesGap * 2, handlesGap * 2);
            arectangle[7] = new Rectangle(l - handlesGap, j1 - handlesGap, handlesGap * 2, handlesGap * 2);
            graphics2d.setStroke(basicstroke);
            if(boxContained != -1 && boxContained < arectangle.length)
                if(selectedHandleColor != null)
                {
                    Color color = graphics2d.getColor();
                    graphics2d.setColor(selectedHandleColor);
                    graphics2d.fill(arectangle[boxContained]);
                    graphics2d.setColor(color);
                } else
                {
                    graphics2d.fill(arectangle[boxContained]);
                }
            for(int j3 = 0; j3 < arectangle.length; j3++)
                if(j3 != boxContained)
                    graphics2d.draw(arectangle[j3]);

        }
        graphics2d.setStroke(stroke);
        if(actualBox == null)
        {
            lastCursorBoxOnScreen = cursorBoxOnScreen;
        } else
        {
            Rectangle rectangle = cursorBoxOnScreen.getBounds();
            int k = (int)rectangle.getMinX();
            int i1 = (int)rectangle.getMinY();
            int k1 = (int)rectangle.getMaxX();
            int i2 = (int)rectangle.getMaxY();
            Rectangle rectangle1 = actualBox.getBounds();
            int l2 = (int)rectangle1.getMinX();
            if(l2 < k)
                k = l2;
            l2 = (int)rectangle1.getMinY();
            if(l2 < i1)
                i1 = l2;
            l2 = (int)rectangle1.getMaxX();
            if(l2 > k1)
                k1 = l2;
            l2 = (int)rectangle1.getMaxY();
            if(l2 > i2)
                i2 = l2;
            lastCursorBoxOnScreen = new Rectangle(k - 5, i1 - 5, (10 + k1) - k, 10 + (i2 - i1));
        }
    }

    private void paintHighlights(Graphics2D graphics2d)
    {
        int i = highlightedZonesSelected.length;
        for(int j = 0; j < i; j++)
        {
            if(highlightedZonesSelected[j])
            {
                highlightStoryOnscreen(graphics2d, j);
                continue;
            }
            if((highlightMode & 1) != 1 || fragmentShapes[j] == null)
                continue;
            graphics2d.setComposite(AlphaComposite.getInstance(3, 0.1F));
            if(highlightColors[j] == null)
                graphics2d.setColor(Color.darkGray);
            else
                graphics2d.setColor(highlightColors[j]);
            graphics2d.fill(fragmentShapes[j]);
            graphics2d.draw(outlineZone[j]);
            graphics2d.setComposite(AlphaComposite.getInstance(3, 0.9F));
            graphics2d.draw(outlineZone[j]);
            if(linkedItems == null)
                continue;
            int ai[] = (int[])linkedItems[j];
            if(ai != null)
                numberItems(false, graphics2d, j + "-", ai);
        }

        if(pageLines != null && (highlightMode & 2) == 2)
            pageLines.drawLines(graphics2d);
        if(selectionOrder != null)
            numberItems(false, graphics2d, "", selectionOrder);
    }

    private void highlightStoryOnscreen(Graphics2D graphics2d, int i)
    {
        if(fragmentShapes[i] != null)
        {
            if(highlightColors[i] == null)
                graphics2d.setColor(Color.blue);
            else
                graphics2d.setColor(highlightColors[i]);
            graphics2d.setComposite(AlphaComposite.getInstance(3, 0.1F));
            graphics2d.fill(fragmentShapes[i]);
            graphics2d.draw(outlineZone[i]);
            graphics2d.setComposite(AlphaComposite.getInstance(3, 0.9F));
            graphics2d.draw(outlineZone[i]);
            int j = outlineZone[i].getBounds().x;
            int k = outlineZone[i].getBounds().y;
            graphics2d.drawLine(j, k + (int)outlineZone[i].getBounds().getHeight(), j + (int)outlineZone[i].getBounds().getWidth(), k);
            if(linkedItems != null)
            {
                int ai[] = (int[])linkedItems[i];
                if(ai != null)
                {
                    int l = ai.length;
                    for(int i1 = 1; i1 < l; i1++)
                        if(ai[i1] != -1)
                            highlightStoryOnscreen(graphics2d, ai[i1]);

                }
            }
        }
    }

    private void numberItems(boolean flag, Graphics2D graphics2d, String s, int ai[])
    {
        int i = ai.length;
        if(i == 0)
            return;
        int j = ai[0];
        int k = 1;
label0:
        for(int l = 0; l < i; l++)
        {
            int i1 = ai[l];
            if(i1 == -1)
            {
                l = i;
                continue;
            }
            String s1 = s + "" + k;
            if(linkedItems == null)
            {
                if(fragmentShapes[i1] != null)
                    numberItem(graphics2d, i1, s1);
                k++;
                continue;
            }
            int ai1[] = (int[])linkedItems[i1];
            if(ai1 != null)
            {
                k++;
                int j1 = ai1.length;
                int k1 = 0;
                int l1 = 0;
                do
                {
                    if(l1 >= j1)
                        continue label0;
                    int i2 = ai1[l1];
                    k1++;
                    if(i2 == -1)
                        l1 = j1;
                    else
                    if(fragmentShapes[i1] != null)
                        numberItem(graphics2d, i2, s1 + "." + k1);
                    l1++;
                } while(true);
            }
            if(parents[i1] != -1)
                continue;
            if(fragmentShapes[i1] != null)
                numberItem(graphics2d, i1, s1);
            k++;
        }

    }

    private void numberItem(Graphics2D graphics2d, int i, String s)
    {
        AffineTransform affinetransform = new AffineTransform();
        GlyphVector glyphvector = highlightFont.createGlyphVector(graphics2d.getFontRenderContext(), s);
        affinetransform.scale(1.0D, -1D);
        affinetransform.translate(cx[i], -cy[i]);
        Area area = new Area(glyphvector.getOutline());
        area.transform(affinetransform);
        graphics2d.setColor(Color.black);
        graphics2d.fill(area.getBounds());
        graphics2d.setColor(Color.white);
        graphics2d.fill(area);
    }

    private void flagNumbersForRedraw(int i)
    {
        if(selectionOrder != null && cx != null && cy != null)
        {
            for(int j = 0; j < selectionOrder.length; j++)
            {
                int k = selectionOrder[j];
                if(k == -1)
                {
                    j = selectionOrder.length;
                    continue;
                }
                currentManager.addDirtyRegion(this, (int)((float)cx[k] * scaling), (int)((float)(i - cy[k] - 25) * scaling), (int)(25F * scaling), (int)(25F * scaling));
                int ai[] = (int[])linkedItems[k];
                if(ai == null)
                    continue;
                for(int l = 0; l < ai.length; l++)
                {
                    int i1 = ai[l];
                    if(i1 == -1)
                        l = ai.length;
                    else
                        currentManager.addDirtyRegion(this, (int)((float)cx[i1] * scaling), (int)((float)(i - cy[i1] - 25) * scaling), (int)(25F * scaling), (int)(25F * scaling));
                }

            }

        }
    }

    public final int getPDFWidth()
    {
        if((displayRotation == 90) | (displayRotation == 270))
            return y_size + insetW + insetW;
        else
            return x_size + insetW + insetW;
    }

    public final int getRawPDFWidth()
    {
        if((displayRotation == 90) | (displayRotation == 270))
            return y_size;
        else
            return x_size;
    }

    public final int getPDFHeight()
    {
        if((displayRotation == 90) | (displayRotation == 270))
            return x_size + insetH + insetH;
        else
            return y_size + insetH + insetH;
    }

    public final int getRawPDFHeight()
    {
        if((displayRotation == 90) | (displayRotation == 270))
            return x_size;
        else
            return y_size;
    }

    public Rectangle[] getPageHotspots()
    {
        return displayHotspots.getAnnotationhotSpots();
    }

    public void disableBorderForPrinting()
    {
        useBorder = false;
    }

    public final void setPDFBorder(Border border)
    {
        myBorder = border;
        useBorder = true;
    }

    protected final AffineTransform getScalingForImage(int i, float f)
    {
        double d = (float)pageData.getMediaBoxX(pageNumber) * f;
        double d1 = (float)pageData.getMediaBoxY(pageNumber) * f;
        double d2 = (float)pageData.getMediaBoxWidth(pageNumber) * f;
        double d3 = (float)pageData.getMediaBoxHeight(pageNumber) * f;
        double d4 = (float)pageData.getCropBoxWidth(pageNumber) * f;
        double d5 = (float)pageData.getCropBoxHeight(pageNumber) * f;
        double d6 = (float)pageData.getCropBoxX(pageNumber) * f;
        double d7 = (float)pageData.getCropBoxY(pageNumber) * f;
        AffineTransform affinetransform = new AffineTransform();
        int j = (int)(d4 + (d6 - d));
        int k = (int)(d5 + (d7 - d1));
        if(i == 270)
        {
            affinetransform.rotate(-1.5707963267948966D, j / 2, k / 2);
            double d8 = affinetransform.getTranslateX();
            double d9 = affinetransform.getTranslateY();
            affinetransform.translate((double)k - d9, -d8);
            affinetransform.translate(0.0D, k);
            affinetransform.scale(1.0D, -1D);
            affinetransform.translate(-(d6 + d), -(d3 - d5 - (d7 - d1)));
        } else
        if(i == 180)
        {
            affinetransform.rotate(3.1415926535897931D, j / 2, k / 2);
            affinetransform.translate(-(d6 + d), ((double)k + (d7 + d1)) - (d3 - d5 - (d7 - d1)));
            affinetransform.scale(1.0D, -1D);
        } else
        if(i == 90)
        {
            affinetransform.rotate(1.5707963267948966D);
            affinetransform.translate(0.0D, (d7 + d1) - (d3 - d5 - (d7 - d1)));
            affinetransform.scale(1.0D, -1D);
        } else
        {
            affinetransform.translate(0.0D, k);
            affinetransform.scale(1.0D, -1D);
            affinetransform.translate(0.0D, -(d3 - d5 - (d7 - d1)));
        }
        affinetransform.scale(f, f);
        return affinetransform;
    }

    public final void setPageRotation(int i)
    {
        displayRotation = i;
        screenNeedsRedrawing = true;
        backBuffer = null;
        displayScaling = getScalingForImage(displayRotation, scaling);
        if(displayRotation == 90)
            displayScaling.translate((float)insetH / scaling, (float)insetW / scaling);
        else
        if(displayRotation == 270)
            displayScaling.translate((float)(-insetH) / scaling, (float)(-insetW) / scaling);
        else
        if(displayRotation == 180)
            displayScaling.translate((float)(-insetW) / scaling, (float)insetH / scaling);
        else
            displayScaling.translate((float)insetW / scaling, (float)(-insetH) / scaling);
        if(viewableArea != null)
        {
            viewScaling = new AffineTransform();
            double d = (double)viewableArea.width / (double)pageData.getCropBoxWidth(pageNumber);
            double d1 = (double)viewableArea.height / (double)pageData.getCropBoxHeight(pageNumber);
            double d2 = d;
            if(d1 < d)
                d2 = d1;
            double d3 = viewableArea.x;
            double d4 = (double)viewableArea.y + ((double)viewableArea.height - (double)pageData.getCropBoxHeight(pageNumber) * d2);
            viewScaling.translate(d3, d4);
            viewScaling.scale(d2, d2);
        } else
        {
            viewScaling = null;
        }
        //if(currentFormRenderer != null)
        //    currentFormRenderer.resetScaledLocation(scaling, displayRotation);
    }

    public final void setPageParameters(float f, int i, int j)
    {
        isNewRotationSet = true;
        displayRotation = j;
        setPageParameters(f, i);
    }

    public final void setPageParameters(float f, int i)
    {
        overRideAcceleration = false;
        pageNumber = i;
        scaling = f;
        int j = pageData.getMediaBoxWidth(i);
        max_y = pageData.getMediaBoxHeight(i);
        int k = pageData.getMediaBoxX(i);
        int l = pageData.getMediaBoxY(i);
        volatileWidth = pageData.getCropBoxWidth(i);
        volatileHeight = pageData.getCropBoxHeight(i);
        int i1 = volatileWidth;
        int j1 = volatileHeight;
        int k1 = pageData.getCropBoxX(i);
        int l1 = pageData.getCropBoxY(i);
        x_size_cropped = (int)((float)i1 * f);
        y_size_cropped = (int)((float)j1 * f);
        x_size = (int)((float)i1 * f);
        y_size = (int)((float)j1 * f);
        if(!isNewRotationSet)
            displayRotation = pageData.getRotation(i);
        else
            isNewRotationSet = false;
        currentDisplay.init(j, max_y, displayRotation);
        setPageRotation(displayRotation);
    }

    protected final AffineTransform setPageParametersForImage(float f, int i)
    {
        AffineTransform affinetransform = new AffineTransform();
        int j = pageData.getMediaBoxWidth(i);
        int k = pageData.getMediaBoxHeight(i);
        int l = pageData.getMediaBoxX(i);
        int i1 = pageData.getMediaBoxY(i);
        int j1 = pageData.getCropBoxWidth(i);
        int k1 = pageData.getCropBoxHeight(i);
        int l1 = pageData.getCropBoxX(i);
        int i2 = pageData.getCropBoxY(i);
        if((displayRotation == 90) | (displayRotation == 270))
        {
            int j2 = j1;
            j1 = k1;
            k1 = j2;
        }
        double d = f;
        int k2 = (int)((float)j1 * f);
        int l2 = (int)((float)k1 * f);
        int i3 = pageData.getRotation(i);
        affinetransform.translate((float)(-l1) * f, (float)i2 * f);
        if(i3 == 270)
        {
            affinetransform.rotate(-1.5707963267948966D, k2 / 2, l2 / 2);
            double d1 = affinetransform.getTranslateX();
            double d3 = affinetransform.getTranslateY();
            affinetransform.translate((double)l2 - d3, -d1);
        } else
        if(i3 == 180)
            affinetransform.rotate(3.1415926535897931D, k2 / 2, l2 / 2);
        else
        if(i3 == 90)
        {
            affinetransform.rotate(1.5707963267948966D, k2 / 2, l2 / 2);
            double d2 = affinetransform.getTranslateX();
            double d4 = affinetransform.getTranslateY();
            affinetransform.translate(-d4, (double)k2 - d2);
        }
        if(d < 1.0D)
        {
            affinetransform.translate(k2, l2);
            affinetransform.scale(1.0D, -1D);
            affinetransform.translate(-k2, 0.0D);
            affinetransform.scale(d, d);
        } else
        {
            affinetransform.translate(k2, l2);
            affinetransform.scale(1.0D, -1D);
            affinetransform.translate(-k2, 0.0D);
            affinetransform.scale(f, f);
        }
        return affinetransform;
    }

    public void setHardwareAccelerationforScreen(boolean flag)
    {
        useAcceleration = flag;
    }

    public int getScrollInterval()
    {
        return scrollInterval;
    }

    public void setScrollInterval(int i)
    {
        scrollInterval = i;
    }
}
