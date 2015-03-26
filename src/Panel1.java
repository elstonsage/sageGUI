package sage;

import java.awt.*;

import javax.swing.*;
import com.borland.jbcl.layout.XYLayout;
import com.borland.jbcl.layout.*;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class Panel1 extends JPanel {
    JPanel jPanel1 = new JPanel();
    JPanel jPanel2 = new JPanel();
    JPanel jPanel3 = new JPanel();
    JPanel jPanel4 = new JPanel();
    XYLayout xYLayout1 = new XYLayout();
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JButton jButton3 = new JButton();
    JButton jButton4 = new JButton();
    JButton jButton5 = new JButton();
    JButton jButton6 = new JButton();
    JButton jButton7 = new JButton();
    XYLayout xYLayout2 = new XYLayout();
    XYLayout xYLayout3 = new XYLayout();
    XYLayout xYLayout4 = new XYLayout();
    JScrollPane jScrollPane1 = new JScrollPane();
    JScrollPane jScrollPane2 = new JScrollPane();
    JScrollPane jScrollPane3 = new JScrollPane();
    XYLayout xYLayout5 = new XYLayout();
    GridBagLayout gridBagLayout1 = new GridBagLayout();
    GridBagLayout gridBagLayout2 = new GridBagLayout();
    JSplitPane jSplitPane1 = new JSplitPane();
    JScrollPane jScrollPane4 = new JScrollPane();
    JPanel jPanel5 = new JPanel();
    public Panel1() {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        //jSplitPane1.add(jPanel5, );
        this.setLayout(xYLayout5);
        jPanel1.setLayout(gridBagLayout2);
        jPanel2.setBackground(Color.white);
        jPanel2.setPreferredSize(new Dimension(210,250));

        jPanel2.setLayout(xYLayout2);
        jPanel3.setBackground(Color.lightGray);
        jPanel3.setPreferredSize(new Dimension(130,240));

        jPanel3.setLayout(xYLayout3);
        jPanel4.setBackground(Color.pink);
        jPanel4.setLayout(xYLayout4);
        jPanel4.setPreferredSize(new Dimension(230, 240));

        jButton1.setText("jButton1");
        jButton2.setText("jButton2");
        jButton3.setText("jButton3");
        jButton4.setText("jButton4");
        jButton5.setText("jButton5");
        jButton6.setText("jButton6");
        jButton7.setText("jButton7");
        xYLayout5.setWidth(640);
        xYLayout5.setHeight(459);
        jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jPanel4.add(jScrollPane3, new XYConstraints(10, 10, 210, 220));
        this.add(jSplitPane1, new XYConstraints(48, 293, 242, 123));
        jPanel2.add(jScrollPane1, new XYConstraints(10, 10,100, 220));
        jPanel2.add(jScrollPane2, new XYConstraints(120, 10,100, 220));
        jPanel3.add(jButton2, new XYConstraints(5, 40, 120, 25));
        jPanel3.add(jButton3, new XYConstraints(5, 70, 120, 25));
        jPanel3.add(jButton4, new XYConstraints(5, 100, 120, 25));
        jPanel3.add(jButton5, new XYConstraints(5, 130, 120, 25));
        jPanel3.add(jButton7, new XYConstraints(5, 205, 120, 25));
        jPanel3.add(jButton6, new XYConstraints(5, 175, 120, 25));
        jPanel3.add(jButton1, new XYConstraints(5, 10, 120, 25));
        this.add(jPanel1, new XYConstraints(0, 0, -1, -1));
        jPanel1.add(jPanel2, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(10, 10, 0, 0), 20, -10));
        jPanel1.add(jPanel3, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0
            , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(10, 10, 0, 0), 0, 0));
        jPanel1.add(jPanel4, new GridBagConstraints(2, 0, 1, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(10, 10, 0, 0), 0, 0));
        jSplitPane1.add(jPanel5, JSplitPane.RIGHT);

    }
}
