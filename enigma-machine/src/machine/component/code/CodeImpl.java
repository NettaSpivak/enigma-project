package machine.component.code;

import machine.component.reflector.Reflector;
import machine.component.rotor.Rotor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CodeImpl implements Code {
    private final List<Rotor> rotors;
    private final List <RotorPosition> rotorPositions; // the chosen code positions for each rotor
    private final Reflector reflector;

    public CodeImpl(List<Rotor> rotors, List<RotorPosition> rotorPositions, Reflector reflector) {
        this.rotors = rotors;
        this.rotorPositions = rotorPositions;
        resetRotorsPositions();
        this.reflector = reflector;
    }

    @Override
    public void resetRotorsPositions() {
        for (RotorPosition rp : rotorPositions) {
            rp.getRotor().initializeRotorPosition(rp.getRotorPosition());
        }
    }

    @Override
    public List<Rotor> getRotors() {
        return this.rotors;
    }

    @Override
    public List<RotorPosition> getRotorPosition() {
        return this.rotorPositions;
    }

    @Override
    public Reflector getReflector() {
        return this.reflector;
    }

    @Override
    public String showCurrentCodeData() {
        List<RotorPosition> currentPositions = new ArrayList<>();
        for (Rotor rotor : rotors) {
            currentPositions.add(new RotorPosition(rotor, rotor.getPosition()));
        }
        return formatCode(this.rotors, currentPositions, this.reflector);
    }

    @Override
    public String showOriginalCodeData() {
        return formatCode(this.rotors, this.rotorPositions, this.reflector);
    }

    private String formatCode (List <Rotor> rotors, List<RotorPosition> rotorPositions, Reflector reflector) {
        StringBuilder codeData = new StringBuilder();
        List<Rotor> reversedRotors = new ArrayList<>(rotors);
        List<RotorPosition> reversedPositions = new ArrayList<>(rotorPositions);
        Collections.reverse(reversedRotors);
        Collections.reverse(reversedPositions);

        codeData.append("<");
        for (Rotor rotor : reversedRotors) {
            codeData.append(rotor.getId());
        }
        codeData.append("><");
        for (RotorPosition rp : reversedPositions) {
            codeData.append(rp.getRotorPosition()).append("(").append(rp.getRotor().calculateNotchDistanceFromIndex(rp.getRotorPositionIndex())).append(")");
        }
        codeData.append("><");
        codeData.append(reflector.getId()).append(">\n");
        return codeData.toString();

    }
}
