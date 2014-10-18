package chap01.crud;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JunitTest {

	@BeforeClass
	public static void setupBefore() throws Exception {
		System.out.println("제일 먼저 딱 한번 되는 것");
		assertEquals(1, 1);
		assertNull(null);

	}

	@Before
	public void testName() throws Exception {
		System.out.println("시작되기전에 가동됨");
		assertEquals(1, 1);
	}

	@Test
	public void test() {
		System.out.println("첫번째 테스트 ");
		assertEquals(0, 1 / 0);
	}

	@Test
	public void testName1() throws Exception {
		System.out.println("두번째 테스트");
	}

	@After
	public void after() {
		System.out.println("애프터 ");
	}

	@AfterClass
	public static void afterClass() {
		System.out.println("마지막 후에 ");
	}

}
