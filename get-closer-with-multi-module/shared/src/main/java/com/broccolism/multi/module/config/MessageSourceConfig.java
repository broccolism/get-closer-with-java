package com.broccolism.multi.module.config;

import java.util.Locale;

import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.context.MessageSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageSourceConfig extends MessageSourceAutoConfiguration {

    /**
     * {@link MessageSourceAutoConfiguration} 의 선언 이관
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.messages")
    public MessageSourceProperties messageSourceProperties() {
        return new MessageSourceProperties();
    }

    @Bean
    public MessageSourceAccessor messageSourceAccessor(MessageSource messageSource) {
        ((ResourceBundleMessageSource)messageSource).setUseCodeAsDefaultMessage(true);
        ((ResourceBundleMessageSource)messageSource).addBasenames("messages/notification");
        return new MessageSourceAccessor(messageSource);
    }

    protected static class CustomResourceBundleCondition extends ResourceBundleCondition {
    }
}
