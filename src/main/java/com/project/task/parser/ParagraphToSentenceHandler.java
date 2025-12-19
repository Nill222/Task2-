package com.project.task.parser;

import com.project.task.composite.ComponentType;
import com.project.task.composite.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParagraphToSentenceHandler extends AbstractTextHandler {
    private static final Logger logger = LogManager.getLogger();
    private static final String SENTENCE_REGEX = "(?<=[.!?])[\\\\s\\n]+";

    @Override
    public void parse(TextComposite composite, String text) {
        if(text.isBlank()) {
            logger.warn("ParagraphToSentenceHandler received blank paragraph, skipping parsing");
            return;
        }

        logger.debug("ParagraphToSentenceHandler started parsing paragraph: '{}'", text);
        String[] lines = text.split(SENTENCE_REGEX);

        for(String line : lines) {
            if(!line.isBlank()) {
                TextComposite linesComposite = new TextComposite(ComponentType.SENTENCE);
                composite.add(linesComposite);
                logger.debug("Created SentenceComposite with content: '{}'", line.strip());
                if(nextParser != null) {
                    nextParser.parse(linesComposite, line.strip());
                }
            }
        }
    }
}
