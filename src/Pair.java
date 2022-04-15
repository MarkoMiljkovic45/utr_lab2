import java.util.Objects;

public class Pair<T extends Comparable<T>, V extends Comparable<V>> implements Comparable<Pair<T, V>>{

    private T first;
    private V second;

    public Pair() {}
    public Pair(T first, V second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(V second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return (first.equals(pair.first) && second.equals(pair.second)) ||
                (first.equals(pair.second) && second.equals(pair.first));
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public int compareTo(Pair<T, V> o) {
        if (equals(o)) return 0;
        else {
            if (first.compareTo(o.first) == 0) return second.compareTo(o.second);
            else return first.compareTo(o.first);
        }
    }
}
