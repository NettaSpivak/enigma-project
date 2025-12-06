package engine.codeBuilder;

import machine.component.code.Code;

import java.util.List;

public interface CodeBuilder {
    Code buildCode(List<Integer> rotors, String rotorsPositions, String reflector);
}
