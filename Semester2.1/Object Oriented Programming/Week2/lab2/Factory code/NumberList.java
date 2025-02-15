public abstract class NumberList {

//Protected arrays to store integers , doubles and hexadecimals
protected int[] intList;
protected double[] doubleList;
protected int[] hexList;

//Constructor which intializes all arrays to null
NumberList() {
	intList = null; doubleList = null; hexList = null;
}
//Get method to retrieve the integer list
public int[] getIntList() {
	return intList; }

//Get method to retrieve the double list
public double[] getDoubleList() {
	return doubleList; }

//Get method to retrieve the hexadecimal list
public int[] getHexList() {
	return hexList; }

//Method to display the numbers in the list
public void display() {}

//Method to return the sum of the lists
public Number sum() { return null; }
}
