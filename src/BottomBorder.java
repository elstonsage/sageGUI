package sage;

import javax.swing.border.Border;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.*;
/**
 * <p>Title: SAGE</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author suna
 * @version 1.0
 */

public class BottomBorder implements Border, java.io.Serializable {
    protected int m_w=0;
    protected int m_h=1;
    protected Color m_topColor = Color.white;
    protected Color m_bottomColor = Color.gray;
    protected Color m_bgColor = new Color(236,233,216);

    public BottomBorder() {
    }
    public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
        w--;
        h--;

        g.setColor(m_bgColor); // top line : left to right
        g.drawLine(x, y, x+w, y);

        g.setColor(m_bgColor); // left line : to bottom
        g.drawLine(x, y+h, x, y);

        g.setColor(m_bottomColor); // bottom line : left to right
        g.drawLine(x, y+h, x+w, y+h);

        g.setColor(m_bgColor);// right line : to bottom
        g.drawLine(x+w, y, x+w, y+h);
    }
    public Insets getBorderInsets(Component c) {
        return new Insets(m_h, m_w, m_h, m_w);
    }
    public boolean isBorderOpaque() {
        return true;
    }

}
