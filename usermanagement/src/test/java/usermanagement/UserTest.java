package usermanagement;


import java.util.Calendar;
import java.util.Date;


import junit.framework.TestCase;

public class UserTest extends TestCase {
	
	private User user;
	private Date dateOfBirth;
	private final int YEAR = 1998;
	
	public void setUp() throws Exception {
		super.setUp();
		user = new User();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR, Calendar.JULY, 16);
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
		assertEquals(curyear.get(Calendar.YEAR) - YEAR, user.getAge());
	}

}