package com.jcaro.jcstudentapi.infrastructure.service;

import com.itextpdf.text.DocumentException;

import com.jcaro.jcstudentapi.application.service.EmailService;
import com.jcaro.jcstudentapi.domain.model.Student;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Service
public class EmailServiceAdapter implements EmailService {


    @Autowired
    private JavaMailSender emailSender;


    @Override
    public void sendEmailWithPdfScore(Student student, ByteArrayInputStream pdf) throws MessagingException, DocumentException, IOException {
        if (student.email() != null) {
            MimeMessage message = emailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("jcaroromeroprog@gmail.com");
            helper.setTo(student.email());
            helper.setSubject("Notas " + student.name());
            helper.setText("Estimado/a " + student.name() + ",\n\n" +
                    "Adjuntamos tus notas de los laboratorios.\n\n" +
                    "Ten en cuenta las siguientes consideraciones para la evaluación:\n\n" +
                    "- Las calificaciones oscilan entre 0 y 3.\n\n" +
                    "- Una nota de 0 indica que el laboratorio no fue entregado, se subió un repositorio incorrecto, o se entregó vacío.\n\n" +
                    "- Para la certificación, es imprescindible completar todos los laboratorios obligatorios. Por favor, revisa la lista de los labs requeridos.\n\n" +
                    "Si tienes alguna pregunta, no dudes en contactarnos.\n\n" +
                    "Saludos cordiales,\n" +
                    "El equipo de soporte.");

            File file = new File("/tmp/output.txt");
            Files.copy(pdf, file.toPath(), StandardCopyOption.REPLACE_EXISTING);

            FileSystemResource file1 = new FileSystemResource(file);
            helper.addAttachment(student.name() + ".pdf", file1);
            emailSender.send(message);
        }
    }
}
