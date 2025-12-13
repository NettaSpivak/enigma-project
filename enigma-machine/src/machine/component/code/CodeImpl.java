package machine.component.code;

import machine.component.reflector.Reflector;
import machine.component.rotor.Rotor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CodeImpl implements Code, Serializable {
    private final List<Rotor> rotors;
    private final List<RotorPosition> rotorPositions; // the chosen code positions for each rotor
    private final Reflector reflector;

    public CodeImpl(List<Rotor> rotors, List<RotorPosition> rotorPositions, Reflector reflector) {
        this.rotors = rotors;
        this.rotorPositions = rotorPositions;
        resetRotorsCurrentPositions();
        this.reflector = reflector;
    }

    @Override
    public void resetRotorsCurrentPositions() {
        for (RotorPosition rp : this.rotorPositions) {
            rp.getRotor().initializeRotorPosition(rp.getRotorPosition());
        }
    }

    @Override
    public List<Rotor> getRotors() {
        return List.copyOf(this.rotors);
    }

    @Override
    public List<RotorPosition> getRotorsPositionsList() {
        return List.copyOf(this.rotorPositions);
    }

    @Override
    public Reflector getReflector() {
        return this.reflector;
    }

    @Override
    public List<Integer> getRotorsIds() {
        List<Integer> ids = new ArrayList<>();
        for (Rotor rotor : this.rotors) {
            ids.add(rotor.getId());
        }
        return ids;
    }

    @Override
    public List<Character> getRotorsCurrentPositions() {
        List<Character> positions = new ArrayList<>();
        for (Rotor rotor : this.rotors) {
            positions.add(rotor.getPosition());
        }
        return positions;
    }

}