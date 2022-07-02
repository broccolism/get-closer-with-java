package com.broccolism.multi.module.service;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageSourceAccessor messageSourceAccessor;

    public String getNotificationMessage() {
        log.debug("@@@@@@@@@@@@@ read: "+ messageSourceAccessor.getMessage("notification.example"));
        return messageSourceAccessor.getMessage("notification.example");
    }
}
