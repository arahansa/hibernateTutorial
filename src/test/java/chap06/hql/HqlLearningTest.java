package chap06.hql;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;

import util.DaoCommon;
import util.HibernateTestUtil;

public class HqlLearningTest {

	@Before
	public void setUp() throws Exception {
	}

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
		
		for (UserDetail userDetail : list) {
			System.out.println(userDetail);
		}	
	}

}
