package ru.seasnake.iconv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.Set;

public class Main {
    public static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("{}={}", "args", args);
        if (args.length != 4) {
            System.out.println("Usage: java " + Main.class.getName() + " srcCharSet dstCharSet srcDir dstDir");
            System.out.println("AvailableCharsets: ");
            Set<String> availCSs = Charset.availableCharsets().keySet();
            for (String availCS : availCSs)
                System.out.println('\t' + availCS);
        }
        ArgsParser argsParser = new ArgsParser(args);
        if (argsParser.foundProblems()) {
            argsParser.writeProblems(System.out);
            return;
        }
        new IconvDir(
                argsParser.getSrcDir(), argsParser.getSrcCharSet(),
                argsParser.getDstDir(), argsParser.getDstCharSet()
        );
    }
}
