public class motorbike extends car {
    //Initialising motorbike variables
    boolean carrier;

    //Motorbike Constructor
    public motorbike(String colour, String type, double 
    engineSize, double netPrice, int numOfDoors, boolean carrier){
        //calls in the car constructor
        super(colour, type, engineSize, netPrice, numOfDoors);
        this.carrier = carrier;
    }
    
    @Override
    public double vrt(){
        return netPrice *0.13;
    }
}
