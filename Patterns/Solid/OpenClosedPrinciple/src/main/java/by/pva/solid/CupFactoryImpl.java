package by.pva.solid;

import static java.util.Arrays.asList;

public class CupFactoryImpl implements CupFactory {

    public Cup getCup() {
        Cup cup = new Cup();
        cup.setZeroIngredients(asList("espresso", "water", "milk", "foam"));
        return cup;
    }
}
