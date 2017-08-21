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
public class Manufacturer extends Entity {

    private String manufacturer;

    public Manufacturer() {
    }

    public Manufacturer(String manufacturer, int id) {
	super(id);
	this.manufacturer = manufacturer;
    }

    public Manufacturer(String manufacturer) {
	this.manufacturer = manufacturer;
    }

    public String getManufacturer() {
	return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
	this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
	return "Manufacturer{" + super.toString() + "manufacturer=" + manufacturer + '}';
    }

    @Override
    public int hashCode() {
	int hash = 3;
	hash = 67 * hash + Objects.hashCode(this.manufacturer);
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
	final Manufacturer other = (Manufacturer) obj;
	if (!Objects.equals(this.manufacturer, other.manufacturer)) {
	    return false;
	}
	return true;
    }

}
