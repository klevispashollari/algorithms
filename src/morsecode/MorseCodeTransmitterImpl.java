package morsecode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MorseCodeTransmitterImpl implements MorseCodeTransmitter {

    private static final String WORDS_SEPARATOR="0000000";

    @Override
    public String convertMorseCodeToBinary(List<AbstractMorseToken> morseCode) {
        return morseCode.stream().map(AbstractMorseToken::toBinaryString).collect(Collectors.joining(WORDS_SEPARATOR));
    }

    @Override
    public List<AbstractMorseToken> convertBinaryToMorseCodes(String binary) {
        return Arrays.stream(binary.split(WORDS_SEPARATOR)).map(this::splitWord).collect(Collectors.toList());
    }
    private AbstractMorseToken splitWord(String word){
        AbstractMorseToken morseToken = new MorseToken();
        String[] chars = word.split(MorseToken.CHAR_SEPARATOR);
        for (int i = 0; i <chars.length; i++) {
            String morseCode = Arrays.stream(chars[i].split(MorseToken.MORSE_CHAR_SEPARATOR)).map(this::binaryCharToMors).collect(Collectors.joining());
            morseToken.addCode(morseCode);
        }
        return morseToken;
    }

    private String binaryCharToMors(String binaryChar) {
        if (binaryChar.equals("1")) {
            return ".";
        } else if (binaryChar.equals("111")) {
            return "-";
        } else {
            return "";
        }
    }
}
