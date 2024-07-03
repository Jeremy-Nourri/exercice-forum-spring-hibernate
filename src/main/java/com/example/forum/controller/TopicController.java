package com.example.forum.controller;

import com.example.forum.model.Message;
import com.example.forum.model.Topic;
import com.example.forum.model.User;
import com.example.forum.service.MessageService;
import com.example.forum.service.TopicService;
import com.example.forum.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class TopicController {

    private final TopicService topicService;
    private final UserService userService;
    private final MessageService messageService;

    @Autowired
    public TopicController(TopicService topicService, UserService userService, MessageService messageService) {
        this.topicService = topicService;
        this.userService = userService;
        this.messageService = messageService;
    }

    @GetMapping("/topics")
    public String displayTopics(Model model, HttpSession session){
        if (userService.verifyIfUserIsLogged()) {
            model.addAttribute("topics", topicService.getAllTopics());
            return "topics";
        }
        return "redirect:/signin";
    }

    @GetMapping("/topic/{id}")
    public String displayTopic(@PathVariable Long id, Model model){
        if (userService.verifyIfUserIsLogged()) {
            model.addAttribute("topic", (Topic) topicService.getTopicById(id));
            return "topic";
       }
        return "redirect:/signin";
    }

    @PostMapping("/topic/create")
    public String createTopic(@ModelAttribute Topic topic, Model model){
        if (userService.verifyIfUserIsLogged()) {
            topic.setAuthorOfTopic(userService.getLoggedUser());
            topic.setDateOfTopic(LocalDateTime.now());
            topicService.saveTopic(topic);
            model.addAttribute("topics", topicService.getAllTopics());
            return "topics";
        }
        return "redirect:/signin";
    }

    @PostMapping("/topic/{id}/message/create")
    public String createMessage(@PathVariable Long id, @ModelAttribute Message newMessage, Model model){
        if (userService.verifyIfUserIsLogged()) {
            Message message = new Message();
            message.setContent(newMessage.getContent());
            message.setDateOfMessage(LocalDateTime.now());
            message.setAuthorOfMessage(userService.getLoggedUser());
            message.setTopic(topicService.getTopicById(id));
            messageService.saveMessage(message);
            model.addAttribute("topic", topicService.getTopicById(id));

            return "topic";
        }
        return "redirect:/signin";
    }








}
