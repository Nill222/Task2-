package service;

import com.project.task.composite.CharacterLeaf;
import com.project.task.composite.ComponentType;
import com.project.task.composite.TextComposite;
import com.project.task.service.SortService;
import com.project.task.service.impl.SortServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SortServiceTest {
    private SortService sortService;
    private TextComposite textComposite;

    @BeforeEach
    void setUp() {
        sortService = new SortServiceImpl();

        textComposite = new TextComposite(ComponentType.TEXT);
        TextComposite paragraph = new TextComposite(ComponentType.PARAGRAPH);
        textComposite.add(paragraph);

        TextComposite sentence1 = new TextComposite(ComponentType.SENTENCE);
        paragraph.add(sentence1);

        TextComposite lexeme1 = new TextComposite(ComponentType.LEXEME);
        sentence1.add(lexeme1);

        TextComposite word1 = new TextComposite(ComponentType.WORD);
        lexeme1.add(word1);
        word1.add(new CharacterLeaf('H'));
        word1.add(new CharacterLeaf('i'));

        TextComposite sentence2 = new TextComposite(ComponentType.SENTENCE);
        paragraph.add(sentence2);

        TextComposite lexeme2 = new TextComposite(ComponentType.LEXEME);
        sentence2.add(lexeme2);

        TextComposite word2 = new TextComposite(ComponentType.WORD);
        lexeme2.add(word2);
        word2.add(new CharacterLeaf('H'));
        word2.add(new CharacterLeaf('e'));
        word2.add(new CharacterLeaf('l'));
        word2.add(new CharacterLeaf('l'));
        word2.add(new CharacterLeaf('o'));
    }

    @Test
    void testFindMaxSentencesWithSimilarWords() {
        int result = sortService.findMaxSentencesWithSimilarWords(textComposite);
        assertTrue(result >= 1);
    }

    @Test
    void testSortSentencesByLexemeCount() {
        List<TextComposite> sorted = sortService.sortSentencesByLexemeCount(textComposite);

        assertEquals(2, sorted.size());
        assertTrue(sorted.get(0).buildText().contains("Hi"));
        assertTrue(sorted.get(1).buildText().contains("Hello"));
    }

    @Test
    void testReplaceFirstAndLastLexemes() {
        TextComposite sentence = new TextComposite(ComponentType.SENTENCE);

        TextComposite firstLexeme = new TextComposite(ComponentType.LEXEME);
        TextComposite firstWord = new TextComposite(ComponentType.WORD);
        firstWord.add(new CharacterLeaf('A'));
        firstLexeme.add(firstWord);

        TextComposite lastLexeme = new TextComposite(ComponentType.LEXEME);
        TextComposite lastWord = new TextComposite(ComponentType.WORD);
        lastWord.add(new CharacterLeaf('Z'));
        lastLexeme.add(lastWord);

        sentence.add(firstLexeme);
        sentence.add(lastLexeme);

        TextComposite paragraph = (TextComposite) textComposite.getComponents().get(0);
        paragraph.add(sentence);

        sortService.replaceFirstAndLastLexemes(textComposite);

        TextComposite modifiedSentence = (TextComposite) paragraph.getComponents().get(2);
        String firstLexemeText = modifiedSentence.getComponents().get(0).buildText();
        String lastLexemeText = modifiedSentence.getComponents()
                .get(modifiedSentence.getComponents().size() - 1)
                .buildText();

        assertEquals("Z", firstLexemeText);
        assertEquals("A", lastLexemeText);
    }
}
