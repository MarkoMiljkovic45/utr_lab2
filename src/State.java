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
