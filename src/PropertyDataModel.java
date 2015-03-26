package sage;

import java.util.Vector;
import java.util.Properties;
import java.io.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PropertyDataModel extends Properties
	implements DataCollectionModel, Serializable
{
	protected Vector listeners = new Vector();

	public PropertyDataModel()
	{
		super();
	}

	public boolean hasValue(String name)
	{
		return containsKey(name);
	}

	public Object getValue(String name)
	{
		return get(name);
	}

	public Object getValue(String name, Object def)
	{
		if (containsKey(name)) return get(name);
		else return def;
	}

	public void setValue(String name, Object value)
	{
		put(name, value);
		fireChangeEvent();
	}

	public void removeValue(String name)
	{
		remove(name);
		fireChangeEvent();
	}

	public void addChangeListener(ChangeListener listener)
	{
		listeners.addElement(listener);
	}

	public void removeChangeListener(ChangeListener listener)
	{
		listeners.removeElement(listener);
	}

	public void fireChangeEvent()
	{
		Vector list = (Vector)listeners.clone();
		ChangeEvent event = new ChangeEvent(this);
		ChangeListener listener;
		for (int i = 0; i < list.size(); i++)
		{
			listener = (ChangeListener)list.elementAt(i);
			listener.stateChanged(event);
		}
	}

}
