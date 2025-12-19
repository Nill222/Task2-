package composite;

import com.project.task.composite.CharacterLeaf;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CharacterLeafTest {

    @Test
    void buildTextShouldReturnCharacterAsString() {
        CharacterLeaf leaf = new CharacterLeaf('a');
        assertEquals("a", leaf.buildText());
    }

    @Test
    void getComponentCountShouldReturnOne() {
        CharacterLeaf leaf = new CharacterLeaf('z');
        assertEquals(1, leaf.getComponentCount());
    }
}
