package by.myproject.news.servise;

import java.util.List;

import by.myproject.news.bean.Comment;
import by.myproject.news.servise.exception.ServiseException;

public interface CommentServise {
	
	String add(String comment, int idNews, int idUser) throws ServiseException;
	
	List<Comment> getComments(int idNews) throws ServiseException;
	
	String deleteComment(int idComment) throws ServiseException;

	void deleteAllComments(int idNews) throws ServiseException;
}
