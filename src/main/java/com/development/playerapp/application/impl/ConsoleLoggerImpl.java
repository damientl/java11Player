package com.development.playerapp.application.impl;

import com.development.playerapp.application.Logger;

/**
 * Logger implementation providing log levels
 */
public class ConsoleLoggerImpl implements Logger {

    private enum LogLevel {
        INFO, DEBUG, ERROR
    }

    private final LogLevel logLevel = LogLevel.DEBUG;


    public void debug(String message) {
        if (LogLevel.DEBUG == logLevel) {
            System.out.println(message);
        }
    }

    public void info(String message) {
        if (LogLevel.DEBUG == logLevel ||
                LogLevel.INFO == logLevel) {
            System.out.println(message);
        }
    }

    public void error(String message, Throwable e) {
        if (LogLevel.DEBUG == logLevel ||
                LogLevel.INFO == logLevel ||
                LogLevel.ERROR == logLevel) {
            System.err.println(message);
            e.printStackTrace();
        }
    }
}
