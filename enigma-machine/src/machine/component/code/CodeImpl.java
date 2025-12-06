package machine.component.code;

import machine.component.reflector.Reflector;
import machine.component.rotor.Rotor;

import java.util.List;

public class CodeImpl implements Code {
    private List<Rotor> rotors;
    private List <RotorPosition> rotorPositions; // the chosen code positions for each rotor
    private Reflector reflector;

    public CodeImpl(List<Rotor> rotors, List<RotorPosition> rotorPositions, Reflector reflector) {
        setRotors(rotors);
        setRotorPositions(rotorPositions);
        setReflector(reflector);
    }

    public void setRotors(List<Rotor> rotors) {
        this.rotors = rotors;
    }
    public void setRotorPositions(List <RotorPosition> rotorPositions) {
        for (RotorPosition rp : rotorPositions) {
            rp.getRotor().initializeRotorPosition(rp.getRotorPosition());
        }
        this.rotorPositions = rotorPositions;
    }

    public void setReflector(Reflector reflector) {
        this.reflector = reflector;
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
}
