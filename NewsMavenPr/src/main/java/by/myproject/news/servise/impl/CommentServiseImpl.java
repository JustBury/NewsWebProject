package by.myproject.news.servise.impl;

import java.util.List;

import by.myproject.news.bean.Comment;
import by.myproject.news.dao.CommentDAO;
import by.myproject.news.dao.DAOProvider;
import by.myproject.news.dao.exception.DAOException;
import by.myproject.news.dao.impl.SQLCommentDAO;
import by.myproject.news.servise.CommentServise;
import by.myproject.news.servise.ServiseProvider;
import by.myproject.news.servise.exception.ServiseException;

public class CommentServiseImpl implements CommentServise {

	private static final DAOProvider PROVIDER = DAOProvider.getInstance();
	private static final CommentDAO COMMENT_DAO = new SQLCommentDAO();

	@Override
	public String add(String comment, int idNews, int idUser) throws ServiseException {
		try {
			return COMMENT_DAO.add(comment, idNews, idUser);
		} catch (DAOException e) {
			throw new ServiseException(e);
		}
	}

	@Override
	public List<Comment> getComments(int idNews) throws ServiseException {
		try {
			return COMMENT_DAO.getComments(idNews);
		} catch (DAOException e) {
			throw new ServiseException(e);
		}
	}

	@Override
	public String deleteComment(int idComment) throws ServiseException {
		try {
			return COMMENT_DAO.deleteComment(idComment);
		} catch (DAOException e) {
			throw new ServiseException(e);
		}
	}

	@Override
	public void deleteAllComments(int idNews) throws ServiseException {
		try {
			COMMENT_DAO.deleteAllComments(idNews);
		} catch (DAOException e) {
			throw new ServiseException(e);
		}
		
	}
	
	

}
