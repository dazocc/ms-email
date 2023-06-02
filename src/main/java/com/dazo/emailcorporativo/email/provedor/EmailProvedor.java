package com.dazo.emailcorporativo.email.provedor;

import com.dazo.emailcorporativo.email.EmailDTO;

public interface EmailProvedor {

    void sendEmail(EmailDTO emailDTO);

    ProvedorEmailType getProvedorEmail();
}
