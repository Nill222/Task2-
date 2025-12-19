package handler;

import com.project.task.composite.TextComposite;
import com.project.task.composite.ComponentType;
import com.project.task.parser.LexemeToWordHandler;
import com.project.task.parser.SentenceToLexemeHandler;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SentenceToLexemeHandlerTest {

    @Test
    void parseShouldSplitSentenceIntoLexemes() {
        TextComposite sentenceComposite = new TextComposite(ComponentType.SENTENCE);
        SentenceToLexemeHandler handler = new SentenceToLexemeHandler();
        handler.setNextParser(new LexemeToWordHandler());

        handler.parse(sentenceComposite, "Hello world");

        assertFalse(sentenceComposite.getComponents().isEmpty());
    }
}

