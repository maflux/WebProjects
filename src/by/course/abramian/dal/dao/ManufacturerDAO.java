/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.course.abramian.dal.dao;

import by.course.abramian.dal.entitydto.Manufacturer;
import by.course.abramian.dal.dao.interfaces.BasicActionDAO;
import by.course.abramian.dal.dao.interfaces.AbstractDAO;
import by.course.abramian.dal.entitydto.Entity;
import java.sql.SQLException;
import java.util.List;
import static by.course.abramian.dal.dao.config.LocalConstForDAO.*;
import by.course.abramian.dal.dao.exception.logical.IndexWrongException;
import by.course.abramian.dal.dao.exception.logical.PointerNullException;
import by.course.abramian.dal.dao.exception.technical.SQLConnectionException;
import by.course.abramian.dal.dao.exception.technical.SQLDBAPIException;
import by.course.abramian.dal.dao.interfaces.IManufacturer;
import java.sql.Types;
import java.util.ArrayList;
import static by.course.abramian.dal.log4j.Log4j.*;

/**
 *
 * @author Abramian Roland Arturovich
 */
public class ManufacturerDAO extends AbstractDAO implements IManufacturer {

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
	List<Entity> manufacturers = null;

	try {
	    callableStatement = connection.prepareCall(GETALLMANUFACTURERS);
	    resultSet = callableStatement.executeQuery();

	    manufacturers = new ArrayList<>();

	    while (resultSet.next()) {
		manufacturers.add(new Manufacturer(
			resultSet.getString("manufacturer"),
			resultSet.getInt("id_manufacturer")));
	    }

	} catch (SQLException ex) {
	    LOGGER.error(ex);
	    throw new SQLDBAPIException(ex);
//	    System.out.println(e);
	} finally {
	    releaseAllResources(connection);
	}

	return manufacturers;
    }

    @Override
    public Entity getByIndex(int index)
	    throws IndexWrongException, SQLDBAPIException {

	if (index < 0) {
	    throw new IndexWrongException();
	}

	Entity tempManufacturer = null;

	try {

	    callableStatement = connection.prepareCall(GETMANUFACTURERBYINDEX);
	    callableStatement.setInt(1, index);
	    resultSet = callableStatement.executeQuery();

	    while (resultSet.next()) {
		tempManufacturer = new Manufacturer(
			resultSet.getString("manufacturer"),
			resultSet.getInt("id_manufacturer"));

	    }

	} catch (SQLException ex) {
	    LOGGER.error(ex);
	    throw new SQLDBAPIException(ex);
//	    System.out.println(e);
	} finally {
	    releaseAllResources(connection);
	}

	return tempManufacturer;
    }

    @Override
    public int getNumberOf() throws SQLDBAPIException {
	int tempNumber = 0;

	try {

	    callableStatement = connection.prepareCall(GETNUMBEROFMANUFACTURERS);
	    callableStatement.registerOutParameter(1, Types.INTEGER);
	    callableStatement.execute();
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
	Manufacturer tempManu = (Manufacturer) entity;
	try {
	    callableStatement = connection.prepareCall(INSERTMANUFACTURER);
	    callableStatement.setString(1, tempManu.getManufacturer());
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
	boolean result;

	if (entity == null) {
	    throw new PointerNullException();
	}

	try {
	    Manufacturer tempManu = (Manufacturer) entity;

	    callableStatement = connection.prepareCall(INSERTMANUFACTURERBYID);
	    callableStatement.setInt(1, tempManu.getId());
	    callableStatement.setString(2, tempManu.getManufacturer());
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
	boolean result;

	try {
	    Manufacturer tempManu = (Manufacturer) entity;

	    callableStatement = connection.prepareCall(UPDATEMANUFACTURERBYID);
	    callableStatement.setInt(1, tempManu.getId());
	    callableStatement.setString(2, tempManu.getManufacturer());
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
	    callableStatement = connection.prepareCall(DELETEMANUFACTURER);
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
