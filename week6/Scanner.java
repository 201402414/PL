package H06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Scanner {
    // return tokens as an Iterator
    public static Iterator<Token> scan(File file) throws FileNotFoundException {
        ScanContext context = new ScanContext(file);
        return new TokenIterator(context);
    }
    public static Stream<Token> stream(File file) throws FileNotFoundException {
        Iterator<Token> tokens = scan(file);
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(tokens, Spliterator.ORDERED), false);
    }
}