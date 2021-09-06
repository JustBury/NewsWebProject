package by.myproject.news.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.myproject.news.bean.Comment;
import by.myproject.news.bean.User;
import by.myproject.news.dao.CommentDAO;
import by.myproject.news.dao.DAOProvider;
import by.myproject.news.dao.connection.ConnectionPool;
import by.myproject.news.dao.connection.ConnectionPoolException;
import by.myproject.news.dao.exception.DAOException;

public class SQLCommentDAO implements CommentDAO {
	
	private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
	private static final Logger log = LogManager.getLogger(SQLCommentDAO.class);
	private static final String INSERT_COMMENT = "INSERT INTO comments(comment_content, date_publication, id_News, id_Users) VALUES (?,?,?,?);";
	private static final String SELECT_COMMENT = "SELECT news.id_News, users.name, users.surname, comments.comment_content, comments.date_publication, comments.id_Comment"
			+ " FROM news join comments ON news.id_News = comments.id_News join users ON comments.id_Users = users.id_Users WHERE news.id_News = ?;";
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final String COMMENT_CONTENT = "comment_content";
	private static final String NAME_USER = "name";
	private static final String DATE_PUBLICATION = "date_publication";
	private static final String SURNAME_USER = "surname";
	private static final String MESSAGE_ADD_NEWS = "The news was successfully added";
	
	@Override
	public String add(String comment, int idNews, int idUser) throws DAOException {

		try (Connection con = connectionPool.takeConnection();
				PreparedStatement ps = con.prepareStatement(INSERT_COMMENT);) {

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

			ps.setString(1, comment);
			ps.setString(2, sdf.format(date));
			ps.setInt(3, idNews);
			ps.setInt(4, idUser);

			ps.executeUpdate();

			return MESSAGE_ADD_NEWS;
		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new DAOException(e);
		}
	}

	@Override
	public List<Comment> getComments(int idNews) throws DAOException {

		List<Comment> comments = new ArrayList<Comment>();

		ResultSet rs = null;
		try (Connection con = connectionPool.takeConnection();
				PreparedStatement ps = con.prepareStatement(SELECT_COMMENT);) {

			ps.setInt(1, idNews);
			rs = ps.executeQuery();

			while (rs.next()) {
				Comment comment = new Comment();
				User user = new User();
				user.setName(rs.getString(NAME_USER));
				user.setSurname(rs.getString(SURNAME_USER));
				String commentContent = rs.getString(COMMENT_CONTENT);
				String dateComment = rs.getString(DATE_PUBLICATION);
				comment.setContent(commentContent);
				comment.setDatePublication(dateComment);
				comment.setAuthorComment(user);
				comments.add(comment);
			}

			return comments;
		} catch (SQLException e) {
			log.error(e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new DAOException(e);
		}
	}

}
