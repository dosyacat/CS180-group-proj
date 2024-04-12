## Name

Messenger app, “ChatEase”

## Description

Our messaging platform, “ChatEase”, offers a unique user journey, ensuring smooth communication through the effortless exchange of individual messages. What sets our application apart is its innovative feature that curates a list of users eager to engage in conversations, empowering you to initiate dialogues by searching for their username or email. Additionally, users can personalize their profiles with bios, providing a platform to share personal anecdotes, interests, or any other relevant information. This feature enhances user interaction, fostering meaningful connections and enriching the overall messaging experience.   

### Usage

Currently, our application is in the development phase. To begin using it, you will need to download the code and navigate to the "View.java" class to execute it. Upon execution, you will be prompted to sign up and create an account. Once your account is created, you'll need to sign in using your credentials. After signing in successfully, you will gain access to view other users who also have registered accounts. You can then proceed to send messages to these users. This process ensures a seamless user experience as you navigate through the app's functionalities during its development stage.  

In Phase 2 of the project, we will implementing a feature to store the database so that users can resume their sessions if they terminate the program and restart it. To achieve this, we plan to store the data on the server side rather than using a file-based approach. Socket connections will be established to facilitate communication with clients. However, we are currently encountering an issue where the data from the previous session is lost upon restarting the program, i.e., "as of now upon restart the information is lost and is not saved in the database like one would desire it to." 


## 'View.java'

The View class serves as the primary interface for the messenger application, acting as the central entry point. It facilitates essential functionalities such as user authentication, sign-up processes, and navigation across different menus within the application's interface. This pivotal class orchestrates user interactions and ensures seamless navigation throughout the messaging platform's features and functionalities.

## 'User.java

The User class embodies an individual within the messenger application, encapsulating vital user details like their username, password, email, and bio. Additionally, it furnishes methods for overseeing various aspects of user engagement, including friend interactions, message management, and profile information updates. This class serves as the foundational structure for user entities, ensuring comprehensive management and interaction capabilities within the messaging platform. 

## 'Message.java'

The Message class serves as a representation of messages within a messaging system. It utilizes Serializable for object serialization and Interface.SuperMessage for extra message-related features. This class encapsulates sender, receiver, content, and message time properties, offering methods for retrieval and modification. Additionally, its toString method generates a string representation of the message. 

## 'Input.java'

The Input class simplifies console input in Java programs, providing functions for reading characters, integers with defined bounds, strings with or without spaces, and email addresses. It ensures input validation, manages empty or lengthy input, and prompts users for appropriate responses.  

## 'Database.java'

The DataBase class serves as a data manager, utilizing a HashMap to link usernames to user objects. It enables adding users, verifying account credentials, and accessing user details. Additionally, it offers functionality for displaying user information and viewing profile pictures, enhancing the user experience.  

## 'MessageDataBase.java'

The MessageDataBase class functions as a storage system for messages in the messenger application. It utilizes an ArrayList to store messages and a HashMap to index messages by receiver usernames, ensuring efficient retrieval.  

## 'MessageService.java'

The MessageService class facilitates the exchange of messages among users in the messenger application. It initializes a message object, configuring its attributes like sender, receiver, content, and timestamp, before adding the message to both the sender's and receiver's message repositories.  

## 'UserFriendDataBase.java

In Java, the UserFriendDataBase class implements the Interface.UserFriendDataBaseInterface, overseeing user friendships. It manages friend requests, blocking/unblocking friends, and displaying friend lists. Utilizing HashMaps, it effectively stores and manages friend, request, and blocked friend information in the application. 

## 'UserMessageDataBase.java

The UserMessageDataBase class oversees message-related features for users in the messenger application. It tracks sent and received messages through separate array lists and hash maps.  

## 'UserService'

The UserService class comprises multiple methods aimed at overseeing and verifying distinct aspects of user interactions within a messenger application.  

## Picture.java 

The Picture class facilitates image uploading and display in a graphical user interface. Using Java's ImageIO and Swing libraries, it enables users to upload and view images from their computer, with resizable options. This enhances Java applications, offering seamless image handling and integration within the interface.

## PictureMessage.java

PictureMessage class represents a serialized message with an image, implementing Interface.SuperMessage for message functionalities. It stores sender, receiver, image data, and time. Methods manage message details, including toString() for displaying the message with the image.  

## Interface.DataBaseInterface.java 

The Interface.DataBaseInterface defines methods for database management in Java. It covers adding users, checking credentials, displaying user info with profile pictures, and finding users by username. It's a blueprint for implementing database operations, ensuring standardized interaction with user data. Implementing classes customize these methods as needed. 

## Interface.InputInterface.java 

The Interface.InputInterface simplifies console input handling in Java applications by providing methods to read characters, integers within specified limits or ranges, strings with or without spaces, email addresses, and single-character selections ('Y' or 'N'). Implementing classes are required to define these methods, promoting consistent input processing and enhancing usability across the application. 

## Interface.MessageDataBaseInterface.java  

The Interface.MessageDataBaseInterface defines methods for handling message data in Java applications. It includes functions for adding messages, accessing and updating message collections stored as ArrayLists and HashMaps. This interface acts as a guide for implementing message-related database operations in Java programs. 

## Interface.MessageInterface

The Interface.MessageInterface extends Serializable and specifies methods for handling message data. It provides functionality to retrieve and update sender and receiver details, message content, and timestamp. This interface guarantees that message objects can be serialized to preserve data integrity during storage or transmission. 

## Interface.MessageServiceInterface.java   

The Interface.MessageServiceInterface defines methods for sending text and picture messages in Java applications. It provides functionality to send text messages with designated sender, receiver, and content, along with sending picture messages between users. 

## Interface.PictureInterface.java  

The Interface.PictureInterface manages picture-related tasks in Java apps, enabling users to upload images from their computers and display them. These methods serve as a framework for implementing picture functionalities while allowing adaptability in application development. 

## Interface.PictureMessageInterface.java 

The Interface.PictureMessageInterface handles picture messages in Java, allowing manipulation of sender, receiver, image data, and message time. It also facilitates conversion to string format and image display. 

## Interface.UserInterface.java 

The Interface.UserInterface, which extends Serializable, outlines methods to handle user data in Java applications. It covers functions such as managing friends, message privacy settings, handling messages, and accessing user profile details. Serving as a blueprint, it ensures uniformity and adaptability in application development. 

## Interface.UserFriendDataBaseInterface.java 

The Interface.UserFriendDataBaseInterface in Java outlines operations for handling user friendships. It enables adding, removing, and manipulating friends, accepting or declining friend requests, and blocking or unblocking friends. This interface supports retrieval of data about blocked friends, pending requests, and existing connections, facilitating effective user interaction in applications. 

## Interface.UserMessageDataBaseInterface.java 

The Java Interface.UserMessageDataBaseInterface manages user messages, enabling addition, display, and deletion of sent and received messages, alongside message count retrieval. It also offers functionality for accessing and modifying message arrays and hash maps, streamlining message management in applications. 

## Interface.UserServiceInterface.java 

The Interface.UserServiceInterface provides essential user-related functionalities in Java apps, including security checks, friendship, message handling, and privacy settings. These methods enable efficient user interaction and security management within the application environment. 

 

	
