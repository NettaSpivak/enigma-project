package dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodeSnapShotDto {
    private List<Integer> rotorIds;
    private List<Character> RotorPosition;
    private List<Integer> notchDistanceFromWindow;
    private String reflectorId;

    public CodeSnapShotDto(List<Integer> rotorIds, List<Character> RotorPosition, List<Integer> notchDistanceFromWindow, String reflectorId) {
        this.rotorIds = rotorIds;
        this.RotorPosition = RotorPosition;
        this.notchDistanceFromWindow = notchDistanceFromWindow;
        this.reflectorId = reflectorId;
    }
    public CodeSnapShotDto(List<Integer> rotorIds, List<Character> RotorPosition, String reflectorId) {
        this.rotorIds = rotorIds;
        this.RotorPosition = RotorPosition;
        this.notchDistanceFromWindow = null;
        this.reflectorId = reflectorId;
    }

    public List<Integer> getRotorIds() {
        return this.rotorIds;
    }

    public List<Character> getRotorPosition() {
        return this.RotorPosition;
    }

    public List<Integer> getNotchDistanceFromWindow() {
        return this.notchDistanceFromWindow;
    }

    public String getReflectorId() {
        return this.reflectorId;
    }
}
