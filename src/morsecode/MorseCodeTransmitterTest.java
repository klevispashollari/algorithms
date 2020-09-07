package morsecode;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MorseCodeTransmitterTest {

    private static final Path path = Paths.get("./docs/work6/TextToBinary.txt");
    private static final Path path1 = Paths.get("./docs/work6/TextToMorse.txt");

    private List<String> txtFileLines;
    private List<String> txtFileMorseLines;

    private MorseCodeTransmitter morseCodeTransmitter;
    private MorseCodeTranslator morseCodeTranslator;

    @Before
    public void setup() throws IOException {
        txtFileLines = Files.lines(path).collect(Collectors.toList());
        txtFileMorseLines = Files.lines(path1).collect(Collectors.toList());
        morseCodeTransmitter = new MorseCodeTransmitterImpl();
        morseCodeTranslator = new MorseCodeTranslatorImpl();
    }

    /**
     * merr fjaline anglisht e kthen ne morse, kodin mors e konverton ne binare dhe teston nese 
     * konvertimi eshte i barabarte me rreshtin pasardhes apo jo. Kjo per cdo rast
     */
    @Test
    public void convertToBinaryTest(){
        for (int i = 0; i <txtFileLines.size() ; i+=2) {
            List<AbstractMorseToken> morseTokens = morseCodeTranslator.encodeMessage(morseCodeTranslator.tokenizeMessage(txtFileLines.get(i)));
            String binary = morseCodeTransmitter.convertMorseCodeToBinary(morseTokens);
            Assert.assertEquals(txtFileLines.get(i+1), binary);
        }
    }

    /**
     * merr fjaline me kod mors nga textToMorse. Konverton ne text morse kodin binare ne filen
     * TextToBinare. dhe teston nese ky kod mors eshte i njejte me kodin mors qe u mor nga textToMorse.
     * 
     */
    @Test
    public void convertBinaryToMorseCodeTest(){
        for (int i = 1; i <txtFileLines.size() ; i+=2) {
            List<String> morseCodeFromFile = splitString(txtFileMorseLines.get(i));
            List<AbstractMorseToken> morseCodes = morseCodeTransmitter.convertBinaryToMorseCodes(txtFileLines.get(i));
            List<String> allMorseCodes = morseCodes.stream().map(AbstractMorseToken::getCodes).flatMap(List::stream).collect(Collectors.toList());
            Assert.assertEquals(morseCodeFromFile, allMorseCodes);
        }
    }
    private List<String> splitString(String str){
        String[] words = str.split("\\s+");
        return Arrays.stream(words).collect(Collectors.toList());
    }

}
