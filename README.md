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

The Input class simplifies console input in Java programs, providing functions for reading characters, integers with defined bounds, strings with or without spaces, and email addresses. It ensures input validation, manages empty or lengthy input, and prompts users for appropriate responses.  

## 'Database.java'

The DataBase class serves as a data manager, utilizing a HashMap to link usernames to user objects. It enables adding users, verifying account credentials, and accessing user details. Additionally, it offers functionality for displaying user information and viewing profile pictures, enhancing the user experience.  

## 'MessageDataBase.java'

The MessageDataBase class functions as a storage system for messages in the messenger application. It utilizes an ArrayList to store messages and a HashMap to index messages by receiver usernames, ensuring efficient retrieval.  

## 'MessageService.java'

The MessageService class facilitates the exchange of messages among users in the messenger application. It initializes a message object, configuring its attributes like sender, receiver, content, and timestamp, before adding the message to both the sender's and receiver's message repositories.  

## 'UserFriendDataBase.java

In Java, the UserFriendDataBase class implements the UserFriendDataBaseInterface, overseeing user friendships. It manages friend requests, blocking/unblocking friends, and displaying friend lists. Utilizing HashMaps, it effectively stores and manages friend, request, and blocked friend information in the application. 

## 'UserMessageDataBase.java

The UserMessageDataBase class oversees message-related features for users in the messenger application. It tracks sent and received messages through separate array lists and hash maps.  

## 'UserService'

The UserService class comprises multiple methods aimed at overseeing and verifying distinct aspects of user interactions within a messenger application.  


	
