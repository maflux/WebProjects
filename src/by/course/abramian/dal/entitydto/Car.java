/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.course.abramian.dal.entitydto;

import java.util.Objects;

/**
 *
 * @author Abramian Roland Arturovich
 */
public class Car extends Entity {

    private Manufacturer manufacturer;
    private Model model;
    private boolean free;

    public Car() {
    }

    public Car(Manufacturer manufacturer, Model model, boolean free, int id) {
	super(id);
	this.manufacturer = manufacturer;
	this.model = model;
	this.free = free;
    }

    public Car(Manufacturer manufacturer, Model model, boolean free) {
	this.manufacturer = manufacturer;
	this.model = model;
	this.free = free;
    }

    public Manufacturer getManufacturer() {
	return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
	this.manufacturer = manufacturer;
    }

    public Model getModel() {
	return model;
    }

    public void setModel(Model model) {
	this.model = model;
    }

    public boolean isFree() {
	return free;
    }

    public void setFree(boolean free) {
	this.free = free;
    }

    @Override
    public String toString() {
	return "Car{" + super.toString() + "manufacturer=" + manufacturer
		+ ", model=" + model + ", free=" + free + '}';
    }

    @Override
    public int hashCode() {
	int hash = 3;
	hash = 97 * hash + Objects.hashCode(this.manufacturer);
	hash = 97 * hash + Objects.hashCode(this.model);
	hash = 97 * hash + (this.free ? 1 : 0);
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
	final Car other = (Car) obj;
	if (this.free != other.free) {
	    return false;
	}
	if (!Objects.equals(this.manufacturer, other.manufacturer)) {
	    return false;
	}
	if (!Objects.equals(this.model, other.model)) {
	    return false;
	}
	return true;
    }

}
