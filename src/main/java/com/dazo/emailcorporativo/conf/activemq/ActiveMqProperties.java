package com.dazo.emailcorporativo.conf.activemq;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "activemq")
@Getter
@Setter
public class ActiveMqProperties {

    private String brokerUrl;
    private String queueEmail;
}
