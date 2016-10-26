/* 
Incoming JSON mapping to variables in transformer:

msg['collectionID'] ->  collectionID
msg['fieldName'] -> fieldName
msg['ID'] -> ID

*/


// Destination JavaScript Writer:
var obj = new Packages.com.azureHelper.documentDBHelper();
var URI = "documentDB-URI";
var MASTER_KEY = "key";
var DATABASE_ID = "database-ID";
var response =  obj.getDocumentById(URI, MASTER_KEY, DATABASE_ID, $('collectionID'),$('fieldName'),$('ID'));

return JSON.stringify({"document": response});