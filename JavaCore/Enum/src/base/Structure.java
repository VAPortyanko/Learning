package base;

public class Structure {
	
	public static void main(String[] args) {
		System.out.println("The user-type \"" + Users.OWNER.name() + "\" have id = " +  Users.OWNER.getId());
	}
	
	enum Users{
		
		ASMIN(1), USER(2), OWNER(3);
		
		// A field.
		private int id;
		
		// A constructor.
		Users(int id){
			this.id = id;
		}
		
		// A method.
		public int getId(){
			return id;
		}
	}
}