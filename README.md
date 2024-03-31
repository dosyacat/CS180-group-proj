#Name

Messenger app, “ChatEase”

#Description

Our messenger app offers a unique experience by enabling users to exchange individual messages seamlessly. What sets our app apart is its feature that displays a list of users interested in chatting, allowing you to initiate conversations by searching for their username or email. Additionally, users can create personalized bios, providing an opportunity to share insights about themselves or anything else they desire.

#Usage

Right now, our app is still under development, therefore, in order to start it, you would have to download the code and go to the class named “Client.java” and run it. 

After running it, you would have to first sign up and therefore, create an account. When you have successfully created your account, you have to sign it using your credentials. If you’re signed in, you are able to see other users who have created an account and send them messages 


Client.java

The Client class represents the main entry point for the messenger application. It provides functionality for user authentication, sign up, and navigation through various menus.

User.java

The User class represents a user of the messenger application. It encapsulates user information such as username, password, email, and bio, and provides methods for managing friends, messages, and profile information.

Message.java

The Message class represents a message exchanged between users within the messenger application. It encapsulates information such as the sender, receiver, content, and timestamp of the message.

Input.java

 The Input class provides methods for reading user input from the console. It includes functionalities to read characters, integers within a range, strings with specified length limits, email addresses, and user selections.

Database.java

The DataBase class serves as a storage mechanism for user data within the messenger application. It utilizes a HashMap to efficiently store and retrieve user objects based on their usernames.

MessageDataBase.java

The MessageDataBase class serves as a storage mechanism for messages within the messenger application. It maintains an ArrayList to store messages and a HashMap to index messages by receiver usernames for efficient retrieval.

MessageService.java

The MessageService class provides functionality for sending messages between users within the messenger application. It creates a message object, sets its attributes such as sender, receiver, content, and time, and then adds the message to both the sender's and receiver's message lists.

UserFriendDataBase.java

The UserFriendDataBase class manages the friend-related functionalities for users within the messenger application. It keeps track of friends, friend requests, and blocked friends using separate hash maps. 

UserMessageDataBase.java

The UserMessageDataBase class manages the message-related functionalities for users within the messenger application. It keeps track of sent and received messages using separate array lists and hash maps. 

UserService

The UserService class encapsulates several methods designed to manage and check various aspects of user interactions within a messenger application.
	
