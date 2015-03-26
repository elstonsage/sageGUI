package sage;
import java.io.Serializable;

import javax.swing.event.ChangeListener;

/**
 * A Data Colection Model stores data as its being collected
 * and notifies any Change listeners when the data changes.
 * Values may be added, removed or looked up.
**/

public interface DataCollectionModel extends Serializable
{
	public boolean hasValue(String name);
	public Object getValue(String name);
	public Object getValue(String name, Object def);
	public void setValue(String name, Object value);
	public void removeValue(String name);
	public void addChangeListener(ChangeListener listener);
	public void removeChangeListener(ChangeListener listener);
}
