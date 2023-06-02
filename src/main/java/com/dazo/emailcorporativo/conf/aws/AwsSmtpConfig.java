package com.dazo.emailcorporativo.conf.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class AwsSmtpConfig {

    private AwsSmtpProperties awsSmtpProperties;

    public AWSStaticCredentialsProvider awsCredentials() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(awsSmtpProperties.getAccessKey(), awsSmtpProperties.getSecretKey());
        return new AWSStaticCredentialsProvider(credentials);
    }

    @Bean
    public AmazonSimpleEmailService getAmazonSimpleEmailService() {
        return AmazonSimpleEmailServiceClientBuilder
                .standard()
                .withCredentials(awsCredentials())
                .withRegion(Regions.fromName(awsSmtpProperties.getRegionName()))
                .build();
    }
}
