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
public class SQLDBAPIException extends TechnicalException {

    public SQLDBAPIException() {
    }

    public SQLDBAPIException(String message) {
	super(message);
    }

    public SQLDBAPIException(Throwable cause) {
	super(cause);
    }

}
