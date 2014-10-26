package chap05.paging;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import util.DaoCommon;

/**
 * 페이징의 핵심은 이것이다. 
 * 
 * query.setFirstResult((requestPage-1)*numPerPage); 
 * query.setMaxResults(numPerPage); 
 * @author arahansa
 *
 */
public class Test_Paing {

	private DaoCommon<BoardArticle> daoBoard = new DaoCommon<BoardArticle>(BoardArticle.class);

	
	@Test
	public void testName() throws Exception {
		daoBoard.insert(new BoardArticle("arahansa", "hello Count", new Date()));
		assertEquals(1, daoBoard.count());
		daoBoard.deleteAllSetTable();
		assertEquals(0, daoBoard.count());
	}

	public void test() throws NumberFormatException, IOException, ParseException {
		List<BoardArticle> list = FileReader_CVS.getArticles();
		for (BoardArticle boardArticle : list) 
			daoBoard.insert(boardArticle);
		
		List<BoardArticle> pagingList1 = (List<BoardArticle>) daoBoard.getPagingList(1);
		System.out.println("1 Page Load ");
		for (BoardArticle boardArticle : pagingList1)
			System.out.println(boardArticle);
		
		List<BoardArticle> pagingList2 = (List<BoardArticle>) daoBoard.getPagingList(2);
		System.out.println("2 Page Load ");
		for (BoardArticle boardArticle : pagingList2) 
			System.out.println(boardArticle);
	}
}
