package engine.codeBuilder;

import engine.machineRepository.MachineRepository;
import engine.utils.Utils;
import machine.component.code.Code;
import machine.component.code.CodeImpl;
import machine.component.machine.Machine;
import machine.component.reflector.Reflector;
import machine.component.rotor.Rotor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CodeBuilderImpl implements CodeBuilder {
    private MachineRepository machineRepository;

    public CodeBuilderImpl(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    @Override
    public Code buildCode(List<Integer> rotors, String rotorsPositions, String reflector) throws IllegalArgumentException{
       try {
           List<Rotor> rotorsList = getRotors(rotors);
           if (rotorsList.size() != Machine.numberOfRotorsInUse) {
               throw new IllegalArgumentException("Exactly " + Machine.numberOfRotorsInUse + " rotors must be specified.");
           }
           List<Code.RotorPosition> rotorPositionsList = getRotorsPositions(rotorsPositions, rotorsList);
           Reflector reflectorObj = getReflector(reflector);
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
        return  rotorList;
    }

    private List<Code.RotorPosition> getRotorsPositions(String rotorsPositions, List<Rotor> rotorList) throws IllegalArgumentException {
        // get rotors positions
        List<Code.RotorPosition> rotorPositionList = new ArrayList<>();
        rotorsPositions = rotorsPositions.trim();
        int i;
        for (i = 0; i < rotorsPositions.length(); i++) {
            char position = rotorsPositions.charAt(i);
            // validate positions
            if (!Utils.isInAlphabet(position, machineRepository.getAlphabet())) {
                throw new IllegalArgumentException("Invalid rotor position: " + position);
            }
            if (i >= rotorList.size()) {
                throw new IllegalArgumentException("More rotor positions provided than rotors.");
            }
            rotorPositionList.add(new Code.RotorPosition(rotorList.get(i), position));
        }
        if (i < rotorList.size()) {
            throw new IllegalArgumentException("Fewer rotor positions provided than rotors.");
        }
        return rotorPositionList;
    }

    private Reflector getReflector(String reflector) {
        // get reflector from the reflector repository
        String reflectorId = reflector.trim().toUpperCase();
        return machineRepository.getReflectorById(reflectorId);
    }
}
