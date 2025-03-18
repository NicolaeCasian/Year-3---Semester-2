from jsonrpcserver import Success, method, serve
import sys

if len(sys.argv) > 1:
    serverNumber = sys.argv[1]
else:
    print("Sorry, you forgot to add a server number... shutting down")
    quit()

# Ping command method returns pong
@method
def ping():
    return Success("pong")

# WhoAreYou command method now expects a parameter named 'name'
@method
def printName(name):
    return Success(f"hello {name}")

if __name__ == "__main__":
    print(f"server number {serverNumber} running.....")
    portNumber = '500' + serverNumber
    serve(port=int(portNumber))
