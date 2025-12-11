package engine.history;

import java.util.List;

public class MachineHistory {
    List<String> massages;


    public List<String> GetMachineHistory(List<String> massages) {
        return this.massages;
    }

    public int getNumberOfMessagesInHistory() {
        return massages.size();
    }
}
