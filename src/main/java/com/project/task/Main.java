package com.project.task;

import com.project.task.composite.ComponentType;
import com.project.task.composite.TextComposite;
import com.project.task.exception.CustomException;
import com.project.task.parser.*;
import com.project.task.reader.ReadText;
import com.project.task.reader.impl.ReadTextImpl;
import com.project.task.service.SortService;
import com.project.task.service.impl.SortServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Paths;
import java.util.List;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final String path = Paths.get("data", "text.txt").toString();

    public static void main(String[] args) {
        ReadText reader = new ReadTextImpl();
        SortService sortService = new SortServiceImpl();

        TextToParagraphHandler textParser = new TextToParagraphHandler();
        ParagraphToSentenceHandler paragraphParser = new ParagraphToSentenceHandler();
        SentenceToLexemeHandler sentenceParser = new SentenceToLexemeHandler();
        LexemeToWordHandler lexemeParser = new LexemeToWordHandler();
        WordToCharacterHandler wordParser = new WordToCharacterHandler();

        textParser.setNextParser(paragraphParser);
        paragraphParser.setNextParser(sentenceParser);
        sentenceParser.setNextParser(lexemeParser);
        lexemeParser.setNextParser(wordParser);

        try {
            List<String> lines = reader.read(path);
            String fullText = String.join("\n", lines);

            TextComposite textComposite = new TextComposite(ComponentType.TEXT);
            textParser.parse(textComposite, fullText);

            logger.info("Максимум предложений с одинаковыми словами: {}",
                    sortService.findMaxSentencesWithSimilarWords(textComposite));

            logger.info("\nСортировка предложений по количеству лексем:");
            sortService.sortSentencesByLexemeCount(textComposite)
                    .forEach(s -> logger.info(s.buildText()));

            logger.info("\nЗамена первого и последнего лексем:");
            sortService.replaceFirstAndLastLexemes(textComposite);
            sortService.sortSentencesByLexemeCount(textComposite)
                    .forEach(s -> logger.info(s.buildText()));

            logger.info("Полный текст после обработки:\n{}", textComposite.buildText());

        } catch (CustomException e) {
            logger.error("Ошибка чтения файла: {}", e.getMessage(), e);
        }
    }
}
