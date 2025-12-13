package ui.menu.actions;

import dto.CodeSnapShotDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SetCodeManual {
    public static CodeSnapShotDto setCodeMenual(String rotors_, String rotorsPositions_, String reflector){
        // rotors string to ids list
        String rotors = rotors_.trim();
        String rotorsPositions = rotorsPositions_.trim();
        List<Integer> rotorsList = Arrays.stream(rotors.split(","))
                .map(rotor -> Integer.parseInt(rotor.trim())).toList();
        // positions string to list
        List<Character> positionsList = new ArrayList<>();
        rotorsPositions = rotorsPositions.trim().toUpperCase();
        for (Character c : rotorsPositions.toCharArray()) {
            positionsList.add(c);
        }
        return new CodeSnapShotDto(rotorsList, positionsList, reflector.trim());
    }
}
