package by.pva.solid;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CoffeeMachineTest {

	private final CoffeeMachine coffeeMachine = new CoffeeMachine();

	@BeforeEach
	public void setUp() throws Exception {
		Map<String, Recipe> makersMap = new HashMap<>();
		makersMap.put("AMERICANO", new AmericanoMaker());
		makersMap.put("ESPRESSO", new EspressoMaker());
		makersMap.put("LATTE", new LatteMaker());
		makersMap.put("CAPPUCCINO", new CappuccinoMaker());
		makersMap.put("MOCACCINO", new MocaccinoMaker());
		coffeeMachine.setMakersMap(makersMap);
	}

	@Test
	public void shouldGetAmericanoRecipe() {
		String response = coffeeMachine.processRequest("AMERICANO");
		assertEquals("{ \"espresso\": 1, \"water\": 1, \"milk\": 0, \"foam\": 0 }", response);
	}

	@Test
	public void shouldGetEspressoRecipe() {
		String response = coffeeMachine.processRequest("ESPRESSO");
		assertEquals("{ \"espresso\": 1, \"water\": 0, \"milk\": 0, \"foam\": 0 }", response);
	}

	@Test
	public void shouldGetLatteRecipe() {
		String response = coffeeMachine.processRequest("LATTE");
        assertEquals("{ \"espresso\": 1, \"water\": 0, \"milk\": 2, \"foam\": 1 }", response);
	}

	@Test
	public void shouldThrowExceptionWhenRecipeIsUnknown() {
		Assertions.assertThrows(RuntimeException.class, () -> {
			coffeeMachine.processRequest("TEA");
		});
	}

	@Test
	public void shouldGetCappuccinoRecipe() {
		String response = coffeeMachine.processRequest("CAPPUCCINO");
		assertEquals("{ \"espresso\": 1, \"water\": 0, \"milk\": 1, \"foam\": 1 }", response);
	}

	@Test
	public void shouldGetMocaccinoRecipe() {
		String response = coffeeMachine.processRequest("MOCACCINO");
		assertEquals("{ \"espresso\": 1, \"water\": 0, \"milk\": 2, \"foam\": 1, \"chocolate\": 1 }", response);
	}
}
