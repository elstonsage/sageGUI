package sage;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DeckPanel extends JPanel
{
	protected int index = -1;
	protected DeckLayout layout;
	protected SequenceManager manager;

	public DeckPanel(SequenceManager manager)
	{
		setLayout(layout = new DeckLayout());
		setBorder(new EmptyBorder(0,0,0,0));
		this.manager = manager;
	}

	public void addPanel(String name, Component panel)
	{
		if (index < 0)
		{
			manager.setFirst(name);
			manager.setCurrent(name);
		}
		else
		{
			manager.setPrevious(name, getName(index));
		}
		add(name, panel);
		index++;
	}

	public int getIndex(String name)
	{
		return layout.getIndex(this, name);
	}

	public String getName(int index)
	{
		return layout.getName(this, index);
	}

	public Component getComponent(String name)
	{
		return layout.getComponent(name);
	}

	public String getFirst()
	{
		return manager.getFirst();
	}

	public void setFirst()
	{
		setPanel(getFirst());
	}

	public String getCurrent()
	{
		return manager.getCurrent();
	}

	public boolean isLast(String name)
	{
		return manager.getNext(name).equals("");
	}

	public String getNext()
	{
		return manager.getNext(getCurrent());
	}

	public String getPrevious()
	{
		return manager.getPrevious(getCurrent());
	}

	public void setPanel(String name)
	{
		if (name == "") return;
		manager.setCurrent(name);
		layout.show(this, name);
	}

}
