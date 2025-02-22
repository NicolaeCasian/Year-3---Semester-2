package RegistryManager;

public class LogManager {
    private static LogManager logm;


    //Private constructor 
    private LogManager(){
    }

    //Syncronising 
    public static synchronized LogManager getLogManager(){
		// If true then we need to create an instance of
		// WindowManager
		if (logm == null)
			logm = new LogManager();
		return logm;
	}

    //Print message to test out 
    public void print(String message){
		System.out.println(message);
	}
}
