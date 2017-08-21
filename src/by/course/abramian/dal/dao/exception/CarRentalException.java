/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.course.abramian.dal.dao.exception;

/**
 *
 * @author Abramian Roland Arturovich
 */
public class CarRentalException extends Exception {

    public CarRentalException() {
    }

    public CarRentalException(String message) {
	super(message);
    }

    public CarRentalException(Throwable cause) {
	super(cause);
    }

}
