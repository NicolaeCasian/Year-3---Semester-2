import socket



HOST = '127.0.0.1'        

PORT = 50002            

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

s.bind((HOST, PORT))


 



# This is the main function that does all the work

#

#

def manageConnection(con, addr):

    print('Connected by', addr)

   

    while True:

        data = con.recv(1024)

        if not data:

            break  # If no data is received, exit the loop


        print("rec: " + str(data))


        # Look for commands

        if b"<ping>" in data:

            print("found ping command")

            con.send(b"We got the ping command on the server")

        # Deliverable 2 Adding the Hello Command
        elif b"<hello>" in data:
            #Printing to the server
            print("We got the hello command")
            
            
            #Printing to the Client
            con.send(b"Hello to you too")

        # Deliverable 3 Adding the Add method
        elif b"<add-1-2>" in data:
            print("found the add command")

            #Splits the data string each time the '-' character is found 
            parts = str(data).split('-')
            
            #Printing each individual split parts 
            print(parts[0])
            print(parts[1])
            print(parts[2])

            #Deliverable 4
            var1 = parts[1]
            var2 = str(parts[2])[0:-2]

            #Deliverable 5
            con.send(("Got numbers " + var1+ " and " + var2).encode())


        elif b"<q>" in data:

            print("Closing connection and shutting down server")

            con.close()

            quit()






s.listen(1)

conn, addr = s.accept()


# Start a new thread for each connection

manageConnection(conn, addr)

