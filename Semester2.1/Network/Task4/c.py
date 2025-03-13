
from jsonrpcclient import request, parse
import requests
import base64

def sendPing():
    response = requests.post("http://localhost:5000/", json=request("ping"))
    parsed = parse(response.json())
    print("Ping response:", parsed.result)

def sayHello(personName):
    response = requests.post("http://localhost:5000/", json=request("printName", params={"name": personName}))
    parsed = parse(response.json())
    print("sayHello response:", parsed.result)

def sendFile(filename):
    try:
        with open(filename, 'rb') as chunk:
            content = chunk.read()
    except FileNotFoundError:
        print("File not found!")
        return

    encodedContent = base64.b64encode(content).decode("utf-8")
    # For debugging, you can print a snippet of the encoded content
    print("Encoded file snippet:", encodedContent[:60], "...")
    response = requests.post("http://localhost:5000/", json=request("saveNewFile", params={"data": encodedContent}))
    parsed = parse(response.json())
    print("sendFile response:", parsed.result)

def askForList():
    response = requests.post("http://localhost:5000/", json=request("listAll"))
    parsed = parse(response.json())
    print("Directory listing on server:")
    print(parsed.result)

def deleteFile():
    print("Enter the filename to delete:")
    file_to_delete = input()
    response = requests.post("http://localhost:5000/", json=request("deleteSingleFile", params={"filename": file_to_delete}))
    parsed = parse(response.json())
    print("deleteFile response:", parsed.result)

# Basic menu system
print("Welcome!")
while True:
    print("\nPlease choose an option:")
    print("1. send ping")
    print("2. say hello")
    print("3. send file")
    print("4. list all files")
    print("5. delete file")
    option = int(input())

    if option == 1:
        sendPing()
    elif option == 2:
        sayHello("john")
    elif option == 3:
        print("What is the filename to send?")
        filename = input()
        sendFile(filename)
    elif option == 4:
        askForList()
    elif option == 5:
        deleteFile()
    else:
        print("Invalid option")

