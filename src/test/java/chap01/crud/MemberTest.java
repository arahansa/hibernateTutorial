package chap01.crud;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class MemberTest extends DaoMember {

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
