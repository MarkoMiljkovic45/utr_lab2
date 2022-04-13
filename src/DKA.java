import java.util.Set;
import java.util.TreeSet;

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
        //TODO
    }

    private void removeEquivalentStates() {
        //TODO
    }

    public void minimize() {
        //TODO
    }

    public void print() {
        //TODO
    }
}
