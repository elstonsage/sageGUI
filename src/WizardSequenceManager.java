package sage;

import java.util.Hashtable;

public class WizardSequenceManager
	implements SequenceManager
{
	protected String first;
	protected String current;
	protected Hashtable next;
	protected Hashtable prev;

	public WizardSequenceManager()
	{
		next = new Hashtable();
		prev = new Hashtable();
	}

	public String getFirst()
	{
		return first;
	}

	public void setFirst(String first)
	{
		this.first = first;
	}

	public String getCurrent()
	{
		return current;
	}

	public void setCurrent(String current)
	{
		this.current = current;
	}

	public String getNext(String name)
	{
		if (!next.containsKey(name)) return "";
		return (String)next.get(name);
	}

	public void setNext(String name, String link)
	{
		if (next.containsKey(name)) next.remove(name);
		if (prev.containsKey(link)) prev.remove(link);
		next.put(name, link);
		prev.put(link, name);
	}

	public String getPrevious(String name)
	{
		if (!prev.containsKey(name)) return "";
		return (String)prev.get(name);
	}

	public void setPrevious(String name, String link)
	{
		if (prev.containsKey(name)) prev.remove(name);
		if (next.containsKey(link)) next.remove(link);
		prev.put(name, link);
		next.put(link, name);
	}
}
