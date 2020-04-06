package by.pva.solid;

public class AmericanoMaker extends AbstractMaker {
    @Override
    public Cup make() {
        Cup cup = takeNewEmptyCup();
        cup.add("espresso");
        cup.add("water");
        return cup;
    }
}
