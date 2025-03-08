public class FtpDecorator extends Decorator {
  FtpDecorator(Content contentToBeDecorated){super(contentToBeDecorated);}
  public void show(){
    // Decorate
    System.out.print("ftp.");
    // Delegate
    super.show();
  }
}
