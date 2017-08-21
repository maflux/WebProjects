package by.course.abramian.dal.dao.interfaces;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import static by.course.abramian.dal.dao.config.LocalConstForConnection.*;
import by.course.abramian.dal.dao.exception.logical.NotFoundClassException;
import by.course.abramian.dal.dao.exception.technical.SQLConnectionException;
import static by.course.abramian.dal.log4j.Log4j.*;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Abramian Roland Arturovich
 */
public class AbstractDAO {

    protected Connection connection = null;
    protected CallableStatement callableStatement = null;
    protected ResultSet resultSet = null;
    protected PreparedStatement preparedStatement = null;
    protected Statement statement = null;

    protected ConnectionPoolSingleton connectionPool;

    {
	try {
	    Class.forName(DRIVER);
	    connectionPool = ConnectionPoolSingleton.getConnectionPoolSingleton();
	    LOGGER.trace(DRIVER + " - LOADED");
	} catch (ClassNotFoundException ex) {
//	    throw new NotFoundClassException();
	    LOGGER.error(ex);
//            System.out.println(e);
	}
    }

    public Connection getConnection() throws SQLConnectionException {
	try {
	    if (connectionPool.tryLock()) {
		return connectionPool.poll();
	    } else {
		throw new SQLConnectionException("No free connections to DB! Please retry later.");
	    }

	} catch (SQLException | SQLConnectionException ex) {
	    LOGGER.error(ex);
	    throw new SQLConnectionException(ex);
	}

    }

    public void releaseConnection(Connection connection) {
	connectionPool.offer(connection);
    }

    public void releaseAllResources(Connection connection) {
	connectionPool.offer(connection);
	callableStatement = null;
	resultSet = null;
	preparedStatement = null;
	statement = null;
    }

    public void releaseCallableStatement() {
	callableStatement = null;
    }

    public void releaseResultSet() {
	resultSet = null;
    }

    public void releasePreparedStatement() {
	preparedStatement = null;
    }

    public void releaseStatement() {
	statement = null;
    }

}
