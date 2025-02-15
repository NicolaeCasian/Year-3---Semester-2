//Class to test out java functions
public class Test {
    public static void main(String[] args) {
        String s = "7A2b";
        System.out.println(Integer.parseInt(s, 16));
        System.out.println(Integer.toHexString(Integer.parseInt(s, 16)).toUpperCase());
    }
}
