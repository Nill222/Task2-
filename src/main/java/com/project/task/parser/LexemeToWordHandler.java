package com.project.task.parser;

import com.project.task.composite.CharacterLeaf;
import com.project.task.composite.ComponentType;
import com.project.task.composite.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeToWordHandler extends AbstractTextHandler {
    private static final Logger logger = LogManager.getLogger();
    private static final String WORD_REGEX = "([a-zA-Z]+)|(\\p{Punct})";

    @Override
    public void parse(TextComposite composite, String text) {
        if(text.isBlank()) return;

        Pattern pattern = Pattern.compile(WORD_REGEX);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()){
            String word = matcher.group(1);
            String pun = matcher.group(2);

            if (word != null) {
                logger.debug("Found Word: '{}'", word);

                TextComposite wordComposite = new TextComposite(ComponentType.WORD);
                composite.add(wordComposite);

                if (nextParser != null) {
                    nextParser.parse(wordComposite, word);
                }
            } else if (pun != null) {
                logger.debug("Found Punctuation: '{}'", pun.charAt(0));

                composite.add(new CharacterLeaf(pun.charAt(0)));
            }
        }
    }
}
