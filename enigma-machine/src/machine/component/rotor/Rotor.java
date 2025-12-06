package machine.component.rotor;

public interface Rotor {
    void initializeRotorPosition(char position) throws IllegalArgumentException;
    int process(int input, Direction direction);
    boolean advance();
}
