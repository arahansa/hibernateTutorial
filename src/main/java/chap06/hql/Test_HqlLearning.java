package chap06.hql;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;

import util.DaoCommon;
import util.HibernateTestUtil;
/**
 * 그냥 hql 문을 쓰는 방법도 있지만 sql인젝션의 방어를 위하여 이렇게 쓴다고 한다.
 * hql 문안에 ? 를 넣고서 query.set타입(순번, 값);
 * hql 문안에 :이름 넣고서 query.set타입("이름", 값);
 * 
 * @author arahansa
 */
public class Test_HqlLearning {

	@Test
	public void test() {
		DaoCommon<UserDetail> daoUserDetail = new DaoCommon<UserDetail>(UserDetail.class);
		
		for(int i=0; i<10;i++){
			daoUserDetail.insert(new UserDetail("User"+i));
		}
		SessionFactory factory = HibernateTestUtil.getSessionFactory(UserDetail.class);
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		//String minUserid= "5 or 1=1";
		//Query query = session.createQuery("from UserDetail where id> "+minUserid);
		/*Query query = session.createQuery("from UserDetail where id> ? and userName = ?");
		String minUserid= "5";
		query.setInteger(0, Integer.parseInt(minUserid));
		query.setString(1, "User9");*/
		
		String minUserid= "5";
		Query query = session.createQuery("from UserDetail where id> :userId and userName = :userName");
		query.setInteger("userId", Integer.parseInt(minUserid));
		query.setString("userName", "User9");
		
		List<UserDetail> list = query.list();
		session.getTransaction().commit();
		//람다사용해서 foreach 로 안의 내용확인
		list.forEach(n->System.out.println(n));
		
	}

}
