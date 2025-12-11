package machine.component.rotor;

public interface Rotor {
    int getId();
    char getPosition();
    int getPositionIndex();
    void initializeRotorPosition(char position) throws IllegalArgumentException;
    int process(int input, Direction direction);
    boolean advance();
    int calculateNotchDistanceFromIndex(int ind);
    int getCharIndex(char c);
}
