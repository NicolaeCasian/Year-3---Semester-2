# client.py
from jsonrpcclient import request, parse
import requests

def send_command(port, method_name, params=None):
    if params is None:
        params = {}
    try:
        response = requests.post(f"http://localhost:{port}/", json=request(method_name, params=params))
        parsed = parse(response.json())
        print(parsed.result)
    except Exception as e:
        print(f"Error sending {method_name} command: {e}")

def main():
    port = int(input("Enter the server port to connect to: "))
    
    print("\nPlease type a menu option:")
    print("1.make_folder <foldername>")
    print("2. delete_folder <foldername>")
    print("3. whoareyou")
    print("4. get_version")
    print("5. ping")
    print("6. search <filename>")
    print("7. startup <server_number>")
    print("8. shutdown <server_number>")
    print("9. list_friends")
    print("10. online <server_number>")
    print("11. offline <server_number>")
    print("12. heartbeat")
    print("13. pass <msg> <server_number>")
    print("14. quit")
    
    while True:
        command = input("\nEnter command: ").strip()
        if command == "quit":
            break
        parts = command.split()
        if not parts:
            continue
        cmd = parts[0]
        if cmd == "make_folder" and len(parts) == 2:
            send_command(port, "make_folder", {"foldername": parts[1]})
        elif cmd == "delete_folder" and len(parts) == 2:
            send_command(port, "delete_folder", {"foldername": parts[1]})
        elif cmd == "whoareyou":
            send_command(port, "whoareyou")
        elif cmd == "get_version":
            send_command(port, "get_version")
        elif cmd == "ping":
            send_command(port, "ping")
        elif cmd == "search" and len(parts) == 2:
            send_command(port, "search", {"filename": parts[1]})
        elif cmd == "startup" and len(parts) == 2:
            send_command(port, "startup", {"x": parts[1]})
        elif cmd == "shutdown" and len(parts) == 2:
            send_command(port, "shutdown", {"x": parts[1]})
        elif cmd == "list_friends":
            send_command(port, "list_friends")
        elif cmd == "online" and len(parts) == 2:
            send_command(port, "online", {"x": parts[1]})
        elif cmd == "offline" and len(parts) == 2:
            send_command(port, "offline", {"x": parts[1]})
        elif cmd == "heartbeat":
            send_command(port, "heartbeat")
        elif cmd == "pass" and len(parts) >= 3:
            # The message is everything except the first word and the last parameter.
            target = parts[-1]
            msg = " ".join(parts[1:-1])
            send_command(port, "pass_msg", {"msg": msg, "x": target})
        else:
            print("Unknown or malformed command.")

if __name__ == "__main__":
    main()
