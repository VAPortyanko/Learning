package polymorphism;

public class Example_01 {

	public static void main(String[] args) {
		
		Enimals pet = Enimals.Dog;
		pet.voice();
		pet = Enimals.Cat;
		pet.voice();
	}

	enum Enimals{
		Cat,
		Dog(){
			void voice(){
				System.out.println("voice of the " + this.name() + " - Gav - gav");
			}
		},
		Elephant,
		Snake;
		
		void voice(){
			System.out.println("voice of the " + this.name() + " - ???");
		}
	}
	
}
