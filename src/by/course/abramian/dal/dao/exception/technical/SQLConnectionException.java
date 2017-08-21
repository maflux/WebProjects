/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.course.abramian.dal.dao.exception.technical;

/**
 *
 * @author Abramian Roland Arturovich
 */
public class SQLConnectionException extends TechnicalException {

    public SQLConnectionException() {
    }

    public SQLConnectionException(String message) {
	super(message);
    }

    public SQLConnectionException(Throwable cause) {
	super(cause);
    }

}
