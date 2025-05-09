import unittest, requests

from jsonrpcclient import request, parse, Ok


class MyTests(unittest.TestCase):
    #Test 1: Test the ping function
    def test_ping(self): # 4 spaces across
        response = requests.post("http://localhost:5001/", json=request("ping")) # 8
        parsed = parse(response.json()) #8
        print("result from server: " + parsed.result) # 8
        # run the test # 8
        self.assertEqual(parsed.result, 'pong') #
    
    #Test 2: Test the make_folder function
    def test_make_folder(self):
        response = requests.post("http://localhost:5001/", json=request("make_folder", {"foldername": "hello"}))
        parsed = parse(response.json())
        print("result from server: " + parsed.result)
        self.assertEqual(parsed.result, 'Folder hello made.')
    
    #Test 3: Test the delete_folder function
    def test_delete_folder(self):
        response = requests.post("http://localhost:5001/", json=request("delete_folder", {"foldername": "test"}))
        parsed = parse(response.json())
        print("result from server: " + parsed.result)
        self.assertEqual(parsed.result, 'Folder test deleted.')

    #Test 4: Test the whoareyou function
    def test_whoareyou(self):
        response = requests.post("http://localhost:5001/", json=request("whoareyou"))
        parsed = parse(response.json())
        print("result from server: " + parsed.result)
        self.assertEqual(parsed.result, 'Server 1 running on port 5001')

    #Test 5: Test the get_version function
    def test_get_version(self):
        response = requests.post("http://localhost:5001/", json=request("get_version"))
        parsed = parse(response.json())
        print("result from server: " + parsed.result)
        self.assertEqual(parsed.result, '3.13.2')
    
    #Test 6: Test the get_version function
    def test_search(self):
        response = requests.post("http://localhost:5001/", json=request("search", {"filename": "s.py"}))
        parsed = parse(response.json())
        print("result from server: " + parsed.result)
        self.assertEqual(parsed.result, "File 's.py' found.")
    
    #Test 7: Test the startup function
    def test_startup(self):
        response = requests.post("http://localhost:5001/", json=request("startup", {"x": "2"}))
        parsed = parse(response.json())
        print("result from server: " + parsed.result)
        self.assertEqual(parsed.result, "Started server 2 on port 5002.")
    
    #Test 8: Test the shutdown function
    def test_shutdown(self):
        response = requests.post("http://localhost:5001/", json=request("shutdown", {"x": "2"}))
        parsed = parse(response.json())
        print("result from server: " + parsed.result)
        self.assertEqual(parsed.result, "Shutdown command sent to server 2 on port 5002.")
    
    #Test 9: Test the list_friend function
    def test_list_friends(self):
        response = requests.post("http://localhost:5001/", json=request("list_friends"))
        parsed = parse(response.json())
        print("result from server: " + parsed.result)
        self.assertEqual(parsed.result, 'Friends: 2')
    
    #Test 10: Test the shutdown function
    def test_online(self):
        response = requests.post("http://localhost:5001/", json=request("online", {"x": "2"}))
        parsed = parse(response.json())
        print("result from server: " + parsed.result)
        self.assertEqual(parsed.result, "Server 2 is now online.")
    
    #Test 11: Test the shutdown function
    def test_offline(self):
        response = requests.post("http://localhost:5001/", json=request("offline", {"x": "2"}))
        parsed = parse(response.json())
        print("result from server: " + parsed.result)
        self.assertEqual(parsed.result, "Server 2 is now offline.")
    
    #Test 12: Test the heartbeat function
    def test_heartbeat(self):
        response = requests.post("http://localhost:5001/", json=request("heartbeat"))
        parsed = parse(response.json())
        print("result from server: " + parsed.result)
        self.assertEqual(parsed.result, "Heartbeat results: {'2': 'pong'}")

    # Test 13: Test the pass msg function
    def test_pass_msg(self):
    # Ensuring server 2 is online before sending a message
         requests.post("http://localhost:5001/", json=request("online", {"x": "2"}))
         response = requests.post("http://localhost:5001/", json=request("pass_msg", {"msg": "hello", "x": "2"}))
         parsed = parse(response.json())
         print("result from server: " + parsed.result)
         self.assertEqual(parsed.result, "Message forwarded towards server 2.")
    
    
if __name__ == '__main__':

    unittest.main()