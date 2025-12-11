package engine.engine;

public interface Engine {
    void loadXml(String filePath) throws Exception;
    String showMachineData(); // returns some machine dto
    void codeManual(String rotors, String rotorsPositions, String reflector);
    void codeAutomatic();
    String processMessage(String message);
    void resetCode();
    void statistics();
}
