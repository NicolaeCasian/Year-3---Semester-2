import java.io.*;

class WindowManager2{
	// Will be set to true if one exists
	private static boolean instance_flag = false;

	public WindowManager2() throws SingletonException{
		if (instance_flag)
			throw new SingletonException("Only one WindowManager allowed.");
		else
			instance_flag = true;
	}
	// Test method so we can ensure that our
	// object works
	public void print(String message){
		System.out.println(message);
	}
}





