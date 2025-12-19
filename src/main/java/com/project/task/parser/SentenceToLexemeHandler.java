package com.project.task.parser;

import com.project.task.composite.ComponentType;
import com.project.task.composite.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SentenceToLexemeHandler extends AbstractTextHandler {
    private static final Logger logger = LogManager.getLogger();
    private static final String LEXEME_REGEX = "\\s+";

    @Override
    public void parse(TextComposite composite, String text) {
        if(text.isBlank()) return;

        logger.debug("SentenceToLexemeHandler started parsing sentence: '{}'", text);
        String[] lines = text.split(LEXEME_REGEX);

        for(String line : lines) {
            if(!line.isBlank()) {
                TextComposite linesComposite = new TextComposite(ComponentType.SENTENCE);
                composite.add(linesComposite);
                logger.debug("Created LexemeComposite with content: '{}'", line.strip());
                if(nextParser != null) {
                    nextParser.parse(linesComposite, line.strip());
                }
            }
        }
    }
}
