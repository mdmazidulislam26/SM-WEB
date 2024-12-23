package com.mazid.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;



// @Entity annotation indicates that this class represents a database entity.
@Entity
// Lombok annotations to automatically generate constructors, getters, setters, toString, hashCode, and equals methods.
@Data
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor // Generates a constructor with all fields
public class Message {

    @Id// @Id annotation marks the primary key of the entity.
    // @GeneratedValue strategy indicates that the id field will be auto-generated by the database.
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String content;// The content of the message, typically the text sent by the user.

    private String image;// The image associated with the message, which can be a URL or file path.
    // @ManyToOne indicates that each message is associated with one user.
    // Many messages can be associated with a single user.
    @ManyToOne
    private User user;
    // @JsonIgnore annotation prevents the `chat` field from being serialized into JSON.
    // This is typically done to avoid circular references or unnecessary data being sent to the client.
    @JsonIgnore
    // @ManyToOne indicates that each message is associated with one chat.
    // Many messages can be part of a single chat.
    @ManyToOne
    private Chat chat;
    // Timestamp indicating when the message was sent.
    private LocalDateTime timestamp;


}
