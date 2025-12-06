package engine.engine;

import engine.codeBuilder.CodeBuilder;
import engine.codeBuilder.CodeBuilderImpl;
import engine.machineRepository.MachineRepository;
import engine.utils.Utils;
import generated.BTEEnigma;
import machine.component.code.Code;
import machine.component.code.CodeImpl;
import machine.component.machine.Machine;
import machine.component.machine.MachineImpl;
import machine.component.reflector.Reflector;
import machine.component.rotor.Rotor;
import xmlLoader.MachineRepositoryBuilder;
import xmlLoader.XmlLoader;

import java.util.*;

public class EngineImpl implements Engine {
    MachineRepository machineRepository;
    Machine machine;

    @Override
    public void loadXml(String filePath) throws Exception {
        try {
            BTEEnigma bteEnigma = XmlLoader.loadXml(filePath);
            MachineRepositoryBuilder machineRepositoryBuilder = new MachineRepositoryBuilder();
            this.machineRepository = machineRepositoryBuilder.buildMachineRepository(bteEnigma);
            this.machine = new MachineImpl(this.machineRepository.getAlphabet());
        } catch (Exception e) {
            throw new Exception("Failed to load XML file: " + e.getMessage(), e);
        }
    }

    @Override
    public void showMachineData() {

    }

    @Override
    public void codeManual(String rotors, String rotorsPositions, String reflector) throws IllegalArgumentException {
        // turn rotors ids string into list of ids as integers
        try {
            List<Integer>  rotorsList = Arrays.stream(rotors.split(","))
                    .map(rotor -> Integer.parseInt(rotor.trim())).toList();
            CodeBuilder codeBuilder = new CodeBuilderImpl(machineRepository);
            machine.setCode(codeBuilder.buildCode(rotorsList, rotorsPositions, reflector));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid rotor ID format. Rotor IDs must be integers separated by commas.");
        }
    }

    @Override
    public void codeAutomatic() throws IllegalArgumentException {
        try {
            Random random = new Random();
            // generate random rotors ids
            int numOfRotors = machineRepository.getNumberOfDefinedRotors();
            List<Integer> rotorsIds = new ArrayList<>();
            for (int i = 1; i <= numOfRotors; i++) {
                rotorsIds.add(i);
            }
            Collections.shuffle(rotorsIds);
            // generate random rotors positions
            String randomRotorsPositions = "";
            String alphabet = machineRepository.getAlphabet();
            for (int i = 0; i < Machine.numberOfRotorsInUse; i++) {
                randomRotorsPositions += alphabet.charAt(random.nextInt(alphabet.length()));
            }
            // generate random reflector id
            int numOfReflectors = machineRepository.getNumberOfDefinedReflectors();
            String randomReflectorId = Utils.intToRoman(random.nextInt(numOfReflectors) + 1);
            // build code and set it to machine
            CodeBuilder codeBuilder = new CodeBuilderImpl(machineRepository);
            machine.setCode(codeBuilder.buildCode(rotorsIds.subList(0, 3), randomRotorsPositions, randomReflectorId));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Failed to generate automatic code: " + e.getMessage());
        }
    }

    @Override
    public String processMessage(String message) {
        StringBuilder result = new StringBuilder();
        for (char c : message.toCharArray()) {
            result.append(machine.process(c));
        }
        return result.toString();
    }

    @Override
    public void statistics() {

    }
}
