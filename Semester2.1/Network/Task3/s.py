import socket
import os

HOST = '127.0.0.1'  # Localhost
PORT = 40003         # Port to listen on

# Create socket
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((HOST, PORT))

# Listen for connections
s.listen(1)

def manageConnection(con, addr):
    print('Connected by', addr)
    #Deliverable 2 
    #Code to print out to console.log
    with open("connection.log", "w") as f:
        f.write("In the file\n") 

# Read and print the content of the file
    with open("connection.log", "r") as f:
        print(f.read()) 

        #Creating a variable to store conn and addr
        s = [f"{con,addr}\n"]
    #Everytime I log it updates the connection.log file
    with open("connection.log", "a") as f:
        f.writelines(s) 
#It opens and reads the conncetion.log file
    with open("connection.log", "r") as f:
        print(f.read())  

    while True:
        # Receive data
        data = con.recv(1024)

        if not data:
            break  # Exit if no data

        print("Received:", data)

        # Handle commands
        if b"<ping>" in data:
            print("Found ping command")
            con.send(b"We got the ping command on the server")

        elif b"<q>" in data:
            print("Closing connection and shutting down server")
            con.close()
            quit()

        elif b"<add-1-2>" in data:
            print("Found the add command")

            parts = str(data).split('-')
            print(parts[0])
            print(parts[1])
            print(parts[2])

            var1 = parts[1]
            var2 = str(parts[2])[0:-2]

            con.send(("Got numbers " + var1 + " and " + var2).encode())

        elif b"<addsong>" in data:
            print("Found <sendingsong> from client")

            parts = str(data).split('-')
            print(parts[0])
            print(parts[1])
            print(parts[2])

            filename = parts[1]
            partNum = str(parts[2])[:-2]

            print(f'Filename: {filename}')
            print(f'Part number: {partNum}')

            con.send(b"<startsending>")

            # Open file for saving
            f = open(f"{partNum}-saved.mp3", 'wb')

            # Receive file data
            filedata = con.recv(1000)
            while filedata:
                f.write(filedata)
                filedata = con.recv(1000)

                if b'<end>' in filedata:
                    print("Found <end> finishing file")
                    f.close()  # Close the file
                    break

            print(f"Finished receiving file: {partNum}")
        
        #Deliverable 4
        #List command to show all files in current directory 
        elif b"<list>" in data:
            print("Found <list> fro client")
            # List files in the current directory
            files = os.listdir('.')  

            # Send the file list to the client
            con.send(("Directory List"+ files).encode())  
            

# Accept a new connection
conn, addr = s.accept()

# Start a new thread for the connection
manageConnection(conn, addr)
