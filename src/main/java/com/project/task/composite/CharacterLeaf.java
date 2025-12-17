package com.project.task.composite;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CharacterLeaf implements TextComponent {
    private static final Logger log = LogManager.getLogger();
    private final char value;
    private final ComponentType type = ComponentType.CHARACTER;

    public CharacterLeaf(char value) {
        this.value = value;
    }

    @Override
    public String buildText() {
        log.trace("Building text for CharacterLeaf value='{}'", value);
        return String.valueOf(value);
    }

    @Override
    public int getComponentCount() {
        log.trace("getComponentCount called for CharacterLeaf value='{}'", value);
        return 1;
    }
}
