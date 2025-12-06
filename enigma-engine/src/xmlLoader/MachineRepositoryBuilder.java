package xmlLoader;

import engine.machineRepository.MachineRepository;
import engine.machineRepository.MachineRepositoryImpl;
import engine.utils.Utils;
import generated.*;
import machine.component.reflector.Reflector;
import machine.component.reflector.ReflectorImpl;
import machine.component.rotor.Rotor;
import machine.component.rotor.RotorImpl;

import java.util.*;

public class MachineRepositoryBuilder {

    public MachineRepository buildMachineRepository(BTEEnigma bteEnigma) throws IllegalArgumentException {
        try {
            String alphabet = bteEnigma.getABC().trim().toUpperCase();
            validateAlphabet(alphabet);
            Map<Integer, Rotor> rotors = RotorsBuilder.buildRotors(bteEnigma.getBTERotors(), alphabet);
            Map<String, Reflector> reflectors = ReflectorsBuilder.buildReflectors(bteEnigma.getBTEReflectors(), alphabet);

            return new MachineRepositoryImpl(alphabet, rotors, reflectors);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("BteEnigma contains illegal arguments: " + e.getMessage(), e);
        }

    }

    private void validateAlphabet(String alphabet) throws IllegalArgumentException {
        if (alphabet == null || alphabet.isEmpty()) {
            throw new IllegalArgumentException("ABC cannot be null or empty.");
        }
        // check for even count of characters
        if (alphabet.length() % 2 != 0) {
            throw new IllegalArgumentException("ABC must have an even number of characters.");
        }
        // check for unique characters
        Set<Character> seen = new HashSet<>();
        for (char c : alphabet.toCharArray()) {
            if (!seen.add(c))
                throw new IllegalArgumentException("ABC contains duplicate character: " + c);
        }
    }

}
