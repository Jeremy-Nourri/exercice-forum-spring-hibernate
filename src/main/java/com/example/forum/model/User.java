package com.example.forum.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @OneToMany(mappedBy = "authorOfTopic")
    private List<Topic> topics;

    @OneToMany(mappedBy = "authorOfMessage")
    private List<Message> messages;


}
