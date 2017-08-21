package by.course.abramian.dal.dao;

import by.course.abramian.dal.dao.interfaces.AbstractDAO;
import by.course.abramian.dal.entitydto.User;
import java.sql.SQLException;
import java.util.List;
import static by.course.abramian.dal.dao.config.LocalConstForDAO.*;
import by.course.abramian.dal.dao.exception.logical.IndexWrongException;
import by.course.abramian.dal.dao.exception.logical.PointerNullException;
import by.course.abramian.dal.dao.exception.technical.LoginPasswordWrongException;
import by.course.abramian.dal.dao.exception.technical.SQLConnectionException;
import by.course.abramian.dal.dao.exception.technical.SQLDBAPIException;
import by.course.abramian.dal.dao.interfaces.IUserDAO;
import by.course.abramian.dal.entitydto.Entity;
import by.course.abramian.dal.entitydto.Role;
import java.sql.Types;
import java.util.ArrayList;
import static by.course.abramian.dal.log4j.Log4j.*;

public class UserDAO extends AbstractDAO implements IUserDAO {

    {
	try {
	    connection = getConnection();
	} catch (SQLConnectionException ex) {
	    LOGGER.error(ex);
//	    System.out.println(e);
	}
    }

    @Override
    public List<Entity> getAll() throws SQLDBAPIException {
	List<Entity> tempUsers = null;
	try {

	    tempUsers = new ArrayList<>();
	    callableStatement = connection.prepareCall(GETALLUSERS);
	    resultSet = callableStatement.executeQuery();

	    while (resultSet.next()) {
		tempUsers.add(new User(resultSet.getInt("id_user"),
			resultSet.getString("login"),
			resultSet.getString("password"),
			new Role(resultSet.getInt("id_role"),
				resultSet.getString("role"))));
	    }

	} catch (SQLException ex) {
	    LOGGER.error(ex);
	    throw new SQLDBAPIException(ex);
//	    System.out.println(e);
	} finally {
	    releaseAllResources(connection);
	}

	return tempUsers;
    }

    @Override
    public Entity getByIndex(int index)
	    throws IndexWrongException, SQLDBAPIException {

	if (index < 0) {
	    throw new IndexWrongException();
	}

	User user = null;
	try {

	    callableStatement = connection.prepareCall(GETUSERBYINDEX);
	    callableStatement.setInt(1, index);
	    resultSet = callableStatement.executeQuery();

	    user = new User();

	    while (resultSet.next()) {
		user.setId(resultSet.getInt("id_user"));
		user.setLogin("login");
		user.setPassword("password");
		user.setRole(new Role(
			resultSet.getInt("id_role"),
			resultSet.getString("role")));
	    }

	    //Нижний пример не работает почему????    
//            user = new User(resultSet.getInt("id_authorization"),
//                    resultSet.getString("login"),
//                    resultSet.getString("password"),
//                    new Role(resultSet.getInt("id_role"),
//                            resultSet.getString("role")));
	} catch (SQLException ex) {
	    LOGGER.error(ex);
	    throw new SQLDBAPIException(ex);
//	    System.out.println(e);
	} finally {
	    releaseAllResources(connection);
	}

	return user;
    }

    @Override
    public List<User> getUsersByRole(int index)
	    throws IndexWrongException, SQLDBAPIException {

	if (index < 0) {
	    throw new IndexWrongException();
	}
	List<User> users = new ArrayList<>();
	try {

	    callableStatement = connection.prepareCall(GETUSERSBYROLE);
	    callableStatement.setInt(1, index);
	    resultSet = callableStatement.executeQuery();

	    while (resultSet.next()) {
		users.add(new User(resultSet.getInt("id_user"),
			resultSet.getString("login"),
			resultSet.getString("password"),
			new Role(resultSet.getInt("id_role"),
				resultSet.getString("role"))));
	    }

	} catch (SQLException ex) {
	    LOGGER.error(ex);
	    throw new SQLDBAPIException(ex);
//	    System.out.println(e);
	} finally {
	    releaseAllResources(connection);
	}

	return users;

    }

