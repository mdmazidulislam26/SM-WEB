##Introduction to Java Spring Boot

---

This repository contains the source code for a **Full-Stack Social Media Web Application** 
built using **Java Spring Boot** for the backend. 
The project is designed to provide social media features, including user authentication, posts, comments, reels, messaging, and more.

---

## Features
- **User Authentication**
  - Sign up and login with JWT-based authentication.
  - Secure password encryption with BCrypt.
- **Posts Management**
  - Create, update, delete, and view posts.
  - Like and save posts.
- **Comments**
  - Add comments to posts.
  - Like comments.
- **Reels and Stories**
  - Add, view, and manage reels and stories.
- **Messaging and Chat**
  - Send and receive messages in real-time.
  - Manage chatrooms.
- **Follow System**
  - Follow and unfollow other users.

---

## Technologies Used

### Backend
- **Java 17**
- **Spring Boot 3.3.6**
  - Spring Security
  - Spring Data JPA
  - Spring Web
  - Spring Boot DevTools
- **JWT** for token-based authentication.
- **Lombok** for boilerplate code reduction.
- **MySQL** as the relational database.

### Dependencies
- `spring-boot-starter-web`
- `spring-boot-starter-security`
- `spring-boot-starter-data-jpa`
- `mysql-connector-j`
- `jjwt-api`, `jjwt-impl`, `jjwt-jackson`
- `lombok`

---

## Installation and Setup

### Prerequisites
1. **Java Development Kit (JDK)** version 17 or higher.
2. **MySQL Server** installed and running.
3. **Maven** installed.

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/mazidu-social-media.git
   cd mazidu-social-media
   ```

2. Set up the MySQL database:
   - Create a database named `social_media`.
   - Update the `application.properties` file with your MySQL credentials:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/social_media
     spring.datasource.username=YOUR_USERNAME
     spring.datasource.password=YOUR_PASSWORD
     spring.jpa.hibernate.ddl-auto=update
     ```

3. Build and run the application:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. The application will run at `http://localhost:8080`.

---

## API Endpoints

### Authentication
- **POST** `/auth/signup` - Register a new user.
- **POST** `/auth/signin` - Login and receive a JWT token.

### User
- **GET** `/api/users` - Fetch all users.
- **GET** `/api/users/{userId}` - Fetch a user by ID.
- **PUT** `/api/users` - Update user details.
- **PUT** `/api/users/follow/{userId}` - Follow a user.

### Posts
- **POST** `/api/posts` - Create a new post.
- **GET** `/api/posts` - Get all posts.
- **GET** `/api/posts/{postId}` - Get a specific post by ID.
- **DELETE** `/api/posts/{postId}` - Delete a post.
- **PUT** `/api/posts/like/{postId}` - Like a post.

### Comments
- **POST** `/api/comments/post/{postId}` - Add a comment to a post.
- **PUT** `/api/comments/like/{commentId}` - Like a comment.

### Chats
- **POST** `/api/chats` - Create a new chat.
- **GET** `/api/chats` - Get all chats for the logged-in user.

### Messages
- **POST** `/api/messages/chat/{chatId}` - Send a message in a chat.
- **GET** `/api/messages/chat/{chatId}` - Get all messages in a chat.

---

## Project Structure
```plaintext
Mazidu-Social-Media-Project
├── pom.xml
├── src
│   └── main
│       ├── java
│       │   └── com.mazid
│       │       ├── config
│       │       ├── controller
│       │       ├── exceptions
│       │       ├── models
│       │       ├── repository
│       │       ├── request
│       │       ├── response
│       │       └── service
│       └── resources
│           ├── application.properties
│           └── static
└── README.md
```
