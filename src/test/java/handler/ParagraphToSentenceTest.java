package handler;

import com.project.task.composite.TextComposite;
import com.project.task.composite.ComponentType;
import com.project.task.parser.ParagraphToSentenceHandler;
import com.project.task.parser.SentenceToLexemeHandler;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParagraphToSentenceTest {

    @Test
    void parseShouldSplitParagraphIntoSentences() {
        TextComposite paragraphComposite = new TextComposite(ComponentType.PARAGRAPH);
        ParagraphToSentenceHandler handler = new ParagraphToSentenceHandler();
        handler.setNextParser(new SentenceToLexemeHandler());

        handler.parse(paragraphComposite, "Hello world. Bye!");

        assertFalse(paragraphComposite.getComponents().isEmpty());
    }
}

