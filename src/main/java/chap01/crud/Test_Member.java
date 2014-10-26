package chap01.crud;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
/**
 * 간단히 CRUD 하는 테스트를 만들어보았다. 
 * DaoMember 에 CRUD가 있다.참조.
 * @author arahansa
 *
 */
public class Test_Member extends DaoMember {

	private static final String HELLO_HIBERNATE = "hello hibernate";
	private static final String HELLO_WORLD = "hello world";
	private static final String ARAHANSA = "arahansa";
	
	@Test
	public void crudTest() {
		//Insert 
		Member member = new Member(ARAHANSA, HELLO_WORLD);
		insert(member);

		//Select One
		Member selectedMember = selectById(1);
		assertEquals(HELLO_WORLD, selectedMember.getMessage());
		
		//Update 
		selectedMember.setMessage(HELLO_HIBERNATE);
		update(selectedMember);
		Member updatedMember = selectById(1);
		assertEquals(HELLO_HIBERNATE, updatedMember.getMessage());
		
		//Delete 
		delete(updatedMember);
		Member deleteMember = selectById(1);
		assertNull(deleteMember);
		
		//List 
		insert(member);
		insert(updatedMember);
		List<Member> list = selectList();
		assertEquals(HELLO_WORLD, list.get(0).getMessage());
		assertEquals(HELLO_HIBERNATE, list.get(1).getMessage());
		for (Member member2 : list) {
			System.out.println(member2);
		}
	}

}
