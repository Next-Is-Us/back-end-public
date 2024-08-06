package com.nextisus.project.util.email.service;

import com.nextisus.project.client.healthrecord.dto.request.EmailMessageDto;
import com.nextisus.project.client.healthrecord.dto.request.EmailRequestDto;
import com.nextisus.project.domain.User;
import com.nextisus.project.image.service.S3UploadService;
import com.nextisus.project.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;
    private final S3UploadService s3Service;

    @Override
    public void sendPdf(EmailRequestDto dto, Long userId) {

        // 유저 찾기
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        // 현재 날짜를 사용하여 파일명 생성
        String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String userNickname = user.getNickname();
        String attachmentFileName = "오늘의맘_" + formattedDate + "_" + userNickname + "님.pdf";
        String emailSubject = "[" + formattedDate + " " + userNickname + "님] 오늘의 맘 의료 기록입니다.";

        // 보낼 이메일 내용 구성
        EmailMessageDto emailMessageDto = EmailMessageDto.builder()
                .to(dto.getEmail())
                .subject(emailSubject)
                .content("첨부된 PDF 파일을 확인하세요.")
                .build();

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            // multipart 플래그를 true로 설정하여 첨부 파일을 허용
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setTo(emailMessageDto.getTo());
            mimeMessageHelper.setSubject(emailMessageDto.getSubject());
            mimeMessageHelper.setText(emailMessageDto.getContent());

            // S3에서 파일을 InputStream으로 가져오기
            InputStream inputStream = s3Service.getFileAsInputStream(dto.getPdfUrl());
            InputStreamSource inputStreamSource = new ByteArrayResource(inputStream.readAllBytes());

            mimeMessageHelper.addAttachment(attachmentFileName, inputStreamSource);

            javaMailSender.send(mimeMessage);

        } catch (MessagingException | IOException e) {
            log.error("메일 전송 에러: {}", e.getLocalizedMessage());
            throw new RuntimeException("메일 전송 실패", e);
        }
    }
}
