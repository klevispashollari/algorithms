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

import static org.junit.Assert.assertThat;

public class MorseCodeTranslatorTest {

    private static final Path path = Paths.get("./docs/work6/TextToMorse.txt");
    private List<String> txtFileLines;
    private MorseCodeTranslator morseCodeTranslator;

    @Before
    public void setup() throws IOException {
        txtFileLines = Files.lines(path).collect(Collectors.toList());
         morseCodeTranslator = new MorseCodeTranslatorImpl();
    }

    @Test
    public void testEncode(){
        for (int i = 0; i <txtFileLines.size() ; i+=2) {
            List<TextToken> textTokens = morseCodeTranslator.tokenizeMessage(txtFileLines.get(i));
            List<AbstractMorseToken> tokens = morseCodeTranslator.encodeMessage(textTokens);
            System.out.println(tokens);
            List<String> textMorseEncoded = tokens.stream().map(AbstractMorseToken::getCodes).flatMap(List::stream).collect(Collectors.toList());
            List<String> textMorse = splitString(txtFileLines.get(i+1));
            Assert.assertEquals(textMorse,textMorseEncoded);
        }
    }

    @Test
    public void testDecode(){
        for (int i = 0; i <txtFileLines.size() ; i+=2) {
            List<TextToken> textTokens = morseCodeTranslator.tokenizeMessage(txtFileLines.get(i));
            List<AbstractMorseToken> tokens = morseCodeTranslator.encodeMessage(textTokens);
            List<TextToken> textTokensDecode = morseCodeTranslator.decodeMessage(tokens);
            String decodeText = morseCodeTranslator.textTokenListToString(textTokensDecode);
            Assert.assertEquals(txtFileLines.get(i).toLowerCase(),decodeText);
        }
    }

    private List<String> splitString(String str){
        String[] words = str.split("\\s+");
        return Arrays.stream(words).collect(Collectors.toList());
     }

}
