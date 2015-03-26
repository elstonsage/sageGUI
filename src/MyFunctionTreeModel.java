package sage;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.*;

import java.io.*;

public class MyFunctionTreeModel extends DefaultTreeModel {
  public MyFunctionTreeModel(TreeNode root) {
    super(root);
  }

  public void valueForPathChanged(TreePath path, Object newValue) {
    IconNode node = (IconNode) path.getLastPathComponent();
    NodeInfo nodeInfo = (NodeInfo) node.getUserObject();
    nodeInfo.setName(newValue.toString());

    nodeChanged(node);

    String leafname = nodeInfo.nodetype;
    if (leafname.indexOf("Analysis") >= 0)
    {
      SageFilePanel panel = (SageFilePanel) nodeInfo.component_vector.elementAt(0);
      panel.OutputNameField.setText(newValue.toString());
    }
    if (leafname.indexOf("File") >= 0)
    {
      File original = nodeInfo.file;

      String parent_path = original.getParent();
      String new_file_path = parent_path + System.getProperty("file.separator") + newValue.toString();

      File newfile = new File(new_file_path);
      MyInternalFrame.CopyFile(original, newfile);
      original.delete();

      nodeInfo.file = newfile;
    }
  }
}
