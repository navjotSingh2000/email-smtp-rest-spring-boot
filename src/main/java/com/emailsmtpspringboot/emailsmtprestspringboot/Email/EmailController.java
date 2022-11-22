package com.emailsmtpspringboot.emailsmtprestspringboot.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/email")
    public ResponseEntity<Object> sendEmail(@RequestBody Email mail)
    {
        boolean response = emailService.sendSimpleEmail(
                mail.getName(),
                mail.getEmail(),
                mail.getSubject(),
                mail.getMessage());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
