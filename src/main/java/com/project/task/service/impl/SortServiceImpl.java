package com.project.task.service.impl;

import com.project.task.composite.TextComponent;
import com.project.task.composite.TextComposite;
import com.project.task.service.SortService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class SortServiceImpl implements SortService {
    private static final Logger log = LogManager.getLogger();
    private static final String REGEX = "[a-zA-Z]+";

    @Override
    public int findMaxSentencesWithSimilarWords(TextComposite textComposite) {
        List<TextComposite> sentence = extractSentences(textComposite);

        Map<String, Set<Integer>> wordToSentence = new HashMap<>();
        for(int i = 0; i < sentence.size(); i++) {
            Set<String> words = normalizeWords(sentence.get(i));
            for(String word : words) {
                wordToSentence
                        .computeIfAbsent(word, k -> new HashSet<>())
                        .add(i);
            }
        }

        return wordToSentence
                .values()
                .stream()
                .mapToInt(Set::size)
                .max()
                .orElse(0);
    }

    @Override
    public List<TextComposite> sortSentencesByLexemeCount(TextComposite textComposite) {
        List<TextComposite> sentences = extractSentences(textComposite);

        return sentences
                .stream()
                .sorted(Comparator.comparingInt(TextComponent::getComponentCount))
                .collect(Collectors.toList());
    }

    @Override
    public void replaceFirstAndLastLexemes(TextComposite textComposite) {
        List<TextComposite> sentences = extractSentences(textComposite);

        for(TextComposite sentence : sentences) {
            List<TextComponent> lexeme = new ArrayList<>(sentence.getComponents());
            if(lexeme.size() > 1) {
               Collections.swap(lexeme, 0, lexeme.size() - 1);
            }
        }
    }

    private List<TextComposite> extractSentences(TextComposite textComposite) {
        List<TextComposite> sentences = new ArrayList<>();

        for(TextComponent paragraph :textComposite.getComponents()) {
            if(paragraph instanceof TextComposite) {
                for(TextComponent sentence : ((TextComposite)paragraph).getComponents()) {
                    if(sentence instanceof TextComposite) {
                        sentences.add((TextComposite)sentence);
                    }
                }
            }
        }
        return sentences;
    }

    private Set<String> normalizeWords(TextComposite textComposite) {
        Set<String> words = new HashSet<>();

        for(TextComponent lexeme : textComposite.getComponents()) {
        if(lexeme instanceof TextComposite) {
            for(TextComponent word : ((TextComposite)lexeme).getComponents()) {
                if(word instanceof TextComposite) {
                    StringBuilder builder = new StringBuilder();
                    for(TextComponent character : ((TextComposite)word).getComponents()) {
                        builder.append(character.buildText());
                    }
                    String normalized = builder.toString()
                            .toLowerCase()
                            .replaceAll(REGEX, "");

                    if(!normalized.isEmpty()) {
                        words.add(normalized);
                    }
                }
            }
        }
        }
        return words;
    }
}
