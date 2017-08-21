package by.course.abramian.dal.dao.config;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Abramian Roland Arturovich
 */
public class LocalConstForDAO {

    private static final ResourceBundle bundleForRole;
    private static final ResourceBundle bundleForUser;
    private static final ResourceBundle bundleForCar;
    private static final ResourceBundle bundleForOrder;
    private static final ResourceBundle bundleForModel;
    private static final ResourceBundle bundleForManufacturer;
    private static final ResourceBundle bundleForClient;

    static {
	bundleForRole = ResourceBundle.getBundle(
		"by.course.abramian.dal.dao.config.SQLForRoleDAO",
		new Locale("SQLForRoleDAO"));
	bundleForUser = ResourceBundle.getBundle(
		"by.course.abramian.dal.dao.config.SQLForUserDAO",
		new Locale("SQLForUserDAO"));
	bundleForCar = ResourceBundle.getBundle(
		"by.course.abramian.dal.dao.config.SQLForCarDAO",
		new Locale("SQLForCarDAO"));
	bundleForOrder = ResourceBundle.getBundle(
		"by.course.abramian.dal.dao.config.SQLForOrderDAO",
		new Locale("SQLForOrderDAO"));
	bundleForModel = ResourceBundle.getBundle(
		"by.course.abramian.dal.dao.config.SQLForModelDAO",
		new Locale("SQLForModelDAO"));
	bundleForManufacturer = ResourceBundle.getBundle(
		"by.course.abramian.dal.dao.config.SQLForManufacturerDAO",
		new Locale("SQLForManufacturerDAO"));
	bundleForClient = ResourceBundle.getBundle(
		"by.course.abramian.dal.dao.config.SQLForClientDAO",
		new Locale("SQLForClientDAO"));
    }

    public static final String ROLESPROCEDURE = bundleForRole.getString("getAllRoles");
    public static final String GETROLEBYINDEX = bundleForRole.getString("getRoleByIndex");
    public static final String GETNUMBEROFROLES = bundleForRole.getString("getNumberOfRoles");
    public static final String INSERTROLE = bundleForRole.getString("insertRole");
    public static final String DELETEROLE = bundleForRole.getString("deleteRole");
    public static final String DEFAULTROLE = bundleForRole.getString("defaultRole");
    public static final String DEFAULTIDROLE = bundleForRole.getString("defaultIdRole");
    public static final String INSERTROLEBYID = bundleForRole.getString("insertRoleById");
    public static final String UPDATEROLEBYID = bundleForRole.getString("updateRoleById");

    public static final String GETALLUSERS = bundleForUser.getString("getAllUsers");
    public static final String GETUSERBYINDEX = bundleForUser.getString("getUserByIndex");
    public static final String GETUSERSBYROLE = bundleForUser.getString("getUsersByRoles");
    public static final String GETNUMBEROFUSERS = bundleForUser.getString("getNumberOfUsers");
    public static final String INSERTUSER = bundleForUser.getString("insertUser");
    public static final String DELETEUSER = bundleForUser.getString("deleteUser");
    public static final String CHECKLOGINPASSWORD = bundleForUser.getString("checkLoginPassword");
    public static final String INSERTUSERBYID = bundleForUser.getString("insertUserById");
    public static final String UPDATEUSERBYID = bundleForUser.getString("updateUserById");

    public static final String GETNUMBEROFCAR = bundleForCar.getString("getNumberOfCars");
    public static final String GETALLCAR = bundleForCar.getString("getAllCars");
    public static final String GETCARBYINDEX = bundleForCar.getString("getCarByIndex");
    public static final String INSERTCAR = bundleForCar.getString("insertCar");
    public static final String DELETECAR = bundleForCar.getString("deleteCar");
    public static final String INSERTCARBYINDEX = bundleForCar.getString("insertCarByIndex");
    public static final String INSERTCARBYID = bundleForCar.getString("insertCarById");
    public static final String UPDATECARBYID = bundleForCar.getString("updateCarById");

    public static final String GETORDERBYINDEX = bundleForOrder.getString("getOrderByIndex");
    public static final String GETALLORDERS = bundleForOrder.getString("getAllOrders");
    public static final String GETNUMBEROFORDERS = bundleForOrder.getString("getNumberOfOrders");
    public static final String INSERTORDER = bundleForOrder.getString("insertOrder");
    public static final String DELETEORDER = bundleForOrder.getString("deleteOrder");
    public static final String GETORDERSBYCLIENT = bundleForOrder.getString("getOrdersByClient");

    public static final String GETMODELBYINDEX = bundleForModel.getString("getModelByIndex");
    public static final String GETALLMODELS = bundleForModel.getString("getAllModels");
    public static final String GETNUMBEROFMODELS = bundleForModel.getString("getNumberOfModels");
    public static final String INSERTMODEL = bundleForModel.getString("insertModel");
    public static final String DELETEMODEL = bundleForModel.getString("deleteModel");
    public static final String INSERTMODELBYID = bundleForModel.getString("insertModelById");
    public static final String UPDATEMODELBYID = bundleForModel.getString("updateModelById");

    public static final String GETMANUFACTURERBYINDEX = bundleForManufacturer.getString("getManufacturerByIndex");
    public static final String GETALLMANUFACTURERS = bundleForManufacturer.getString("getAllManufacturers");
    public static final String GETNUMBEROFMANUFACTURERS = bundleForManufacturer.getString("getNumberOfManufacturers");
    public static final String INSERTMANUFACTURER = bundleForManufacturer.getString("insertManufacturer");
    public static final String DELETEMANUFACTURER = bundleForManufacturer.getString("deleteManufacturer");
    public static final String INSERTMANUFACTURERBYID = bundleForManufacturer.getString("insertManufacturerById");
    public static final String UPDATEMANUFACTURERBYID = bundleForManufacturer.getString("updateManufacturerById");

    public static final String GETCLIENTBYINDEX = bundleForClient.getString("getClientByIndex");
    public static final String GETALLCLIENTS = bundleForClient.getString("getAllClients");
    public static final String GETNUMBEROFCLIENTS = bundleForClient.getString("getNumberOfClients");
    public static final String INSERTCLIENT = bundleForClient.getString("insertClient");
    public static final String DELETECLIENT = bundleForClient.getString("deleteClient");
    public static final String GETIDCLIENTBYUSER = bundleForClient.getString("getIDClientByUser");
}
