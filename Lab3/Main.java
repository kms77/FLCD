import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String filename = "C:/Users/komsa/FLCD/Laboratory3-final/Laboratory3-final/src/Files/p1.txt";
        var lexicalScanner = new LexicalScanner(filename);
        lexicalScanner.parseFile();
    }
}
