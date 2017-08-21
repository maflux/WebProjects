package by.course.abramian.dal.entitydto;

import static by.course.abramian.dal.dao.config.LocalConstForDAO.*;
import java.util.Objects;

/**
 *
 * @author Abramian Roland Arturovich
 */
public class Role extends Entity {

    private String role;

    public Role() {
	super(Integer.parseInt(DEFAULTIDROLE));
	this.role = DEFAULTROLE;
    }

    public Role(int id, String role) {
	super(id);
	this.role = role;
    }

    public Role(String role) {
	this.role = role;
    }

    public String getRole() {
	return role;
    }

    public void setRole(String role) {
	this.role = role;
    }

    @Override
    public String toString() {
	return "Role{" + super.toString() + "role=" + role + '}';
    }

    @Override
    public int hashCode() {
	int hash = 7;
	hash = 71 * hash + Objects.hashCode(this.role);
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
	final Role other = (Role) obj;
	if (!Objects.equals(this.role, other.role)) {
	    return false;
	}
	return true;
    }

}
