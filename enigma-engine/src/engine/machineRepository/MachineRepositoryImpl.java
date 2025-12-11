package engine.machineRepository;

import machine.component.reflector.Reflector;
import machine.component.rotor.Rotor;

import java.util.Map;

public class MachineRepositoryImpl implements MachineRepository {
    private final String alphabet;
    private final Map<Integer, Rotor> rotorsRepository;
    private final Map<String, Reflector> reflectorsRepository;

    public MachineRepositoryImpl(String alphabet, Map<Integer, Rotor> rotorsRepository, Map<String, Reflector> reflectorsRepository ) {
        this.alphabet = alphabet.toUpperCase();
        this.rotorsRepository = rotorsRepository;
        this.reflectorsRepository = reflectorsRepository;
    }

    @Override
    public String getAlphabet() {
        return this.alphabet;
    }

    @Override
    public Rotor getRotorById(int id) throws IllegalArgumentException {
        Rotor rotor = rotorsRepository.get(id);
        if (rotor == null) {
            throw new IllegalArgumentException("Rotor with ID " + id + " does not exist in repository");
        }
        return rotor;
    }

    @Override
    public Reflector getReflectorById(String id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("Reflector ID cannot be null");
        }
        String key = id.trim().toUpperCase(); // כי ב‑MachineBuilder שמרת ככה
        Reflector reflector = reflectorsRepository.get(key);
        if (reflector == null) {
            throw new IllegalArgumentException("Reflector with ID " + key + " does not exist in repository");
        }
        return reflector;
    }

    @Override
    public int getNumberOfDefinedRotors() {
        return rotorsRepository.size();
    }

    @Override
    public  int getNumberOfDefinedReflectors() {
        return reflectorsRepository.size();
    }

    @Override
    public StringBuilder showMachineRepositoryData() {
        StringBuilder machineRepositoryData = new StringBuilder();
        machineRepositoryData.append("Number of rotors in system: ").append(getNumberOfDefinedRotors()).append("\n");
        machineRepositoryData.append("Number of reflectors in system: ").append(getNumberOfDefinedReflectors()).append("\n");
        return machineRepositoryData;
    }
}
