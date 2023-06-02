package com.dazo.emailcorporativo.email.jms;

import com.dazo.emailcorporativo.email.EmailDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class EmailProducer {

    private final JmsTemplate jmsTemplate;

    public void sendMessage(EmailDTO emailDTO){
        try{
            jmsTemplate.convertAndSend("email_corporativo", emailDTO);
        } catch(Exception e){
            log.error("Recieved Exception during send Message: ", e);
        }
    }
}
