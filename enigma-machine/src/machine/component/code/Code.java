package machine.component.code;

import machine.component.reflector.Reflector;
import machine.component.rotor.Rotor;

import java.util.List;

public interface Code {
    List<Rotor> getRotors();
    List<RotorPosition> getRotorPosition();
    Reflector getReflector();

    public static class RotorPosition {
        private Rotor rotor;
        private char rotorPosition;

        public RotorPosition(Rotor rotor, char rotorPosition) {
            this.rotor = rotor;
            this.rotorPosition = rotorPosition;
        }
        public Rotor getRotor() {
            return rotor;
        }
        public char getRotorPosition() {
            return rotorPosition;
        }
    }
}
