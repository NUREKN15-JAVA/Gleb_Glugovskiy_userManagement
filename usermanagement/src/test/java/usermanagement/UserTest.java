package usermanagement;


import java.util.Calendar;
import java.util.Date;

import org.junit.Before;

import junit.framework.TestCase;

public class UserTest extends TestCase {
	
	private User user;
	private Date dateOfBirth;
	private final int yearOfBirth = 1998;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		user = new User();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(yearOfBirth, Calendar.JULY, 16);
		dateOfBirth = calendar.getTime();
;	}


	public void testGetFullName() {
		user.setFirstName("Gleb");
		user.setLastName("Glugovskiy");
		assertEquals("Glugovskiy, Gleb", user.getFullName());
	}
	
	
	public void testGetAge() {
		user.setDateOfBirth(dateOfBirth);
		Calendar curyear = Calendar.getInstance();
		assertEquals(curyear.get(Calendar.YEAR) - yearOfBirth, user.getAge());
	}

}
