# Echo client program

import socket


HOST = '127.0.0.1'    # The remote host

PORT = 50002         # The same port as used by the server



# create a new connection to the server

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

s.connect((HOST, PORT))




# start an infinite loop allowing us to send

# commands to the server and wait for the response

while True:

    print("type input: ( try type <ping> with the brackets.")

    text = input()

   

   

    if text == '<q>':

        s.sendall('<q>'.encode())

        s.close()

        break


    s.sendall(text.encode())

    data = s.recv(1000)

    print("Response:" + str(data))

   

   

    

   