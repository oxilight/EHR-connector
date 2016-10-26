# EHR
Connector between Back end and external systems (e.g. external EHRs)

### Importing resources in Mirth Connect
1. Any methods written in Java must be packed into a JAR with all dependencies included
2. Place the JAR file in the custom-lib folder within Mirth Connect directory
3. In mirth connect, navigate to **Settings -> Resources -> Reload Resource**
4. Ensure that the JAR file appears in loaded libraries

### blobStorageSend Channel
1. Configure incoming and outgoing source to JSON
2. Add and configure listener as source
3. Map incoming request to variables according to documentation in */mirth-connect/blobStorageSend.js*
4. Pick JavaScript Writer as destination and copy JavaScript code in */mirth-connect/blobStorageSend.js*

### blobStorageGet Channel
1. Configure incoming and outgoing source to JSON
2. Add and configure listener as source, and map response to destination output
3. Map incoming request to variables according to documentation in */mirth-connect/blobStorageGet.js*
4. Pick JavaScript Writer as destination and copy JavaScript code in */mirth-connect/blobStorageGet.js*

### documentDBSend Channel
1. Configure incoming and outgoing source to JSON
2. Add and configure listener as source
3. Add filter according to documentation in */mirth-connect/documentDBSend.js*
4. Map incoming request to variables according to documentation in */mirth-connect/documentDBSend.js*
5. Pick JavaScript Writer as destination and copy JavaScript code in **/mirth-connect/documentDBSend.js*

### documentDBGet Channel
1. Configure incoming and outgoing source to JSON
2. Add and configure listener as source, and map response to destination output
3. Map incoming request to variables according to documentation in */mirth-connect/documentDBSend.js*
4. Pick JavaScript Writer as destination and copy JavaScript code in **/mirth-connect/documentDBSend.js*
