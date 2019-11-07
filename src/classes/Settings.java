package classes;

import java.io.Serializable;
import application.*;

public class Settings implements Serializable {
  public boolean alertOnDelete;
  
  public Settings() {
    //Default Settings
    alertOnDelete = false;
  }
}
