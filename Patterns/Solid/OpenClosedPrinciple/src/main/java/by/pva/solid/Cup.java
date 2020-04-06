package by.pva.solid;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Cup {

	private Map<String, Integer> ingredients = new LinkedHashMap<>();

	private void addZeroKey(List<String> keys) {
		keys.forEach(key -> ingredients.put(key, 0));
	}

	public void add(String ingredient) {
		if (ingredients.containsKey(ingredient))
			ingredients.put(ingredient, ingredients.get(ingredient) + 1);
		else
			ingredients.put(ingredient, 1);
	}

	public String composeResponse() {
		return ingredients.entrySet().stream().map((e) -> "\"" + e.getKey() + "\": " + e.getValue())
				.collect(Collectors.joining(", ", "{ ", " }"));
	}

	public void setZeroIngredients(List<String> zeroIngredients) {
		addZeroKey(zeroIngredients);
	}

}