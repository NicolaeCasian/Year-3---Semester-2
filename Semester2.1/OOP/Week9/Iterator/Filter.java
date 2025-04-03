import java.util.*;

class Filter implements Enumeration{
	//private variables for data and filter elements
	private Enumeration data;
	private String filter;
	private String element;

	//Constructor to initialize the filter and data variables
	public Filter(Enumeration data, String filter){
		this.filter = filter;
		this.data = data;
		element = null;
	}

	//Method to check if there are more elements 
	public Object nextElement(){
		//If statement chcking if element is null
		//if not return the element
		//else throw NoSuchElementException
		if (element != null)
			return element;
		else
			throw new NoSuchElementException();
	}

	//Method to check if there are more elements in the data
	public boolean hasMoreElements(){
		boolean found = false;
		//loop through the data and check if the element starts with the filter
		while(data.hasMoreElements() && !found){
			element = (String)data.nextElement();
			found = element.startsWith(filter);
		}
		//if its not found element becomes null
		//and return false
		if (! found)
			element = null;
		return found;
	}

}


