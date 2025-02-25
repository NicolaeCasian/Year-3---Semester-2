import java.io.*;

class WindowManager
{
	// This will be the one and only WindowManager instance
	private static WindowManager wmanager;
	private WindowManager(){
		// Private constructor that does nothing
	}
	// Public synchronized method which will return a
	// WindowManager
	public static synchronized WindowManager getManager(){
		// If true then we need to create an instance of
		// WindowManager
		if (wmanager == null)
			wmanager = new WindowManager();
		return wmanager;
	}
	// Test method so we can ensure that our
	// object works
	public void print(String message){
		System.out.println(message);
	}
}

















