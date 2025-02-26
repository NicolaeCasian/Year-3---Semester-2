import java.util.StringTokenizer;

public class HexList extends NumberList {
    int size; // Stores number of elements in the list
    HexList(String list) {//Constructor that takes in space separated string of integers
    
    size = 0;
    StringTokenizer token = new StringTokenizer(list);
    size = token.countTokens();
    
    // Allocate some space for the array
    hexList = new int[size];
    
    // Store each list item an the appropriate array
    for (int i = 0; i < size; i++) {
        String hexString = token.nextToken();

        // Parse the hex string into an integer
        hexList[i] = Integer.parseInt(hexString.substring(2), 16);
    }
}
//Method to calculate sum of the hexlist
    public Number sum() {
    
    
    int n = 0;
    for(int i = 0; i < size; i++)
    {
    n = n + hexList[i];
    }
    return  n;
    
    }
    //Method to display the hexlist
    public void display() {
        System.out.print("Hexadecimal List");
    
        for(int i = 0; i < size; i++)
        System.out.println("[" + i + "] = " + Integer.toHexString(hexList[i]).toUpperCase());
    }
    
    
}
