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
        self.assertEqual(parsed.result, 'Folder '"test"' deleted.')

    
if __name__ == '__main__':

    unittest.main()