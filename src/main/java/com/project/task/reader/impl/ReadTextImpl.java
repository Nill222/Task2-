package com.project.task.reader.impl;

import com.project.task.exception.CustomException;
import com.project.task.reader.ReadText;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ReadTextImpl implements ReadText {
    private static final Logger log = LogManager.getLogger();

    @Override
    public List<String> read(String path) throws CustomException {
        Path p = Paths.get(path);

        try {
            return Files.readAllLines(p);
        } catch (IOException e) {
            throw new CustomException(e);
        }
    }
}
