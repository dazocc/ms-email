package com.dazo.emailcorporativo.conf.activemq;

import com.dazo.emailcorporativo.email.jms.EmailConsumer;
import jakarta.jms.ConnectionFactory;
import lombok.AllArgsConstructor;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;

import java.util.List;

@Configuration
@EnableJms
@AllArgsConstructor
public class ActiveMqConfig {

    private final ActiveMqProperties activeMqProperties;
    private final EmailConsumer emailConsumer;

    @Bean
    public ConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory activeMQConnectionFactory  = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(activeMqProperties.getBrokerUrl());
        activeMQConnectionFactory.setTrustedPackages(List.of("com.dazo.emailcorporativo.email"));
        return  activeMQConnectionFactory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        return factory;
    }

    @Bean
    public MessageListenerContainer listenerContainer() {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setDestinationName(activeMqProperties.getQueueEmail());
        container.setMessageListener(emailConsumer);
        return container;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory());
        return jmsTemplate;
    }

}