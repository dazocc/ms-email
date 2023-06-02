package com.dazo.emailcorporativo.email.provedor;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class EmailProvedorFactory {

    private final Map<ProvedorEmailType, EmailProvedor> provedoresMap = new HashMap<>();

    public EmailProvedorFactory(Set<EmailProvedor> emailProvedoresSet) {
        emailProvedoresSet.forEach(provedor -> provedoresMap.put(provedor.getProvedorEmail(), provedor));
    }

    public EmailProvedor encontraProvedor(ProvedorEmailType provedorEmailType) {
        return provedoresMap.get(provedorEmailType);
    }

}
