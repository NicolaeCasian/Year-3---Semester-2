package RegisteryManager;
import java.io.*;
import java.net.*;

public class Registry {

   
   // Private constructor to prevent instantiation
   private Registry() {}

   // Static methods to get instances of the manager classes
   //Since I have already specified what each function is returning in the 
   //Appropriate classes, I can just call the function and return the instance
   public static   LogManager getLogManager() {
     return LogManager.getLogManager();
   }

   public static PrintManager getPrintManager() {
       return PrintManager.getPrintManager();
   }

   public static  WindowManager getWindowManager() {
     return WindowManager.getManager();
   }

   public static MessageManager getMessageManager() {
        return MessageManager.getManager();
   }
}



