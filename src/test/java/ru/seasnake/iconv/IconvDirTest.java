package ru.seasnake.iconv;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.junit.Assert.fail;

public class IconvDirTest {
    @Rule
    public TemporaryFolder sandBox = new TemporaryFolder();

    @Test
    public void direct() {
        System.out.println(sandBox.getRoot());
        fail();
    }
}