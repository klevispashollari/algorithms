package morsecode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MorseToken extends AbstractMorseToken {
    public static final String CHAR_SEPARATOR = "000";
    public static final String MORSE_CHAR_SEPARATOR = "0";
    String[] characters = {"--..--", ".-.-.-", "..--.."};

    public MorseToken(List<String> codes) {
        super(codes);
    }

    public MorseToken() {
        super(new ArrayList<>());
    }

    @Override
    public String toBinaryString() {
        return codes.stream().map(this::toBinary).collect(Collectors.joining(CHAR_SEPARATOR));
    }

    private String toBinary(String morseChar) {
        String s = morseChar.chars().mapToObj(c -> (char) c).map(this::morseCharToBinary).collect(Collectors.joining(MORSE_CHAR_SEPARATOR));
        if (morseChar.equals(characters[0]) || morseChar.equals(characters[1]) || morseChar.equals(characters[2])){
            s = "0000" + s;
        }
        return s;
    }

    private String morseCharToBinary(char c) {
        if (c == '.') {
            return "1";
        } else if (c == '-') {
            return "111";
        } else {
            return "";
        }
    }

    @Override
    public String toString() {
        return "MorseToken{" +
                "codes=" + codes +
                '}';
    }

}
