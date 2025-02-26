public class car  extends vehicle{
    //Intialising Car Atrributes
    int numOfDoors ;

    //Car Constructor which calls the vehicle constructor
    public car(String colour, String type, double 
    engineSize, double netPrice, int numOfDoors){
        //calls in the Vehicle Constructor
        super(colour, type, engineSize, netPrice);
        this.numOfDoors = numOfDoors;
    }
}
