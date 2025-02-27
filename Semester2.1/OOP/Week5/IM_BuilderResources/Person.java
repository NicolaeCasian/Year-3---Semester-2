public class Person {
    // Required paramters
    private String firstName;
    private String lastName;
    // Optional parameters
    private String middleName;
    private int age;
    private String fathersName;
    private String mothersName;
    private double height;
    private double weight;

    //Create a Builder constructor with all parameters required and optional
    public Person(Builder builder) {
        this.firstName = builder.firstName;
        this.middleName = builder.middleName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.fathersName = builder.fathersName;
        this.mothersName = builder.mothersName;
        this.height = builder.height;
        this.weight = builder.weight;
    }
    //Display method to display all the parameters
    public void display(){
        System.out.println(firstName + "," + middleName + "," + lastName + "," +
                           age + "," + fathersName + "," + mothersName + "," + height + "," + weight);
    }
    //Builder class to build the person object
    public static class Builder {
        //Private variables
        private String firstName;
        private String middleName;
        private String lastName;
        private int age;
        private String fathersName;
        private String mothersName;
        private double height;
        private double weight;
        //Builder constructor with required parameters
        public Builder(String firstName, String lastName){
            this.firstName = firstName;
            this.lastName = lastName;
        }
        //Setters for optional parameters
        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        public Builder setMiddleName(String middleName) {
            this.middleName = middleName;
            return this;
        }
        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        public Builder setAge(int age) {
            this.age = age;
            return this;
        }
        public Builder setFathersName(String fathersName) {
            this.fathersName = fathersName;
            return this;
        }
        public Builder setMothersName(String mothersName) {
            this.mothersName = mothersName;
            return this;
        }
        public Builder setHeight(double height) {
            this.height = height;
            return this;
        }
        public Builder setWeight(double weight) {
            this.weight = weight;
            return this;
        }
        //End of setters

        //Build method to build the person object
        public Person build() {
            return new Person(this);
        }
    }
}
