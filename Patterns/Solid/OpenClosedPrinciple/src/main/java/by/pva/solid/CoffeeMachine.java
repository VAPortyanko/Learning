package by.pva.solid;

import java.util.HashMap;
import java.util.Map;

public class CoffeeMachine {

	private Map<String, Recipe> makersMap = new HashMap<>();

	public String processRequest(String request) {
		if (makersMap.containsKey(request))
			return makersMap.get(request).make().composeResponse();
		else
			throw new RuntimeException("Wrong Request");
	}

	public Map<String, Recipe> getMakersMap() {
		return makersMap;
	}

	public void setMakersMap(Map<String, Recipe> makersMap) {
		this.makersMap = makersMap;
	}
}