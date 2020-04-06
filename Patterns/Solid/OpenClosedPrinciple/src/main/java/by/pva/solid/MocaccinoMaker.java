package by.pva.solid;

public class MocaccinoMaker extends AbstractMaker {

    public Cup make() {
        Cup cup = takeNewEmptyCup();
        cup.add("espresso");
        cup.add("milk");
        cup.add("milk");
        cup.add("foam");
        cup.add("chocolate");
        return cup;
    }
}
