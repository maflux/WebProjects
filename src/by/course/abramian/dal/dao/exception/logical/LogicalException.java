/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.course.abramian.dal.dao.exception.logical;

import by.course.abramian.dal.dao.exception.CarRentalException;

/**
 *
 * @author Abramian Roland Arturovich
 */
public class LogicalException extends CarRentalException {

    public LogicalException() {
    }

    public LogicalException(String message) {
	super(message);
    }

    public LogicalException(Throwable cause) {
	super(cause);
    }

}
