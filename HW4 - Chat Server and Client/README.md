# Instructions
 1. The chat server will accept connections from clients on port 5190 and relay anything sent to the server to all clients (including the one who originally sent the message), with the sender's name prepended to the message. The server should not produce any output on the screen. Clients, upon connecting, have to announce their username, which should NOT be forwarded to the other clients. The server should not introduce itself, to the client!
    
 2. Design the chat client for the above server. The client will have a top box which is displays all the messages from the clients, and a bottom input which will allow text to be written to the server. Also include a "SEND" button. Upon starting the client, you should ask which server to connect to and what username to use. When the client connects to the server it sends the username as the first message. After the first message is sent, anything received from the server is displayed in the top box and anything written in the bottom box (after the send button is pushed) is sent to the server.

 # Demo
![HW4 Demo](https://github.com/KristenTse/Java/blob/main/HW4%20-%20Chat%20Server%20and%20Client/Demo4.gif)
