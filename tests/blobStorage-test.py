import requests
import json
import base64

# Azure- BlobStorage #################################################################################################
## Sending Image

### Encode image into base64 to send
encoded = str(base64.b64encode(open("image.jpg", "rb").read())).split('\'')[1]
JSONtoSend = {"container": "myimages",
        "imageName": "imageTest",
        "imageBase64": encoded}

### Send post request to the URL blobStorageSend channel is listening to
storageRequest = requests.post('http://localhost:8444/', json=JSONtoSend)

## Getting Image
### Create JSON to request
JSONToGet = {"container": "myimages",
        "blobName": "imageTest.jpeg"}

### Send post request to the URL blobStorageGet channel is listening to
response = requests.get('http://localhost:8333/', json=JSONToGet).json()
encodedBase64 = response['base64']
file_ = open('image-name.jpg', 'wb')
file_.write(base64.b64decode(encodedBase64))
file_.close()

