package machine.component.code;

import machine.component.reflector.Reflector;
import machine.component.rotor.Rotor;

import java.util.List;

public interface Code {
    List<Rotor> getRotors();
    List<RotorPosition> getRotorPosition();
    Reflector getReflector();

    public static class RotorPosition {
        public Rotor rotor;
        public char position;
        //public  RotorPosition() {}
        public RotorPosition(Rotor rotor, char position) {
            this.rotor = rotor;
            this.position = position;
        }
    }
}
