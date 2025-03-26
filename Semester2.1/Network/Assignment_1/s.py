# server.py
from jsonrpcserver import method, serve, Success
import sys
import os
import subprocess
import requests
import platform
import glob

# Global list of friend servers (stored by server number)
myFriends = []

#Get the server number from the command line 
if len(sys.argv) > 1:
    serverNumber = sys.argv[1]
else:
    print("No server number given shutting down")
    quit()

# Calculate the port number based on the server number.
portNumber = 5000 + int(serverNumber)

#Methods that can be called by the client
#Ping method which returns pong
@method
def ping():
    return Success("pong")

#Method to make a new directory aka a new folder
@method
def make_folder(foldername):
    #try to make a directory and return a success message if successful
    try:
        os.mkdir(foldername)
        return Success(f"Folder {foldername} made.")
    #return an error message if the directory cannot be made
    except Exception as e:
        return Success(f"Failed to make folder {foldername}: {e}")

#Method to delete a folder
@method
def delete_folder(foldername):
    #try to remove a folder if not return an error message
    try:
        os.rmdir(foldername)
        return Success(f"Folder {foldername} deleted.")
    #return an error message if the folder cannot be deleted
    except Exception as e:
        return Success(f"Failed to delete folder {foldername}: {e}")

#Method to show the servernumber and port number of the running server
@method
def whoareyou():
    return Success(f"Server {serverNumber} running on port {portNumber}")

#Method to show the current version of python
@method
def get_version():
    return Success(platform.python_version())

#Method to search for a file
@method
def search(filename):
    #Search for a file and if the file mathches the filename#
    #return a success message if the file is found
    matches = glob.glob(filename)
    if matches:
        return Success(f"File '{filename}' found.")
    else:
        return Success(f"File '{filename}' not found.")

@method
def startup(x):
    # x is the server number for the new server.
    new_server_num = x  # expecting x to be a string or number
    new_port = 5000 + int(new_server_num)
    # Start a new server process.
    subprocess.Popen(["python", "s.py", str(new_server_num)])
    # Register the new server as a friend.
    if new_server_num not in myFriends:
        myFriends.append(new_server_num)
    return Success(f"Started server {new_server_num} on port {new_port}.")

@method
def shutdown(x):
    # x is the server number to shutdown.
    target_port = 5000 + int(x)
    try:
        # Send a JSON-RPC shutdown request to the target server.
        requests.post(
            f"http://localhost:{target_port}/",
            json={"method": "shutdown", "params": {}, "jsonrpc": "2.0", "id": 1}
        )
        # Remove the target server from friend list.
        if x in myFriends:
            myFriends.remove(x)
        return Success(f"Shutdown command sent to server {x} on port {target_port}.")
    except Exception as e:
        return Success(f"Failed to send shutdown command to server {x}: {e}")

#Method to list the friends of the server
@method
def list_friends():
    return Success(f"Friends: {', '.join(str(f) for f in myFriends)}")

#Method to make a server go online
@method
def online(x):
    # Add server number x to the friend list.
    if x not in myFriends:
        myFriends.append(x)
    return Success(f"Server {x} is now online.")

#Method to make a server go offline
@method
def offline(x):
    # Remove server number x from the friend list.
    if x in myFriends:
        myFriends.remove(x)
        return Success(f"Server {x} is now offline.")
    else:
        return Success(f"Server {x} was not online.")
    
#Method to ping all the servers in the firends list
@method
def heartbeat():
    #Array to store the results
    results = {}

    #Loop through the friends list and ping each server
    #If the server is online return pong else return no response
    for friend in myFriends:
        friend_port = 5000 + int(friend)
        try:
            print(f"Pinging server on port {friend_port}...")  # Debug statement
            resp = requests.post(
                f"http://localhost:{friend_port}/",
                json={"method": "ping", "params": {}, "jsonrpc": "2.0", "id": 1}
            )
            parsed = resp.json()
            results[friend] = parsed.get("result", "no response")
        except Exception as e:
            results[friend] = f"Error: {e}"
            print(f"Error pinging server on port {friend_port}: {e}")  # Debug statement
    return Success(f"Heartbeat results: {results}")


@method
def pass_msg(msg, x):
    # Check if the message is for this server.
    if int(serverNumber) == int(x):
        return Success(f"Message received at server {serverNumber}: {msg}")
    else:
        # Forward the message to all friends.
        forwarded = False
        for friend in myFriends:
            friend_port = 5000 + int(friend)
            try:
                requests.post(
                    f"http://localhost:{friend_port}/",
                    json={"method": "pass_msg", "params": {"msg": msg, "x": x}, "jsonrpc": "2.0", "id": 1}
                )
                forwarded = True
            except Exception:
                continue
        # Return a success message if the message was forwarded.
        if forwarded:
            return Success(f"Message forwarded towards server {x}.")
        else:
            return Success("No friend could forward the message.")
#Print the server number and port number of the running server
print(f"Server number {serverNumber} running on port {portNumber}.....")
serve(port=portNumber)
