public class ComputerBuilderDirector {

    public static Computer getBasicComputer() {
        // Build and return a basic computer
        return new Computer.Builder("2GB", "2TB", "Intel i7").setBluetoothEnabled(true).build();
    }

    public static Computer getGraphicsCardEnabledComputer() {
        // Build and return a GFX computer
        return new Computer.Builder("2GB", "2TB", "Intel i7").setGraphicsCardEnabled(true).build();
    }
    
}
