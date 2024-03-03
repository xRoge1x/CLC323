package com.OutdoorLife.LoggerComponent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogComponent {
    
    private static final Logger logger = LoggerFactory.getLogger(LogComponent.class);
    
    public void testLogger() {
        logger.info("Logging with Log4j2 and SLF4J");
    }
    
    public void logInfo(String message) {
        logger.info(message);
    }

    public void logWarning(String message) {
        logger.warn(message);
    }

    public void logError(String message, Throwable throwable) {
        logger.error(message, throwable);
    }
}
