import java.io.*;
import java.util.*;

class Client{
	//Vectors to store data
	private Vector data;
	private Filter filter;

	//Constructor to initialize the data vector with names
	public Client(){
		data = new Vector();
		data.addElement("Alan"); data.addElement("Joanne");
		data.addElement("John"); data.addElement("Martin");
	}

	//Method to list all names in the vector
	public void listNames(){
		Enumeration e = data.elements();
		while(e.hasMoreElements()){
			String s = (String)e.nextElement();
			System.out.println(s);
		}
	}
	//Method to filter names that start with 'J'
	public void filterNames(){
		filter = new Filter(data.elements(), "J");//Initializing the filter with the enumaretion of names starting with J
		
		//while loop to iterate through the filtered names and print them 
		while(filter.hasMoreElements()){
			String s = (String)filter.nextElement();
			System.out.println(s);
		}
	}

	//Main method to run the program
	public static void main(String[] args){
		//Client instance
		Client app = new Client();

		//Printing the names in the vector and filtered names 
		System.out.println("All names");
		app.listNames();
		System.out.println("Filtered names");
		app.filterNames();
	}
}
