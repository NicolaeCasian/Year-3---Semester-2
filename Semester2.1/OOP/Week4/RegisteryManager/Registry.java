package RegisteryManager;
import java.io.*;
import java.net.*;

public class Registry {

   // Private static instances for each manager class
   private static LogManager logManager;
   private static PrintManager printManager;
   private static WindowManager windowManager;
   private static MessageManager messageManager;

   // Private constructor to prevent instantiation
   private Registry() {}

   // Static methods to get instances of the manager classes
   //Since I have already specified what each function is returning in the 
   //Appropriate classes, I can just call the function and return the instance
   public static  synchronized LogManager getLogManager() {
       return LogManager.getLogManager();
   }

   public static  synchronized PrintManager getPrintManager() {
       return PrintManager.getPrintManager();
   }

   public static synchronized  WindowManager getWindowManager() {
       return WindowManager.getWindowManager();
   }

   public static synchronized MessageManager getMessageManager() {
     return MessageManager.getMessageManager();
   }
}



