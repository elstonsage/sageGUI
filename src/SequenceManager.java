package sage;

public interface SequenceManager
{
	public String getFirst();
	public void setFirst(String name);

	public String getCurrent();
	public void setCurrent(String name);

	public String getNext(String name);
	public void setNext(String name, String next);

	public String getPrevious(String name);
	public void setPrevious(String name, String previous);
}
