import java.util.Objects;

public class Transition implements Comparable<Transition> {

    private State oldState;
    private String symbol;
    private State newState;

    public Transition(State oldState, String symbol, State newState) {
        this.oldState = oldState;
        this.symbol = symbol;
        this.newState = newState;
    }

    public State getOldState() {
        return oldState;
    }

    public String getSymbol() {
        return symbol;
    }

    public State getNewState() {
        return newState;
    }

    public void setOldState(State oldState) {
        this.oldState = oldState;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setNewState(State newState) {
        this.newState = newState;
    }

    @Override
    public String toString() {
        return oldState.getName() + "," + symbol + "->" + newState.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transition that = (Transition) o;
        return oldState.equals(that.oldState) && symbol.equals(that.symbol) && newState.equals(that.newState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oldState, symbol, newState);
    }

    @Override
    public int compareTo(Transition o) {
        return symbol.compareTo(o.symbol);
    }
}
