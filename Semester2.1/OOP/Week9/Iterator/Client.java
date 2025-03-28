import java.io.*;
import java.util.*;

class Client{
	private Vector data;
	private Filter filter;

	public Client(){
		data = new Vector();
		data.addElement("Alan"); data.addElement("Joanne");
		data.addElement("John"); data.addElement("Martin");
	}
	public void listNames(){
		Enumeration e = data.elements();
		while(e.hasMoreElements()){
			String s = (String)e.nextElement();
			System.out.println(s);
		}
	}
	public void filterNames(){
		filter = new Filter(data.elements(), "J");
		while(filter.hasMoreElements()){
			String s = (String)filter.nextElement();
			System.out.println(s);
		}
	}
	public static void main(String[] args){
		Client app = new Client();
		System.out.println("All names");
		app.listNames();
		System.out.println("Filtered names");
		app.filterNames();
	}
}
