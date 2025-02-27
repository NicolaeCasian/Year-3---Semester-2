public class PersonClient{
    public static void main(String args[]){

        // Create the person using the builder pattern
        Person person = new Person.Builder("Paul", "Smyth")
            .setAge(45)
            .setMothersName("Julie")
            .setHeight(6)
            .setWeight(80)
            .build();

        // Display the persons details
        person.display();
    }
}
