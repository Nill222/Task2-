package com.project.task.reader;

import com.project.task.exception.CustomException;

import java.util.List;

public interface ReadText {
    List<String> read(String path) throws CustomException;
}
