package com.sample.controller;

import com.sample.dto.SimpleDto;
import com.sample.dto.StepDto;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Random;

@RestController
public class TestController {

    //For stomp
    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ModelAndView view() {
        return new ModelAndView("test");
    }

    @RequestMapping(value = "/restCall", method = RequestMethod.GET)
    public SimpleDto restCall() {
        return SimpleDto.sample();
    }

    @MessageMapping("/consolidate")
    public void webtest(Principal principal) {
        Random random = new Random(System.nanoTime());
        new Thread(() -> {
                StepDto.buildAll().forEach(step -> {
                    try {
                        int delay = random.nextInt(10);
                        String message = MessageFormatter.format("exec start for {} step ~{} sec", step.getType(), delay).getMessage();

                        //send message to specific use
                        messagingTemplate.convertAndSendToUser(principal.getName(), "/broker/consolidationResult", message);

                        Thread.sleep(delay * 1000);

                        String end = MessageFormatter.format("step {} successfully finished | user {}", step.getType(), principal.getName()).getMessage();

                        //send message to specific use
                        messagingTemplate.convertAndSendToUser(principal.getName(), "/broker/consolidationResult", end);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }).start();

    }


}
