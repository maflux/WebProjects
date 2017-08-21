/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.course.abramian.dal.dao.interfaces;

import static by.course.abramian.dal.dao.config.LocalConstForConnection.*;
import static by.course.abramian.dal.log4j.Log4j.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Abramian Roland Arturovich
 */
public class ConnectionPoolSingleton {

    public static final int MAXCONNECTIONS = 2;
    private int totalUsedConnections = 0;
    private int freeConnections = 0;
    private Queue<Connection> connections;
    private Lock lock;

    {
	lock = new ReentrantLock();
	connections = new ArrayBlockingQueue<>(MAXCONNECTIONS);
    }

    private static class SingletonHolder {

	private static final ConnectionPoolSingleton INSTANCE = new ConnectionPoolSingleton();
    }

    private ConnectionPoolSingleton() {
    }

    public static ConnectionPoolSingleton getConnectionPoolSingleton() {
	return SingletonHolder.INSTANCE;
    }

    public Connection poll() throws SQLException {
	lock.lock();
	try {

	    if (freeConnections == 0) {
		if (totalUsedConnections < MAXCONNECTIONS) {
		    addConnectionInPool();
		} else {
		    throw new SQLException("No free connections to DB! Please retry later.");
		}
	    }
	    --freeConnections;
	    return connections.poll();

	} catch (SQLException ex) {
	    LOGGER.error(ex);
	    throw new SQLException(ex);
	} finally {
	    lock.unlock();
	}
    }

    private void addConnectionInPool() throws SQLException {
	try {
	    connections.offer(DriverManager.getConnection(URL, LOGIN, PASSWORD));
	    ++freeConnections;
	    ++totalUsedConnections;
	} catch (SQLException ex) {
	    LOGGER.error(ex);
	    throw new SQLException(ex);
	}
    }

    public void offer(Connection connection) {
	lock.lock();
	try {

	    connections.offer(connection);
	    ++freeConnections;

	} finally {
	    lock.unlock();
	}
    }

    public boolean tryLock() {
	return lock.tryLock();
    }

    public boolean checkFreeConnection() {
	return totalUsedConnections < MAXCONNECTIONS;
    }

}
