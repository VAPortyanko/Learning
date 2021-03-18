package by.pva.hibernate.part01.criteria;

public class PersonWrapper {

    private final Long id;
    private final String nickName;

    public PersonWrapper(Long id, String nickName) {
        this.id = id;
        this.nickName = nickName;
    }

    public Long getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }
    
}
