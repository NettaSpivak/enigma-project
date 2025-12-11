package machine.component.machine;

import machine.component.code.Code;

public interface Machine {
    static final int numberOfRotorsInUse = 3;
    Code getCode();
    void setCode (Code code);
    void resetCode();
    char process(char input);
    String showCodeData();

}
