package com.nextisus.project.util.email.service;


import com.nextisus.project.client.healthrecord.dto.request.EmailRequestDto;

public interface EmailService {
    void sendPdf(EmailRequestDto dto, Long userId);
}
