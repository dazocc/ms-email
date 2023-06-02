package com.dazo.emailcorporativo.email.jms;

import com.dazo.emailcorporativo.email.EmailDTO;
import com.dazo.emailcorporativo.email.EmailService;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.ObjectMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class EmailConsumer implements MessageListener {

    private final EmailService emailService;

    @Override
//    @Transactional
    public void onMessage(Message message) {
        try{
            ObjectMessage objectMessage = (ObjectMessage)message;
            EmailDTO emailDTO = (EmailDTO) objectMessage.getObject();
            emailService.sendEmail(emailDTO);
        } catch(Exception e) {
            log.error("Received Exception : "+ e);
            throw new RuntimeException("teste");
        }

    }
}