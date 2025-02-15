public class NumberFactory {
    public NumberList getNumberList(String list) {
        // Check if it's a list of doubles
        if (list.contains(".")) {
            return new DoubleList(list);
        }
        // Check if it's a hexadecimal list
        else if (list.startsWith("0x")) {  
            return new HexList(list);
        }
        // Otherwise, assume it's an integer list
        else {
            return new IntList(list);
        }
    }
}
