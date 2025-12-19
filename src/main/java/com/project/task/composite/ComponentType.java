package com.project.task.composite;

public enum ComponentType {
    TEXT("/n"),
    PARAGRAPH(" "),
    SENTENCE(" "),
    LEXEME(""),
    WORD(""),
    CHARACTER("");

    private final String delimiter;

    ComponentType(final String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return delimiter;
    }
}
