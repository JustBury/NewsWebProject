package by.myproject.news.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.myproject.news.bean.RegistrationInfo;
import by.myproject.news.bean.User;
import by.myproject.news.dao.UserDAO;
import by.myproject.news.dao.connection.ConnectionPool;
import by.myproject.news.dao.connection.ConnectionPoolException;
import by.myproject.news.dao.exception.DAOException;

import by.myproject.news.dao.passwordauthentication.PasswordAuthentication;

public class SQLUserDAO implements UserDAO {

	private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
	private static final PasswordAuthentication passwordAuthentication = PasswordAuthentication.getInstance();
	private static final Logger log = LogManager.getLogger(SQLUserDAO.class);
	private static final String SELECT_USERS = "SELECT * FROM users";
	private static final String SELECT_USER = "SELECT * FROM users WHERE email = ?";
	private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id_Users = ?";
	private static final String DELETE_USER = "DELETE FROM users WHERE id_Users = ?";
	private static final String SQL_INSERT_INTO = "INSERT INTO users(name,surname,role_user,email,password,Date) VALUES(?,?,?,?,?,?)";
	private static final String UPDATE_DATA_USER = "UPDATE news_site.users SET ";
	private static final String PART_UPDATE_DATA_USER_STRING = " = ? WHERE id_Users = ?";
	private static final String USER_ID = "id_Users";
	private static final String EMAIL_USER = "email";
	private static final String NAME_USER = "name";
	private static final String USER = "user";
	private static final String SURNAME_USER = "surname";
	private static final String DATE_USER = "Date";
	private static final String PASSWORD = "password";
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final String ROLE_USER = "role_user";

	@Override
	public User registration(RegistrationInfo info) throws DAOException {

		PreparedStatement psINS = null;
		ResultSet rs = null;
		try (Connection con = connectionPool.takeConnection();
				PreparedStatement psSL = con.prepareStatement(SELECT_USER);) {

			psSL.setString(1, info.getEmail());
			rs = psSL.executeQuery();

			while (rs.next()) {
				if (rs.getString(EMAIL_USER) != null) {
					return null;
				}
			}

			psINS = con.prepareStatement(SQL_INSERT_INTO);
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

			psINS.setString(1, info.getName());
			psINS.setString(2, info.getSurname());
			psINS.setString(3, USER);
			psINS.setString(4, info.getEmail());
			psINS.setString(5, passwordAuthentication.hash(info.getFirstPassword()));
			psINS.setString(6, sdf.format(date));

			psINS.executeUpdate();
			return new User(info.getEmail(), info.getFirstPassword());
		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new DAOException(e);
		} finally {
			try {
				if (psINS != null) {
					psINS.close();
				}
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}
	}

	@Override
	public User authorization(RegistrationInfo info) throws DAOException {

		ResultSet rs = null;
		try (Connection con = connectionPool.takeConnection();
				PreparedStatement ps = con.prepareStatement(SELECT_USER);) {
			User user = null;
			ps.setString(1, info.getEmail());
			rs = ps.executeQuery();
			while (rs.next()) {
				if (passwordAuthentication.authenticate(info.getFirstPassword(), rs.getString(PASSWORD))) {
					user = getUserById(rs.getInt(USER_ID));
				}
			}
			return user;
		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new DAOException(e);
		}
	}

	@Override
	public User changeData(User user, String changeData, String newData, String password) throws DAOException {

		PreparedStatement psUP = null;
		ResultSet rs = null;
		try (Connection con = connectionPool.takeConnection();
				PreparedStatement psSL = con.prepareStatement(SELECT_USER);) {

			psSL.setString(1, user.getEmail());
			rs = psSL.executeQuery();
			while (rs.next()) {
				if (!passwordAuthentication.authenticate(password, rs.getString("password"))) {
					return null;
				}
			}

			psUP = con.prepareStatement(UPDATE_DATA_USER + changeData + PART_UPDATE_DATA_USER_STRING);

			if (changeData.equals(PASSWORD)) {
				psUP.setString(1, passwordAuthentication.hash(newData));
			} else {
				psUP.setString(1, newData);
			}
			psUP.setInt(2, user.getId());

			psUP.executeUpdate();

			User newDataUser = getUserById(user.getId());

			return newDataUser;
		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new DAOException(e);
		} finally {
			try {
				if (psUP != null) {
					psUP.close();
				}
			} catch (SQLException e) {
				log.error(e);
				throw new DAOException(e);
			}
		}
	}

	private User getUserById(int id) throws DAOException {

		ResultSet rs = null;
		User user = new User();
		try (Connection con = connectionPool.takeConnection();
				PreparedStatement ps = con.prepareStatement(SELECT_USER_BY_ID);) {

			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				user.setName(rs.getString(NAME_USER));
				user.setSurname(rs.getString(SURNAME_USER));
				user.setEmail(rs.getString(EMAIL_USER));
				user.setDateRegistration(rs.getString(DATE_USER));
				user.setId(rs.getInt(USER_ID));
				user.setRole(rs.getString(ROLE_USER));
			}
			return user;
		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new DAOException(e);
		}
	}

	@Override
	public List<User> getListUsers() throws DAOException {

		try (Connection con = connectionPool.takeConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(SELECT_USERS);) {

			List<User> users = new ArrayList<>();

			while (rs.next()) {
				User user = new User();

				user.setName(rs.getString(NAME_USER));
				user.setSurname(rs.getString(SURNAME_USER));
				user.setEmail(rs.getString(EMAIL_USER));
				user.setDateRegistration(rs.getString(DATE_USER));
				user.setId(rs.getInt(USER_ID));
				user.setRole(rs.getString(ROLE_USER));
				users.add(user);
			}
			return users;
		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new DAOException(e);
		}

	}

	@Override
	public void delete(int idUser) throws DAOException {

		try (Connection con = connectionPool.takeConnection();
				PreparedStatement ps = con.prepareStatement(DELETE_USER)) {
			ps.setInt(1, idUser);
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
			e.printStackTrace();
		} catch (ConnectionPoolException e) {
			log.error(e);
			e.printStackTrace();
		}
	}
}
