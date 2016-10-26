import requests
import json

# Azure - DocumentDB ###################################################################################################

## Send Document to DocumentDB
### Sample JSON in a different format than the standardized database format.
observationJSON = {
  "type": "observation",
  "observationID": "24812",
  "unitID": "4",
  "deviceID": "33",
  "patientID": "1053836",
  "date": "21/07/2016",
  "measurements": [
    {
      "measurementID": "23",
      "controlWound": "",
      "location": "ankle",
      "side": "left",
      "pushScore": "3",
      "image": "1053836-processed.jpg"
    }
  ]
}
### Send post request to the URL documentDBSend channel is listening to.
documentDBRequest = requests.post('http://localhost:7444/', json=observationJSON)

## Get Document from DocumentDB
getDocument = {"collectionID":"dataCollection",
               "fieldName":"observationID",
               "ID":"24812"}

### Send get request to the URL documentDBGet channel is listening to
response = requests.get('http://localhost:7333/', json=getDocument).json()
document = json.loads(response['document'])
