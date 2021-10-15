package by.pva.springio.beans.dependencies.collectionsandproperties;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class MainBean {

	private Properties adminEmails;
	private Properties capitals;
	private List<Object> someList;
	private Map<Object, Object> someMap;
	private Set<Object> someSet;
	
	public Properties getAdminEmails() {
		return adminEmails;
	}
	public void setAdminEmails(Properties adminEmails) {
		this.adminEmails = adminEmails;
	}
	public Properties getCapitals() {
		return capitals;
	}
	public void setCapitals(Properties capitals) {
		this.capitals = capitals;
	}
	public List<Object> getSomeList() {
		return someList;
	}
	public void setSomeList(List<Object> someList) {
		this.someList = someList;
	}
	public Map<Object, Object> getSomeMap() {
		return someMap;
	}
	public void setSomeMap(Map<Object, Object> someMap) {
		this.someMap = someMap;
	}
	public Set<Object> getSomeSet() {
		return someSet;
	}
	public void setSomeSet(Set<Object> someSet) {
		this.someSet = someSet;
	}
	
}
