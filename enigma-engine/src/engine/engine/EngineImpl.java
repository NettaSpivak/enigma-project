package engine.engine;

import dto.*;
import engine.codeBuilder.CodeBuilder;
import engine.codeBuilder.CodeBuilderImpl;
import engine.history.CodeHistory;
import engine.history.MachineHistory;
import engine.history.MessageHistory;
import engine.machineRepository.MachineRepository;
import engine.utils.Utils;
import generated.BTEEnigma;
import machine.component.code.Code;
import machine.component.code.CodeSnapShot;
import machine.machine.Machine;
import machine.machine.MachineImpl;
import xmlLoader.MachineRepositoryBuilder;
import xmlLoader.XmlLoader;

import java.io.*;
import java.util.*;

public class EngineImpl implements Engine, Serializable {
    private static final long serialVersionUID = 1L;
    private MachineRepository machineRepository;
    private Machine machine;
    private MachineHistory machineHistory;

    @Override
    public void loadXml(String filePath) throws Exception {
        try {
            BTEEnigma bteEnigma = XmlLoader.loadXml(filePath);
            MachineRepositoryBuilder machineRepositoryBuilder = new MachineRepositoryBuilder();
            this.machineRepository = machineRepositoryBuilder.buildMachineRepository(bteEnigma);
            this.machine = new MachineImpl(this.machineRepository.getAlphabet());
            this.machineHistory = new MachineHistory();
        } catch (Exception e) {
            throw new Exception("Failed to load XML file: " + e.getMessage(), e);
        }
    }

    @Override
    public MachineDataDto showMachineData() {
        int rotorsInSystem = machineRepository.getNumberOfDefinedRotors();
        int reflectorsInSystem = machineRepository.getNumberOfDefinedReflectors();
        int processedMessageCount = machineHistory.getTotalNumberOfProcessedMessages();
        CodeSnapShot originalCodeSnapShot = machineHistory.getCurrentCodeSnapShot();
        CodeSnapShot currentCodeSnapShot = machine.getCurrentCodeSnapShot();
        return new MachineDataDto(rotorsInSystem, reflectorsInSystem, processedMessageCount, generateCodeSnapShotToDto(originalCodeSnapShot), generateCodeSnapShotToDto(currentCodeSnapShot));
    }

    private CodeSnapShotDto generateCodeSnapShotToDto(CodeSnapShot codeSnapShot) {
        if (codeSnapShot == null)
            return null;
        List<Integer> rotorIds = codeSnapShot.getRotorIds().reversed();
        List<Character> rotorsPositions = codeSnapShot.getRotorPosition().reversed();
        List<Integer> notchDistanceFromWindow = codeSnapShot.getNotchDistanceFromWindow().reversed();
        return new CodeSnapShotDto(rotorIds, rotorsPositions, notchDistanceFromWindow, codeSnapShot.getReflectorId());
    }

    @Override
    public void codeManual(CodeSnapShotDto codeSnapShotDto) throws IllegalArgumentException {
        try {
            CodeBuilder codeBuilder = new CodeBuilderImpl(machineRepository);
            Code newCode = codeBuilder.buildCode(codeSnapShotDto.getRotorIds(), codeSnapShotDto.getRotorPosition(), codeSnapShotDto.getReflectorId());
            machine.setCode(newCode);
            machineHistory.addNewCodeToHistory(newCode);
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
            List <Character> randomRotorsPositions = new ArrayList<>();
            String alphabet = machineRepository.getAlphabet();
            for (int i = 0; i < Machine.numberOfRotorsInUse; i++) {
                randomRotorsPositions.add(alphabet.charAt(random.nextInt(alphabet.length())));
            }
            // generate random reflector id
            int numOfReflectors = machineRepository.getNumberOfDefinedReflectors();
            String randomReflectorId = Utils.intToRoman(random.nextInt(numOfReflectors) + 1);
            // build code and set it to machine
            CodeBuilder codeBuilder = new CodeBuilderImpl(machineRepository);
            Code newCode = codeBuilder.buildCode(rotorsIds.subList(0, Machine.numberOfRotorsInUse), randomRotorsPositions, randomReflectorId);
            machine.setCode(newCode);
            machineHistory.addNewCodeToHistory(newCode);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Failed to generate automatic code: " + e.getMessage());
        }
    }

    @Override
    public MessageDto processMessage(MessageDto messagedto) throws IllegalArgumentException {
        try {
            String message = messagedto.getMessage().toUpperCase();
            long startTime = System.nanoTime();
            for (char c : message.toCharArray()) {
                if(!Utils.isInAlphabet(c, machineRepository.getAlphabet())){
                    throw new IllegalArgumentException("Input character " + c + " is not in alphabet");
                }
            }
            StringBuilder result = new StringBuilder();
            for (char c : message.toCharArray()) {
                result.append(machine.process(c));
            }
            long endTime = System.nanoTime();
            long durationNano = endTime - startTime;
            this.machineHistory.addMessageToCode(message, result.toString(), durationNano);
            return new MessageDto(result.toString());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Failed to process message: " + e.getMessage());
        }
    }

    @Override
    public void resetCode(){
        machine.resetCode();
    }


    @Override
    public MachineHistoryDto historyAndStatistics() {
        List<CodeHistoryDto> codeHistoryDto = new ArrayList<>();
        for (CodeHistory codeHistory: machineHistory.getCodeHistory()) {
            CodeSnapShotDto codeSnapShotDto = generateCodeSnapShotToDto(codeHistory.getCodeSnapShot());
            List<MessageHistoryDto> messageHistoryDto = new ArrayList<>();
            for (MessageHistory messageHistory: codeHistory.getMessageHistory()) {
                messageHistoryDto.add(generateMessageHistoryToDto(messageHistory));
            }
            codeHistoryDto.add(new CodeHistoryDto(codeSnapShotDto, messageHistoryDto));
        }
        return new MachineHistoryDto(codeHistoryDto);
    }
    private MessageHistoryDto generateMessageHistoryToDto(MessageHistory messageHistory) {
        if (messageHistory == null) {
            return null;
        }
        return new MessageHistoryDto(messageHistory.getMessage(), messageHistory.getProcessedMessage(), messageHistory.getProcessTimeNano());
    }

    @Override
    public void saveSnapshot(String path) throws RuntimeException {
        if (path == null || path.isBlank()) {
            throw new IllegalArgumentException("Path cannot be empty");
        }

        String fullPath = path.endsWith(".bin") ? path : path + ".bin";

        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(fullPath))) {

            out.writeObject(this);

        } catch (IOException e) {
            throw new RuntimeException("Failed to save machine state" + e.getMessage());
        }
    }

    @Override
    public Engine loadSnapshot(String path) throws RuntimeException {
        if (path == null || path.isBlank()) {
            throw new IllegalArgumentException("Path cannot be empty");
        }

        String fullPath = path.endsWith(".bin") ? path : path + ".bin";

        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(fullPath))) {

            Object obj = in.readObject();

            if (!(obj instanceof EngineImpl)) {
                throw new IllegalArgumentException("Invalid snapshot file");
            }

            return (Engine ) obj;

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to load machine state", e);
        }
    }

    public boolean haveCode() {
        if (this.machine.getCode() == null) return false;
        else return true;
    }
}
