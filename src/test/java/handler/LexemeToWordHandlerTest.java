package handler;

import com.project.task.composite.TextComposite;
import com.project.task.composite.ComponentType;
import com.project.task.parser.LexemeToWordHandler;
import com.project.task.parser.WordToCharacterHandler;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LexemeToWordHandlerTest {

    @Test
    void parseShouldCreateWordCompositeAndCharacters() {
        TextComposite lexemeComposite = new TextComposite(ComponentType.LEXEME);
        LexemeToWordHandler handler = new LexemeToWordHandler();
        handler.setNextParser(new WordToCharacterHandler());

        handler.parse(lexemeComposite, "Hi!");

        assertEquals(2, lexemeComposite.getComponents().size());
        assertEquals("Hi", lexemeComposite.getComponents().get(0).buildText());
        assertEquals("!", lexemeComposite.getComponents().get(1).buildText());
    }
}
