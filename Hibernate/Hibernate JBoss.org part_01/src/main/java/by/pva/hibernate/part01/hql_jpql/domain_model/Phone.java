package by.pva.hibernate.part01.hql_jpql.domain_model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.MapKeyTemporal;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TemporalType;

@Entity(name = "Phone27")
@Table(name = "Phones27")
public class Phone {

    @Id
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;
    @Column(name = "phone_number")
    private String number;
    @Enumerated(EnumType.STRING)
    @Column(name = "phone_type")
    private PhoneType type;
    @OneToMany(mappedBy = "phone", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Call> calls = new ArrayList<>(  );
    @OneToMany(mappedBy = "phone")
    @MapKey(name = "timestamp")
    @MapKeyTemporal(TemporalType.TIMESTAMP )
    private Map<Date, Call> callHistory = new HashMap<>();
    @ElementCollection
    private List<Date> repairTimestamps = new ArrayList<>(  );
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public PhoneType getType() {
		return type;
	}
	public void setType(PhoneType type) {
		this.type = type;
	}
	public List<Call> getCalls() {
		return calls;
	}
	public void setCalls(List<Call> calls) {
		this.calls = calls;
	}
	public Map<Date, Call> getCallHistory() {
		return callHistory;
	}
	public void setCallHistory(Map<Date, Call> callHistory) {
		this.callHistory = callHistory;
	}
	public List<Date> getRepairTimestamps() {
		return repairTimestamps;
	}
	public void setRepairTimestamps(List<Date> repairTimestamps) {
		this.repairTimestamps = repairTimestamps;
	}
    public void addCall(Call call) {
        calls.add(call);
        callHistory.put(call.getTimestamp(), call);
        call.setPhone(this);
    }
}
