package com.dazo.emailcorporativo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class EmailCorporativoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailCorporativoApplication.class, args);
	}

}
