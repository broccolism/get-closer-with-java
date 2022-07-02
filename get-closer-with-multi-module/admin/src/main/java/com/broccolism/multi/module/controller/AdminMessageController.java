package com.broccolism.multi.module.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.broccolism.multi.module.service.MessageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class AdminMessageController {

    private final MessageService messageService;

    @GetMapping("/notification")
    public String getNotificationMessage() {
        log.debug("@@@@@@@@@@@@@@@ admin message controller");
        return messageService.getNotificationMessage();
    }

}
