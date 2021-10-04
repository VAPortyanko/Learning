package by.pva.servletapi.jsp.el.function;

public class DiceRoller {
	public static int rollDice() {
		return (int) ((Math.random() * 6) +1 );
	}
}
