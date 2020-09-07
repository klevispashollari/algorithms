package morsecode;

import java.util.ArrayList;
import java.util.List;

public class MorseToken  extends AbstractMorseToken{

    public MorseToken(List<String> codes) {
        super(codes);
    }

    public MorseToken() {
        super(new ArrayList<>());
    }

    @Override
    public String toString() {
        return "MorseToken{" +
                "codes=" + codes +
                '}';
    }

    @Override
    public String toBinaryString() {
        return null;
    }
}
