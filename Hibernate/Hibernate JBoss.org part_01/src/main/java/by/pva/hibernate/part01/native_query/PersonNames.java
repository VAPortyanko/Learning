package by.pva.hibernate.part01.native_query;

public class PersonNames {

    private final String name;
    private final String nickName;

    public PersonNames(String name, String nickName) {
        this.name = name;
        this.nickName = nickName;
    }

    public String getName() {
        return name;
    }

    public String getNickName() {
        return nickName;
    }

	@Override
	public String toString() {
		return "PersonNames [name=" + name + ", nickName=" + nickName + "]";
	}
    
}

