package com.example.forum.service;

import com.example.forum.model.Topic;
import com.example.forum.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService implements ITopicService {

    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    @Override
    public Topic getTopicById(Long id) {
        return topicRepository.findById(id).orElse(null);
    }

    @Override
    public Topic saveTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    @Override
    public Topic updateTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    @Override
    public void deleteTopic(Long id) {
        topicRepository.findById(id).ifPresent(topicRepository::delete);
    }
}
