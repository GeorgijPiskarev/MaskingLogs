package com.example.log.util;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.util.regex.Matcher;

public class MaskingPatternLayout extends PatternLayout {

    @Override
    public String doLayout(ILoggingEvent event) {
        final StringBuilder message = new StringBuilder(super.doLayout(event));
        PatternCompiler.getInstance().getPattern().ifPresent(value -> mask(value.matcher(message), message));
        return message.toString();
    }

    private void mask(Matcher matcher, StringBuilder message) {
        while (matcher.find()) {
            for (int group = 1; group <= matcher.groupCount(); group++) {
                if (matcher.group(group) != null) {
                    for (int i = matcher.start(group) + 1; i < matcher.end(group) - 1; i++) {
                        message.setCharAt(i, '*');
                    }
                }
            }
        }
    }
}
