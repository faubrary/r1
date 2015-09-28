package ru.seasnake.iconv;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
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
    public void allArgs() throws IOException {
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
    public void withoutDstDir() throws IOException {
        fail();
    }

    @Test
    public void wrongCharset() throws IOException {
        mkDirs();
        ArgsParser argsParser = new ArgsParser("-f", "cp-1251", "-t", "UTF-8",
                srcDir.getAbsolutePath(), dstDir.getAbsolutePath());
        assertTrue(argsParser.foundProblems());
        List<String> problems = argsParser.getProblems();
        assertEquals(Arrays.asList("Wrong charset cp-1251"), problems);
    }
}