package chap07.criteria;

import static org.junit.Assert.*;

import java.util.List;

import javassist.expr.NewArray;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;

import util.DaoCommon;
import util.HibernateTestUtil;

public class Test_Student {

	SessionFactory factory = HibernateTestUtil.getSessionFactory(Student.class);
	DaoCommon<Student> daoStu = new DaoCommon<Student>(Student.class);
	
	@Before
	public void setUp(){
		daoStu.deleteAllSetTable();
		daoStu.insert(new Student("홍길동", 100, 100, 100, 100));
		daoStu.insert(new Student("제시카", 25, 90, 90, 90));
		daoStu.insert(new Student("티파니", 24, 80, 80, 80));
		daoStu.insert(new Student("태연", 23, 70, 70, 70));
		daoStu.insert(new Student("써니", 22, 60, 60, 60));
		daoStu.insert(new Student("효연", 21, 50, 50, 50));
		daoStu.insert(new Student("수영", 20, 40, 40, 40));
	}
	
	
	@Test
	//https://docs.jboss.org/hibernate/orm/3.3/reference/ko-KR/html/querycriteria.html
	public void criteria() {
		assertEquals(7, daoStu.count());
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Student.class);
		criteria.add(Restrictions.gt("age", 22));
		criteria.add(Restrictions.le("math", 90));
		criteria.addOrder(Order.desc("id"));
		/*criteria.setFirstResult(1); 페이징
		criteria.setMaxResults(2);*/
		List<Student> students = criteria.list();
		session.getTransaction().commit();
		
		System.out.println("나이는 22살 초과, 수학점수는 90점이하 id정렬");
		students.forEach(n -> System.out.println(n));
	}
	
	
	// Restriction 추가
	@Test
	public void restriction() throws Exception {
		assertEquals(7, daoStu.count());
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Student.class);
		criteria.add(Restrictions.or(
				Restrictions.between("age", 21, 25), Restrictions.between("kor", 0, 90)));
		
		List<Student> list = criteria.list();
		System.out.println("나이는 21에서 25사이이거나, 국어점수가 0점에서 90점사이 ");
		list.forEach(n-> System.out.println(n));
		
		session.getTransaction().commit();
	}
	
	
	// Projection
	//Link : https://docs.jboss.org/hibernate/orm/3.6/javadocs/org/hibernate/criterion/Projections.html
	@Test
	public void projection() throws Exception {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Student.class);
		double avg = (double) criteria.setProjection(Projections.avg("kor")).uniqueResult();
		System.out.println("국어점수 평균 : "+avg);
		session.getTransaction().commit();
	}
	
	//NamedQuery
	//http://www.youtube.com/watch?v=o_P-p2b_k6w&index=28&list=PL5757A5DB24A40BDC
	@Test
	public void namedQuery() throws Exception {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		Query query = session.getNamedQuery("FindByAge");
		query.setInteger(0, 25);
		List<Student> list = query.list();
		System.out.println("나이가 25살이상인 학생 ");
		list.forEach(n-> System.out.println(n));
		session.getTransaction().commit();
	}
	
	//Native Query
	//http://www.mkyong.com/hibernate/hibernate-native-sql-queries-examples/
	@Test
	public void nativeQuery() throws Exception {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Query query = session.createSQLQuery("SELECT * FROM \"USER\".\"STUDENT\"").addEntity(Student.class);
		List<Student> list = query.list();
		session.getTransaction().commit();
		System.out.println("일반 그냥 순수 쿼리로 뽑은 것 ");
		for (Student student : list) {
			System.out.println(student);
		}
	}
	
	//Example Query
	// http://www.youtube.com/watch?v=2DXjdl8gzOo&list=PL5757A5DB24A40BDC&index=31
	//왜 안되는지 모르겠음(무책임)
	@Test
	public void exampleQuery() throws Exception {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		Student student = new Student();
		student.setName("제시카");
		
		Criteria criteria = session.createCriteria(Student.class);
		Example example= Example.create(student);
		criteria.add(example);
		
		List<Student> list = criteria.list();
		
		session.getTransaction().commit();
		System.out.println("샘플로 얻어진 객체 보여주기");
		list.forEach(n->System.out.println(n));
		System.out.println("리스트 사이즈 "+list.size());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
