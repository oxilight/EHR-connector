package com.azureHelper;

import java.util.List;

import com.microsoft.azure.documentdb.ConnectionPolicy;
import com.microsoft.azure.documentdb.ConsistencyLevel;
import com.microsoft.azure.documentdb.Database;
import com.microsoft.azure.documentdb.Document;
import com.microsoft.azure.documentdb.DocumentClient;
import com.microsoft.azure.documentdb.DocumentClientException;
import com.microsoft.azure.documentdb.DocumentCollection;

public class documentDBHelper
{
	private static DocumentClient documentClient;

	public static DocumentClient getDocumentClient(String HOST,String MASTER_KEY) {
	    if (documentClient == null) {
	        documentClient = new DocumentClient(HOST, MASTER_KEY,
	                ConnectionPolicy.GetDefault(), ConsistencyLevel.Session);
	    }
	    return documentClient;
	}
	
	 private static Database getDatabase(String DATABASE_ID){
		// Get the database if it exists
		 Database database = null;
         List<Database> databaseList = documentClient
                 .queryDatabases(
                         "SELECT * FROM root r WHERE r.id='" + DATABASE_ID
                                 + "'", null).getQueryIterable().toList();

         if (databaseList.size() > 0) {
             // Cache the database object so we won't have to query for it again.
             database = databaseList.get(0);
         } else {
             // Create the database if it doesn't exist.
             try {
                 Database databaseDefinition = new Database();
                 databaseDefinition.setId(DATABASE_ID);

                 database = documentClient.createDatabase(
                         databaseDefinition, null).getResource();
             } catch (DocumentClientException e) {
                 e.printStackTrace();
             }
         }
         return database;
	 }
	 
	 private static DocumentCollection getCollection(Database database,String COLLECTION_ID){
		 DocumentCollection collection = null;
		// Get the collection if it exists.
         List<DocumentCollection> collectionList = documentClient
                 .queryCollections(
                         database.getSelfLink(),
                         "SELECT * FROM root r WHERE r.id='" + COLLECTION_ID
                                 + "'", null).getQueryIterable().toList();

         if (collectionList.size() > 0) {
             // Cache the collection object so we won't have to query for it again
             collection = collectionList.get(0);
         } else {
             // Create the collection if it doesn't exist.
             try {
                 DocumentCollection collectionDefinition = new DocumentCollection();
                 collectionDefinition.setId(COLLECTION_ID);

                 collection = documentClient.createCollection(
                 		database.getSelfLink(),
                         collectionDefinition, null).getResource();
             } catch (DocumentClientException e) {
                 // TODO: Something has gone terribly wrong - the app wasn't
                 // able to query or create the collection.
                 // Verify your connection, endpoint, and key.
                 e.printStackTrace();
             }
         }
         return collection;
	 }
	 
	 public static void sendDocument(String HOST, String MASTER_KEY, String DATABASE_ID, String COLLECTION_ID, String JSON) {
			// The DocumentDB Client
	        documentClient = getDocumentClient(HOST,MASTER_KEY);
	        Database database =  getDatabase(DATABASE_ID);
	        DocumentCollection collection = getCollection(database, COLLECTION_ID);
	        Document documentToSend = new Document(JSON);
	        try {
	            // Persist the document using the DocumentClient.
	            documentToSend = documentClient.createDocument(
	            		collection.getSelfLink(), documentToSend, null,
	                    false).getResource();
	        } catch (DocumentClientException e) {
	            e.printStackTrace();
	            return;
	        }
		}
		
		public static String getDocumentById(String HOST, String MASTER_KEY, String DATABASE_ID, String COLLECTION_ID, String fieldName, String ID) {
		    // Retrieve the document using the DocumentClient.
			// The DocumentDB Client
	        documentClient = getDocumentClient(HOST,MASTER_KEY);
	        Database database =  getDatabase(DATABASE_ID);
	        DocumentCollection collection = getCollection(database, COLLECTION_ID);
		    List<Document> documentList = documentClient
		            .queryDocuments(collection.getSelfLink(),
		                    "SELECT * FROM root r WHERE r."+ fieldName +"='" + ID + "'", null)
		            .getQueryIterable().toList();

		    if (documentList.size() > 0) {
		    	// Removing some default fields
		    	Document document = documentList.get(0);
		    	document.remove("_attachments");
		    	document.remove("_rid");
		    	document.remove("_self");
		    	document.remove("_etag");
		    	document.remove("_ts");
		    	String response = document.toString();
		    	
		    	return response;
		    } else {
		        return null;
		    }
		}
}
