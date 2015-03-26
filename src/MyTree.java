package sage;

import javax.swing.JTree;
import javax.swing.tree.*;
import java.awt.event.*;

public class MyTree extends JTree {
  private static MyTree tree;
  public MyTree(MyTreeModel m) {
    super(m);
    tree = this;

    setRowHeight(22);
    putClientProperty("JTree.lineStyle", "Angled");
  }

  public String getToolTipText(MouseEvent evt) {
    if (getRowForLocation(evt.getX(), evt.getY()) == -1)
      return null;
    TreePath curPath = getPathForLocation(evt.getX(), evt.getY());
    IconNode current = (IconNode) curPath.getLastPathComponent();
    NodeInfo cn = (NodeInfo) current.getUserObject();

    if(cn.file!=null)
      return cn.file.getPath();
    if(cn.nodetype.indexOf("Analysis")>=0)
      return cn.analysis_type;
    return null;

  }

  static void Refresh()
  {
    tree.setRowHeight(22);
  }

}
