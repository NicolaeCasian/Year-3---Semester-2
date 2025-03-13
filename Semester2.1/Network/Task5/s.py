from jsonrpcserver import Success, method, serve

import sys

if len(sys.argv) > 1:

    serverNumber = sys.argv[1]

else:

    print("sorry you forgot to add a server number... shutting down")

    quit()
myFriends = list()




@method

def ping():

    return Success("pong")

   

   

   


@method

def printName(name):

    return Success(f"hello {name}")
if __name__ == "__main__":

    print(f"server number {serverNumber} running.....")

    portNumber = '500' + serverNumber

    serve(port=int(portNumber))