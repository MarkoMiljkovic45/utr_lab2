import java.util.*;

public final class StateEquivalenceTable {

    private final boolean[][] equivalenceTable;
    private final ArrayList<State> states;
    private final Set<String> symbols;
    private final ArrayList<State> acceptableStates;
    private final Map<OrderedPair<State>, Set<OrderedPair<State>>> stateDependencies;

    public StateEquivalenceTable(Collection<State> states, Collection<String> symbols, Collection<State> acceptableStates) {
        this.states = new ArrayList<>(states);
        this.symbols = new TreeSet<>(symbols);
        this.acceptableStates = new ArrayList<>(acceptableStates);
        this.stateDependencies = new HashMap<>();
        equivalenceTable = new boolean[states.size()][states.size()];
        initTable();
    }

    private void initTable() {
        for (int i = 0; i < states.size() - 1; i++) {
            for (int j = i + 1; j < states.size(); j++) {
                if ((acceptableStates.contains(states.get(i)) && !acceptableStates.contains(states.get(j))) ||
                    (!acceptableStates.contains(states.get(i)) && acceptableStates.contains(states.get(j)))) {
                    equivalenceTable[i][j] = false;
                } else {
                    equivalenceTable[i][j] = true;
                }
            }
        }
    }

    private void testState(State firstState, State secondState) {
        int firstStateIndex = states.indexOf(firstState);
        int secondStateIndex = states.indexOf(secondState);

        for (String symbol: symbols) {
            State newFirstState = firstState.getNext(symbol);
            State newSecondState = secondState.getNext(symbol);

            int newFirstStateIndex = states.indexOf(newFirstState);
            int newSecondStateIndex = states.indexOf(newSecondState);

            if (!equivalenceTable[newFirstStateIndex][newSecondStateIndex])
                equivalenceTable[firstStateIndex][secondStateIndex] = false;
            else {
                OrderedPair<State> keyPair = new OrderedPair<>(newFirstState, newSecondState);
                OrderedPair<State> valuePair = new OrderedPair<>(firstState, secondState);
                if (stateDependencies.get(keyPair) == null) {
                    Set<OrderedPair<State>> set = new TreeSet<>();
                    set.add(valuePair);
                    stateDependencies.put(keyPair, set);
                } else {
                    stateDependencies.get(keyPair).add(valuePair);
                }
            }
        }
    }

    private void resolveDependencies() {
        //TODO
    }

    private void testTable() {
        for(int i = 0; i < states.size() - 1; i++) {
            State iState = states.get(i);
            for (int j = i + 1; j < states.size(); j++) {
                State jState = states.get(j);
                testState(iState, jState);
            }
        }

        resolveDependencies();
    }

    public Set<Pair<State, State>> getEquivalentStates() {
        testTable();
        Set<Pair<State, State>> equivalentStates = new TreeSet<>();

        for (int i = 0; i < states.size() - 1; i++) {
            for (int j = i + 1; j < states.size(); j++) {
                if (equivalenceTable[i][j])
                    equivalentStates.add(new Pair<State, State>(states.get(i), states.get(j)));
            }
        }

        return equivalentStates;
    }
}
