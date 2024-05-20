package com.mb.cap.blog.config.locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MesssageConfiguaration {
    @Value("${spring.messages.encoding}")
    private String encoding;

    @Value("${spring.messages.basename}")
    private String baseName;

    @Value("${spring.messages.cache-duration}")
    private int cacheDuration;

    @Value("${spring.messages.use-code-as-default-message}")
    private boolean useCodeAsDefaultMessage;

    /**
     * Locale resolver.
     *
     * @return the locale resolver
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();

        localeResolver.setDefaultLocale(Labels.VN);

        return localeResolver;
    }

    /**
     * Bundle message source.
     *
     * @return the message source
     */
    @Bean
    public MessageSource bundleMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(baseName);
        messageSource.setDefaultEncoding(encoding);
        messageSource.setCacheSeconds(cacheDuration);
        messageSource.setUseCodeAsDefaultMessage(useCodeAsDefaultMessage);

        return messageSource;
    }
}
