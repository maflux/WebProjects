/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.course.abramian.dal.entitydto;

import static by.course.abramian.dal.dao.config.LocalConstForDAO.*;
import java.util.Objects;

/**
 *
 * @author Abramian Roland Arturovich
 */
public class User extends Entity {

    private String login;
    private String password;
    private Role role;

    public User() {
    }

    public User(int id, String login, String password, Role role) {
        super(id);
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(String login, String password, Role role) {
	this.login = login;
	this.password = password;
	this.role = role;
    }
    

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + super.toString() + "login=" + login
                + ", password=" + password + ", role=" + role + '}';
    }

    @Override
    public int hashCode() {
	int hash = 5;
	hash = 29 * hash + Objects.hashCode(this.login);
	hash = 29 * hash + Objects.hashCode(this.password);
	hash = 29 * hash + Objects.hashCode(this.role);
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
	final User other = (User) obj;
	if (!Objects.equals(this.login, other.login)) {
	    return false;
	}
	if (!Objects.equals(this.password, other.password)) {
	    return false;
	}
	if (!Objects.equals(this.role, other.role)) {
	    return false;
	}
	return true;
    }
    
    
}
