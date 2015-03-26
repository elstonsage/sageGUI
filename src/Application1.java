package sage;

import javax.swing.*;
import java.awt.*;

public class Application1 {
  boolean packFrame = false;
  Frame1 frame;

  private JWindow splashScreen = null;
  private JLabel splashLabel = null;

  //Construct the application
  public Application1()  throws Exception{
    frame = new Frame1();

    createSplashScreen();
    showSplashScreen();

    //Validate frames that have preset sizes
    //Pack frames that have useful preferred size info, e.g. from their layout
    SwingUtilities.invokeLater(new Runnable() {
        public void run() {

            frame.initializeFrame();

            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
            // Validate frames that have preset sizes
            // Pack frames that have useful preferred size info, e.g. from their layout
            if (packFrame) {
                frame.pack();
            } else {
                frame.validate();
            }

            // Center the window
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension frameSize = frame.getSize();
            if (frameSize.height > screenSize.height) {
                frameSize.height = screenSize.height;
            }
            if (frameSize.width > screenSize.width) {
                frameSize.width = screenSize.width;
            }

            frame.setLocation(0,0);
            frame.setVisible(true);

            hideSplash();

            frame.ShowSetupDialog();
        }
       });
  }

  /**
 * Show the spash screen while the rest of the demo loads
 */
public void createSplashScreen() throws Exception{
    splashLabel = new JLabel(createImageIcon("splash_6_3.png"));

        splashScreen = new JWindow(frame);
        splashScreen.getContentPane().add(splashLabel);
        splashScreen.pack();
        Rectangle screenRect = frame.getGraphicsConfiguration().getBounds();

        splashScreen.setLocation(screenRect.x + screenRect.width/2 - splashScreen.getSize().width/2, screenRect.y + screenRect.height/2 - splashScreen.getSize().height/2);
}

public void showSplashScreen() {
            splashScreen.setVisible(true);

}
/**
 * pop down the spash screen
 */
public void hideSplash() {
        splashScreen.setVisible(false);
        splashScreen = null;
        splashLabel = null;
}

/**
 * Creates an icon from an image contained in the "images" directory.
 */
public ImageIcon createImageIcon(String filename) throws Exception{
    return new ImageIcon(((Application1.class.getResource(filename)).toURI()).toURL());
}

  //Main method
  public static void main(String[] args) {
    try {

        String os_type = System.getProperty("os.name");
        if (os_type.indexOf("Windows") >= 0 || os_type.indexOf("Mac") >= 0) {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }

      new Application1();

    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
}
