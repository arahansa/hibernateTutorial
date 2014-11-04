package chap07.criteria;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.NamedQuery;

@Entity
@NamedQuery(name="FindByAge", query="from Student where age>=?")
public class Student {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	private int age;
	private int kor;
	private int eng;
	private int math;
	
	public Student() {
	}

	public Student(String name, int age, int kor, int eng, int math) {
		super();
		this.name = name;
		this.age = age;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", kor=" + kor + ", eng=" + eng + ", math=" + math + "]";
	}
	
	
	
	
	
}
