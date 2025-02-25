
//Client
public class GUIBuilder{
  public void buildWindow(AbstractWidgetFactory widgetFactory, String title){
    //Creates the window object for the GUI 
    Window window = widgetFactory.createWindow(title);
    window.repaint();
  }
}




