package sage;

import javax.swing.border.Border;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.*;

public class TopbottomBorder implements Border {
    protected int m_w=2;
    protected int m_h=2;
    protected Color m_topColor = Color.white;
    protected Color m_bottomColor = Color.gray;
    protected Color m_bgColor = new Color(236,233,216);

    public TopbottomBorder() {
    }
    public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
        w--;
        h--;

        g.setColor(m_bottomColor);
        g.drawLine(x, y, x+w, y);// top line : left to right
        g.setColor(m_topColor);
        g.drawLine(x, y+1, x+w, y+1);

        g.setColor(m_bgColor);// left line : to bottom
        g.drawLine(x, y+h, x, y);

        g.setColor(m_bottomColor);
        g.drawLine(x, y+h, x+w, y+h);// bottom line : left to right
        g.setColor(m_topColor);
        g.drawLine(x, y+h+1, x+w, y+h+1);

        g.setColor(m_bgColor);
        g.drawLine(x+w, y, x+w, y+h);// right line : to bottom
    }
    public Insets getBorderInsets(Component c) {
        return new Insets(m_h, m_w, m_h, m_w);
    }
    public boolean isBorderOpaque() {
        return true;
    }

}
