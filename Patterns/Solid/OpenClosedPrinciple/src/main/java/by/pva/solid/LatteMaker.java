package by.pva.solid;

public class LatteMaker extends AbstractMaker {

	public Cup make() {
		Cup cup = takeNewEmptyCup();
        cup.add("espresso");
        cup.add("milk");
        cup.add("milk");
        cup.add("foam");
        return cup;
    } 
}
