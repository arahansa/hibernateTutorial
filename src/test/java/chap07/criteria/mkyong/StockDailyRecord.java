package chap07.criteria.mkyong;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class StockDailyRecord {

	@Id
	@GeneratedValue
	private int id;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	private int volume;
	
	public StockDailyRecord() {
	}

	public StockDailyRecord(Date date, int volume) {
		super();
		this.date = date;
		this.volume = volume;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	@Override
	public String toString() {
		return "StockDailyRecord [id=" + id + ", date=" + date + ", volume=" + volume + "]";
	}
	
}
