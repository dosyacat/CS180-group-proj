## Name

Messenger app, “ChatEase”

## Description

Our messaging platform, “ChatEase”, offers a unique user journey, ensuring smooth communication through the effortless exchange of individual messages. What sets our application apart is its innovative feature that curates a list of users eager to engage in conversations, empowering you to initiate dialogues by searching for their username or email. Additionally, users can personalize their profiles with bios, providing a platform to share personal anecdotes, interests, or any other relevant information. This feature enhances user interaction, fostering meaningful connections and enriching the overall messaging experience.   

### Usage

Currently, our application is in the development phase. To begin using it, you will need to download the code and navigate to the "Client.java" class to execute it. Upon execution, you will be prompted to sign up and create an account. Once your account is created, you'll need to sign in using your credentials. After signing in successfully, you will gain access to view other users who also have registered accounts. You can then proceed to send messages to these users. This process ensures a seamless user experience as you navigate through the app's functionalities during its development stage.  


## 'Client.java'

The Client class represents the main entry point for the messenger application. It provides functionality for user authentication, sign up, and navigation through various menus.

## 'User.java

The User class represents a user of the messenger application. It encapsulates user information such as username, password, email, and bio, and provides methods for managing friends, messages, and profile information. 

## 'Message.java'

The Message class serves as a representation of messages within a messaging system. It utilizes Serializable for object serialization and SuperMessage for extra message-related features. This class encapsulates sender, receiver, content, and message time properties, offering methods for retrieval and modification. Additionally, its toString method generates a string representation of the message. 

## 'Input.java'

 The Input class provides methods for reading user input from the console. It includes functionalities to read characters, integers within a range, strings with specified length limits, email addresses, and user selections.

## 'Database.java'

The DataBase class serves as a storage mechanism for user data within the messenger application. It utilizes a HashMap to efficiently store and retrieve user objects based on their usernames.

## 'MessageDataBase.java'

The MessageDataBase class serves as a storage mechanism for messages within the messenger application. It maintains an ArrayList to store messages and a HashMap to index messages by receiver usernames for efficient retrieval.

## 'MessageService.java'

The MessageService class provides functionality for sending messages between users within the messenger application. It creates a message object, sets its attributes such as sender, receiver, content, and time, and then adds the message to both the sender's and receiver's message lists.

## 'UserFriendDataBase.java'

The UserFriendDataBase class manages the friend-related functionalities for users within the messenger application. It keeps track of friends, friend requests, and blocked friends using separate hash maps. 

## 'UserMessageDataBase.java'

The UserMessageDataBase class manages the message-related functionalities for users within the messenger application. It keeps track of sent and received messages using separate array lists and hash maps. 

## 'UserService'

The UserService class encapsulates several methods designed to manage and check various aspects of user interactions within a messenger application.
	
