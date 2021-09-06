package by.myproject.news.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.myproject.news.bean.Comment;
import by.myproject.news.bean.News;
import by.myproject.news.bean.User;
import by.myproject.news.dao.NewsDAO;
import by.myproject.news.dao.connection.ConnectionPool;
import by.myproject.news.dao.connection.ConnectionPoolException;
import by.myproject.news.dao.exception.DAOException;
import by.myproject.news.dao.exception.DAOExsepsionClose;

public class SQLNewsDAO implements NewsDAO {

	private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
	private static final Logger log = LogManager.getLogger(SQLNewsDAO.class);
	private static final String TITLE_NEWS = "title";
	private static final String BRIEF_NEWS = "brief";
	private static final String LINK_IMAGE_NEWS = "link_image";
	private static final String CONTENT_NEWS = "content";
	private static final String ID_NEWS = "id_News";
	private static final String USER_ID = "id_Users";
	private static final String EMAIL_USER = "email";
	private static final String NAME_USER = "name";
	private static final String SURNAME_USER = "surname";
	private static final String DATE_USER = "Date";
	private static final String ROLE_USER = "role_user";
	private static final String DATE_PUBLICATION = "date_publication";
	private static final String SELECT_NEWSES = "SELECT * FROM news INNER JOIN users ON news.Users_id_Users = users.id_Users ORDER BY date_publication DESC LIMIT 10";
	private static final String SELECT_NEWS = "SELECT * FROM news WHERE id_News = ?";
	private static final String INSERT_NEW = "INSERT INTO news(title, brief, content, date_publication, link_image,Users_id_Users,Status_News) VALUES (?,?,?,?,?,?,?)";
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final String STATUS_NEWS = "Status_News";
	private static final String DELETE_NEWS = "DELETE FROM news WHERE id_News = ?";
	private static final String PUBLISH_NEWS = "UPDATE news_site.news SET Status_News = 'Published' WHERE id_News = ?";
	private static final String UPDATE_NEWS = "UPDATE news_site.news SET title = ?, brief = ?, content = ?, date_publication = ?, link_image = ? WHERE id_News = ?";
	private static final String REFINE_NEWS = "UPDATE news_site.news SET Status_News = 'Refine' WHERE id_News = ?";
	private static final String PUBLISH_MESSEGE = "The news was successfully published";
	private static final String UPDATE_MESSEGE = "The news was successfully updated";
	private static final String DELETE_MESSEGE = "The news was successfully deleted";
	private static final String SEND_CORRECT_MESSEGE = "The news has been sent for revision";

	@Override
	public void addNews(News news) throws DAOException {

		try (Connection con = connectionPool.takeConnection();
				PreparedStatement ps = con.prepareStatement(INSERT_NEW);) {

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

			ps.setString(1, news.getTitle());
			ps.setString(2, news.getBrief());
			ps.setString(3, news.getContent());
			ps.setString(4, sdf.format(date));
			ps.setString(5, news.getLinkImage());
			ps.setInt(6, news.getAuthor().getId());
			ps.setString(7, news.getStatus());
			ps.executeUpdate();

		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new DAOException(e);
		} 
	}

	@Override
	public List<News> getNewses(String[] status) throws DAOException {

		try (Connection con = connectionPool.takeConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(SELECT_NEWSES);) {

			List<News> newses = new ArrayList<News>();
			while (rs.next()) {
				for (int i = 0; i < status.length; i++) {
					if (rs.getString(STATUS_NEWS).equals(status[i])) {
						News news = new News();

						news.setNewsId(rs.getInt(ID_NEWS));
						news.setTitle(rs.getString(TITLE_NEWS));
						news.setBrief(rs.getString(BRIEF_NEWS));
						news.setContent(rs.getString(CONTENT_NEWS));
						news.setLinkImage(rs.getString(LINK_IMAGE_NEWS));
						news.setStatus(rs.getString(STATUS_NEWS));
						news.setDataPublication(rs.getString(DATE_PUBLICATION));
						User user = new User();
						user.setName(rs.getString(NAME_USER));
						user.setSurname(rs.getString(SURNAME_USER));
						user.setEmail(rs.getString(EMAIL_USER));
						user.setDateRegistration(rs.getString(DATE_USER));
						user.setId(rs.getInt(USER_ID));
						user.setRole(rs.getString(ROLE_USER));
						news.setAuthor(user);
						newses.add(news);
					}
				}
			}
			return newses;
		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new DAOException(e);
		}
	}

	@Override
	public News getNews(int idNews) throws DAOException {

		ResultSet rs = null;

		try (Connection con = connectionPool.takeConnection();
				PreparedStatement ps = con.prepareStatement(SELECT_NEWS);) {

			ps.setInt(1, idNews);
			rs = ps.executeQuery();

			News news = new News();

			while (rs.next()) {
				news.setNewsId(rs.getInt(ID_NEWS));
				news.setTitle(rs.getString(TITLE_NEWS));
				news.setBrief(rs.getString(BRIEF_NEWS));
				news.setContent(rs.getString(CONTENT_NEWS));
				news.setLinkImage(rs.getString(LINK_IMAGE_NEWS));
			}
			return news;
		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new DAOException(e);
		}
	}

	@Override
	public String delete(int newsId) throws DAOException {

		try (Connection con = connectionPool.takeConnection();
				PreparedStatement ps = con.prepareStatement(DELETE_NEWS);) {
			ps.setInt(1, newsId);
			ps.executeUpdate();
			return DELETE_MESSEGE;
		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new DAOException(e);
		}
	}

	@Override
	public String sendToCorrect(int newsId) throws DAOException {

		try (Connection con = connectionPool.takeConnection();
				PreparedStatement ps = con.prepareStatement(REFINE_NEWS);) {

			ps.setInt(1, newsId);
			ps.executeUpdate();
			return SEND_CORRECT_MESSEGE;
		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new DAOException(e);
		}
	}

	@Override
	public String publish(News news) throws DAOException {

		try (Connection con = connectionPool.takeConnection();
				PreparedStatement ps = con.prepareStatement(PUBLISH_NEWS);) {

			update(news);
			ps.setInt(1, news.getNewsId());
			ps.executeUpdate();

			return PUBLISH_MESSEGE;
		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new DAOException(e);
		}
	}

	@Override
	public String update(News news) throws DAOException {

		try (Connection con = connectionPool.takeConnection();
				PreparedStatement ps = con.prepareStatement(UPDATE_NEWS);) {

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

			ps.setString(1, news.getTitle());
			ps.setString(2, news.getBrief());
			ps.setString(3, news.getContent());
			ps.setString(4, sdf.format(date));
			ps.setString(5, news.getLinkImage());
			ps.setInt(6, news.getNewsId());
			ps.executeUpdate();

			return UPDATE_MESSEGE;
		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new DAOException(e);
		}
	}
}
