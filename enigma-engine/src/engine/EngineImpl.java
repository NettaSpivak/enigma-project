package engine;

import machine.component.code.Code;
import machine.component.machine.Machine;

public class EngineImpl implements Engine {
    Machine machine;

    @Override
    public void loadXml(String filePath) {

    }

    @Override
    public void showMachineData() {

    }

    @Override
    public void codeManual() {
        Code code = null;
        // need arguments
        // constract the code from the arguments

        machine.setCode(code);
    }

    @Override
    public void codeAutomatic() {

    }

    @Override
    public String processMessage(String message) {
        String result = "";
        for (char c : message.toCharArray()) {
            result += machine.process(c);
        }
        return result;
    }

    @Override
    public void statistics() {

    }
}
