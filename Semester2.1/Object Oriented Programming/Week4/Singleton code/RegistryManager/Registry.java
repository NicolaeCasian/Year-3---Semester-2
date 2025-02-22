package RegistryManager;
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
   public static LogManager getLogManager() {
       if (logManager == null) {
           logManager = LogManager.getLogManager();
       }
       return logManager;
   }

   public static PrintManager getPrintManager() {
       if (printManager == null) {
           printManager = PrintManager.getPrintManager();
       }
       return printManager;
   }

   public static WindowManager getWindowManager() {
       if (windowManager == null) {
           windowManager = WindowManager.getManager();
       }
       return windowManager;
   }

   public static MessageManager getMessageManager() {
       if (messageManager == null) {
           messageManager = MessageManager.getManager();
       }
       return messageManager;
   }
}



