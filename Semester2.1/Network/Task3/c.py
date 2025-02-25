
# Echo client program

import socket


HOST = '127.0.0.1'    # The remote host

PORT = 40003         # The same port as used by the server



# create a new connection to the server

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

s.connect((HOST, PORT))
while True:

    print("type input: ( try type <ping> with the brackets.")

    text = input()

   

   

    if text == '<q>':

        s.sendall('<q>'.encode())

        s.close()

        break


    elif text =='<sendsong>':

        print("what is the file name?")

        filename = input()

        print("which part number?")

        numParts= input()


        text = f'<addsong-{filename}-{numParts}>'

        s.sendall(text.encode())

        data = s.recv(1000)


        print("Response:" + str(data))

        print(f"Sending file: {numParts}")
        chunk = open(str(numParts)+'.mp3', 'rb')

        content = chunk.read()

        chunk.close()



        s.sendall(content)

        s.sendall(b'<end>')