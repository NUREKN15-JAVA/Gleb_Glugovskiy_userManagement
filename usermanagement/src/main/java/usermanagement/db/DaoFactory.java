package usermanagement.db;


import java.io.IOException;
import java.util.Properties;

public abstract class DaoFactory {
	protected static String USER_DAO = "usermanagement.db.UserDAO";
    protected static String DAO_FACTORY = "dao.factory";
    protected static Properties properties;
    
	private static DaoFactory INSTANCE;
	
    public static synchronized DaoFactory getInstance() {
    	if (INSTANCE == null) {
    		Class factoryClass;
    		try {
    			 factoryClass = Class.forName(properties.getProperty(DAO_FACTORY));
    			 INSTANCE = (DaoFactory) factoryClass.newInstance();
             } catch (Exception e) {
                 throw new RuntimeException(e);
             }
        }   	
        return INSTANCE;
    }
    
    static {
        properties = new Properties();
        try {
            properties.load(DaoFactory.class.getClassLoader()
                    .getResourceAsStream("settings.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    protected DaoFactory() {
    	
    }

    protected ConnectionFactory getConnectionFactory() {
        return new ConnectionFactoryImpl(properties);

    }
    
    public abstract UserDao getUserDao();
    
    public static void init(Properties prop) {
        properties = prop;
        INSTANCE = null;
    }
    
    
}
