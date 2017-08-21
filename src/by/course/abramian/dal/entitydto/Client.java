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
public class Client extends Entity {

    private String passport;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String numberPhone;
    private String email;
    private User user;

    public Client() {
    }

    public Client(String passport, String firstName, String lastName, String fatherName, String numberPhone, String email, User user, int id) {
	super(id);
	this.passport = passport;
	this.firstName = firstName;
	this.lastName = lastName;
	this.fatherName = fatherName;
	this.numberPhone = numberPhone;
	this.email = email;
	this.user = user;
    }

    public Client(String passport, String firstName, String lastName, String fatherName, String numberPhone, String email, User user) {
	this.passport = passport;
	this.firstName = firstName;
	this.lastName = lastName;
	this.fatherName = fatherName;
	this.numberPhone = numberPhone;
	this.email = email;
	this.user = user;
    }

    public String getPassport() {
	return passport;
    }

    public void setPassport(String passport) {
	this.passport = passport;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getFatherName() {
	return fatherName;
    }

    public void setFatherName(String fatherName) {
	this.fatherName = fatherName;
    }

    public String getNumberPhone() {
	return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
	this.numberPhone = numberPhone;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    @Override
    public String toString() {
	return "Client{" + super.toString() + "passport=" + passport + ", first_name="
		+ firstName + ", last_name=" + lastName + ", father_name="
		+ fatherName + ", number_phone=" + numberPhone + ", email="
		+ email + ", user=" + user + '}';
    }

    @Override
    public int hashCode() {
	int hash = 3;
	hash = 97 * hash + Objects.hashCode(this.passport);
	hash = 97 * hash + Objects.hashCode(this.firstName);
	hash = 97 * hash + Objects.hashCode(this.lastName);
	hash = 97 * hash + Objects.hashCode(this.fatherName);
	hash = 97 * hash + Objects.hashCode(this.numberPhone);
	hash = 97 * hash + Objects.hashCode(this.email);
	hash = 97 * hash + Objects.hashCode(this.user);
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
	final Client other = (Client) obj;
	if (!Objects.equals(this.passport, other.passport)) {
	    return false;
	}
	if (!Objects.equals(this.firstName, other.firstName)) {
	    return false;
	}
	if (!Objects.equals(this.lastName, other.lastName)) {
	    return false;
	}
	if (!Objects.equals(this.fatherName, other.fatherName)) {
	    return false;
	}
	if (!Objects.equals(this.numberPhone, other.numberPhone)) {
	    return false;
	}
	if (!Objects.equals(this.email, other.email)) {
	    return false;
	}
	if (!Objects.equals(this.user, other.user)) {
	    return false;
	}
	return true;
    }

}
