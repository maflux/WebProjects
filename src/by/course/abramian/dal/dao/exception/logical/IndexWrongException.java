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
public class IndexWrongException extends LogicalException {

    public IndexWrongException() {
    }

    public IndexWrongException(String message) {
	super(message);
    }

    public IndexWrongException(Throwable cause) {
	super(cause);
    }

}
