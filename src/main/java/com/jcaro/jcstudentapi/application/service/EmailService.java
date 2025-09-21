package com.jcaro.jcstudentapi.application.service;

import com.itextpdf.text.DocumentException;
import com.jcaro.jcstudentapi.domain.model.Student;
import jakarta.mail.MessagingException;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public interface EmailService {
    public void sendEmailWithPdfScore(Student student, ByteArrayInputStream pdf) throws MessagingException, DocumentException, IOException;
}
