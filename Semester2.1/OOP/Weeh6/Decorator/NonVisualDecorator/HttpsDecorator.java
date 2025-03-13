public class HttpsDecorator extends Decorator {
    
    HttpsDecorator(Content contentToBeDecorated) {
        super(contentToBeDecorated);
    }
    
    @Override
    public void show() {
        System.out.print("https://www.");
        super.show();
    }
}
