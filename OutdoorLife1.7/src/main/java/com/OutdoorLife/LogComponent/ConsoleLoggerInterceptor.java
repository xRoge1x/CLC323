package com.OutdoorLife.LogComponent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConsoleLoggerInterceptor implements LoggerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(ConsoleLoggerInterceptor.class);

    @Override
    public void log(Level level, String className, String methodName, String message) {
        String formattedMessage = formatMessage(level, className, methodName, message);
        switch (level) {
            case DEBUG:
                logger.debug(formattedMessage);
                break;
            case INFO:
                logger.info(formattedMessage);
                break;
            case WARN:
                logger.warn(formattedMessage);
                break;
            case ERROR:
                logger.error(formattedMessage);
                break;
            default:
                logger.trace(formattedMessage);
                break;
        }
    }

    private String formatMessage(Level level, String className, String methodName, String message) {
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return String.format("[%s] %s - %s.%s - %s", timeStamp, level, className, methodName, message);
    }
}
