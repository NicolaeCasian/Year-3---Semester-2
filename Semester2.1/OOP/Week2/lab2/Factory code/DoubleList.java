
import java.util.StringTokenizer;

public class DoubleList extends NumberList {

int size; //Stores the number of elements in the list
DoubleList(String list) { size = 0; //Constructor which takes a string of double values

StringTokenizer token = new StringTokenizer(list); //Uses Stringtokenizer to split the input string into individual numbers aka tokens
size = token.countTokens();

// Allocate some space for the array
doubleList = new double[size];

// Store each list item an the appropriate array
for(int i = 0; i < size; i++) {
	doubleList[i] = Double.parseDouble(token.nextToken());
}
}

//Method to calculate and return the sum of all numbers
public Number sum() {

double n = 0;
for(int i = 0; i < size; i++) {
	n = n + doubleList[i];
}
return new Double(n);
}

//Method to display the double list
public void display() {
System.out.print("Double List"); for(int i = 0; i < size; i++)
System.out.println("[" + i + "] = " + doubleList[i]);
}
}