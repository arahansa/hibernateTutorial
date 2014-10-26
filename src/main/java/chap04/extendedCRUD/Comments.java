package chap04.extendedCRUD;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Comments {

	@Id
	@GeneratedValue
	private int id;
	
	private String userId;
	
	private String message;
	
	public Comments() {
	}
	
	

	public Comments(String userId, String message) {
		super();
		this.userId = userId;
		this.message = message;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}



	@Override
	public String toString() {
		return "Comments [id=" + id + ", userId=" + userId + ", message=" + message + "]";
	}
	
	
}
