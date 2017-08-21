/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.course.abramian.dal.dao.interfaces;

import by.course.abramian.dal.dao.exception.logical.IndexWrongException;
import by.course.abramian.dal.dao.exception.logical.PointerNullException;
import by.course.abramian.dal.dao.exception.technical.SQLConnectionException;
import by.course.abramian.dal.dao.exception.technical.SQLDBAPIException;
import by.course.abramian.dal.entitydto.Entity;
import java.util.List;

/**
 *
 * @author Abramian Roland Arturovich
 */
public interface BasicActionDAO {

    List<Entity> getAll() throws SQLDBAPIException;

    Entity getByIndex(int index) throws IndexWrongException, SQLDBAPIException;

    int getNumberOf() throws SQLDBAPIException;

    int insertDefault(Entity entity) throws PointerNullException, SQLDBAPIException;
    
    boolean insertById(Entity entity)
	    throws IndexWrongException, SQLDBAPIException, PointerNullException;

    boolean updateById(Entity entity, int index)
	    throws IndexWrongException, SQLDBAPIException, PointerNullException;

    int delete(int index) throws IndexWrongException, SQLDBAPIException;

}
