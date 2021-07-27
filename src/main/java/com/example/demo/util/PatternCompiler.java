package com.example.demo.util;

import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

public class PatternCompiler {
    private static final String MODEL_PATH = "com.example.demo.model";

    private static PatternCompiler instance;

    private final Optional<Pattern> toStringPattern;
    private final Optional<Pattern> jsonPattern;


    private PatternCompiler() {
        Reflections reflections = new Reflections(MODEL_PATH, new FieldAnnotationsScanner());
        Set<Field> maskFields = reflections.getFieldsAnnotatedWith(Mask.class);

        Set<String> fields = new HashSet<>();
        Set<String> jsonFields = new HashSet<>();
        for (Field field : maskFields) {
            if (field.isAnnotationPresent(Mask.class)) {
                fields.add(field.getName() + "=([\\s\\S]*?)[,|)]");
                jsonFields.add("\"" + field.getName() + "\"\\s*:\\s*\"([\\s\\S]*?)\"");
            }
        }
        if (!fields.isEmpty()) {
            this.toStringPattern = Optional.of(Pattern.compile(StringUtils.join(fields, '|'), Pattern.MULTILINE));
            this.jsonPattern = Optional.of(Pattern.compile(StringUtils.join(jsonFields, '|'), Pattern.MULTILINE));
        } else {
            this.toStringPattern = Optional.empty();
            this.jsonPattern = Optional.empty();
        }
    }

    public static PatternCompiler getInstance() {
        if (instance == null) {
            instance = new PatternCompiler();
        }
        return instance;
    }

    public Optional<Pattern> getToStringPattern() {
        return toStringPattern;
    }

    public Optional<Pattern> getJsonPattern() {
        return jsonPattern;
    }
}
