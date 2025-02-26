public class Test{
    public static void main(String[] args){
        car car = new car("blue", "sedan", 1.5, 10000.0, 5);
        motorbike bike = new motorbike("green", "harley", 2.0, 5000.0, 0, false);

        System.out.println("Car: \n Colour: "+ car.colour + " Type: "+car.type+ " EnginSize: " + car.engineSize + " Net Price:" + car.netPrice +" Car VRT: "+ car.vrt());
        System.out.println("Motorbike \n " + "Colour: " +bike.colour+" Type: "+ bike.type+" Engine Size: " + bike.engineSize + " Net Price: "+ bike.netPrice +
        " Motorbike VRT: " + bike.vrt());
    }
}