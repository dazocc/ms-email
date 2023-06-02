package com.dazo.emailcorporativo.email;

import com.dazo.emailcorporativo.email.jms.EmailProducer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/email")
public class EmailController {

    private final EmailProducer emailProducer;
    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<Void> sendingEmail(@RequestBody EmailDTO emailDTO){
        emailService.sendEmail(emailDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/jms")
    public ResponseEntity<Void> sendingJMS(@RequestBody EmailDTO emailDTO){
        emailProducer.sendMessage(emailDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}