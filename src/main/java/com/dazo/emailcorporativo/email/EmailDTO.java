package com.dazo.emailcorporativo.email;

import com.dazo.emailcorporativo.email.provedor.ProvedorEmailType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailDTO implements Serializable {

        @NotBlank
        @Email
        private String emailDe;

        @NotBlank
        @Email
        private String emailPara;

        @NotBlank
        private String assunto;

        @NotBlank
        private String texto;

        @NotNull
        private ProvedorEmailType provedorEmailType;
}