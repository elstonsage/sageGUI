package sage;

import javax.swing.border.Border;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import java.awt.*;

public class MyBorder implements Border {
    protected int m_w=1;
    protected int m_h=1;
    protected Color m_bottomColor = Color.gray;

    public MyBorder() {
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
        w--;
        h--;
        g.setColor(m_bottomColor);
        g.drawLine(x, y+h, x, y);
        g.drawLine(x, y, x+w, y);
        g.drawLine(x, y+h, x+w, y+h);
        g.drawLine(x+1, y+h+1, x+w+1, y+h+1);

        g.setColor(m_bottomColor);
        g.drawLine(x+w, y, x+w, y+h);
        g.drawLine(x+w+1, y+1, x+w+1, y+h+1);
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(m_h, m_w, m_h, m_w);
    }

    public boolean isBorderOpaque() {
        return true;
    }

}
