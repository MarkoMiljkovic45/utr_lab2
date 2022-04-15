public class OrderedPair<T extends Comparable<T>> extends Pair<T, T>{

    public OrderedPair(T first, T second) {
        if (first.compareTo(second) < 0) {
            setFirst(second);
            setSecond(first);
        }
    }

    public OrderedPair(Pair<T, T> pair) {
        this(pair.getFirst(), pair.getSecond());
    }
}
