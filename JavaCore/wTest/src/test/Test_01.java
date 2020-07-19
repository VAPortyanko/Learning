// https://stepik.org/lesson/12785/step/8?unit=3132
package test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Test_01 {

	public static void main(String[] args) throws IOException {      
        Animal[] animalM1 = { new Animal("Cat"), 
        		              new Animal("Dog"),
        		              new Animal("Elephant"),
        		              new Animal("Cock"),
        		              new Animal("Bull"),
        		              new Animal("Ant"),
        		              new Animal("Tentecles"),
        		              new Animal("Worm")
        		             };
        
        ByteArrayOutputStream bai = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bai);              
        oos.writeInt(animalM1.length);
        for (int i = 0; i < animalM1.length; i++) {              
            oos.writeObject(animalM1[i]);
        }      
        oos.flush();
        oos.close();
       
		Animal[] animalM2 = deserializeAnimalArray(bai.toByteArray());
		
		System.out.println(Arrays.toString(animalM2));
    }

	public static Animal[] deserializeAnimalArray(byte[] data) {
		
		if (data == null || data.length<1)
			throw new IllegalArgumentException();
		
		Animal[] animals = null;
		
		try (ObjectInputStream din = new ObjectInputStream(new ByteArrayInputStream(data))) {
			System.out.println(Arrays.toString(data));
			int size = din.readInt();
			if (size < 0)
				throw new IllegalArgumentException();
			
			animals = new Animal[size];
			
			for(int i = 0; i<size; i++) {
				Object o = din.readObject();
				if (o instanceof Animal)
					animals[i] = (Animal) o;
				else 
					throw new IllegalArgumentException();
			}
			
		} catch (IOException e) {
			throw new IllegalArgumentException();
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException();
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException();
		}
		
		return animals;
	}

}

class Animal implements Serializable {
	private static final long serialVersionUID = 1L;
	private final String name;

	public Animal(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Animal) {
			return Objects.equals(name, ((Animal) obj).name);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}