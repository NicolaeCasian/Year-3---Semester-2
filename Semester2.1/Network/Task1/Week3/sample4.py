
#Prompts and inputs for user to input details
print("Hello there please enter your first name: ")
firstname = input()
print("Please enter your last name: ")
lastname = input()
print("Please enter your age: ")
age = int(input())

#Function for determining age group
if age <= 20:
    print("Still young!")

elif 40 <= age < 60:
    print("Getting old!")

else : 
    print("Retired!")

#printing details to console 
print("First Name: " + firstname +" Lastname: " + lastname + " Age: " + str(age))
