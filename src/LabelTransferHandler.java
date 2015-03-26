package sage;

import javax.swing.*;

public class LabelTransferHandler extends StringTransferHandler{
  protected NodeInfo exportString(JComponent c) {

      JLabel label = (JLabel)c;
      String text = label.getToolTipText();

      NodeInfo node = new NodeInfo(text, "",text, "");
      return node;
  }

  protected void importString(JComponent c, NodeInfo str) {

  }
}
