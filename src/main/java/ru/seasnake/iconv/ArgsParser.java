package ru.seasnake.iconv;

import java.io.File;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ArgsParser {
    private final ArrayList<String> problems = new ArrayList<>();
    private Charset srcCharSet;
    private Charset dstCharSet;
    private final Path srcDir;
    private final Path dstDir;

    public ArgsParser(String... args) {
        try {
            srcCharSet = Charset.forName(args[0]);
        } catch (Exception e) {
            problems.add("Specify srcCharSet correctly");
        }
        try {
            dstCharSet = Charset.forName(args[1]);
        } catch (Exception e) {
            problems.add("Specify dstCharSet correctly");
        }
        srcDir = Paths.get(args[2]);
        File[] srcFileList = srcDir.toFile().listFiles();
        if (srcFileList == null || srcFileList.length == 0)
            problems.add("srcDir must be not empty directory");

        dstDir = Paths.get(args[3]);
        File[] dstFileList = dstDir.toFile().listFiles();
        if (dstFileList == null || dstFileList.length > 0)
            problems.add("dstDir must be empty directory");
    }

    public boolean foundProblems() {
        return !problems.isEmpty();
    }

    public void writeProblems(PrintStream out) {
        out.println("Found problems:");
        for (String problem : problems) {
            out.print('\t');
            out.println(problem);
        }
    }

    public Path getSrcDir() {
        return srcDir;
    }

    public Path getDstDir() {
        return dstDir;
    }

    public Charset getSrcCharSet() {
        return srcCharSet;
    }

    public Charset getDstCharSet() {
        return dstCharSet;
    }

    List<String> getProblems() {
        return problems;
    }
}
