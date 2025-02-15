//Main Driver Program 
public class Main{
  public static void main(String[] args){
    //Creates a new object for builder 
    GUIBuilder builder = new GUIBuilder();
    AbstractWidgetFactory widgetFactory = null;
    //check what platform we're on

    //Checks for mac os
    if (System.getProperty("os.name").toLowerCase().contains("mac")){
      widgetFactory  = new MacOSXWidgetFactory();
    //Checks for windows os 
    } else if (System.getProperty("os.name").toLowerCase().contains("windows")) {
      widgetFactory  = new MsWindowsWidgetFactory();
    }
    //If its not windows or mac print unix os
    else{
      widgetFactory = new UnixWidgetFactory();
    }
    
    builder.buildWindow(widgetFactory, "New Window");
  }
}



