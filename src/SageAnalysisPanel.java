package sage;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.XYLayout;

public class SageAnalysisPanel extends JPanel {
    XYLayout xYLayout1 = new XYLayout();
    ImageIcon next_image;
    ImageIcon error_image;
    ImageIcon back_image;
    JButton jRunButton = new JButton();

    public SageAnalysisPanel() {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setLayout(xYLayout1);
        this.setSize(440,450);
        next_image = new ImageIcon(SageAnalysisPanel.class.getResource("next.png"));
        error_image = new ImageIcon(SageAnalysisPanel.class.getResource("error_marker.png"));
        back_image = new ImageIcon(SageAnalysisPanel.class.getResource("back.png"));

        jRunButton.setIcon(error_image);
        jRunButton.setMargin(new Insets(2, 2, 2, 2));
        jRunButton.setText("Run");
    }
}
