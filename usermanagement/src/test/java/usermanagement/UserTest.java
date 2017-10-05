package usermanagement;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;

import junit.framework.TestCase;

public class UserTest extends TestCase {
	
	private User user;
	private Date dateOfBirth;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		user = new User();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(1998, Calendar.JULY, 16);
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
		assertEquals(curyear.get(Calendar.YEAR) - 1998, user.getAge());
	}

}
