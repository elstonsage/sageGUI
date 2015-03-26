package sage;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class ToolTipItem {
  String  obj;
  String  toolTipText;

  public ToolTipItem(String obj, String text) {
    this.obj = obj;
    this.toolTipText = text;
  }

  public String getToolTipText() {
    return toolTipText;
  }

  public String toString() {
    return obj;
  }


}
