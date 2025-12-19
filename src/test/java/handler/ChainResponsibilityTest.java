package handler;

import com.project.task.composite.ComponentType;
import com.project.task.composite.TextComposite;
import com.project.task.parser.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChainResponsibilityTest {

    @Test
    void fullChainShouldParseTextIntoHierarchy() {
        TextComposite textComposite = new TextComposite(ComponentType.TEXT);

        TextToParagraphHandler textHandler = new TextToParagraphHandler();
        ParagraphToSentenceHandler paragraphHandler = new ParagraphToSentenceHandler();
        SentenceToLexemeHandler sentenceHandler = new SentenceToLexemeHandler();
        LexemeToWordHandler lexemeHandler = new LexemeToWordHandler();
        WordToCharacterHandler wordHandler = new WordToCharacterHandler();

        textHandler.setNextParser(paragraphHandler);
        paragraphHandler.setNextParser(sentenceHandler);
        sentenceHandler.setNextParser(lexemeHandler);
        lexemeHandler.setNextParser(wordHandler);

        textHandler.parse(textComposite, "Hi world!");

        assertEquals(1, textComposite.getComponents().size());
        assertTrue(textComposite.buildText().contains("Hi"));
        assertTrue(textComposite.buildText().contains("world"));
    }
}
