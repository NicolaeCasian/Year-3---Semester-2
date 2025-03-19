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

if len(sys.argv) > 1:
    serverNumber = sys.argv[1]
else:
    print("No server number given shutting down")
    quit()

# Calculate the port number based on the server number.
portNumber = 5000 + int(serverNumber)

@method
def ping():
    return Success("pong")

@method
def printName(portname):
    return Success(f"hello {portname}")

@method
def make_folder(foldername):
    try:
        os.mkdir(foldername)
        return Success(f"Folder '{foldername}' made.")
    except Exception as e:
        return Success(f"Failed to make folder '{foldername}': {e}")

@method
def delete_folder(foldername):
    try:
        os.rmdir(foldername)
        return Success(f"Folder '{foldername}' deleted.")
    except Exception as e:
        return Success(f"Failed to delete folder '{foldername}': {e}")

@method
def whoareyou():
    return Success(f"Server {serverNumber} running on port {portNumber}")

@method
def get_version():
    return Success(platform.python_version())

@method
def search(filename):
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
    subprocess.Popen(["python", "server.py", str(new_server_num)])
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

@method
def list_friends():
    return Success(f"Friends: {', '.join(str(f) for f in myFriends)}")

@method
def online(x):
    # Add server number x to the friend list.
    if x not in myFriends:
        myFriends.append(x)
    return Success(f"Server {x} is now online.")

@method
def offline(x):
    # Remove server number x from the friend list.
    if x in myFriends:
        myFriends.remove(x)
        return Success(f"Server {x} is now offline.")
    else:
        return Success(f"Server {x} was not online.")

@method
def heartbeat():
    results = {}
    for friend in myFriends:
        friend_port = 5000 + int(friend)
        try:
            resp = requests.post(
                f"http://localhost:{friend_port}/",
                json={"method": "ping", "params": {}, "jsonrpc": "2.0", "id": 1}
            )
            parsed = resp.json()
            results[friend] = parsed.get("result", "no response")
        except Exception as e:
            results[friend] = f"Error: {e}"
    return Success(f"Heartbeat results: {results}")

@method
def pass_msg(msg, x):
    # x is the target server number.
    if int(serverNumber) == int(x):
        return Success(f"Message received at server {serverNumber}: {msg}")
    else:
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
        if forwarded:
            return Success(f"Message forwarded towards server {x}.")
        else:
            return Success("No friend could forward the message.")

# This method is used to shutdown the current server.
@method
def shutdown():
    os._exit(0)

print(f"Server number {serverNumber} running on port {portNumber}.....")
serve(port=portNumber)
