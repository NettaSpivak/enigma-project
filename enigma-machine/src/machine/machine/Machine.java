package machine.machine;

import machine.component.code.Code;
import machine.component.code.CodeSnapShot;

public interface Machine {
    int numberOfRotorsInUse = 3;
    Code getCode();
    void setCode (Code code);
    void resetCode();
    char process(char input);
    CodeSnapShot getCurrentCodeSnapShot();
}
