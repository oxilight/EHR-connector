/* 
Incoming JSON mapping to variables in transformer:

msg['container'] -> container
msg['imageName'] -> imagename
msg['imageBase64'] -> imagebase64

*/


// Destination Destination JavaScript Writer:
var obj = new Packages.com.azureHelper.blobStorageHelper();
var storageConnectionString =
    		    "DefaultEndpointsProtocol=https;" +
    		    "AccountName=storage-account-name;" +
    		    "AccountKey=key";

obj.uploadBlob(storageConnectionString, $('container'),$('imagename'),$('imagebase64'))
