package chap04;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import chap01.crud.Member;
import util.DaoCommon;
import util.HibernateUtil;

public class BoardArticleTest {

	private static final String ARAHANSA = "arahansa";
	DaoCommon<BoardArticle> daoBoard = new DaoCommon<BoardArticle>(BoardArticle.class);
	DaoCommon<Comments> daoComments = new DaoCommon<Comments>(Comments.class);
	
	@Before
	public void setUp() throws Exception {
	}
	
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
