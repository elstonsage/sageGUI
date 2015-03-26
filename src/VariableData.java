package sage;

import java.io.*;

public class VariableData implements Serializable{

  protected String   Name;
  protected String varType;

  public VariableData() {
    this(null,"");
  }

  public VariableData(String name, String type) {
    this.Name = name;
    this.varType = type;
  }

  public void setName(String name) {
    this.Name = name;
  }

  public String getName() {
    return Name;
  }

  public String getVarType() {
      return varType;
    }

  public void setVarType(String name) {
    varType = name;
  }

}
