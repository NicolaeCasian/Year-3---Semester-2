package RegisteryManager;

class RegistryTest{

    public static void main(String[] args){
    //Calling in all of the files 
    //Window manager, log manager, print manager and message manager
    WindowManager wm1 = Registry.getWindowManager();
    WindowManager wm2 = Registry.getWindowManager();


    LogManager lm1 = Registry.getLogManager();
    LogManager lm2 = Registry.getLogManager();

    PrintManager pm1 = Registry.getPrintManager();
    PrintManager pm2 = Registry.getPrintManager();
    
    MessageManager mm1 = Registry.getMessageManager();
    MessageManager mm2 = Registry.getMessageManager();
   
    //Checking the SIngletone functionality
		System.out.println("Log Manager: " + (lm1 == lm2) );
        System.out.println("Window Manager: "+(wm1 == wm2) );
        System.out.println("Print Manager: " +(pm1 == pm2));
        System.out.println("Message Manager: "+(mm1 == mm2) );

    }
}
