import java.lang.module.ModuleDescriptor.Builder;

public class Computer {
    //required parameters
    private String ram;
    private String hdd;
    private String cpu;
    //optional parameters
    private boolean isGraphicsCardEnabled;
    private boolean isBluetoothEnabled;

    //Constructor with required parameters
    private Computer(Builder builder) {
        this.ram = builder.ram;
        this.hdd = builder.hdd;
        this.cpu = builder.cpu;
        this.isGraphicsCardEnabled = builder.isGraphicsCardEnabled;
        this.isBluetoothEnabled = builder.isBluetoothEnabled;
    }
    //Display method to display all the parameters
    public void display(){
        System.out.println(ram + "," + hdd + "," + cpu + "," + isGraphicsCardEnabled + "," + isBluetoothEnabled);
    }

    //Builder class to build the computer object
    public static class Builder {
        //Private variables
        private String ram;
        private String hdd;
        private String cpu;
        private boolean isGraphicsCardEnabled;
        private boolean isBluetoothEnabled;

        //Builder constructor with required parameters
        public Builder(String ram, String hdd, String cpu){
            this.ram = ram;
            this.hdd = hdd;
            this.cpu = cpu;
        }
        //Setters for optional parameters
        public Builder setRam(String ram) {
            this.ram = ram;
            return this;
        }
        public Builder setHdd(String hdd) {
            this.hdd = hdd;
            return this;
        }
        public Builder setCpu(String cpu) {
            this.cpu = cpu;
            return this;
        }
        public Builder setGraphicsCardEnabled(boolean isGraphicsCardEnabled) {
            this.isGraphicsCardEnabled = isGraphicsCardEnabled;
            return this;
        }
        public Builder setBluetoothEnabled(boolean isBluetoothEnabled) {
            this.isBluetoothEnabled = isBluetoothEnabled;
            return this;
        }
        //Build method to return the computer object
        public Computer build(){
            return new Computer(this);
        }
    }
}
