package handler;

import com.project.task.composite.TextComposite;
import com.project.task.composite.ComponentType;
import com.project.task.parser.ParagraphToSentenceHandler;
import com.project.task.parser.TextToParagraphHandler;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TextToParagraphHandlerTest {

    @Test
    void parseShouldSplitTextIntoParagraphs() {
        TextComposite textComposite = new TextComposite(ComponentType.TEXT);
        TextToParagraphHandler handler = new TextToParagraphHandler();
        handler.setNextParser(new ParagraphToSentenceHandler());

        handler.parse(textComposite, "Hello world.\nBye!");

        assertFalse(textComposite.getComponents().isEmpty());
    }
}

