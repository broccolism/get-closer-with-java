package com.broccolism.multi.module.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {
    public String getNotificationMessage() {
        return "this is a test message.";
    }
}
