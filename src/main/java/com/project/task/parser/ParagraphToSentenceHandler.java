package com.project.task.parser;

import com.project.task.composite.ComponentType;
import com.project.task.composite.TextComposite;

public class ParagraphToSentenceHandler extends AbstractTextHandler {
    private static final String SENTENCE_REGEX = "(?<=[.!?])[\\\\s\\\\n]+";

    @Override
    public void parse(TextComposite composite, String text) {
        if(text.isBlank()) return;

        String[] lines = text.split(SENTENCE_REGEX);

        for(String line : lines) {
            if(line.isBlank()) {
                TextComposite linesComposite = new TextComposite(ComponentType.SENTENCE);
                composite.add(linesComposite);

                if(nextParser != null) {
                    nextParser.parse(linesComposite, line.trim());
                }
            }
        }
    }
}
