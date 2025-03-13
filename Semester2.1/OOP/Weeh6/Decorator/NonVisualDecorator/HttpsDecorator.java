public class HttpsDecorator extends Decorator {
    // public Constructor that takes a Content object as a parameter
    HttpsDecorator(Content contentToBeDecorated) {
        super(contentToBeDecorated);
    }

    public void show() {
        // Decorate
        System.out.print("https://www.");
        // Delegate
        super.show();
    }
}
