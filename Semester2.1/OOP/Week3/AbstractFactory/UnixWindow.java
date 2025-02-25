//Subclass of Window inheriting the Window title and repaint function
public class UnixWindow extends Window {
    //Unix Window and title
    UnixWindow(String text){this.title = text;}

    //Function to print the Title and window 
        public void repaint(){
            System.out.println("Title: " + title);
            System.out.println("Unix Style: Unix Window");
        }

}
