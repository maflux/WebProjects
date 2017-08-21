/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.course.abramian.dal.dao.exception.technical;

import by.course.abramian.dal.dao.exception.CarRentalException;

/**
 *
 * @author Abramian Roland Arturovich
 */
public class TechnicalException extends CarRentalException {

    public TechnicalException() {
    }

    public TechnicalException(String message) {
	super(message);
    }

    public TechnicalException(Throwable cause) {
	super(cause);
    }

}
