package com.sample.shceduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Scheduler {

    //For Stomp
    //private static final String[] users = {"user1", "user2"};

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Scheduled(fixedDelay = 1000)
    @Async
    public void sched() {
        messagingTemplate.convertAndSend("/broker/broadcast", "broadcast " + new Date().getTime());
    }

    /*@Scheduled(fixedDelay = 100)
    @Async
    public void paulson() {
        messagingTemplate.convertAndSendToUser(users[1], "/broker/perUser", "special " + new Date().getTime());
    }

    @Scheduled(fixedDelay = 1000)
    @Async
    public void fab() {
        messagingTemplate.convertAndSendToUser(users[0], "/broker/perUser", "special " + new Date().getTime());
    }*/

}
