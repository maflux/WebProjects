/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.course.abramian.dal.entitydto;

import java.util.Calendar;
import java.util.Objects;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

/**
 *
 * @author Abramian Roland Arturovich
 */
public class Order extends Entity {

    private Timestamp dateOfBegining;
    private Timestamp dateOfEnding;
    private Car car;
    private Client client;

    public Order() {
    }

    public Order(Timestamp dateOfBegining, Timestamp dateOfEnding, Car car, Client client, int id) {
	super(id);
	this.dateOfBegining = dateOfBegining;
	this.dateOfEnding = dateOfEnding;
	this.car = car;
	this.client = client;
    }

    public Order(Timestamp dateOfBegining, Timestamp dateOfEnding, Car car, Client client) {
	this.dateOfBegining = dateOfBegining;
	this.dateOfEnding = dateOfEnding;
	this.car = car;
	this.client = client;
    }

    public Timestamp getDateOfBegining() {
	return dateOfBegining;
    }

    public void setDateOfBegining(Timestamp dateOfBegining) {
	this.dateOfBegining = dateOfBegining;
    }

    public Timestamp getDateOfEnding() {
	return dateOfEnding;
    }

    public void setDateOfEnding(Timestamp dateOfEnding) {
	this.dateOfEnding = dateOfEnding;
    }

    public Car getCar() {
	return car;
    }

    public void setCar(Car car) {
	this.car = car;
    }

    public Client getClient() {
	return client;
    }

    public void setClient(Client client) {
	this.client = client;
    }

    @Override
    public String toString() {
	return "Order{" + super.toString() + "dateOfBegining="
		+ dateOfBegining + ", dateOfEnding=" + dateOfEnding + ", car="
		+ car + ", client=" + client + '}';
    }

    @Override
    public int hashCode() {
	int hash = 7;
	hash = 67 * hash + Objects.hashCode(this.dateOfBegining);
	hash = 67 * hash + Objects.hashCode(this.dateOfEnding);
	hash = 67 * hash + Objects.hashCode(this.car);
	hash = 67 * hash + Objects.hashCode(this.client);
	return hash;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final Order other = (Order) obj;
	if (!Objects.equals(this.dateOfBegining, other.dateOfBegining)) {
	    return false;
	}
	if (!Objects.equals(this.dateOfEnding, other.dateOfEnding)) {
	    return false;
	}
	if (!Objects.equals(this.car, other.car)) {
	    return false;
	}
	if (!Objects.equals(this.client, other.client)) {
	    return false;
	}
	return true;
    }

}
