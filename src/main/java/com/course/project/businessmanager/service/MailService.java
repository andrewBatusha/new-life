package com.course.project.businessmanager.service;

public interface MailService {
    void send(String receiver, String subject, String message);
}

