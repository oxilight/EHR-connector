/* 
Incoming JSON mapping to variables in transformer:

msg['container'] -> container
msg['blobName'] -> blobName

*/


// Destination JavaScript Writer:
var obj = new Packages.com.azureHelper.blobStorageHelper();
var storageConnectionString =
    		    "DefaultEndpointsProtocol=https;" +
    		    "AccountName=storage-account-name;" +
    		    "AccountKey=key";

var image = obj.getBlob(storageConnectionString, $('container'),$('blobName'));
return JSON.stringify({"base64":image});
