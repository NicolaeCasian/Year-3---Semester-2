
//Subclass of AbstractWidgetFactory ---ConcreteFactory1
public class MsWindowsWidgetFactory extends AbstractWidgetFactory{
  //create an MSWindow and returns it 
  public Window createWindow(String title){
    MSWindow window = new MSWindow(title);
    return window;
  }
}





