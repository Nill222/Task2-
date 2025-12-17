package com.project.task.composite;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextComposite implements TextComponent {
    private static final Logger log = LogManager.getLogger();
    private final ComponentType type;
    private final List<TextComponent> components = new ArrayList<>();

    public TextComposite(ComponentType type) {
        this.type = type;
    }

    public ComponentType getType() {
        return type;
    }

    public void add(TextComponent component) {
        log.debug("Added component of type {} to {}",
                (component instanceof TextComposite ? ((TextComposite) component).getType() : "LEAF"),
                type);
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
        //toDo
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
        log.debug("Component count for {} is {}", type, count);
        return count;
    }
}
