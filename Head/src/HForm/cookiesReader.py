import requests

def process_post_request(url, data):
    response = requests.post(url, data=data)
    
    if response.status_code == 200:
        response_data = response.json()  # Assuming the response is in JSON format
        # Process the response data here
        print(response_data)
    else:
        print(f"Error: {response.status_code} - {response.text}")

# Example usage:
url = 'https://anhsangsoiduong.com.vn/get-list-question'  # Replace with your API endpoint URL
data = {
    'param1': 'value1',
    'param2': 'value2',
    'param3': 'value3'
}

process_post_request(url, data)
