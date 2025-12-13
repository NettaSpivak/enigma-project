package engine.codeBuilder;

import engine.machineRepository.MachineRepository;
import engine.utils.Utils;
import machine.component.code.Code;
import machine.component.code.CodeImpl;
import machine.machine.Machine;
import machine.component.reflector.Reflector;
import machine.component.rotor.Rotor;

import java.util.*;

public class CodeBuilderImpl implements CodeBuilder {
    private MachineRepository machineRepository;

    public CodeBuilderImpl(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    @Override
    public Code buildCode(List<Integer> rotors, List<Character> rotorsPositions, String reflector) throws IllegalArgumentException{
       try {
           List<Rotor> rotorsList = getRotors(rotors);
           if (rotorsList.size() != Machine.numberOfRotorsInUse) {
               throw new IllegalArgumentException("Exactly " + Machine.numberOfRotorsInUse + " rotors must be specified.");
           }
           List<Code.RotorPosition> rotorPositionsList = getRotorsPositions(rotorsPositions, rotorsList);
           Reflector reflectorObj = getReflector(reflector);
           Collections.reverse(rotorsList);
           Collections.reverse(rotorPositionsList);
           return new CodeImpl(rotorsList, rotorPositionsList, reflectorObj);
       } catch (IllegalArgumentException e) {
           throw new IllegalArgumentException("Error building code: " + e.getMessage());
       }
    }

    private List<Rotor> getRotors(List<Integer> rotors) throws  IllegalArgumentException {
        List<Rotor> rotorList = new ArrayList<>();
        Set<Integer> rotorIdsSet = new HashSet<>();
        // get rotors from the rotors repository
        for (int rotorId : rotors) {
            if (rotorIdsSet.contains(rotorId)) {
                throw new IllegalArgumentException("Duplicate rotor ID found: " + rotorId);
            }
            rotorList.add(machineRepository.getRotorById(rotorId));
            rotorIdsSet.add(rotorId);
        }
        return rotorList;
    }

    private List<Code.RotorPosition> getRotorsPositions(List<Character> rotorsPositions, List<Rotor> rotorList) throws IllegalArgumentException {
        // get rotors positions
        List<Code.RotorPosition> rotorPositionList = new ArrayList<>();
        if (rotorsPositions.size() != rotorList.size()) {
            throw new IllegalArgumentException("Rotor positions count doesn't mach rotors count.");
        }
        for (int i=0; i<rotorsPositions.size(); i++) {
            Character position = rotorsPositions.get(i);
            // validate positions
            if (!Utils.isInAlphabet(position, machineRepository.getAlphabet())) {
                throw new IllegalArgumentException("Invalid rotor position: " + position);
            }
            rotorPositionList.add(new Code.RotorPosition(rotorList.get(i), position));
        }
        return rotorPositionList;
    }

    private Reflector getReflector(String reflector) {
        // get reflector from the reflector repository
        String reflectorId = reflector.trim().toUpperCase();
        return machineRepository.getReflectorById(reflectorId);
    }
}
