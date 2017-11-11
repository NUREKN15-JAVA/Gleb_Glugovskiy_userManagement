package usermanagement.db;

import java.util.Collection;

import usermanagement.User;

public interface UserDao {
	
	User create(User user) throws DatabaseException;
	
	void update(User user) throws DatabaseException;
	
	void delete(User user) throws DatabaseException;
	
	User find(Long ID) throws DatabaseException;
	
	Collection <User> findAll() throws DatabaseException;
	
    void setConnectionFactory(ConnectionFactory connectionFactory);

}
