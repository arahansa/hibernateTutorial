package chap05.paging;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BoardArticle {

	@Id
	@GeneratedValue
	private int id;
	
	private String userId;
	
	private String message;
	
	private Date dateWrite;
	
	public BoardArticle() {
	}

	public BoardArticle(int id, String userId, String message, Date dateWrite) {
		super();
		this.id = id;
		this.userId = userId;
		this.message = message;
		this.dateWrite = dateWrite;
	}




	public BoardArticle(String userId, String message, Date dateWrite) {
		super();
		this.userId = userId;
		this.message = message;
		this.dateWrite = dateWrite;
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

	public Date getDateWrite() {
		return dateWrite;
	}

	public void setDateWrite(Date dateWrite) {
		this.dateWrite = dateWrite;
	}

	@Override
	public String toString() {
		return "BoardArticle [id=" + id + ", userId=" + userId + ", message=" + message + ", dateWrite=" + dateWrite + "]";
	}
	
	
	
	
}
