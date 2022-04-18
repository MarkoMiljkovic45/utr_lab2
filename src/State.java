import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public final class State implements Comparable<State> {

    private String name;
    private boolean visited;
    private final Set<Transition> transitions;

    public State(String name) {
        this.name = name;
        this.visited = false;
        this.transitions = new TreeSet<>();
    }

    public String getName() {
        return name;
    }

    public boolean isVisited() {
        return visited;
    }

    public Set<Transition> getTransitions() {
        return transitions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public State getNext(String symbol) {
        for (Transition transition: transitions)
            if (transition.getSymbol().equals(symbol))
                return transition.getNewState();
        return null;
    }

    private void absorb(State state) {
        for (Transition transition: transitions) {
            if (transition.getNewState().equals(state)) {
                transition.setNewState(this);
            }
        }
    }

    public static void replaceStates(Collection<State> states, State oldState, State newState) {
        if (!states.contains(newState)) return;
        newState.absorb(oldState);
        if (!states.remove(oldState)) return;
        for(State state: states) {
            for (Transition transition: state.getTransitions()) {
                if (transition.getNewState().equals(oldState)) {
                    transition.setNewState(newState);
                }
            }
        }
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return name.equals(state.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(State o) {
        return name.compareTo(o.name);
    }
}
