import java.io.*;

class WindowManagerTest2{
	public static void main(String[] args){
		WindowManager2 wm1, wm2;
		System.out.println("Opening WindowManager");
		// Try to create WindowManager for the
		// first time
		try{
			wm1 = new WindowManager2();
		}
		catch(SingletonException e){
			System.out.println(e.getMessage());
		}
		// Try to create WindowManager for the
		// second time
		try{
			wm2 = new WindowManager2();
		}
		catch(SingletonException e){
			System.out.println(e.getMessage());
		}
	}
}



