package chap01.crud;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 * 
 * 부록에 쓰인 Junit 테스트
 * 일단 어노테이션과 assert시리즈만 알면된다. 
 * @BeforeClass 는 모든 테스트시작전
 * @Before는 모든 테스트 전에 돌아감.
 * @Test는 테스트
 * @After, @AfterClass는 성질이 비슷하며
 * 
 * assertEquals()로 값이 같은지 비교한다. 
 * http://junit.org API를 참조.
 * 
 * @author arahansa
 *
 */
public class Test_JUnit {

	@BeforeClass
	public static void setupBefore() throws Exception {
		System.out.println("맨첨에 출력");
		assertEquals(1, 1);
		assertNull(null);
	}

	@Before
	public void testName() throws Exception {
		System.out.println("테스트전 매번 출력");
		assertEquals(1, 1);
	}

	@Test(expected=ArithmeticException.class)
	public void test() {
		System.out.println("이건 에러 예상");
		assertEquals(0, 1 / 0);
	}

	@Test
	public void testName1() throws Exception {
		System.out.println("그냥 출력");
	}

	@After
	public void after() {
		System.out.println("테스트 후 출력");
	}

	@AfterClass
	public static void afterClass() {
		System.out.println("모두 끝나고 출력");
	}

}
