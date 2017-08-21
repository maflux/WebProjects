/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.course.abramian.dal.dao;

import by.course.abramian.dal.dao.interfaces.ICarDAO;
import by.course.abramian.dal.dao.interfaces.AbstractDAO;
import by.course.abramian.dal.entitydto.Entity;
import java.sql.SQLException;
import java.util.List;
import static by.course.abramian.dal.dao.config.LocalConstForDAO.*;
import by.course.abramian.dal.dao.exception.logical.IndexWrongException;
import by.course.abramian.dal.dao.exception.logical.PointerNullException;
import by.course.abramian.dal.dao.exception.technical.SQLConnectionException;
import by.course.abramian.dal.dao.exception.technical.SQLDBAPIException;
import by.course.abramian.dal.entitydto.Car;
import by.course.abramian.dal.entitydto.Manufacturer;
import by.course.abramian.dal.entitydto.Model;
import static by.course.abramian.dal.log4j.Log4j.*;
import java.sql.Types;
import java.util.ArrayList;

/**
 *
 * @author Abramian Roland Arturovich
 */
public class CarDAO extends AbstractDAO implements ICarDAO {

    {
	try {
	    connection = getConnection();
	} catch (SQLConnectionException ex) {
	    LOGGER.error(ex);
//	    System.out.println(ex);
	}
    }

    @Override
    public List<Entity> getAll() throws SQLDBAPIException {
	LOGGER.trace("TEST!K.");
	List<Entity> tempCars = null;

	try {
	    tempCars = new ArrayList<>();
	    callableStatement = connection.prepareCall(GETALLCAR);
	    resultSet = callableStatement.executeQuery();

	    while (resultSet.next()) {
		tempCars.add(new Car(
			new Manufacturer(
				resultSet.getString("manufacturer"),
				resultSet.getInt("id_manufacturer")),
			new Model(resultSet.getString("color"),
				resultSet.getString("transmission"),
				resultSet.getString("body_type"),
				resultSet.getInt("power"),
				resultSet.getInt("id_model")),
			resultSet.getBoolean("free"),
			resultSet.getInt("id_car")));
	    }
	} catch (SQLException ex) {
	    LOGGER.error(ex);
	    throw new SQLDBAPIException(ex);
//	    System.out.println(e);
	} finally {
	    releaseAllResources(connection);
	}

	return tempCars;

    }

    //Esli ykazat nepravilno index vernetsa "null"
    @Override
    public Entity getByIndex(int index)
	    throws IndexWrongException, SQLDBAPIException {
	Entity tempCar = null;

	if (index < 0) {
	    throw new IndexWrongException();
	}

	try {
	    callableStatement = connection.prepareCall(GETCARBYINDEX);
	    callableStatement.setInt(1, index);
	    resultSet = callableStatement.executeQuery();

//	    tempCar = new Car();
	    while (resultSet.next()) {
		tempCar = new Car(
			new Manufacturer(
				resultSet.getString("manufacturer"),
				resultSet.getInt("id_manufacturer")),
			new Model(
				resultSet.getString("color"),
				resultSet.getString("transmission"),
				resultSet.getString("body_type"),
				resultSet.getInt("power"),
				resultSet.getInt("id_model")),
			resultSet.getBoolean("free"),
			resultSet.getInt("id_car"));

	    }

	} catch (SQLException ex) {
	    LOGGER.error(ex);
	    throw new SQLDBAPIException(ex);
//	    System.out.println(e);
	} finally {
	    releaseAllResources(connection);
	}

	return tempCar;
    }

    @Override
    public int getNumberOf() throws SQLDBAPIException {
	int tempNumber = 0;
	try {
	    callableStatement = connection.prepareCall(GETNUMBEROFCAR);
	    callableStatement.registerOutParameter(1, Types.INTEGER);
	    resultSet = callableStatement.executeQuery();
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
	Car tempCar = (Car) entity;

	try {
	    callableStatement = connection.prepareCall(INSERTCAR);
	    callableStatement.setInt(1, tempCar.getManufacturer().getId());
	    callableStatement.setInt(2, tempCar.getModel().getId());
	    callableStatement.setBoolean(3, tempCar.isFree());
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
	try {
	    Car tempCar = (Car) entity;
	    callableStatement = connection.prepareCall(INSERTCARBYID);
	    callableStatement.setInt(1, tempCar.getId());
	    callableStatement.setInt(2, tempCar.getManufacturer().getId());
	    callableStatement.setInt(3, tempCar.getModel().getId());
	    callableStatement.setBoolean(4, tempCar.isFree());
	    result = callableStatement.executeUpdate() == 0 ? false : true;

	} catch (SQLException ex) {
	    LOGGER.error(ex);
	    throw new SQLDBAPIException(ex);
	}

	return result;
    }

    @Override
    public void updateFieldFreeByIndex(int index, boolean free) 
	    throws SQLDBAPIException {

	try {
	    callableStatement = connection.prepareCall("UPDATE car SET free= 1 WHERE id_car = ?");
//	    callableStatement.setBoolean(1, free);
	    callableStatement.setInt(1, index);
	    callableStatement.executeUpdate();
	} catch (SQLException ex) {
	    LOGGER.error(ex);
	    throw new SQLDBAPIException(ex);
//	    System.out.println(ex);
	} finally {
	    releaseAllResources(connection);
	}
    }

    @Override
    public boolean updateById(Entity entity, int index)
	    throws IndexWrongException, SQLDBAPIException, PointerNullException {

	boolean result;

	try {
	    Car tempCar = (Car) entity;
	    callableStatement = connection.prepareCall(UPDATECARBYID);
	    callableStatement.setInt(1, tempCar.getId());
	    callableStatement.setInt(2, tempCar.getManufacturer().getId());
	    callableStatement.setInt(3, tempCar.getModel().getId());
	    callableStatement.setBoolean(4, tempCar.isFree());
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
	    callableStatement = connection.prepareCall(DELETECAR);
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
