package com.example.forum.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
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
