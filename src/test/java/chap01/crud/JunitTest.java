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
		System.out.println("���� ���� �� �ѹ� �Ǵ� ��");
		assertEquals(1, 1);
		assertNull(null);

	}

	@Before
	public void testName() throws Exception {
		System.out.println("���۵Ǳ����� ������");
		assertEquals(1, 1);
	}

	@Test
	public void test() {
		System.out.println("ù��° �׽�Ʈ ");
		assertEquals(0, 1 / 0);
	}

	@Test
	public void testName1() throws Exception {
		System.out.println("�ι�° �׽�Ʈ");
	}

	@After
	public void after() {
		System.out.println("������ ");
	}

	@AfterClass
	public static void afterClass() {
		System.out.println("������ �Ŀ� ");
	}

}
