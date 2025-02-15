//Subclass of AbstractWidgetFactory --- ConcreteFactory2
public class UnixWidgetFactory extends AbstractWidgetFactory {
    //Window 
    public Window createWindow(String title){
        //Creates window object and inherits title from AbstractWidgetFactory
        UnixWindow window = new UnixWindow(title);
        return window;
      }
}
