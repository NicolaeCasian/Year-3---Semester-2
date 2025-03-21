import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;


public class Account {
	private double balance;
	private String type;
	private int accId;
	private ArrayList<Transaction> trans;
	
	public Account(double balance, String type, int accId){
		this.balance = balance;
		this.type = type;
		this.accId = accId;
		trans =  new ArrayList<Transaction>();
	}
	
	public double getBalance(){
		return balance;
	}
	
	public void setBalance( double bal){
		this.balance = bal;
	}
	
	public String getType()
	{
		return type;
	}
	
	public int getAccId()
	{
		return accId;
	}
	
	
	public void doTransaction(double amount, int time, String type)
	{
		Transaction T1 = new Transaction(amount, time, type);
		trans.add(T1);
	}
	
	@SuppressWarnings("unchecked")
	public void printTransLog()
	{
		try {
			PrintWriter writer = new PrintWriter("TransLog.txt");
			if(trans == null)
			{
				writer.println("No transactions to display ");
				writer.close();
			}
			else
			{   writer.println("Account ID: " + accId + " " + "Balance: " + balance + " " + "Type: " + type);
			    writer.flush();
			    writer.println("Transcaction logs: ");
				Collections.sort(trans);
				
				for(int i = 0; i < trans.size(); i++)
				{
					writer.println("Amount: $" + trans.get(i).getAmount()  + " " + "Time :" + trans.get(i).getTime() + "min "  + "Type: " + trans.get(i).getType());
					writer.flush();
				}
				writer.close();
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
}
