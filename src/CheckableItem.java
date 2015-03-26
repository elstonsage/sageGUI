package sage;

class CheckableItem implements java.io.Serializable{
  private VariableData  str;
  private boolean isSelected;
  private String  str2;
  private boolean isSelected2;

  public CheckableItem(VariableData str) {
    this(str, null);
  }

  public CheckableItem(VariableData str, String str2) {
    this.str = str;
    this.str2 = str2;
    isSelected = false;
    isSelected2 = false;
  }

  public VariableData getVariableData()
  {
      return this.str;
  }

  public String getVarType()
  {
    return str.varType;
  }

  public void setSelected(boolean b) {
    isSelected = b;
  }

  public void setSelected2(boolean b) {
    isSelected2 = b;
  }

  public boolean isSelected() {
    return isSelected;
  }

  public boolean isSelected2() {
    return isSelected2;
  }

  public String toString() {
    return str.Name;
  }

  public String toString2() {
    return str2;
  }
}
