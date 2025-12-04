package engine;

public interface Engine {
    void loadXml(String filePath);
    void showMachineData(); // returns some machine dto
    void codeManual();
    void codeAutomatic();
    String processMessage(String message);
    void statistics();
}
