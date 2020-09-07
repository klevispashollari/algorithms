package morsecode;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MorseCodeTranslatorImpl implements MorseCodeTranslator {

    private final Map<Character, String> charToMorseMap;
    private final Map<String, Character> morseToCharMap;

    public MorseCodeTranslatorImpl() {
        charToMorseMap = IntStream.range(0, characters.length).boxed()
                .collect(Collectors.toMap(i -> characters[i], i -> morse[i]));
        morseToCharMap = IntStream.range(0, characters.length).boxed()
                .collect(Collectors.toMap(i -> morse[i], i -> characters[i]));
    }

    @Override
    public String convertCharacterToMorse(Character c) {
        Character lowerCaseC = Character.toLowerCase(c);
        return charToMorseMap.get(lowerCaseC);
    }

    @Override
    public char convertMorseToCharacter(String morse) {
        return morseToCharMap.get(morse);
    }

    @Override
    public List<TextToken> tokenizeMessage(String message) {
        String[] words = message.split("\\s+");
        return Arrays.stream(words).map(TextToken::new).collect(Collectors.toList());
    }

    @Override
    public List<AbstractMorseToken> encodeMessage(List<TextToken> clearTextMessage) {
        return clearTextMessage.stream().map(this::convertToMorseToken).collect(Collectors.toList());
    }

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

    public TextToken convertToTextToken(AbstractMorseToken morseToken) {
        String text = morseToken.getCodes().stream().map(this::convertMorseToCharacter).map(Objects::toString).collect(Collectors.joining());
        return new TextToken(text);
    }

    @Override
    public String textTokenListToString(List<TextToken> textTokens) {
        return textTokens.stream().map(TextToken::getText).collect(Collectors.joining(" "));
    }
}
