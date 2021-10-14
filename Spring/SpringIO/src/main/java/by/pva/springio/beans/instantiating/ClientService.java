package by.pva.springio.beans.instantiating;

public class ClientService {

	private static ClientService clientService = new ClientService();

	private ClientService() {
	}

	public static ClientService createInstance() {
		return clientService;
	}
	
	@Override
	public String toString() {
		return "The \"clientService\" bean was instantiated with a static factory method!";
	}
}
