public class vehicle{
    //Initialisng Vehicle Attributes
    String colour;
    String type;
    double engineSize;
    double netPrice;

    //vehicle Constructor 
    public vehicle(String colour, String type, 
    double engineSize, double netPrice){
        this.colour = colour;
        this.type=type;
        this.engineSize = engineSize;
        this.netPrice = netPrice;
    }

    //Method to calculate VRT
    public double vrt() {
        return netPrice *0.21;
    }
}