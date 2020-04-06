package by.pva.solid;

public class EspressoMaker extends AbstractMaker {
    @Override
    public Cup make() {
        Cup cup = takeNewEmptyCup();
        cup.add("espresso");
        return cup;
    }
}
