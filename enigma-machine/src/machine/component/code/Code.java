package machine.component.code;

import machine.component.reflector.Reflector;
import machine.component.rotor.Rotor;

import java.util.List;

public interface Code {
    List<Rotor> getRotors();
    List<RotorPosition> getRotorPosition();
    Reflector getReflector();
    void resetRotorsPositions();
    String showCurrentCodeData();
    String showOriginalCodeData();


    public static class RotorPosition {
        private final Rotor rotor;
        private final char rotorPosition;
        private final int rotorPositionIndex;

        public RotorPosition(Rotor rotor, char rotorPosition) {
            this.rotor = rotor;
            this.rotorPosition = rotorPosition;
            this.rotorPositionIndex = rotor.getCharIndex(rotorPosition);
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
