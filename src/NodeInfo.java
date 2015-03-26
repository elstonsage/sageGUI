package sage;

import java.util.*;
import java.io.*;
import java.awt.datatransfer.*;

public class NodeInfo implements Transferable, Serializable{
  static final long serialVersionUID = -5815094335766948964L;
  public static DataFlavor FLAVOUR;
  static{
    try{
      FLAVOUR = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType);
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
  }

  public String analysis_type = new String();
  public String nodename = new String();
  public String nodetype = new String();
  public sage_analysis_info analysis_info;
  public String analysis_block = new String();
  public File file;
  public Vector component_vector;
  public Vector component_name_vector;
  public int tab_selected_index;
  public DataCollectionModel infomodel;

  public String temp;

  public NodeInfo()
  {
  }

  public NodeInfo(String nname, String ntype, String atype, sage_analysis_info object, String ablock) {
    analysis_info = object;
    analysis_type = atype;
    nodename = nname;
    nodetype = ntype;
    analysis_block = ablock;
    tab_selected_index = 0;
    component_vector = new Vector();
    component_name_vector = new Vector();
  }

  public NodeInfo(String nname, String ntype, String atype, String name) {
    temp = name;
      analysis_type = atype;
      nodename = nname;
      nodetype = ntype;
      tab_selected_index = 0;
  }

  public NodeInfo(String nname, String ntype, File object) {
    file = object;
    nodename = nname;
    nodetype = ntype;
    tab_selected_index = 0;
    component_vector = new Vector();
    component_name_vector = new Vector();
  }

  public String getName() {
      return nodename;
  }

  public void setName(String newName) {
      nodename = newName;
  }

  public String toString() {
      return nodename;
  }

  public void setModel(DataCollectionModel model) {
    this.infomodel = model;
  }

  public Object getTransferData(DataFlavor flavor) throws  UnsupportedFlavorException, IOException
  {
    if(!isDataFlavorSupported(flavor))
      throw new UnsupportedFlavorException(flavor);
    return this;
  }

  public boolean isDataFlavorSupported(DataFlavor flavor)
  {
    return FLAVOUR.equals(flavor);
  }

  public DataFlavor[] getTransferDataFlavors()
  {
    return new DataFlavor[] { FLAVOUR };
  }
}
