from jsonrpcserver import Success, method, serve
import base64
import os

@method
def ping():
    return Success("pong")

@method
def hello():
    return Success("hello world")

@method
def printName(name):
    return Success(f"hello {name}")

@method
def saveNewFile(data):
    # Print the encoded data (for debugging)
    print("Encoded file data received:")
    print(data)
    file_bytes = base64.b64decode(data)
    filename = "1-saved.mp3"
    with open(filename, "wb") as file:
        file.write(file_bytes)
    return Success("saved")

@method
def listAll():
    # List files in the current working directory.
    files = os.listdir(".")
    # Return a string with each file on a new line
    return Success("\n".join(files))

@method
def deleteSingleFile(filename):
    try:
        os.remove(filename)
        return Success("Deleted")
    except Exception as e:
        return Success(f"Error: {str(e)}")

if __name__ == "__main__":
    serve()

