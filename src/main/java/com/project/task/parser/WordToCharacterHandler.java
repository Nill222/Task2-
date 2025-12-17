package com.project.task.parser;

import com.project.task.composite.CharacterLeaf;
import com.project.task.composite.TextComposite;

public class WordToCharacterHandler extends AbstractTextHandler {
    @Override
    public void parse(TextComposite composite, String text) {
        char[] chars = text.toCharArray();

        for(char character : chars) {
            composite.add(new CharacterLeaf(character));
        }
    }
}
