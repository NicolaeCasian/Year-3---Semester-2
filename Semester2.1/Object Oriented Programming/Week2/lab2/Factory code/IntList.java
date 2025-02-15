import java.util.StringTokenizer;

public class IntList extends NumberList {

int size; // Stores number of elements in the list
IntList(String list) {//Constructor that takes a separated string of integer values

size = 0;
StringTokenizer token = new StringTokenizer(list);
size = token.countTokens();

// Allocate some space for the array
intList = new int[size];

// Store each list item an the appropriate array
	for(int i=0;i<size;i++) {
	intList[i] = Integer.parseInt(token.nextToken());
	}

}

//Method to calculate the sum of list
public Number sum() {


int n = 0;
for(int i = 0; i < size; i++)
{
n = n + intList[i];
}
return  n; // returns the sum

}
//Method to display the list
public void display() {
	System.out.print("Integer List");

	for(int i = 0; i < size; i++)
	System.out.println("[" + i + "] = " + intList[i]);
}

}//end class