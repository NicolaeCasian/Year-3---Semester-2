from jsonrpcserver import Success, method, serve

@method

def ping():

    return Success("pong")

   

   

@method

def hello():

    return Success("hello world")

   

@method

def printName(name):

    return Success(f"hello {name}")

   

   

   

   

   

if __name__ == "__main__":

    serve()