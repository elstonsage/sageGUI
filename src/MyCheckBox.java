package sage;

import javax.swing.JCheckBox;
import java.io.*;

public class MyCheckBox extends JCheckBox implements Serializable{
  public IconNode temp;
  public NodeInfo tempnode;

  public MyCheckBox(IconNode temp,NodeInfo tempnode) {
    super();
    this.temp = temp;
    this.tempnode = tempnode;
  }
}
