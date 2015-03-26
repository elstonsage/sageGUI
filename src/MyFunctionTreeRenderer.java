package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.tree.*;
import java.util.*;

public class MyFunctionTreeRenderer extends DefaultTreeCellRenderer {

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

      if(name.compareTo("Folder_plus")==0)
      {
        if(expanded)
        {
          Icon icon = (Icon)icons.get("Folder_minus");
          setIcon(icon);
        }
      }

      if(name.compareTo("Folder")==0)
      {
        if(sel)
        {
          Icon icon = (Icon)icons.get("Folder_open");
          setIcon(icon);
        }
      }
    return this;
  }
}
