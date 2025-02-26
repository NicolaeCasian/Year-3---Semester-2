import socket
import os

HOST = '127.0.0.1'  # The remote host
PORT = 40003         # The same port as used by the server

# Create a new connection to the server
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((HOST, PORT))

while True:
    print("Type input: (Try typing <ping> with the brackets)")

    text = input()

    if text == '<q>':
        # Send the quit command to the server and close the connection
        s.sendall('<q>'.encode())
        s.close()
        break

    elif text == '<list>':
        # Send the list command to the server and receive the file list
        s.send(b"<list>")
        data = s.recv(1024)

        print("Files available on the server:")
        print(data.decode())

    elif text == '<sendsong>':
        print("What is the file name?")
        filename = input()

        print("Which part number?")
        numParts = input()

        # Prepare the message in the required format
        text = f'<addsong-{filename}-{numParts}>'

        # Send the song information to the server
        s.sendall(text.encode())
        data = s.recv(1000)

        print("Response: " + str(data.decode()))

        # Check if the file exists before sending it
        if os.path.exists(filename):
            print(f"Sending file: {filename} (Part {numParts})")

            with open(filename, 'rb') as chunk:
                content = chunk.read(1000)  # Read the file in chunks

                while content:
                    s.sendall(content)  # Send each chunk to the server
                    content = chunk.read(1000)  # Read the next chunk

                # After sending the file part, send the end marker
                s.sendall(b'<end>')
        else:
            print(f"Error: File {filename} does not exist.")

# After all interactions, the socket is already closed when <q> is typed
