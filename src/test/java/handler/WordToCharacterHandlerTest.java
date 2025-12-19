package handler;

import com.project.task.composite.TextComposite;
import com.project.task.composite.ComponentType;
import com.project.task.parser.WordToCharacterHandler;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WordToCharacterHandlerTest {

    @Test
    void parseShouldSplitWordIntoCharacters() {
        TextComposite wordComposite = new TextComposite(ComponentType.WORD);
        WordToCharacterHandler handler = new WordToCharacterHandler();

        handler.parse(wordComposite, "Hi");

        assertEquals(2, wordComposite.getComponents().size());
        assertEquals("H", wordComposite.getComponents().get(0).buildText());
        assertEquals("i", wordComposite.getComponents().get(1).buildText());
    }
}
