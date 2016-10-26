package com.azureHelper;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.apache.commons.codec.binary.Base64;

import com.microsoft.azure.documentdb.DocumentClientException;
import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.blob.*;

public class blobStorageHelper {
	public static void uploadBlob(String storageConnectionString, String containerName, String imageName, String imageBase64 ){
		byte[] data = Base64.decodeBase64(imageBase64);
		try
		{
		    // Retrieve storage account from connection-string.
		    CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);

		    // Create the blob client.
		    CloudBlobClient blobClient = storageAccount.createCloudBlobClient();

		    // Retrieve reference to a previously created container.
		    CloudBlobContainer container = blobClient.getContainerReference(containerName);
		    // Create the container if it does not exist.
		    container.createIfNotExists();

		    // Create or overwrite the "myimage.jpg" blob with contents from a local file.
		    CloudBlockBlob blob = container.getBlockBlobReference(String.format("%s.jpeg", imageName));
		    blob.uploadFromByteArray(data, 0, data.length);
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
 	}
	
	public static String getBlob(String storageConnectionString, String containerName, String blobName){
		String imageBase64 = null;
		try
		{
		    // Retrieve storage account from connection-string.
		    CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);

		    // Create the blob client.
		    CloudBlobClient blobClient = storageAccount.createCloudBlobClient();

		   // Retrieve reference to a previously created container.
		    CloudBlobContainer container = blobClient.getContainerReference(containerName);
		    CloudBlob blob = container.getBlockBlobReference(blobName);
		    blob.downloadAttributes();
		    long fileByteLength = blob.getProperties().getLength();
		    byte [] data = new byte[(int) fileByteLength];
		    blob.downloadToByteArray(data, 0);
		    imageBase64 = Base64.encodeBase64String(data);
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
		return imageBase64;
 	}
}
