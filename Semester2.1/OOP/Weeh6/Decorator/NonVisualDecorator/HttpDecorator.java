public class HttpDecorator extends Decorator {
  HttpDecorator(Content contentToBeDecorated){super(contentToBeDecorated);}
  public void show(){
    // Decorate
    System.out.print("http://www.");
    // Delegate
    super.show();
  }
}
