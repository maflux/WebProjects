package by.course.abramian.dal.dao.config;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Abramian Roland Arturovich
 */
public class LocalConstForConnection {

    public static final ResourceBundle bundle;

    static {
        bundle = ResourceBundle.getBundle("by.course.abramian.dal.dao.config.DataForConnection",
                new Locale("DataForConnection"));
    }

    public static final String DRIVER = bundle.getString("driver");
    public static final String LOGIN = bundle.getString("login");
    public static final String PASSWORD = bundle.getString("password");
    public static final String URL = bundle.getString("url");

}
