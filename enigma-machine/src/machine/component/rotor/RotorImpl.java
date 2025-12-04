package machine.component.rotor;

public class RotorImpl implements Rotor{
    private final int id;
    private final int notchPosition;
    private final char[] right;
    private final char[] left;
    private final int alphabetSize;
    private int position;

    RotorImpl(int id, int notchPosition, int alphabetSize, char[] right, char[] left){
        this.id = id;
        this.notchPosition = notchPosition;
        this.alphabetSize = alphabetSize;
        this.right = right;
        this.left = left;
    }

    public void initializeRotorPosition(int position) {
        this.position = position;
    }

    @Override
    public int process(int input, Direction direction) {
        // shift the input index by the rotor position
        int shiftedInput = (input + position) % alphabetSize;
        char letter;
        char[] targetArray;
        if (direction == Direction.FORWARD) {
            letter = right[shiftedInput];
            targetArray = left;
        } else {
            letter = left[shiftedInput];
            targetArray = right;
        }
        for (int i = 0; i < alphabetSize; i++) {
            // find the index of the letter in the target array
            if (targetArray[i] == letter) {
                // reverse shift the output index by the rotor position
                int output = (i - position + alphabetSize) % alphabetSize;
                return output;
            }
        }
        throw new IllegalArgumentException("Invalid input character index: " + input);
    }

    @Override
    public boolean advance() {
        position = (position + 1) % alphabetSize;
        return position == notchPosition;
    }
}
