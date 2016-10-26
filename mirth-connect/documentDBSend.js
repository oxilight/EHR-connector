/* 
Incoming JSON mapping to variables in transformer:

msg['observationID'] ->  observationID
msg['unitID'] -> unitID
msg['deviceID'] -> deviceID
msg['patientID'] -> patientID
msg['measurements'] -> measurements
msg['date'] -> date

Incoming source filter:

field: msg['patientID']
rule: Accept message if "patientID" exists
*/

// Date format transformation
var rawDate = msg['date'].toString();

var formattedDate = rawDate.substring(6,10) + '-' + rawDate.substring(3,5) + '-' + rawDate.substring(0,2);
channelMap.put('date', validate(formattedDate, '',new Array()));


// Destination JavaScript Writer:
var obj = new Packages.com.azureHelper.documentDBHelper();
var URI = "documentDB-URI";
var MASTER_KEY = "key";
var DATABASE_ID = "database-ID";
var COLLECTION_ID = "collection-ID";
var completeJSON = {
	"type": "observation",
	"observationID": $('observationID'),
	"unitId": $('unitID'),
	"deviceID": $('deviceID'),
	"patientID": $('patientID'),
	"date": $('date'),
	"BPS": "",
	"BPD": "",
	"HR": "",
	"isRequested": "False",
	"measurements": $('measurements')
};
obj.sendDocument(HOST, MASTER_KEY, DATABASE_ID, COLLECTION_ID,JSON.stringify(completeJSON));