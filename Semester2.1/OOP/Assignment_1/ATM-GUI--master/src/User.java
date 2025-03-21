
import java.util.ArrayList;


public class User{

	
	private String name;
	private String password;
	private int userID;
	private static int count = 1;
	private ArrayList<String> userTrans;
	
	public User(String name, String password)
	{
		this.name = name;
		this.password = password;
		this.userID = count;
		userTrans = new ArrayList<String>();
		count++;
		
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getPassword(){
		return this.password;
	}
	
    public void setPassword(String pass){
    	this.password = pass;
    }
    
    public void Spend(int accID, double amount, String type)
    {   	
    	String s1 = accID + " " +  amount + " " + type;
    	userTrans.add(s1);  	
    }
    
    public ArrayList<String> getUserTrans()
    {
    	return userTrans;
    }
    
	public int getUID(){
		return this.userID;
	}
	
}
