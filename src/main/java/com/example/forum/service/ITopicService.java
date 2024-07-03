package com.example.forum.service;

import com.example.forum.model.Topic;

import java.util.List;

public interface ITopicService {
    List<Topic> getAllTopics();
    Topic getTopicById(Long id);
    Topic saveTopic(Topic topic);
    Topic updateTopic(Topic topic);
    void deleteTopic(Long id);
}
