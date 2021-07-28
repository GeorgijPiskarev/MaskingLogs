package com.example.log.util;

import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

public class PatternCompiler {
    private static final String MODEL_PATH = "com.example.log.model";

    private static PatternCompiler instance;

    private final Optional<Pattern> pattern;


    private PatternCompiler() {
        Reflections reflections = new Reflections(MODEL_PATH, new FieldAnnotationsScanner());
        Set<Field> maskFields = reflections.getFieldsAnnotatedWith(MaskLog.class);

        Set<String> fields = new HashSet<>();
        for (Field field : maskFields) {
            if (field.isAnnotationPresent(MaskLog.class)) {
                fields.add(field.getName() + "=([\\s\\S]*?)[,|)]");
                fields.add("\"" + field.getName() + "\"\\s*:\\s*\"([\\s\\S]*?)\"");
            }
        }
        if (!fields.isEmpty()) {
            this.pattern = Optional.of(Pattern.compile(StringUtils.join(fields, '|'), Pattern.MULTILINE));
        } else {
            this.pattern = Optional.empty();
        }
    }

    public static PatternCompiler getInstance() {
        if (instance == null) {
            instance = new PatternCompiler();
        }
        return instance;
    }

    public Optional<Pattern> getPattern() {
        return pattern;
    }
}
