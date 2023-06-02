package com.dazo.emailcorporativo.conf.aws;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "aws.smtp")
@Getter
@Setter
public class AwsSmtpProperties {

    private String accessKey;
    private String secretKey;
    private String regionName;

}