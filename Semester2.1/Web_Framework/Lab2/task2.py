from datetime import datetime

class Person:
    def __init__(self, dob ):
        self.dob = dob
   
    def calculate_age(dob):
         dob = datetime.strptime(dob, "%Y-%m-%d")  # Convert input string to datetime object
         today = datetime.today()  # Get today's date
         age = today.year - dob.year - ((today.month, today.day) < (dob.month, dob.day))
         return age
    
    name_input = input("Enter your name: ")
    country_input = input("Enter your country:")
    dob_input = input("Enter your date of birth (YYYY-MM-DD): ")
    print("Name: "+ name_input + "\n" + "Country: "+ country_input+"\n"+ "Your age is: ", calculate_age(dob_input))

