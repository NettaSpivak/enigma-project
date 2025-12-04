package machine.component.reflector;

public class ReflectorImpl implements Reflector {
    private final String id;
    private final int[] mapping;

    public ReflectorImpl(String id, int[] mapping) {
        this.id = id;
        this.mapping = mapping;
    }

    @Override
    public int process(int input) {
        return mapping[input];
    }
}
