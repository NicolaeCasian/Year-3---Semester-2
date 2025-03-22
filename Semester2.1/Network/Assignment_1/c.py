from jsonrpcclient import request, parse, Ok
import requests
import base64

def make_folder():
    try:
        port = int(input("What is the server's port? "))
    except ValueError:
        print("Invalid port number!")
        return
    foldername = input("Enter folder name: ")
    try:
        response = requests.post(f"http://localhost:{port}/", json=request("make_folder", params={"foldername": foldername}))
        parsed = parse(response.json())
        print(parsed.result)
    except Exception as e:
        print("----- Error sending make_folder command:")
        print("Error:", e)

def delete_folder():
    try:
        port = int(input("What is the server's port? "))
    except ValueError:
        print("Invalid port number!")
        return
    foldername = input("Enter folder name to delete: ")
    try:
        response = requests.post(f"http://localhost:{port}/", json=request("delete_folder", params={"foldername": foldername}))
        parsed = parse(response.json())
        print(parsed.result)
    except Exception as e:
        print("----- Error sending delete_folder command:")
        print("Error:", e)

def whoareyou():
    try:
        port = int(input("What is the server's port? "))
    except ValueError:
        print("Invalid port number!")
        return
    portname = input("Enter the name to send: ")
    try:
        response = requests.post(
            f"http://localhost:{port}/",
            json=request("printName", params={"portname": portname})
        )
        parsed = parse(response.json())
        print(parsed.result)
    except Exception as e:
        print("----- Error sending printName command:")
        print("Error:", e)

def get_version():
    try:
        port = int(input("What is the server's port? "))
    except ValueError:
        print("Invalid port number!")
        return
    try:
        response = requests.post(f"http://localhost:{port}/", json=request("get_version"))
        parsed = parse(response.json())
        print(parsed.result)
    except Exception as e:
        print("----- Error sending get_version command:")
        print("Error:", e)

def ping():
    try:
        port = int(input("What is the server's port? "))
    except ValueError:
        print("Invalid port number!")
        return
    try:
        response = requests.post(f"http://localhost:{port}/", json=request("ping"))
        parsed = parse(response.json())
        print(parsed.result)
    except Exception as e:
        print("----- Error sending ping command:")
        print("Error:", e)

def search_file():
    try:
        port = int(input("What is the server's port? "))
    except ValueError:
        print("Invalid port number!")
        return
    filename = input("Enter filename to search: ")
    try:
        response = requests.post(f"http://localhost:{port}/", json=request("search", params={"filename": filename}))
        parsed = parse(response.json())
        print(parsed.result)
    except Exception as e:
        print("----- Error sending search command:")
        print("Error:", e)

def startup():
    try:
        port = int(input("What is the server's port? "))
    except ValueError:
        print("Invalid port number!")
        return
    server_number = input("Enter server number: ")
    try:
        response = requests.post(f"http://localhost:{port}/", json=request("startup", params={"x": server_number}))
        parsed = parse(response.json())
        print(parsed.result)
    except Exception as e:
        print("----- Error sending startup command:")
        print("Error:", e)

def shutdown():
    try:
        port = int(input("What is the server's port? "))
    except ValueError:
        print("Invalid port number!")
        return
    server_number = input("Enter server number: ")
    try:
        response = requests.post(f"http://localhost:{port}/", json=request("shutdown", params={"x": server_number}))
        parsed = parse(response.json())
        print(parsed.result)
    except Exception as e:
        print("----- Error sending shutdown command:")
        print("Error:", e)

def list_friends():
    try:
        port = int(input("What is the server's port? "))
    except ValueError:
        print("Invalid port number!")
        return
    try:
        response = requests.post(f"http://localhost:{port}/", json=request("list_friends"))
        parsed = parse(response.json())
        print(parsed.result)
    except Exception as e:
        print("----- Error sending list_friends command:")
        print("Error:", e)

def online():
    try:
        port = int(input("What is the server's port? "))
    except ValueError:
        print("Invalid port number!")
        return
    server_number = input("Enter server number: ")
    try:
        response = requests.post(f"http://localhost:{port}/", json=request("online", params={"x": server_number}))
        parsed = parse(response.json())
        print(parsed.result)
    except Exception as e:
        print("----- Error sending online command:")
        print("Error:", e)

def offline():
    try:
        port = int(input("What is the server's port? "))
    except ValueError:
        print("Invalid port number!")
        return
    server_number = input("Enter server number: ")
    try:
        response = requests.post(f"http://localhost:{port}/", json=request("offline", params={"x": server_number}))
        parsed = parse(response.json())
        print(parsed.result)
    except Exception as e:
        print("----- Error sending offline command:")
        print("Error:", e)

def heartbeat():
    try:
        port = int(input("What is the server's port? "))
    except ValueError:
        print("Invalid port number!")
        return
    try:
        response = requests.post(f"http://localhost:{port}/", json=request("heartbeat"))
        parsed = parse(response.json())
        print(parsed.result)
    except Exception as e:
        print("----- Error sending heartbeat command:")
        print("Error:", e)

def pass_message():
    try:
        port = int(input("What is the server's port? "))
    except ValueError:
        print("Invalid port number!")
        return
    target = input("Enter server number: ")
    msg = input("Enter message to pass: ")
    try:
        response = requests.post(f"http://localhost:{port}/", json=request("pass_msg", params={"msg": msg, "x": target}))
        parsed = parse(response.json())
        print(parsed.result)
    except Exception as e:
        print("----- Error sending pass_msg command:")
        print("Error:", e)

def main():
    print("Welcome!")
    
    while True:
        print("\nPlease type a menu option:")
        print("1.  Make folder")
        print("2.  Delete folder")
        print("3.  Who are you?")
        print("4.  Get version")
        print("5.  Ping")
        print("6.  Search file")
        print("7.  Startup")
        print("8.  Shutdown")
        print("9.  List friends")
        print("10. Online")
        print("11. Offline")
        print("12. Heartbeat")
        print("13. Pass message")
        print("14. Quit")
        
        try:
            option = int(input("Option: "))
        except ValueError:
            print("Invalid option, please enter a number.")
            continue

        if option == 1:
            make_folder()
        elif option == 2:
            delete_folder()
        elif option == 3:
            whoareyou()
        elif option == 4:
            get_version()
        elif option == 5:
            ping()
        elif option == 6:
            search_file()
        elif option == 7:
            startup()
        elif option == 8:
            shutdown()
        elif option == 9:
            list_friends()
        elif option == 10:
            online()
        elif option == 11:
            offline()
        elif option == 12:
            heartbeat()
        elif option == 13:
            pass_message()
        elif option == 14:
            print("Goodbye!")
            break
        else:
            print("Invalid option. Please try again.")

if __name__ == "__main__":
    main()
