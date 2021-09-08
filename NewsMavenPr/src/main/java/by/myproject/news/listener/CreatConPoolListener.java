package by.myproject.news.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.myproject.news.dao.connection.ConnectionPool;
import by.myproject.news.dao.connection.ConnectionPoolException;
import by.myproject.news.dao.impl.SQLUserDAO;
import jakarta.servlet.ServletContextListener;

public class CreatConPoolListener implements ServletContextListener {

	private static final ConnectionPool cp = ConnectionPool.getInstance();
	private static final Logger log = LogManager.getLogger(CreatConPoolListener.class);

	public CreatConPoolListener() {
		try {
			
			cp.initPoolData();
		} catch (ConnectionPoolException e) {
			log.error(e);
		}
	}
}
