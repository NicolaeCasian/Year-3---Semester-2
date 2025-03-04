
public class ComputerClient {
    public static void main(String[] args) {
        //Testing the builder pattern

        //Creating a computer instance using the builder pattern
        Computer computer = new Computer.Builder("2GB", "2TB", "Intel i7")
        .setBluetoothEnabled(false)
        .setGraphicsCardEnabled(true)
        .build();
        //Displaying the computer instance
        computer.display();

        //Testing the Computer Builder Director class
        //Creating a basic computer instance from the director class
        Computer basicComputer = ComputerBuilderDirector.getBasicComputer();
        //Displaying the basic computer instance
        basicComputer.display();
        
        //Creating a computer instance with a graphics card enabled
        Computer gfxComputer = ComputerBuilderDirector.getGraphicsCardEnabledComputer();
        //Displaying the computer instance with a graphics card enabled
        gfxComputer.display();
    }
}
