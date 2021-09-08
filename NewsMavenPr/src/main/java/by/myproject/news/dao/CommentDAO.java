package by.myproject.news.dao;

import java.util.List;

import by.myproject.news.bean.Comment;
import by.myproject.news.dao.exception.DAOException;

public interface CommentDAO {

	String add(String comment, int idNews, int idUser) throws DAOException;

	List<Comment> getComments(int idNews) throws DAOException;
	
	String deleteComment(int idComment) throws DAOException;
	
	void deleteAllComments(int idNews) throws DAOException;

}
