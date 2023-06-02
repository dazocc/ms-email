package com.dazo.emailcorporativo.email.provedor;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.dazo.emailcorporativo.email.EmailDTO;
import com.dazo.emailcorporativo.email.EmailEntity;
import com.dazo.emailcorporativo.email.provedor.EmailProvedor;
import com.dazo.emailcorporativo.email.provedor.ProvedorEmailType;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.Properties;

@Service
@AllArgsConstructor
public class EmailAwsSmtpProviderService implements EmailProvedor {

    private final AmazonSimpleEmailService amazonSimpleEmailService;

    @Override
    public void sendEmail(EmailDTO emailDTO) {

        EmailEntity emailEntity = new EmailEntity();
        BeanUtils.copyProperties(emailDTO, emailEntity);

        Session s = Session.getInstance(new Properties(), null);
        MimeMessage mimeMessage = new MimeMessage(s);
        
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            
            helper.setTo(emailEntity.getEmailDe());
            helper.setFrom(emailEntity.getEmailDe());
            helper.setSubject(emailEntity.getAssunto());
            helper.setSentDate(new Date());
            helper.setText(emailEntity.getTexto());

            SendRawEmailRequest rawEmailRequest = montaSendRawEmailRequest(mimeMessage);

            amazonSimpleEmailService.sendRawEmail(rawEmailRequest);
        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private SendRawEmailRequest montaSendRawEmailRequest(MimeMessage mimeMessage) throws IOException, MessagingException {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        mimeMessage.writeTo(outputStream);
        RawMessage rawMessage = new RawMessage(ByteBuffer.wrap(outputStream.toByteArray()));

        return new SendRawEmailRequest(rawMessage);
    }

    @Override
    public ProvedorEmailType getProvedorEmail() {
        return ProvedorEmailType.AWS;
    }
}
