package com.example.demo.appender;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.example.demo.annotations.Mask;
import org.apache.tomcat.util.buf.StringUtils;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaskingPatternLayout extends PatternLayout {

    private String patternsProperty;
    private Optional<Pattern> pattern;
    private Optional<Pattern> jsonPattern;

    public String getPatternsProperty() {
        return patternsProperty;
    }

    public void setPatternsProperty(String patternsProperty) {
        this.patternsProperty = patternsProperty;
        Reflections reflections = new Reflections("com.example.demo.model", new FieldAnnotationsScanner());
        Set<Field> maskFields = reflections.getFieldsAnnotatedWith(Mask.class);

        List<String> fields = new ArrayList<>();
        List<String> jsonFields = new ArrayList<>();
        for (Field field : maskFields) {
            if (field.isAnnotationPresent(Mask.class)) {
                fields.add(field.getName() + "=([\\s\\S]*?)[,|)]");
                jsonFields.add("\"" + field.getName() + "\"\\s*:\\s*\"([\\s\\S]*?)\"");
            }
        }
        if (!fields.isEmpty()) {
            this.pattern = Optional.of(Pattern.compile(StringUtils.join(fields, '|'), Pattern.MULTILINE));
            this.jsonPattern = Optional.of(Pattern.compile(StringUtils.join(jsonFields, '|'), Pattern.MULTILINE));
        } else {
            this.pattern = Optional.empty();
        }
    }

    @Override
    public String doLayout(ILoggingEvent event) {
        final StringBuilder message = new StringBuilder(super.doLayout(event));
        if (pattern.isPresent()) {
            Matcher matcher = pattern.get().matcher(message);
            mask(matcher, message);
        }
        if (jsonPattern.isPresent()) {
            Matcher matcher = jsonPattern.get().matcher(message);
            mask(matcher, message);
        }
        return message.toString();
    }

    private void mask(Matcher matcher, StringBuilder message) {
        while (matcher.find()) {

            int group = 1;
            while (group <= matcher.groupCount()) {
                if (matcher.group(group) != null) {
                    for (int i = matcher.start(group); i < matcher.end(group); i++) {
                        message.setCharAt(i, '*');
                    }
                }
                group++;
            }
        }
    }
}
