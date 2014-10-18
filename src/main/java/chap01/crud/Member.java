package chap01.crud;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Member {

	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
	private String message;
	
	
	public Member() {
	}


	public Member(String name, String message) {
		super();
		this.name = name;
		this.message = message;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", message=" + message + "]";
	}
	
	
	
	
	
}
