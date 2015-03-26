package sage;

import javax.swing.*;
import javax.swing.tree.*;
import java.io.Serializable;

public class IconNode extends DefaultMutableTreeNode implements Serializable {
  protected String iconName;

  public IconNode() {
    this(null,"");
  }

  public IconNode(Object userObject, String name) {
    this(userObject, true, null, name);
  }

  public IconNode(Object userObject, boolean allowsChildren, Icon icon, String name) {
    super(userObject, allowsChildren);
    this.iconName = name;
  }

  public String getIconName() {
    if (iconName != null) {
      return iconName;
    }
    else {
      return "";
    }
  }

  public void setIconName(String name) {
    iconName = name;
  }

}
