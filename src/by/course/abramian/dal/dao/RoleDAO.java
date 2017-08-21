/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.course.abramian.dal.dao;

import by.course.abramian.dal.dao.interfaces.AbstractDAO;
import static by.course.abramian.dal.dao.config.LocalConstForDAO.*;
import by.course.abramian.dal.dao.exception.logical.IndexWrongException;
import by.course.abramian.dal.dao.exception.logical.PointerNullException;
import by.course.abramian.dal.dao.exception.technical.SQLConnectionException;
import by.course.abramian.dal.dao.exception.technical.SQLDBAPIException;
import by.course.abramian.dal.dao.interfaces.BasicActionDAO;
import by.course.abramian.dal.dao.interfaces.IRole;
import by.course.abramian.dal.entitydto.Entity;
import by.course.abramian.dal.entitydto.Role;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import static by.course.abramian.dal.log4j.Log4j.*;

/**
 *
 * @author Abramian Roland Arturovich
 */
public class RoleDAO extends AbstractDAO implements IRole {

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
	List<Entity> tempRole = new ArrayList<>();

	try {

	    callableStatement = connection.prepareCall(ROLESPROCEDURE);
	    resultSet = callableStatement.executeQuery();

	    while (resultSet.next()) {
		tempRole.add(new Role(
			resultSet.getInt("id_role"),
			resultSet.getString("role")));
	    }

	} catch (SQLException ex) {
	    LOGGER.error(ex);
	    throw new SQLDBAPIException(ex);
//	    System.out.println(e);
	} finally {
	    releaseAllResources(connection);
	}

	return tempRole;
    }

    @Override
    public Entity getByIndex(int index)
	    throws IndexWrongException, SQLDBAPIException {

	if (index < 0) {
	    throw new IndexWrongException();
	}

	Role tempRole = null;
	try {
	    callableStatement = connection.prepareCall(GETROLEBYINDEX);
	    callableStatement.setInt(1, index);
	    resultSet = callableStatement.executeQuery();

	    tempRole = new Role();
	    while (resultSet.next()) {
		tempRole.setId(resultSet.getInt("id_role"));
		tempRole.setRole(resultSet.getString("role"));
	    }

	} catch (SQLException ex) {
	    LOGGER.error(ex);
	    throw new SQLDBAPIException(ex);
//	    System.out.println(e);
	} finally {
	    releaseAllResources(connection);
	}

	return tempRole;
    }

    @Override
    public int getNumberOf() throws SQLDBAPIException {
	int numberOfRoles = 0;

	try {
	    callableStatement = connection.prepareCall(GETNUMBEROFROLES);
	    callableStatement.registerOutParameter(1, Types.INTEGER);
	    resultSet = callableStatement.executeQuery();
	    numberOfRoles = callableStatement.getInt(1);
	} catch (SQLException ex) {
	    LOGGER.error(ex);
	    throw new SQLDBAPIException(ex);
//	    System.out.println(e);
	} finally {
	    releaseAllResources(connection);
	}

	return numberOfRoles;
    }

    @Override
    public int insertDefault(Entity entity)
	    throws PointerNullException, SQLDBAPIException {

	if (entity == null) {
	    throw new PointerNullException();
	}

	Role tempRole = (Role) entity;
	int tempNumber = 0;

	try {
	    callableStatement = connection.prepareCall(INSERTROLE);
	    callableStatement.setString(1, tempRole.getRole());
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

	Role temRole = (Role) entity;
	try {
	    callableStatement = connection.prepareCall(INSERTROLEBYID);
	    callableStatement.setInt(1, temRole.getId());
	    callableStatement.setString(2, temRole.getRole());
	    callableStatement.executeUpdate();

	} catch (SQLException ex) {
	    LOGGER.error(ex);
	    throw new SQLDBAPIException(ex);
	} finally {
	    releaseAllResources(connection);
	}

	return true;

    }

    @Override
    public boolean updateById(Entity entity, int index)
	    throws IndexWrongException, SQLDBAPIException, PointerNullException {

	if (entity == null) {
	    throw new PointerNullException();
	}

	Role tempRole = (Role) entity;
	boolean result;

	try {
	    callableStatement = connection.prepareCall(UPDATEROLEBYID);
	    callableStatement.setInt(1, tempRole.getId());
	    callableStatement.setString(2, tempRole.getRole());
	    callableStatement.setInt(3, index);
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
	    callableStatement = connection.prepareCall(DELETEROLE);
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

}
