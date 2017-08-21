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
import by.course.abramian.dal.dao.interfaces.IOrderDAO;
import by.course.abramian.dal.dao.interfaces.AbstractDAO;
import by.course.abramian.dal.entitydto.Entity;
import by.course.abramian.dal.entitydto.Car;
import by.course.abramian.dal.entitydto.Client;
import by.course.abramian.dal.entitydto.Manufacturer;
import by.course.abramian.dal.entitydto.Model;
import by.course.abramian.dal.entitydto.Order;
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
public class OrderDAO extends AbstractDAO implements IOrderDAO {

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
	List<Entity> tempOrders = null;

	try {
	    callableStatement = connection.prepareCall(GETALLORDERS);
	    resultSet = callableStatement.executeQuery();

	    tempOrders = new ArrayList<>();
	    while (resultSet.next()) {
		tempOrders.add(new Order(
			resultSet.getTimestamp("date_of_begining"),
			resultSet.getTimestamp("date_of_ending"),
			new Car(
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
				resultSet.getInt("id_car")),
			new Client(
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
				resultSet.getInt("id_user")),
			resultSet.getInt("id_order")));
	    }

	} catch (SQLException ex) {
	    LOGGER.error(ex);
	    throw new SQLDBAPIException(ex);
//	    System.out.println(e);
	} finally {
	    releaseAllResources(connection);
	}

	return tempOrders;
    }

    @Override
    public Entity getByIndex(int index)
	    throws IndexWrongException, SQLDBAPIException {

	if (index < 0) {
	    throw new IndexWrongException();
	}

	Order tempOrder = null;

	try {
	    callableStatement = connection.prepareCall(GETORDERBYINDEX);
	    callableStatement.setInt(1, index);
	    resultSet = callableStatement.executeQuery();

	    while (resultSet.next()) {
		tempOrder = new Order(
			resultSet.getTimestamp("date_of_begining"),
			resultSet.getTimestamp("date_of_ending"),
			new Car(
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
				resultSet.getInt("id_car")),
			new Client(
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
				resultSet.getInt("id_user")),
			resultSet.getInt("id_order"));
	    }

	} catch (SQLException ex) {
	    LOGGER.error(ex);
	    throw new SQLDBAPIException(ex);
//	    System.out.println(e);
	} finally {
	    releaseAllResources(connection);
	}

	return tempOrder;
    }

    @Override
    public int getNumberOf() throws SQLDBAPIException {
	int tempNumber = 0;

	try {
	    callableStatement = connection.prepareCall(GETNUMBEROFORDERS);
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
	Order tempOrder = (Order) entity;

	try {
	    callableStatement = connection.prepareCall(INSERTORDER);
	    callableStatement.setTimestamp(1, tempOrder.getDateOfBegining());
	    callableStatement.setTimestamp(2, tempOrder.getDateOfEnding());
	    callableStatement.setInt(3, tempOrder.getCar().getId());
	    callableStatement.setInt(4, tempOrder.getClient().getId());
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
	    callableStatement = connection.prepareCall(DELETEORDER);
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
    public List<Order> getOrdersByClient(int index)
	    throws IndexWrongException, SQLDBAPIException {

	if (index < 0) {
	    throw new IndexWrongException();
	}
	List<Order> tempOrders = null;

	try {
	    callableStatement = connection.prepareCall(GETORDERSBYCLIENT);
	    callableStatement.setInt(1, index);
	    resultSet = callableStatement.executeQuery();
	    tempOrders = new ArrayList<>();
	    while (resultSet.next()) {
		tempOrders.add(new Order(
			resultSet.getTimestamp("date_of_begining"),
			resultSet.getTimestamp("date_of_ending"),
			new Car(
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
				resultSet.getInt("id_car")),
			new Client(
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
				resultSet.getInt("id_user")),
			resultSet.getInt("id_order")));
	    }

	} catch (SQLException ex) {
	    LOGGER.error(ex);
	    throw new SQLDBAPIException(ex);
//	    System.out.println(e);
	} finally {
	    releaseAllResources(connection);
	}

	return tempOrders;
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
