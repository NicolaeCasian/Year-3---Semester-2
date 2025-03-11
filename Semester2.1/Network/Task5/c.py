from jsonrpcclient import request, parse, Ok


import requests

import base64



def sendPing():


    # send a request to the server

    print("what is the servers port?")

    portToCall = int(input())


    try:

         response = requests.post(f"http://localhost:{portToCall}/", json=request("ping"))

         parsed = parse(response.json())


         print(parsed.result)

    except:

         print(" ----- Are you sure that port and function exist?")
def sayHello(personName):



    # send a request to the server

    print("what is the servers port?")

    portToCall = int(input())

       

       

    try:

        response = requests.post(f"http://localhost:{portToCall}/", json=request("printName", params={"name": personName}) )

        parsed = parse(response.json())

       

        print(parsed.result)

    except:

        print(" ------ Are you sure that port and function exist?")
print("Welcome!")




while True:

    print("please type a menu option")


    print("1. send ping")

    print("2. say hello")



    option = int(input())


    if option == 1:

        sendPing()

       

    elif option == 2:

        sayHello('john')