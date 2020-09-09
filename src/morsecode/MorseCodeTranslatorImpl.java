package morsecode;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MorseCodeTranslatorImpl implements MorseCodeTranslator {

    /**
     * Krijojme dy map nga array me characters dhe array me morse codes;
     * @charToMorseMap ben mapimin e charactereve me codin mors perkates;
     * @morseToCharMap ben mapimin e Codit morse me characterin perkates;
     */
    private final Map<Character, String> charToMorseMap;
    private final Map<String, Character> morseToCharMap;

    public MorseCodeTranslatorImpl() {
        charToMorseMap = IntStream.range(0, characters.length).boxed()
                .collect(Collectors.toMap(i -> characters[i], i -> morse[i]));
        morseToCharMap = IntStream.range(0, characters.length).boxed()
                .collect(Collectors.toMap(i -> morse[i], i -> characters[i]));
    }

    /**
     *
     * @param c the character to be converted.
     * @return code mors that corresponds to paramter c from charToMorseMap;
     */
    @Override
    public String convertCharacterToMorse(Character c) {
        Character lowerCaseC = Character.toLowerCase(c);
        return charToMorseMap.get(lowerCaseC);
    }

    /**
     *
     * @param morse the morse code to be converted;
     * @return character that corresponds to morse code from the morseToCharMap;
     */
    @Override
    public char convertMorseToCharacter(String morse) {
        return morseToCharMap.get(morse);
    }

    /**
     * Takes a string splits it by whitespace map every String to TextToken and return the list of TextToken;
     * @param message the message to be tokenized.
     * @return List<TextToken>
     */
    @Override
    public List<TextToken> tokenizeMessage(String message) {
    	// \\s+ --> regular expression = " "
        String[] words = message.split("\\s+");
        return Arrays.stream(words).map(TextToken::new).collect(Collectors.toList());
    }

    @Override
    public List<AbstractMorseToken> encodeMessage(List<TextToken> clearTextMessage) {
        return clearTextMessage.stream().map(this::convertToMorseToken).collect(Collectors.toList());
    }

    /**
     * Takes a TextToken that represents a word and than for each character of word covert it to morse code
     * A new MorseToken is created that contains the list of code mors for each character
     * @param clearTextMessage TextToken to be converted
     * @return AbstractMorseToken
     */
    public AbstractMorseToken convertToMorseToken(TextToken clearTextMessage) {
        AbstractMorseToken morseToken = new MorseToken();
        List<String> tokens = clearTextMessage.getText().chars().mapToObj(c -> (char) c).map(this::convertCharacterToMorse).collect(Collectors.toList());
        morseToken.setCodes(tokens);
        return morseToken;
    }

    @Override
    public List<TextToken> decodeMessage(List<AbstractMorseToken> morseMessage) {
        return morseMessage.stream().map(this::convertToTextToken).collect(Collectors.toList());
    }

    /**
     * Each morse code is converted to charcter than joied to form a word which is used
     * to create a new TextToken;
     * @param morseToken AbstractMorseToken to be converted to TextToken
     * @return TextToken
     */
    public TextToken convertToTextToken(AbstractMorseToken morseToken) {
        String text = morseToken.getCodes().stream().map(this::convertMorseToCharacter).map(Objects::toString).collect(Collectors.joining());
        return new TextToken(text);
    }

    @Override
    public String textTokenListToString(List<TextToken> textTokens) {
        return textTokens.stream().map(TextToken::getText).collect(Collectors.joining(" "));
    }
}
