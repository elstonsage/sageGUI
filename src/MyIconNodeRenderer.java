package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.tree.*;
import java.util.*;

public class MyIconNodeRenderer extends DefaultTreeCellRenderer {

  public Component getTreeCellRendererComponent(JTree tree, Object value,
      boolean sel, boolean expanded, boolean leaf,
      int row, boolean hasFocus) {

    super.getTreeCellRendererComponent(tree, value,
       sel, expanded, leaf, row, hasFocus);

      Hashtable icons = (Hashtable)tree.getClientProperty("JTree.icons");
      String name = ((IconNode)value).getIconName();

      if ((icons != null) && (name != null)) {
        Icon icon = (Icon)icons.get(name);
        if (icon != null) {
          setIcon(icon);
        }
      }

      if(name.compareTo("InputFolder")==0 || name.compareTo("OutputFolder")==0
          || name.compareTo("Internal")==0 || name.compareTo("External")==0)
      {
        if(expanded)
        {
          Icon icon = (Icon)icons.get("OpenFolder");
          setIcon(icon);
        }
      }

      if(name.compareTo("ErrorFolder")==0)
      {
        if(expanded)
        {
          Icon icon = (Icon)icons.get("ErrorOpenFolder");
          setIcon(icon);
        }
      }
    return this;
  }
}
