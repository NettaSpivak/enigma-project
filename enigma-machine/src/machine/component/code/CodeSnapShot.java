package machine.component.code;

import java.util.List;

public class CodeSnapShot {
    private final List<Integer> rotorIds;
    private final List<Character> rotorPosition;
    private final List<Integer> notchDistanceFromWindow;
    private final String reflectorId;

    public CodeSnapShot(List<Integer> rotorIds, List<Character> rotorPosition, List<Integer> notchDistanceFromWindow, String reflectorId) {
        this.rotorIds = rotorIds;
        this.rotorPosition = rotorPosition;
        this.notchDistanceFromWindow = notchDistanceFromWindow;
        this.reflectorId = reflectorId;
    }

    public List<Integer> getRotorIds() {
        return rotorIds;
    }

    public List<Character> getRotorPosition() {
        return rotorPosition;
    }

    public List<Integer> getNotchDistanceFromWindow() {
        return notchDistanceFromWindow;
    }

    public String getReflectorId() {
        return reflectorId;
    }
}