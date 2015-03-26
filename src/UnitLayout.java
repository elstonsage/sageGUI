package sage;

import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Component;
import java.awt.LayoutManager2;
import java.io.Serializable;

public class UnitLayout extends AbstractLayout
	implements LayoutManager2, Serializable
{
	public static final int TOP = 1;
	public static final int LEFT = 1;
	public static final int CENTER = 2;
	public static final int RIGHT = 3;
	public static final int BOTTOM = 3;

	protected int xpos = CENTER;
	protected int ypos = CENTER;

	private boolean singleton = false;

	public UnitLayout()
	{
		this(CENTER, CENTER);
	}

	public UnitLayout(int xpos, int ypos)
	{
		super();
		this.xpos = xpos;
		this.ypos = ypos;
	}

	public void addLayoutComponent(Component comp, Object constraints)
	{
		if (singleton) throw
			new IllegalArgumentException("Only one child component permitted");
		else singleton = true;
	}

	public void removeLayoutComponent(Component comp)
	{
		singleton = false;
	}

	public Dimension preferredLayoutSize(Container container)
	{
		Insets insets = container.getInsets();
		Dimension size = container.getComponent(0).getPreferredSize();
		return new Dimension(
			insets.left + size.width + insets.right,
			insets.top + size.height + insets.bottom);
	}

	public Dimension minimumLayoutSize(Container container)
	{
		Insets insets = container.getInsets();
		Dimension size = container.getComponent(0).getMinimumSize();
		return new Dimension(
			insets.left + size.width + insets.right,
			insets.top + size.height + insets.bottom);
	}

	public void layoutContainer(Container container)
	{
		Dimension size = container.getSize();
		Insets insets = container.getInsets();
		Component component = container.getComponent(0);
		int x = insets.left;
		int y = insets.top;
		int w = component.getPreferredSize().width;
		int h = component.getPreferredSize().height;

		if (xpos == CENTER) x += (size.width - w) / 2;
		if (xpos == RIGHT) x += size.width - w;

		if (ypos == CENTER) y += (size.height - h) / 2;
		if (ypos == BOTTOM) y += size.height - h;

		component.setBounds(x, y, w, h);
	}

}
