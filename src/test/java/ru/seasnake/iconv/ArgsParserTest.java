package ru.seasnake.iconv;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ArgsParserTest {
    @Rule
    public TemporaryFolder sandBox = new TemporaryFolder();
    private File srcDir;
    private File dstDir;

    @Test
    public void allArgsOk() throws IOException {
        mkDirs();
        //noinspection ResultOfMethodCallIgnored
        File.createTempFile("file_1", null, srcDir);
        ArgsParser argsParser = new ArgsParser(
                "windows-1251", "UTF-8",
                srcDir.getAbsolutePath(), dstDir.getAbsolutePath());
        assertFalse(argsParser.foundProblems());
        assertSame(Charset.forName("windows-1251"), argsParser.getSrcCharSet());
        assertSame(Charset.forName("UTF-8"), argsParser.getDstCharSet());
        assertEquals(srcDir.toPath(), argsParser.getSrcDir());
        assertEquals(dstDir.toPath(), argsParser.getDstDir());
    }

    private void mkDirs() throws IOException {
        srcDir = sandBox.newFolder("srcDir");
        dstDir = sandBox.newFolder("dstDir");
    }

    @Test
    public void wrongArgs() throws IOException {
        mkDirs();
        ArgsParser argsParser = new ArgsParser("non-existing1", "non-existing2",
                srcDir.getAbsolutePath(), dstDir.getAbsolutePath());
        assertTrue(argsParser.foundProblems());
        List<String> problems = argsParser.getProblems();
        List<String> expected = Arrays.asList(
                "Specify srcCharSet correctly",
                "Specify dstCharSet correctly",
                "srcDir must be not empty directory"
        );
        assertEquals(expected, problems);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(256);
        argsParser.writeProblems(new PrintStream(baos));
        String out = baos.toString();
        for (String problem : expected)
            assertTrue(out.contains(problem));
    }
}
