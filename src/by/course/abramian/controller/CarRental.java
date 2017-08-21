/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.course.abramian.controller;

import by.course.abramian.dal.dao.CarDAO;
import by.course.abramian.dal.dao.ClientDAO;
import by.course.abramian.dal.dao.ManufacturerDAO;
import by.course.abramian.dal.dao.ModelDAO;
import by.course.abramian.dal.dao.OrderDAO;
import by.course.abramian.dal.dao.RoleDAO;
import by.course.abramian.dal.dao.UserDAO;
import by.course.abramian.dal.dao.exception.technical.SQLDBAPIException;
import by.course.abramian.dal.dao.exception.technical.TechnicalException;
import by.course.abramian.dal.dao.interfaces.BasicActionDAO;
import by.course.abramian.dal.dao.interfaces.ICarDAO;
import by.course.abramian.dal.dao.interfaces.IModelDAO;
import by.course.abramian.dal.dao.interfaces.IOrderDAO;
import by.course.abramian.dal.dao.interfaces.IRole;
import by.course.abramian.dal.dao.interfaces.IUserDAO;
import by.course.abramian.dal.entitydto.Entity;
import by.course.abramian.dal.entitydto.Car;
import by.course.abramian.dal.entitydto.Client;
import by.course.abramian.dal.entitydto.Manufacturer;
import by.course.abramian.dal.entitydto.Model;
import by.course.abramian.dal.entitydto.Order;
import by.course.abramian.dal.entitydto.Role;
import by.course.abramian.dal.entitydto.User;
import by.course.abramian.dal.log4j.Log4j;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abramian Roland Arturovich
 */
public class CarRental {

    public static void main(String[] args) {

//	BasicActionDAO iRole = new RoleDAO();
//	IUserDAO iUser = new UserDAO();
	ICarDAO iCar = new CarDAO();
//	IOrderDAO iOrder = new OrderDAO();
//	IModelDAO iModel = new ModelDAO();
//	BasicActionDAO iManufacturer = new ManufacturerDAO();
//	BasicActionDAO iClient = new ClientDAO();
//-------------------------------------------------------------
//	Client client = new Client("test", "Irina", "Vasilieva", "", "+6666655", "", 
//		new User(5, "ira", "1234", 
//			new Role(2, "User")),
//		4);
//	System.out.println(iClient.insertDefault(client));
//	System.out.println(iClient.getNumberOf());
//	System.out.println(iClient.getByIndex(1));
//	List<AbstractEntity> clients = iClient.getAll();
//	for (Entity client : clients) {
//	    System.out.println(client);
//	}
//-------------------------------------------------------------
//	Manufacturer tempManu = new Manufacturer("ZAZ", 1);
//	System.out.println(iManufacturer.insertDefault(tempManu));
//	System.out.println(iManufacturer.getNumberOf());
//	System.out.println(iManufacturer.getByIndex(2));
//	List<AbstractEntity> manus = iManufacturer.getAll();
//	for (Entity manu : manus) {
//	    System.out.println(manu);
//	}
//-------------------------------------------------------------
//	Model tempModel = new Model("LimeGreen", "Manual", "Coupe", 230, 0);
//	System.out.println(iModel.insertDefault(tempModel));
//	System.out.println(iModel.getNumberOf());
//	System.out.println(iModel.getByIndex(1));
//	List<AbstractEntity> models = iModel.getAll();
//	for (Entity model : models) {
//	    System.out.println(model);
//	}
//-------------------------------------------------------------
//	Calendar tempDate = new GregorianCalendar(2017, 7, 25);
//	Order order = new Order(
//		new Timestamp(System.currentTimeMillis()),
//		new Timestamp(tempDate.getTimeInMillis()),
//		new Car(
//			new Manufacturer("ZAZ", 13),
//			new Model("Brown", "Manual", "Sedan", 130, 3),
//			true, 
//			3),
//		new Client("test", "Irina", "Vasilieva", "", "+6666655", "",
//			new User(6, "ira", "1234",
//				new Role(2, "User")),
//			5),
//		0);
//	System.out.println(iOrder.insertDefault(order));
//	System.out.println(iOrder.getNumberOf());
//	System.out.println(iOrder.getByIndex(2));
//	Log4j.LOGGER.debug("DEBUG TEST");
//	List<AbstractEntity> orders = iOrder.getAll();
//	for (Entity order : orders) {
//	    System.out.println(order);
//	}
//-------------------------------------------------------------
//	Car car = new Car(
//		new Manufacturer("Kia", 5),
//		new Model("Brown", "Manual", "Sedan", 130, 3),
//		true, 0);
//	System.out.println(iCar.insertDefault(car));
	try {
	    List<Entity> cars = iCar.getAll();
	    for (Entity car : cars) {
		System.out.println(car.toString());
	    }
	} catch (SQLDBAPIException ex) {
	    System.out.println(ex);
	}
//-------------------------------------------------------------
//	Role tempRole = new Role(10, "trolowka");
//	IRole iRole = new RoleDAO();
//        System.out.println(iRole.insertDefault(tempRole));
//        System.out.println(iRole.delete(13));
//        System.out.println(iRole.getByIndex(7));
//-----------------------------------------------------------
//	BasicActionDAO iUser = new UserDAO();
//	User user = new User(0, "varvara", "123", new Role(10, "tolik"));
//	System.out.println(iUser.delete(5));
//	try {
//	    System.out.println(iUser.getIDUserByLoginPassword("ivanko", "123456"));
//
//	} catch (TechnicalException ex) {
//	    System.out.println(ex);
//	}
//        List<AbstractEntity> users = iUser.getAll();
//        List<User> users = iUser.getUsersByRole(2);
//        for (Entity user : users) {
//            System.out.println(user);
//        }
//-------------------------------------------------------------

    }

}
