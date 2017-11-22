package usermanagement.web;

import java.util.Properties;

import com.mockobjects.dynamic.Mock;
import com.mockrunner.servlet.BasicServletTestCaseAdapter;

import usermanagement.db.DaoFactory;
import usermanagement.db.MockDaoFactory;

public abstract class MockServletTestCase extends BasicServletTestCaseAdapter {

	private Mock mockUserDao;
	
	/**
     * @return Returns the mockUserDao.
     */
	public Mock getMockUserDao() {
		return mockUserDao;
	}

	/**
     * @param mockUserDao The mockUserDao to set.
     */
	public void setMockUserDao(Mock mockUserDao) {
		this.mockUserDao = mockUserDao;
	}

	
	/*
     * @see BasicServletTestCaseAdapter#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        Properties properties = new Properties();
        properties.setProperty("dao.factory", MockDaoFactory.class.getName());
        DaoFactory.init(properties);
        setMockUserDao(((MockDaoFactory) DaoFactory.getInstance()).getMockUserDao());
    }

    /*
     * @see BasicServletTestCaseAdapter#tearDown()
     */
    protected void tearDown() throws Exception {
        getMockUserDao().verify();
        super.tearDown();
    }

}
