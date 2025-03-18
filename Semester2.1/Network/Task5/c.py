from jsonrpcclient import request, parse, Ok
import requests
import base64

def sendPing():
    # send a request to the server
    try:
        portToCall = int(input("What is the server's port? "))
    except ValueError:
        print("Invalid port number!")
        return

    try:
        response = requests.post(f"http://localhost:{portToCall}/", json=request("ping"))
        parsed = parse(response.json())
        print(parsed.result)
    except Exception as e:
        print("----- Are you sure that port and function exist?")
        print("Error:", e)

def whoAreyou():
    # send a request to the server
    try:
        portToCall = int(input("What is the server's port? "))
    except ValueError:
        print("Invalid port number!")
        return

    portname = input("Enter the name to send: ")

    try:
        # Use the key "portname" to match the server's parameter name
        response = requests.post(
            f"http://localhost:{portToCall}/", 
            json=request("printName", params={"portname": portname})
        )
        parsed = parse(response.json())
        print(parsed.result)
    except Exception as e:
        print("------ Are you sure that port and function exist?")
        print("Error:", e)

def main():
    print("Welcome!")
    
    while True:
        print("\nPlease type a menu option:")
        print("1. Send ping")
        print("2. Say hello")
        print("3. Quit")
        
        try:
            option = int(input("Option: "))
        except ValueError:
            print("Invalid option, please enter a number.")
            continue

        if option == 1:
            sendPing()
        elif option == 2:
            whoAreyou()
        elif option == 3:
            print("Goodbye!")
            break
        else:
            print("Invalid option. Please try again.")

if __name__ == "__main__":
    main()
