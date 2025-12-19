package composite;

import com.project.task.composite.CharacterLeaf;
import com.project.task.composite.ComponentType;
import com.project.task.composite.TextComponent;
import com.project.task.composite.TextComposite;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TextCompositeTest {

    @Test
    void addAndRemoveComponents() {
        TextComposite word = new TextComposite(ComponentType.WORD);
        CharacterLeaf c1 = new CharacterLeaf('h');
        CharacterLeaf c2 = new CharacterLeaf('i');

        word.add(c1);
        word.add(c2);

        assertEquals(2, word.getComponents().size());
        assertTrue(word.getComponents().contains(c1));

        word.remove(c1);
        assertEquals(1, word.getComponents().size());
        assertFalse(word.getComponents().contains(c1));
    }

    @Test
    void buildTextShouldConcatenateChildrenWithDelimiter() {
        TextComposite sentence = new TextComposite(ComponentType.SENTENCE);
        sentence.add(new CharacterLeaf('H'));
        sentence.add(new CharacterLeaf('i'));

        // SENTENCE delimiter = " "
        assertEquals("H i ", sentence.buildText());
    }

    @Test
    void getComponentCountShouldSumChildren() {
        TextComposite word = new TextComposite(ComponentType.WORD);
        word.add(new CharacterLeaf('a'));
        word.add(new CharacterLeaf('b'));
        word.add(new CharacterLeaf('c'));

        assertEquals(3, word.getComponentCount());
    }

    @Test
    void getComponentsShouldReturnUnmodifiableList() {
        TextComposite word = new TextComposite(ComponentType.WORD);
        word.add(new CharacterLeaf('x'));

        List<TextComponent> components = word.getComponents();
        assertThrows(UnsupportedOperationException.class, () -> components.add(new CharacterLeaf('y')));
    }
}
