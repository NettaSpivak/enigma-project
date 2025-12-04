package machine.component.keyboard;

import java.util.HashMap;
import java.util.Map;

public class KeyboardImpl implements Keyboard {
    private final char[] alphabet;
    private final Map<Character, Integer> charToIndexMap = new HashMap<>();

    public KeyboardImpl(String alphabet) {
        this.alphabet = alphabet.toUpperCase().toCharArray();
        for (int i = 0; i < this.alphabet.length; i++) {
            charToIndexMap.put(this.alphabet[i], i);
        }
    }

    public char[] getAlphabet() {
        return alphabet;
    }

    @Override
    public int processChar(char input) {
        return charToIndexMap.get(Character.toUpperCase(input));
    }

    @Override
    public char lightALamp(int output) {
        return alphabet[output];
    }
}
