import java.io.*;

class WindowManagerTest{
        public static void main(String[] args){
	       // This will create a new instance of WindowManager
	       WindowManager wm1 = WindowManager.getManager();
	       wm1.print("Hello im a new Singleton");
               System.out.println("wm1 = " + wm1);

               WindowManager wm2 = WindowManager.getManager();
               wm2.print("Hello im a new Singleton");
               System.out.println("wm2 = " + wm2);

                // This will fail to compile as WindowManager
                // constructor is private
                // WindowManager wm3 = new WindowManager();
	}
}














