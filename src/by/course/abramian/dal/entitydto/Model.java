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
public class Model extends Entity {

    private String color;
    private String transmission;
    private String bodyType;
    private int power;

    public Model() {
    }

    public Model(String color, String transmission, String bodyType, int power, int id) {
	super(id);
	this.color = color;
	this.transmission = transmission;
	this.bodyType = bodyType;
	this.power = power;
    }

    public Model(String color, String transmission, String bodyType, int power) {
	this.color = color;
	this.transmission = transmission;
	this.bodyType = bodyType;
	this.power = power;
    }

    public String getColor() {
	return color;
    }

    public void setColor(String color) {
	this.color = color;
    }

    public String getTransmission() {
	return transmission;
    }

    public void setTransmission(String transmission) {
	this.transmission = transmission;
    }

    public String getBodyType() {
	return bodyType;
    }

    public void setBodyType(String bodyType) {
	this.bodyType = bodyType;
    }

    public int getPower() {
	return power;
    }

    public void setPower(int power) {
	this.power = power;
    }

    @Override
    public String toString() {
	return "Model{" + super.toString() + "color=" + color + ", transmission="
		+ transmission + ", body_type=" + bodyType + ", power=" + power + '}';
    }

    @Override
    public int hashCode() {
	int hash = 5;
	hash = 89 * hash + Objects.hashCode(this.color);
	hash = 89 * hash + Objects.hashCode(this.transmission);
	hash = 89 * hash + Objects.hashCode(this.bodyType);
	hash = 89 * hash + this.power;
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
	final Model other = (Model) obj;
	if (this.power != other.power) {
	    return false;
	}
	if (!Objects.equals(this.color, other.color)) {
	    return false;
	}
	if (!Objects.equals(this.transmission, other.transmission)) {
	    return false;
	}
	if (!Objects.equals(this.bodyType, other.bodyType)) {
	    return false;
	}
	return true;
    }

}
