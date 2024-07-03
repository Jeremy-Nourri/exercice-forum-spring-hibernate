package com.example.forum.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String title;

    private LocalDateTime dateOfTopic;

    @ManyToOne
    @JoinColumn(name = "author_of_topic_id")
    private User authorOfTopic;

    @OneToMany(mappedBy = "topic")
    private List<Message> messages;

}
