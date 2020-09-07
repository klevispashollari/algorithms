package morsecode;

import java.util.List;

public abstract class AbstractMorseToken {

    List<String> codes;

    AbstractMorseToken(List<String> codes) {
        this.codes = codes;
    }

    public List<String> getCodes() {
        return codes;
    }

    public boolean addCode(String code) {
        return codes.add(code);
    }

    public void setCodes(List<String> codes) {
        this.codes = codes;
    }

    public abstract String toBinaryString();

}
