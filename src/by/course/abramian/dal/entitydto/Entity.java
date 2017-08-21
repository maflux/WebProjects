/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.course.abramian.dal.entitydto;

/**
 *
 * @author Abramian Roland Arturovich
 */
public class Entity {

    private int id;

    protected Entity() {
    }

    public Entity(int id) {
	this.id = id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public int getId() {
	return id;
    }

    @Override
    public String toString() {
	return "{" + "id=" + id + '}';
    }

}
