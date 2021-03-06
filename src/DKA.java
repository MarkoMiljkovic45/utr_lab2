import java.util.*;

public final class DKA {

    private final Set<State> states;
    private final Set<String> symbols;
    private final Set<State> acceptableStates;
    private State initialState;

    public DKA() {
        states = new TreeSet<>();
        symbols = new TreeSet<>();
        acceptableStates = new TreeSet<>();
    }

    public Set<State> getStates() {
        return states;
    }

    public Set<String> getSymbols() {
        return symbols;
    }

    public Set<State> getAcceptableStates() {
        return acceptableStates;
    }

    public State getInitialState() {
        return initialState;
    }

    public void setInitialState(State initialState) {
        this.initialState = initialState;
    }

    private void removeUnreachableStates() {
        states.forEach(state -> state.setVisited(false));
        Set<State> reachableStates= new TreeSet<>();

        Stack<State> stack = new Stack<>();
        stack.push(initialState);
        while(!stack.empty()) {
            State state = stack.pop();
            if (!state.isVisited()) {
                reachableStates.add(state);
                state.setVisited(true);
                state.getTransitions().forEach(transition -> {
                    if (!transition.getNewState().isVisited())
                        stack.push(transition.getNewState());
                });
            }
        }

        Set<State> unreachableStates = new TreeSet<>();
        for (State state: states)
            if (!reachableStates.contains(state))
                unreachableStates.add(state);

        for (State state: unreachableStates) {
            states.remove(state);
            acceptableStates.remove(state);
        }
    }

    private void removeEquivalentStates() {
        StateEquivalenceTable stateEquivalenceTable = new StateEquivalenceTable(states, symbols, acceptableStates);
        Set<Pair<State, State>> equivalentStates = stateEquivalenceTable.getEquivalentStates();
        for (Pair<State,State> pair: equivalentStates) {
            OrderedPair<State> orderedPair = new OrderedPair<>(pair);
            State.replaceStates(states, orderedPair.getSecond(), orderedPair.getFirst());
            states.remove(pair.getSecond());
            acceptableStates.remove(pair.getSecond());
            if (initialState == orderedPair.getSecond()) initialState = orderedPair.getFirst();
        }
    }

    public void minimize() {
        removeUnreachableStates();
        removeEquivalentStates();
    }

    public void print() {
        String strStates = states.toString();
        strStates = strStates.substring(1, strStates.length() - 1).replaceAll(" ", "");

        String strSymbols = symbols.toString();
        strSymbols = strSymbols.substring(1, strSymbols.length() - 1).replaceAll(" ", "");

        String strAcceptableStates = acceptableStates.toString();
        strAcceptableStates = strAcceptableStates.substring(1, strAcceptableStates.length() - 1).replaceAll(" ", "");

        System.out.println(strStates);
        System.out.println(strSymbols);
        System.out.println(strAcceptableStates);
        System.out.println(initialState);
        for (State state: states)
            state.getTransitions().forEach(System.out::println);
    }
}
