import math;

class Circle:
    def __init__(self, radius):
        self.radius = radius
    
    def area(self):
        return math.pi * self.radius  ** 2
    
    def perimeter(self):
         return 2 * math.pi * self.radius

r = int(input("Enter the radius of the circle (integer only): "))
c = Circle(r)  # Create a Circle object with radius 5
print("Area:", c.area())  
print("Perimeter:", c.perimeter())

    

    