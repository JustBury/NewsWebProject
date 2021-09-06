package by.myproject.news.dao;

import java.util.List;


import by.myproject.news.bean.Comment;
import by.myproject.news.bean.News;
import by.myproject.news.dao.exception.DAOException;

public interface NewsDAO {
	
	void addNews(News news) throws DAOException;
	
	List<News> getNewses(String[] status) throws DAOException;
	
	News getNews(int idNews) throws DAOException;
	
	String delete(int newsId) throws DAOException;
	
	String sendToCorrect(int newsId) throws DAOException;
	
	String publish(News news) throws DAOException;
	
	String update(News news) throws DAOException;
	
}
