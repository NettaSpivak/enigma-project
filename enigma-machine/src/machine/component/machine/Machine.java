package machine.component.machine;

import machine.component.code.Code;

public interface Machine {
    void setCode (Code code);
    char process(char input);
}
