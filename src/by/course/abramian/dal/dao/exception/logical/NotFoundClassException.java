/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.course.abramian.dal.dao.exception.logical;

/**
 *
 * @author Abramian Roland Arturovich
 */
public class NotFoundClassException extends LogicalException {

    public NotFoundClassException() {
    }

    public NotFoundClassException(String message) {
	super(message);
    }

    public NotFoundClassException(Throwable cause) {
	super(cause);
    }

}
