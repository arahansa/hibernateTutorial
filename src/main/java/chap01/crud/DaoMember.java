package chap01.crud;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import util.HibernateUtil;

public class DaoMember {


	private SessionFactory factory;

	public DaoMember() {
		factory = HibernateUtil.getSessionFactory();
	}
	
	
	public List<Member> selectList() {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from Member");
		List<Member> list = query.list();
		session.getTransaction().commit();
		return list;
	}

	public void delete(Member updatedMember) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.delete(updatedMember);
		session.getTransaction().commit();
	}

	public void update(Member selectedMember) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.update(selectedMember);
		session.getTransaction().commit();
	}

	public Member selectById(int id) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Member selectedMember = (Member) session.get(Member.class, id);
		session.getTransaction().commit();
		return selectedMember;
	}

	public void insert(Member member) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.save(member);
		session.getTransaction().commit();
	}

}
