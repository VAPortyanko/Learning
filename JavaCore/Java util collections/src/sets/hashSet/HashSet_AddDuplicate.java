package sets.hashSet;

import java.util.HashSet;

public class HashSet_AddDuplicate {

	   public static void main(String a[]){
		   HashSet<Entity> hs = new HashSet<Entity>();
		   
		   Entity e1 = new Entity(1, 1, 2);
		   Entity e2 = new Entity(2, 1, 2);
		   
		   System.out.println("We have created entity e1:" + e1);
		   System.out.println("We have created entity e2:" + e2);
		   System.out.println("The \"id\" field is not involved in equals() and hashcode() methods!");
		   
		   System.out.println();
		   
		   System.out.println("e1 == e2 ?:" + (e1==e2));
		   System.out.println("e1.equals(e2) ?:" + (e1.equals(e2)));
		   
		   System.out.println();
		   
		   // Try to add e1 and e2.
		   boolean result1 = hs.add(e1);
		   boolean result2 = hs.add(e2);
		   
		   System.out.println("Atemption add e1: " + result1);
		   System.out.println("Atemption add e2: " + result2);
		   
		   System.out.println();
		   
		   System.out.println("Content of the hashSet: " + hs);
	   }
	}

class Entity{
	int id;
	int a;
	int b;
	
	public Entity(int id, int a, int b) {
		this.a = a;
		this.b = b;
		this.id = id;
		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + a;
		result = prime * result + b;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity other = (Entity) obj;
		if (a != other.a)
			return false;
		if (b != other.b)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Entity [id=" + id + ", a=" + a + ", b=" + b + "]";
	}

	
	
}