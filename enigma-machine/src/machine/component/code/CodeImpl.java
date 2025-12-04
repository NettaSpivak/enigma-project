package machine.component.code;

import machine.component.reflector.Reflector;
import machine.component.rotor.Rotor;

import java.util.List;

public class CodeImpl implements Code {
    List<Rotor> rotors;
    List<RotorPosition> rotorPositions;
    Reflector reflector;

    public void setRotors(List<Rotor> rotors) {
        this.rotors = rotors;
    }
    public void setRotorPositions(String positions) throws IllegalArgumentException {
        int i;
        for(i=0; i<positions.length(); i++) {
            try {
                rotorPositions.add(new RotorPosition(rotors.get(i), positions.charAt(i)));
            }catch (IndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Too many rotor positions provided");
            }
        }
        if (i<rotors.size()) {
            throw new IllegalArgumentException("Not enough rotor positions provided");
        }
    }

    @Override
    public List<Rotor> getRotors() {
        return List.of();
    }

    @Override
    public List<RotorPosition> getRotorPosition() {
        return List.of();
    }

    @Override
    public Reflector getReflector() {
        return null;
    }
}
