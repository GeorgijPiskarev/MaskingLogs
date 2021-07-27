package com.example.demo.util;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaskingPatternLayout extends PatternLayout {

    @Override
    public String doLayout(ILoggingEvent event) {
        final StringBuilder message = new StringBuilder(super.doLayout(event));
        PatternCompiler patternCompiler = PatternCompiler.getInstance();
        Optional<Pattern> toStringPattern = patternCompiler.getToStringPattern();
        Optional<Pattern> jsonPattern = patternCompiler.getJsonPattern();
        boolean isProcessed;
        if (toStringPattern.isPresent() && jsonPattern.isPresent()) {
            isProcessed = mask(toStringPattern.get().matcher(message), message);
            if (!isProcessed) {
                mask(jsonPattern.get().matcher(message), message);
            }
        }

        return message.toString();
    }

    private boolean mask(Matcher matcher, StringBuilder message) {
        boolean isProcessed = false;
        while (matcher.find()) {
            isProcessed = true;
            int group = 1;
            while (group <= matcher.groupCount()) {
                if (matcher.group(group) != null) {
                    for (int i = matcher.start(group) + 1; i < matcher.end(group) - 1; i++) {
                        message.setCharAt(i, '*');
                    }
                }
                group++;
            }
        }
        return isProcessed;
    }
}
