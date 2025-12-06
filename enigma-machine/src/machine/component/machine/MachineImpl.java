package machine.component.machine;

import machine.component.code.Code;
import machine.component.keyboard.KeyboardImpl;
import machine.component.rotor.Direction;
import machine.component.keyboard.Keyboard;
import machine.component.rotor.Rotor;

import java.util.List;

public class MachineImpl implements Machine {
    private final Keyboard keyboard;
    private Code code;

    public MachineImpl(String alphabet) {
        this.keyboard = new KeyboardImpl(alphabet);
    }

    @Override
    public void setCode(Code code) {
        this.code = code;
    }

    @Override
    public char process(char input) {
        List<Rotor> rotors = code.getRotors();
        int intermediate = keyboard.processChar(input);
        // advance rotors
        advance(rotors);
        // processChar forward
        for (Rotor rotor : rotors) {
            intermediate = rotor.process(intermediate, Direction.FORWARD);
        }
        intermediate = code.getReflector().process(intermediate);
        // processChar backward
        for (int i = rotors.size() - 1; i >= 0; i--) {
            intermediate = rotors.get(i).process(intermediate, Direction.BACKWARD);
        }
        return keyboard.lightALamp(intermediate);
    }

    private void advance(List<Rotor> rotors) {
        for (Rotor rotor : rotors) {
            if (!rotor.advance()) {
                break;
            }
        }
    }
}
