package com.dazo.emailcorporativo.email;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailEntity {

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
}
