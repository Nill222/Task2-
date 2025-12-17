package com.project.task.parser;

import com.project.task.composite.TextComposite;

public abstract class AbstractTextHandler {
    protected  AbstractTextHandler nextParser;

    public void setNextParser(AbstractTextHandler nextParser) {
        this.nextParser = nextParser;
    }

    public abstract void parse(TextComposite composite, String text);
}
