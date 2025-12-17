package com.project.task.parser;

import com.project.task.composite.ComponentType;
import com.project.task.composite.TextComposite;

import java.util.logging.Logger;

public class TextToParagraphHandler extends AbstractTextHandler {
    private static final String PARAGRAPH_REGEX = "[\\n]";

    @Override
    public void parse(TextComposite composite, String text) {
        if(text.isBlank()) return;

        String[] lines = text.split(PARAGRAPH_REGEX);

        for(String line : lines) {
            if(line.isBlank()) {
                TextComposite linesComposite = new TextComposite(ComponentType.PARAGRAPH);
                composite.add(linesComposite);

                if(nextParser != null) {
                    nextParser.parse(linesComposite, line.trim());
                }
            }
        }
    }
}
