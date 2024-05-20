package com.mb.cap.blog.config.logging;

import ch.qos.logback.classic.LoggerContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author toila
 * cấu hình log
 */

public class LoggingConfiguration {
    public LoggingConfiguration(
            @Value("${spring.application.name}") String appName,
            @Value("${server.port}") String serverPort,
            ObjectMapper mapper
    ) throws JsonProcessingException {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

        Map<String, String> map = new HashMap<>();
        map.put("app_name", appName);
        map.put("app_port", serverPort);
        String customFields = mapper.writeValueAsString(map);

//        if (loggingProperties.isUseJsonFormat()) {
//            addJsonConsoleAppender(context, customFields);
//        }
//        if (logstashProperties.isEnabled()) {
//            addLogstashTcpSocketAppender(context, customFields, logstashProperties);
//        }
//        if (loggingProperties.isUseJsonFormat() || logstashProperties.isEnabled()) {
//            addContextListener(context, customFields, loggingProperties);
//        }
    }
}
