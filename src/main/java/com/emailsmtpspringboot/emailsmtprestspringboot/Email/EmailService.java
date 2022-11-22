package com.emailsmtpspringboot.emailsmtprestspringboot.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${TO_EMAIL}")
    private  String SEND_TO_EMAIL;
    @Value("${TO_PHONE}")
    private String SEND_TO_PHONE;

    public boolean sendSimpleEmail(String name, String email, String subject, String message)
    {
        boolean sent = false;
        try {
            SimpleMailMessage simpleMail = new SimpleMailMessage();

            String sender_name = name == "" ? "N/A" : name;
            String sender_email = email == "" ? "N/A" : email;
            String sender_subject = subject == "" ? "N/A" : subject;

            String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
            String email_subject = name == "" ? "New message received on Portfolio" :
                    "New message received from " + name + " on Portfolio";

            simpleMail.setTo(SEND_TO_EMAIL, SEND_TO_PHONE);
            simpleMail.setSubject(email_subject);
            simpleMail.setText("Sender Name : " + sender_name + "\r\n" +
                    "Sender Email : " + sender_email + "\r\n" +
                    "Date Time : " + timeStamp + "\r\n" +
                    "Subject : " + sender_subject + "\r\n" +
                    "Message : " + message + "\r\n<--EOM-->");

            javaMailSender.send(simpleMail);
            sent = true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return sent;
    }
}
