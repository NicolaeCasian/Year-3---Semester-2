
public class Decorator extends Content{
    //Reference to the content object to be decorated
    private Content contentToBeDecorated;
    Decorator(Content contentToBeDecorated){this.contentToBeDecorated = contentToBeDecorated;}
    public void show(){contentToBeDecorated.show();}
}