    @Override
    public int getNumberOf() throws SQLDBAPIException {
	int numberOfUsers = 0;
	try {

	    callableStatement = connection.prepareCall(GETNUMBEROFUSERS);
	    callableStatement.registerOutParameter(1, Types.INTEGER);
	    resultSet = callableStatement.executeQuery();
	    numberOfUsers = callableStatement.getInt(1);

	} catch (SQLException ex) {
	    LOGGER.error(ex);
	    throw new SQLDBAPIException(ex);
//	    System.out.println(e);
	} finally {
	    releaseAllResources(connection);
	}

	return numberOfUsers;

    }

    @Override
    public int insertDefault(Entity entity)
	    throws PointerNullException, SQLDBAPIException {

	if (entity == null) {
	    throw new PointerNullException();
	}

	int tempNumber = 0;
	User tempUser = (User) entity;

	try {
	    callableStatement = connection.prepareCall(INSERTUSER);
	    callableStatement.setString(1, tempUser.getLogin());
	    callableStatement.setString(2, tempUser.getPassword());
	    callableStatement.setInt(3, tempUser.getRole().getId());
	    tempNumber = callableStatement.executeUpdate();
	} catch (SQLException ex) {
	    LOGGER.error(ex);
	    throw new SQLDBAPIException(ex);
//	    System.out.println(e);
	} finally {
	    releaseAllResources(connection);
	}

	return tempNumber;
    }

    @Override
    public boolean insertById(Entity entity)
	    throws IndexWrongException, SQLDBAPIException, PointerNullException {

	if (entity == null) {
	    throw new PointerNullException();
	}
	boolean result;
	try {

	    User tempUser = (User) entity;

	    callableStatement = connection.prepareCall(INSERTUSERBYID);
	    callableStatement.setInt(1, tempUser.getId());
	    callableStatement.setString(2, tempUser.getLogin());
	    callableStatement.setString(3, tempUser.getPassword());
	    callableStatement.setInt(4, tempUser.getRole().getId());
	    result = callableStatement.executeUpdate() == 0 ? false : true;

	} catch (SQLException ex) {
	    LOGGER.error(ex);
	    throw new SQLDBAPIException(ex);
	} finally {
	    releaseAllResources(connection);
	}

	return result;
    }

    @Override
    public boolean updateById(Entity entity, int index)
	    throws IndexWrongException, SQLDBAPIException, PointerNullException {

	if (entity == null) {
	    throw new PointerNullException();
	}
	boolean result;
	try {

	    User tempUser = (User) entity;

	    callableStatement = connection.prepareCall(UPDATEUSERBYID);
	    callableStatement.setInt(1, tempUser.getId());
	    callableStatement.setString(2, tempUser.getLogin());
	    callableStatement.setString(3, tempUser.getPassword());
	    callableStatement.setInt(4, tempUser.getRole().getId());
	    callableStatement.setInt(5, index);
	    result = callableStatement.executeUpdate() == 0 ? false : true;

	} catch (SQLException ex) {
	    LOGGER.error(ex);
	    throw new SQLDBAPIException(ex);
	} finally {
	    releaseAllResources(connection);
	}

	return result;
    }

    @Override
    public int delete(int index)
	    throws IndexWrongException, SQLDBAPIException {

	if (index < 0) {
	    throw new IndexWrongException();
	}

	int tempNumber = 0;

	try {
	    callableStatement = connection.prepareCall(DELETEUSER);
	    callableStatement.setInt(1, index);
	    tempNumber = callableStatement.executeUpdate();
	} catch (SQLException ex) {
	    LOGGER.error(ex);
	    throw new SQLDBAPIException(ex);
//	    System.out.println(e);
	} finally {
	    releaseAllResources(connection);
	}

	return tempNumber;
    }

    @Override
    public int getIDUserByLoginPassword(String login, String password)
	    throws LoginPasswordWrongException, SQLDBAPIException {
	int tempNumber = -1;
//	LOGGER.trace("TEST LOG4j.");

	try {
	    callableStatement = connection.prepareCall(CHECKLOGINPASSWORD);
	    callableStatement.setString(1, login);
	    callableStatement.setString(2, password);
	    resultSet = callableStatement.executeQuery();

	    while (resultSet.next()) {
		tempNumber = resultSet.getInt("id_user");
	    }

	} catch (SQLException ex) {
	    LOGGER.error(ex);
	    throw new SQLDBAPIException(ex);
//	    System.out.println(e);
	} finally {
	    releaseAllResources(connection);
	}

	if (tempNumber < 0) {
	    throw new LoginPasswordWrongException("You input not correct login or password! Please re-entry.");
	}

	return tempNumber;
    }

}
