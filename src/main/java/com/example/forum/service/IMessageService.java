package com.example.forum.service;

import com.example.forum.model.Message;

public interface IMessageService {
    Message saveMessage(Message message, Long topicId);
}
