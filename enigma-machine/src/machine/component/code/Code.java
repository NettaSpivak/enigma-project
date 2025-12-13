package machine.component.code;

import machine.component.reflector.Reflector;
import machine.component.rotor.Rotor;

import java.util.List;

public interface Code {
    List<Rotor> getRotors();
    List<RotorPosition> getRotorsPositionsList();
    Reflector getReflector();
    public List<Integer> getRotorsIds();
    public List<Character> getRotorsCurrentPositions();
    void resetRotorsCurrentPositions();



    public static class RotorPosition {
        private final Rotor rotor;
        private final char rotorPosition;
        private final int rotorPositionIndex;

        public RotorPosition(Rotor rotor, char rotorPosition) {
            this.rotor = rotor;
            this.rotorPosition = rotorPosition;
            this.rotorPositionIndex = rotor.getCharIndex(rotorPosition);
        }
        public RotorPosition(Rotor rotor) {
            this.rotor = rotor;
            this.rotorPosition = rotor.getPosition();
            this.rotorPositionIndex = rotor.getPositionIndex();
        }

        public Rotor getRotor() {
            return rotor;
        }
        public char getRotorPosition() {
            return rotorPosition;
        }
       public  int getRotorPositionIndex() {
            return rotorPositionIndex;
       }
    }
}
