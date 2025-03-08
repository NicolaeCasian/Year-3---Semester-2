
public class Decorator extends Content{
    private Content contentToBeDecorated;
    Decorator(Content contentToBeDecorated){this.contentToBeDecorated = contentToBeDecorated;}
    public void show(){contentToBeDecorated.show();}
}



