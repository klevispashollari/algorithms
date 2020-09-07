package morsecode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * per cdo pike eshte nje 1, per cdo vize jane 3 njesha. Ndermjet cdo kodi mors(me 1 karakter) 
 * ka nje zero. Ndermjet cdo kodi mors qe perfaqeson nje karakter (k --> .-.-) do ndahet me 3 zero
 * cdo fjale do ndahet me 7 zero
 *
 */
public class MorseCodeTransmitterImpl implements MorseCodeTransmitter {

    // seven 0 that will be added between two words;
    private static final String WORDS_SEPARATOR="0000000";

    /**
     * meret lista me kod morse(fjale), nese eshte fund fjale i shton 7 zerot.
     * nese jemi brenda fjale futemi te binary string.
     */
    @Override
    public String convertMorseCodeToBinary(List<AbstractMorseToken> morseCode) {
        return morseCode.stream().map(AbstractMorseToken::toBinaryString).collect(Collectors.joining(WORDS_SEPARATOR));
    }

    /**
     * ndajme binarin ne fjale duke i bere split me 7 zero se thame qe cdo fjale ndahej me 7 zero.
     * per cdo fjale te marre bejme metoden splitToken qe konverton stringen binare ne list codi morse
     */
    @Override
    public List<AbstractMorseToken> convertBinaryToMorseCodes(String binary) {
        return Arrays.stream(binary.split(WORDS_SEPARATOR)).map(this::splitWord).collect(Collectors.toList());
    }

    /**
	 * per cdo fjale marrim secilin karakter te fjales duke i bere split me 3 zero ( te kunderten e ca bem
	 * kur e konvertuam ne binar nga morsi. dhe per cdo karakter e ndajme ne mors karakter. 
	 * Nese binari eshte 1, karakterin mors e bejme '.' nese eshte 3 njisha e bejme '-'
     * @param word Binary String to be converted to AbstractMorseToken
     * @return AbstractMorseToken
     */
    private AbstractMorseToken splitWord(String word){
        AbstractMorseToken morseToken = new MorseToken();
        String[] chars = word.split(MorseToken.CHAR_SEPARATOR);
        for (int i = 0; i <chars.length; i++) {
            String morseCode = Arrays.stream(chars[i].split(MorseToken.MORSE_CHAR_SEPARATOR)).map(this::binaryCharToMors).collect(Collectors.joining());
            morseToken.addCode(morseCode);
        }
        return morseToken;
    }

    /**
     * convert binary string to morse character "." or "-"
     * @param binaryChar must be an 1 or 111
     * @return morse character
     */
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
