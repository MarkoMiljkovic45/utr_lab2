import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public final class MinDka {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String token;

        ArrayList<State> states = new ArrayList<>();
        ArrayList<State> acceptableStates = new ArrayList<>();

        DKA dkaMachine = new DKA();

        //1. States
        token = sc.nextLine();
        ArrayList<String> stateNames = new ArrayList<>(Arrays.asList(token.split(",")));
        for (String stateName: stateNames)
            states.add(new State(stateName));

        //2. Symbols
        token = sc.nextLine();
        dkaMachine.getSymbols().addAll(Arrays.asList(token.split(",")));

        //3. Acceptable states
        //TODO Slucaj kad nema prihvatljibog stanja
        token = sc.nextLine();
        ArrayList<String> acceptableStateNames = new ArrayList<>(Arrays.asList(token.split(",")));
        for (String acceptableStateName: acceptableStateNames)
            acceptableStates.add(states.get(states.indexOf(new State(acceptableStateName))));

        //4. Initial state
        String initialStateName = sc.nextLine();
        dkaMachine.setInitialState(states.get(states.indexOf(new State(initialStateName))));

        //5. Transition functions
        while (sc.hasNext()) {
            token = sc.nextLine();

            String oldStateName = token.split("->")[0].split(",")[0];
            String symbol = token.split("->")[0].split(",")[1];
            String newStateName = token.split("->")[1];

            State oldState = states.get(states.indexOf(new State(oldStateName)));
            State newState = states.get(states.indexOf(new State(newStateName)));

            oldState.getTransitions().add(new Transition(oldState, symbol, newState));
        }
        sc.close();

        dkaMachine.getStates().addAll(states);
        dkaMachine.getAcceptableStates().addAll(acceptableStates);

        dkaMachine.minimize();
        dkaMachine.print();
    }

}
