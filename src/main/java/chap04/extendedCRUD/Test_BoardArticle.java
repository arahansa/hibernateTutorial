package chap04.extendedCRUD;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import chap01.crud.Member;
import util.DaoCommon;
import util.HibernateUtil;
/**
 * 
 * 지난 번에 했던 DaoMember를 확장하였다. 
 * 만들어진 것은 DaoCommon 참조
 * 제너릭을 이용하여서 어떤 클래스를 새로 엔티티로 만들건간에
 * 바로 CRUD를 적용할 수 있게 되었다. 
 * 하지만 Spring-Data-JPA 에 가면 훨씬 좋은 것들이 인터페이스로 제공될 것임을 알기에
 * 만들어 잠깐 쓰고 휴지통으로 버릴 생각을 해야한다. 
 * @author arahansa
 *
 */
public class Test_BoardArticle {

	private static final String ARAHANSA = "arahansa";
	DaoCommon<BoardArticle> daoBoard = new DaoCommon<BoardArticle>(BoardArticle.class);
	DaoCommon<Comments> daoComments = new DaoCommon<Comments>(Comments.class);
	
	
	@Test
	public void test() {
		BoardArticle boardArticle = new BoardArticle(ARAHANSA, "hello", new Date());
		daoBoard.insert(boardArticle);
		
		BoardArticle getBoardArticle = daoBoard.selectById(1);
		assertEquals(ARAHANSA, getBoardArticle.getUserId());
		
		daoComments.insert(new Comments("arahansa", "hello Comments"));
		assertEquals("hello Comments", daoComments.selectById(1).getMessage());
	}

}
