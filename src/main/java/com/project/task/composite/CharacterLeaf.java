package com.project.task.composite;


public class CharacterLeaf implements TextComponent {
    private final char value;
    private final ComponentType type = ComponentType.CHARACTER;

    public CharacterLeaf(char value) {
        this.value = value;
    }

    @Override
    public String buildText() {
        return String.valueOf(value);
    }

    @Override
    public int getComponentCount() {
        return 1;
    }
}
