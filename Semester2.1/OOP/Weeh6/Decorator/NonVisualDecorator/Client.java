public class Client{
   static public void main(String argv[]){
      String url = "design-patterns.com";

      Url t1 = new Url(url);
      //Creating decorators for the text object 
      HttpDecorator t2 = new HttpDecorator(new Url(url));
      FtpDecorator t3 = new FtpDecorator(new Url(url));
      HttpsDecorator t4 = new HttpsDecorator(new Url(url));

      //Displaying the text object and decorators
      System.out.println("Text object show()");
      t1.show();
      
      //Http, Ftp and Https decorators
      System.out.println("----------------------------------");
      System.out.println("Http decorated text show()");
      t2.show();
      System.out.println("----------------------------------");
      System.out.println("Ftp decorated text show()");
      t3.show();
      System.out.println("----------------------------------");
      System.out.println("Https decorated text show()");
      t4.show();
   }
}
