package usermanagement.db;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.*;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;

import java.util.Calendar;
import java.util.Collection;

import usermanagement.User;
import usermanagement.db.ConnectionFactory;
import usermanagement.db.ConnectionFactoryImpl;
import usermanagement.db.DatabaseException;
import usermanagement.db.HsqldbUserDao;

public class HsqldbUserDaoTest extends DatabaseTestCase {
	private static final long TEST_ID = 1000L;
	private static final long USER_ID = 1000L;
	private static final Long UPDATE_ID = 1001L;
	private static final String FIRST_NAME = "Gleb";
	private static final String LAST_NAME = "Glugovskiy";
	private static final int BIRTH_DAY = 16;
	private static final int BIRTH_MONTH = 07;
	private static final int BIRTH_YEAR = 1998;
	

	private HsqldbUserDao dao;
	private ConnectionFactory connectionFactory;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		dao = new HsqldbUserDao(connectionFactory);
	}
	
	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		connectionFactory = new ConnectionFactoryImpl("org.hsqldb.jdbcDriver","jdbc:hsqldb:file:db/usermanagement","sa","");
		return new DatabaseConnection(connectionFactory.createConnection());
}



	    protected IDataSet getDataSet() throws Exception {
	        IDataSet dataSet = new XmlDataSet(getClass().getClassLoader().getResourceAsStream("userdataset.xml"));
	        return dataSet;
	    }
	    


	public void testCreate() {
		try {
			User user = new User();
			user.setFirstName("Gleb");
			user.setLastName("Glugovskiy");
			user.setDateOfBirth(new Date());
			assertNull(user.getId());
			user = dao.create(user);
			assertNotNull(user);
			assertNotNull(user.getId());
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
		
	}
	
	public void testFindId() {
		User userFind = new User();
		User newUser = new User();
		newUser.setFirstName("Bill");
		newUser.setLastName("Gates");
		Calendar dateOfBirthNew = new GregorianCalendar(1968, 3, 26);
		newUser.setDateOfBirth(dateOfBirthNew.getTime());
		try {
			userFind = dao.find(TEST_ID);
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		assertNotNull(userFind.getFirstName());
		assertEquals( userFind.getFirstName(), newUser.getFirstName());
		assertEquals(userFind.getLastName(), newUser.getLastName());
		assertEquals( userFind.getDateOfBirth(), newUser.getDateOfBirth());

	}
	public void testUpdate() {
		User updatedUser = new User();
		User newUser = new User();
		newUser.setId(UPDATE_ID);
		newUser.setFirstName(FIRST_NAME);
		newUser.setLastName(LAST_NAME);
		newUser.setDateOfBirth(new Date());
		Calendar dateOfBirthNew = new GregorianCalendar(BIRTH_YEAR, BIRTH_MONTH, BIRTH_DAY);
		newUser.setDateOfBirth(dateOfBirthNew.getTime());
		try {
			dao.update(newUser);
			updatedUser = dao.find(UPDATE_ID);
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		assertNotNull(updatedUser.getFirstName());
		assertEquals(newUser.getLastName(), updatedUser.getLastName());
		assertTrue(newUser.getFirstName().equals(updatedUser.getFirstName()));
		assertEquals( newUser.getDateOfBirth(), updatedUser.getDateOfBirth());
	}
	
	public void testDelete(){
		Collection<User> collection = new LinkedList<>();
		User user = new User();
		user.setId(USER_ID);
		user.setFirstName(FIRST_NAME);
		user.setLastName(LAST_NAME);
		Calendar dateOfBirthNew = new GregorianCalendar(BIRTH_YEAR, BIRTH_MONTH, BIRTH_DAY);
		user.setDateOfBirth(dateOfBirthNew.getTime());
		try {
			dao.delete(user);
			collection = dao.findAll();
			assertNotNull("Collection is null", collection);
			assertEquals("Collection size.", 1, collection.size());

		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		
}
	
	 public void testFindAll() {
	        try {
				Collection<User> collection = dao.findAll();
	            assertNotNull("Collection is null", collection);
	            assertEquals("Collection size.", 2, collection.size());
	        } catch (DatabaseException e) {
	            e.printStackTrace();
	            fail(e.toString());
	        }
	    }

}
