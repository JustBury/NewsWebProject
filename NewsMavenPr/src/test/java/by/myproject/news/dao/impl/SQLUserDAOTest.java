package by.myproject.news.dao.impl;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.BeforeClass;

import org.junit.Test;

import by.myproject.news.bean.RegistrationInfo;
import by.myproject.news.bean.User;
import by.myproject.news.dao.DAOProvider;
import by.myproject.news.dao.UserDAO;
import by.myproject.news.dao.connection.ConnectionPool;
import by.myproject.news.dao.connection.ConnectionPoolException;
import by.myproject.news.dao.exception.DAOException;

public class SQLUserDAOTest {

	private static final ConnectionPool cp = ConnectionPool.getInstance();
	private static final DAOProvider PROVIDER = DAOProvider.getInstance();
	private static final UserDAO USER_DAO = PROVIDER.getUserDAO();

	@BeforeClass
	public static void creatConnectionPool() {
		try {
			cp.initPoolData();
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void authorizationTest() {
		try (Connection connection = cp.takeConnection();) {
			User expectesUser = new User();
			RegistrationInfo info = new RegistrationInfo();
			info.setEmail("test@test.test");
			info.setFirstPassword("123qweASD$");
			expectesUser.setId(16);
			expectesUser.setName("Test");
			expectesUser.setSurname("Test");
			expectesUser.setRole("user");
			expectesUser.setEmail("test@test.test");
			expectesUser.setDateRegistration("2021-09-08 16:52:38");
			User actualUser = USER_DAO.authorization(info);
			Assert.assertEquals(expectesUser, actualUser);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}

	}

}
