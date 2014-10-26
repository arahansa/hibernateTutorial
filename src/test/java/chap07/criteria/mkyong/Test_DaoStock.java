package chap07.criteria.mkyong;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.chrono.IsoChronology;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.CreateKeySecondPass;
import org.hibernate.criterion.Restrictions;
import org.junit.BeforeClass;
import org.junit.Test;

import util.DaoCommon;
import util.HibernateTestUtil;

/**
 * MKyong에 의하면 성능이슈와 유지보수이슈가 발생한다고 한다. 
 * 생성된 쿼리가 느릴 수도 있고 그럴때는 튜닝하기 힘들다고 한다. 
 * 그리고 쿼리가 잘못나왔을 때 유지보수가 어려울 수 있다.
 * 하이버네이트 매핑 파일안의 NamedQuery 가 더 유지보수가 하기 쉬울 수 있다고 한다.
 * 
 *  이번 테스트는 두가지 방식으로 돌리는데,
 *  1. 그냥 테스트에서는
 *  DaoStock_HQL 에서는 hql 방식으로 돌린다.
 *  DaoStock_Criteria 에서는 criteria 방식으로 돌린다.
 *  
 *  2. 두번째 테스트에서는
 *  Criteria API를 좀 더 살펴보는 시간을 가진다.
 *  
 *  @author arahansa 
 */
public class Test_DaoStock {

	SessionFactory factory = HibernateTestUtil.getSessionFactory(StockDailyRecord.class);
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	static DaoCommon<StockDailyRecord> daoSDR;	
	private List<StockDailyRecord> list;
	private List<StockDailyRecord> list2;
	private List<StockDailyRecord> list3;
	
	@BeforeClass
	public static void before() throws ParseException{
		daoSDR = new DaoCommon<StockDailyRecord>(StockDailyRecord.class);
		daoSDR.insert(new StockDailyRecord(new Date(), 1));
		daoSDR.insert(new StockDailyRecord(sdf.parse("2012-01-01"), 2));
		daoSDR.insert(new StockDailyRecord(sdf.parse("2013-01-01"), 10001));
		daoSDR.insert(new StockDailyRecord(sdf.parse("2014-01-01"), 9999));
		daoSDR.insert(new StockDailyRecord(sdf.parse("2014-01-06"), 10000));
	}
	
	
	
	@Test
	public void justTest() throws Exception {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		list = DaoStock_HQL.getStockDailtRecord(sdf.parse("2014-01-01"), new Date(),  0, session);
		list2= DaoStock_Criteria.getStockDailyRecordCriteria(sdf.parse("2014-01-01"), new Date(), 0, session);
		
		System.out.println("눈으로 소스 확인하는 부분. 두 가지모드로 출력해본다.람다 사용;;");
		list.forEach(n -> System.out.println(n));
		list2.forEach(n -> System.out.println(n));
		
		session.getTransaction().commit();
	}
	
	@Test
	public void criteriaAPI() throws Exception {
		
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Criteria criteria; 
		
		//equals = Restrictions.eq
		criteria= session.createCriteria(StockDailyRecord.class);
		list3 = criteria.add(Restrictions.eq("volume", 10000)).list();
		System.out.println("크리테리아를 toString 하면 혹시 쿼리 안나오겠지? :"+criteria.toString());		
		assertEquals(list3.size(), 1);
		assertEquals(list3.get(0).getVolume(), 10000);
		
		//Less than or equals  = Restrictions.le
		criteria = session.createCriteria(StockDailyRecord.class);
		list3  = criteria.add(Restrictions.le("volume", 10000)).list(); 
		assertEquals(list3.size(), 4);
		
		//Greater than  = Restrictions.gt
		criteria = session.createCriteria(StockDailyRecord.class);
		list3  = criteria.add(Restrictions.gt("volume", 10000)).list();  
		assertEquals(list3.size(), 1);
		assertEquals(list3.get(0).getVolume(), 10001);
		
		//Between = Restrictions.between
		criteria = session.createCriteria(StockDailyRecord.class);
		list3 = criteria.add(Restrictions.between("date", sdf.parse("2014-01-02"), sdf.parse("2014-01-07"))).list();
		assertEquals(list3.size(), 1);
		
		// And plus + like, isNull, isNotNull 등 다양하다.
		// Criteria 도 지난번에 했던 Paging 가능하다.  (setMaxResults, setFirstResults)
		session.getTransaction().commit();
	}
	
	
}
