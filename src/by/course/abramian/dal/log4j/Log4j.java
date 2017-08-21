/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.course.abramian.dal.log4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;
import org.apache.log4j.Appender;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

/**
 *
 * @author Abramian Roland Arturovich
 */
public class Log4j {

    public static Logger LOGGER = Logger.getRootLogger();

    static {
//	InputStream in = null;
//	try {
//	    Properties prop = new Properties();
//	    in = new FileInputStream(
//		    new File(Log4j.class.getResource("log4jDBAPI.properties").toURI()));
//	    prop.load(in);
//	    PropertyConfigurator.configure(prop);
	    LOGGER.setLevel(Level.ALL);
//	} catch (IOException | URISyntaxException ex) {
//	    System.out.println(ex);
//	} finally {
////	    if (in != null) {
//	    try {
//		in.close();
//	    } catch (IOException ex) {
//		System.out.println(ex);
//	    }
////	    }
//	}
    }

}
