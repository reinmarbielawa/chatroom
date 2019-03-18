# chatroom

Simple chatroom application in Java, using threads and sockets. Client-server architecture.

## Status
In active development.

## Design
Client should be very simple, mainly communicating with server.
Server should handle broadcast messages to all users and most of functionality.

### Client
* opens a connection to server using given hostname and port
* in a main thread it waits for user input and sends it to a server
* in an additional thread it reads server incoming messages and prints them to console
* additional control sequence "\q" allows to close whole application 
* all threads should be correctly closed on application close

### Server
* one server instance represents a single chatroom
* in a main thread it waits for new client connections on a given port
* there is a single queue storing messages to be send, it needs to be thread-safe
* every new client is assigned a new dedicated thread reading its messages and adding them to the queue
* in an additional thread messages are taken from the queue and send to clients
* to be able to broadcast messages, server needs to keep a list of connected clients
* if a client disconnects or a connection was lost, client should be deleted from the connected list
* while accepting new client connection, before adding it to connected list, an alphanumeric login needs to be obtained
(3 attempts and disconnect)
* there should be a limit for number of connected clients, if the limit is exceeded, new users should be notified and rejected
* all threads should be correctly closed on application close
