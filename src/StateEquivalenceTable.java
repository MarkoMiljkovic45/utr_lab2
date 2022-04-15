import java.util.*;

public final class StateEquivalenceTable {

    private final boolean[][] equivalenceTable;
    private final ArrayList<State> states;

    public StateEquivalenceTable(Collection<State> states) {
        this.states = new ArrayList<>(states);
        equivalenceTable = new boolean[states.size()][states.size()];
        for (int i = 0; i < states.size(); i++)
            for(int j = 0; j < states.size(); j++)
                equivalenceTable[i][j] = true;
    }

    public void test() {
        //TODO
    }

    public Set<Pair<State, State>> getEquivalentStates() {
        Set<Pair<State, State>> equivalentStates = new TreeSet<>();
        //TODO
        return equivalentStates;
    }
}
