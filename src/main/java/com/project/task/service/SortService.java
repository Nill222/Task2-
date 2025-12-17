package com.project.task.service;

import com.project.task.composite.TextComposite;

import java.util.List;

public interface SortService {
    int findMaxSentencesWithSimilarWords(TextComposite textComposite);
    List<TextComposite> sortSentencesByLexemeCount(TextComposite textComposite);
    void replaceFirstAndLastLexemes(TextComposite textComposite);
}
