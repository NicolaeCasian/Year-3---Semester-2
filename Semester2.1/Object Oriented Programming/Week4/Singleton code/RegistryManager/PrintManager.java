package RegistryManager;
public class PrintManager {
    private static PrintManager ptm;


    //Private constructor 
    private PrintManager(){
    }

    //Syncronising 
    public static synchronized PrintManager getPrintManager(){
		// If true then we need to create an instance of
		// WindowManager
		if (ptm == null)
			ptm = new PrintManager();
		return ptm;
	}

    //Print message to test out 
    public void print(String message){
		System.out.println(message);
	}
}
