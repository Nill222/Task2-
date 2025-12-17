package com.project.task.composite;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextComposite implements TextComponent {
    private final ComponentType type;
    private final List<TextComponent> components = new ArrayList<>();

    public TextComposite(ComponentType type) {
        this.type = type;
    }

    public ComponentType getType() {
        return type;
    }

    public void add(TextComponent component){
        components.add(component);
    }

    public void remove(TextComponent component) {
        components.remove(component);
    }

    public List<TextComponent> getComponents() {
        return Collections.unmodifiableList(components);
    }

    @Override
    public String buildText() {
        StringBuilder builder = new StringBuilder();
        for (TextComponent child : components) {
            builder.append(child.buildText());
        }
        return builder.toString();
    }

    @Override
    public int getComponentCount() {
        int count = 0;
        for(TextComponent child : components) {
            count += child.getComponentCount();
        }
        return count;
    }
}
