/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.course.abramian.dal.dao.interfaces;

import by.course.abramian.dal.dao.exception.logical.IndexWrongException;
import by.course.abramian.dal.dao.exception.logical.PointerNullException;
import by.course.abramian.dal.dao.exception.technical.SQLDBAPIException;
import by.course.abramian.dal.entitydto.Entity;
import java.util.List;

/**
 *
 * @author User
 */
public interface ICarDAO extends BasicActionDAO {

    void updateFieldFreeByIndex(int index, boolean free) throws SQLDBAPIException;

}
