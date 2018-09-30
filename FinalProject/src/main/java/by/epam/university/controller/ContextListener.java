package by.epam.university.controller;

import by.epam.university.dao.connection.ConnectionPool;
import by.epam.university.dao.exception.ConnectionPoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * The class initializes and destroys the connection pool
 * when application starts and shuts down respectively.
 *
 */
public class ContextListener implements ServletContextListener {

    private static final Logger logger
            = LogManager.getLogger(ContextListener.class);

    /** Properties file with data base and connection pool configurations */
    private static final String PROPERTIES_FILE = "db";

    public void contextInitialized(ServletContextEvent sce) {

        try {
            ConnectionPool.getInstance().initialize(PROPERTIES_FILE);
            logger.log(Level.DEBUG, "Connection pool was initialized");

        } catch (ConnectionPoolException e) {
            logger.log(Level.ERROR, "Connection pool wasn't initialized");
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {

        try {
            ConnectionPool.getInstance().destroy();
            logger.log(Level.DEBUG, "Connection pool was closed");

        } catch (ConnectionPoolException e) {
            logger.log(Level.ERROR,
                    "Connection pool wasn't closed correctly");
        }
    }
}
