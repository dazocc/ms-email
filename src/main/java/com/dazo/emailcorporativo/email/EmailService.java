package com.dazo.emailcorporativo.email;

import com.dazo.emailcorporativo.email.provedor.EmailProvedor;
import com.dazo.emailcorporativo.email.provedor.EmailProvedorFactory;
import com.dazo.emailcorporativo.email.provedor.ProvedorEmailType;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class EmailService {

    private final EmailProvedorFactory emailProvedorFactory;

    public void sendEmail(EmailDTO emailDTO){

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<EmailDTO>> violations = validator.validate(emailDTO);
        for (ConstraintViolation<EmailDTO> violation : violations) {
        }

        ProvedorEmailType provedorEmailType = emailDTO.getProvedorEmailType();

        EmailProvedor emailProvedor = emailProvedorFactory.encontraProvedor(provedorEmailType);

        emailProvedor.sendEmail(emailDTO);

    }

}
