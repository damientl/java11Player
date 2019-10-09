package com.development.playerapp.application;

public interface Logger {
    void info(String text);

    void debug(String text);

    void error(String text, Throwable e);
}
