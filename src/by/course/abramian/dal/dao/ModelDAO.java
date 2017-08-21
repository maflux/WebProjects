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
import by.course.abramian.dal.dao.interfaces.BasicActionDAO;
import by.course.abramian.dal.dao.interfaces.IModelDAO;
import by.course.abramian.dal.dao.interfaces.AbstractDAO;
import by.course.abramian.dal.entitydto.Entity;
import by.course.abramian.dal.entitydto.Model;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import static by.course.abramian.dal.log4j.Log4j.*;

/**
 *
 * @author Abramian Roland Arturovich
 */
public class ModelDAO extends AbstractDAO implements IModelDAO {

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
	List<Entity> models = null;

	try {
	    callableStatement = connection.prepareCall(GETALLMODELS);
	    resultSet = callableStatement.executeQuery();

	    models = new ArrayList<>();

	    while (resultSet.next()) {
		models.add(new Model(
			resultSet.getString("color"),
			resultSet.getString("transmission"),
			resultSet.getString("body_type"),
			resultSet.getInt("power"),
			resultSet.getInt("id_model")));
	    }

	} catch (SQLException ex) {
	    LOGGER.error(ex);
	    throw new SQLDBAPIException(ex);
//	    System.out.println(e);
	} finally {
	    releaseAllResources(connection);
	}

	return models;
    }

    @Override
    public Entity getByIndex(int index)
	    throws IndexWrongException, SQLDBAPIException {

	if (index < 0) {
	    throw new IndexWrongException();
	}

	Entity model = null;

	try {
	    callableStatement = connection.prepareCall(GETMODELBYINDEX);
	    callableStatement.setInt(1, index);
	    resultSet = callableStatement.executeQuery();

	    while (resultSet.next()) {
		model = new Model(
			resultSet.getString("color"),
			resultSet.getString("transmission"),
			resultSet.getString("body_type"),
			resultSet.getInt("power"),
			resultSet.getInt("id_model"));

	    }
	} catch (SQLException ex) {
	    LOGGER.error(ex);
	    throw new SQLDBAPIException(ex);
//	    System.out.println(e);
	} finally {
	    releaseAllResources(connection);
	}

	return model;
    }

    @Override
    public int getNumberOf() throws SQLDBAPIException {
	int tempNumber = 0;

	try {
	    callableStatement = connection.prepareCall(GETNUMBEROFMODELS);
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
	Model tempModel = (Model) entity;

	try {
	    callableStatement = connection.prepareCall(INSERTMODEL);
	    callableStatement.setString(1, tempModel.getColor());
	    callableStatement.setString(2, tempModel.getTransmission());
	    callableStatement.setString(3, tempModel.getBodyType());
	    callableStatement.setInt(4, tempModel.getPower());
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
	    Model tempModel = (Model) entity;
	    callableStatement = connection.prepareCall(INSERTMODELBYID);
	    callableStatement.setInt(1, tempModel.getId());
	    callableStatement.setString(2, tempModel.getColor());
	    callableStatement.setString(3, tempModel.getTransmission());
	    callableStatement.setString(4, tempModel.getBodyType());
	    callableStatement.setInt(5, tempModel.getPower());
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

	if (entity == null) {
	    throw new PointerNullException();
	}

	try {
	    Model tempModel = (Model) entity;
	    callableStatement = connection.prepareCall(UPDATEMODELBYID);
	    callableStatement.setInt(1, tempModel.getId());
	    callableStatement.setString(2, tempModel.getColor());
	    callableStatement.setString(3, tempModel.getTransmission());
	    callableStatement.setString(4, tempModel.getBodyType());
	    callableStatement.setInt(5, tempModel.getPower());
	    callableStatement.setInt(6, index);
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
	    callableStatement = connection.prepareCall(DELETEMODEL);
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
