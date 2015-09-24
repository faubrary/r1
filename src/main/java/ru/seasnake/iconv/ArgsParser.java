package ru.seasnake.iconv;

import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Path;

public class ArgsParser {
    public ArgsParser(String[] args) {
    }

    public boolean foundProblems() {
        return true;
    }

    public void writeProblems(PrintStream out) {

    }

    public Path getSrcDir() {
        return null;
    }

    public Path getDstDir() {
        return null;
    }

    public Charset getSrcCharSet() {
        return null;
    }

    public Charset getDstCharSet() {
        return null;
    }
}
