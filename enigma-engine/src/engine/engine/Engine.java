package engine.engine;

public interface Engine {
    void loadXml(String filePath) throws Exception;
    void showMachineData(); // returns some machine dto
    void codeManual(String rotors, String rotorsPositions, String reflector);
    void codeAutomatic();
    String processMessage(String message);
    void statistics();
}
