/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.course.abramian.dal.dao;

import static by.course.abramian.dal.dao.config.LocalConstForDAO.*;
import by.course.abramian.dal.dao.exception.logical.IndexWrongException;
import by.course.abramian.dal.dao.exception.logical.PointerNullException;
import by.course.abramian.dal.dao.exception.technical.SQLConnectionException;
import by.course.abramian.dal.dao.exception.technical.SQLDBAPIException;
import by.course.abramian.dal.dao.interfaces.IClientDAO;
import by.course.abramian.dal.dao.interfaces.AbstractDAO;
import by.course.abramian.dal.entitydto.Entity;
import by.course.abramian.dal.entitydto.Client;
import by.course.abramian.dal.entitydto.Role;
import by.course.abramian.dal.entitydto.User;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import static by.course.abramian.dal.log4j.Log4j.*;

/**
 *
 * @author Abramian Roland Arturovich
 */
public class ClientDAO extends AbstractDAO implements IClientDAO {

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
	List<Entity> listClients = null;

	try {

	    callableStatement = connection.prepareCall(GETALLCLIENTS);
	    resultSet = callableStatement.executeQuery();

	    listClients = new ArrayList<>();
	    while (resultSet.next()) {
		listClients.add(new Client(
			resultSet.getString("passport"),
			resultSet.getString("first_name"),
			resultSet.getString("last_name"),
			resultSet.getString("father_name"),
			resultSet.getString("number_phone"),
			resultSet.getString("email"),
			new User(
				resultSet.getInt("id_user"),
				resultSet.getString("login"),
				resultSet.getString("password"),
				new Role(
					resultSet.getInt("id_role"),
					resultSet.getString("role"))),
			resultSet.getInt("id_client")));
	    }

	} catch (SQLException ex) {
	    LOGGER.error(ex);
	    throw new SQLDBAPIException(ex);
//	    System.out.println(e);
	} finally {
	    releaseAllResources(connection);
	}

	return listClients;
    }

    @Override
    public Entity getByIndex(int index)
	    throws IndexWrongException, SQLDBAPIException {
	Entity client = null;

	if (index < 0) {
	    throw new IndexWrongException();
	}

	try {
	    callableStatement = connection.prepareCall(GETCLIENTBYINDEX);
	    callableStatement.setInt(1, index);
	    resultSet = callableStatement.executeQuery();

	    while (resultSet.next()) {
		client = new Client(
			resultSet.getString("passport"),
			resultSet.getString("first_name"),
			resultSet.getString("last_name"),
			resultSet.getString("father_name"),
			resultSet.getString("number_phone"),
			resultSet.getString("email"),
			new User(
				resultSet.getInt("id_user"),
				resultSet.getString("login"),
				resultSet.getString("password"),
				new Role(
					resultSet.getInt("id_role"),
					resultSet.getString("role"))),
			resultSet.getInt("id_client"));
	    }

	} catch (SQLException ex) {
	    LOGGER.error(ex);
	    throw new SQLDBAPIException(ex);
//	    System.out.println(e);
	} finally {
	    releaseAllResources(connection);
	}

	return client;
    }

    @Override
    public int getNumberOf() throws SQLDBAPIException {
	int tempNumber = 0;

	try {
	    callableStatement = connection.prepareCall(GETNUMBEROFCLIENTS);
	    callableStatement.registerOutParameter(1, Types.INTEGER);
	    callableStatement.executeQuery();
	    tempNumber = callableStatement.getInt(1);

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
    public int insertDefault(Entity entity)
	    throws PointerNullException, SQLDBAPIException {

	if (entity == null) {
	    throw new PointerNullException();
	}

	int tempNumber = 0;
	Client tempClient = (Client) entity;

	try {
	    //Pravilno?
	    callableStatement = connection.prepareCall(INSERTUSER);
	    callableStatement.setString(1, tempClient.getUser().getLogin());
	    callableStatement.setString(2, tempClient.getUser().getPassword());
	    callableStatement.setInt(3, tempClient.getUser().getRole().getId());
	    callableStatement.executeUpdate();
	    callableStatement = connection.prepareCall(CHECKLOGINPASSWORD);
	    callableStatement.setString(1, tempClient.getUser().getLogin());
	    callableStatement.setString(2, tempClient.getUser().getPassword());
	    resultSet = callableStatement.executeQuery();
	    while (resultSet.next()) {
		tempNumber = resultSet.getInt("id_user");
	    }
	    callableStatement = connection.prepareCall(INSERTCLIENT);
	    callableStatement.setString(1, tempClient.getPassport());
	    callableStatement.setString(2, tempClient.getFirstName());
	    callableStatement.setString(3, tempClient.getLastName());
	    callableStatement.setString(4, tempClient.getFatherName());
	    callableStatement.setString(5, tempClient.getNumberPhone());
	    callableStatement.setString(6, tempClient.getEmail());
	    callableStatement.setInt(7, tempNumber);
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
    public int delete(int index)
	    throws IndexWrongException, SQLDBAPIException {

	if (index < 0) {
	    throw new IndexWrongException();
	}

	int tempNumber = 0;

	try {
	    callableStatement = connection.prepareCall(DELETECLIENT);
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
    public int getIDClientByUser(int index)
	    throws IndexWrongException, SQLDBAPIException {

	if (index < 0) {
	    throw new IndexWrongException();
	}

	int tempNumber = 0;
	try {
	    callableStatement = connection.prepareCall(GETIDCLIENTBYUSER);
	    callableStatement.setInt(1, index);
	    resultSet = callableStatement.executeQuery();
	    while (resultSet.next()) {
		tempNumber = resultSet.getInt("id_client");
	    }
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
    public boolean insertById(Entity entity) throws IndexWrongException, SQLDBAPIException, PointerNullException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateById(Entity entity, int index) throws IndexWrongException, SQLDBAPIException, PointerNullException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
