package machine.component.machine;

import machine.component.code.Code;

public interface Machine {
    static final int numberOfRotorsInUse = 3;
    void setCode (Code code);
    char process(char input);
}
