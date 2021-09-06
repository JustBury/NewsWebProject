package by.myproject.news.servise.impl;

import java.util.List;


import by.myproject.news.bean.Comment;
import by.myproject.news.bean.News;
import by.myproject.news.dao.DAOProvider;
import by.myproject.news.dao.NewsDAO;
import by.myproject.news.dao.exception.DAOException;
import by.myproject.news.servise.NewsServise;
import by.myproject.news.servise.exception.ServiseException;

public class NewsServiseImpl implements NewsServise {

	private static final DAOProvider PROVIDER = DAOProvider.getInstance();
	private static final NewsDAO NEWS_DAO = PROVIDER.getNewsDAO();

	@Override
	public void addNews(News news) throws ServiseException {
		try {
			 NEWS_DAO.addNews(news);
		} catch (DAOException e) {
			throw new ServiseException(e);
		}
	}

	@Override
	public List<News> getNewses(String[] status) throws ServiseException {
		// TODO Auto-generated method stub
		try {
			return NEWS_DAO.getNewses(status);
		} catch (DAOException e) {
			throw new ServiseException(e);
		}
	}

	@Override
	public News getNews(int idNews) throws ServiseException {
		try {
			return NEWS_DAO.getNews(idNews);
		} catch (DAOException e) {
			throw new ServiseException(e);
		}
	}

	@Override
	public String delete(int newsId) throws ServiseException {
		try {
			return NEWS_DAO.delete(newsId);
		} catch (DAOException e) {
			throw new ServiseException(e);
		}
		
	}

	@Override
	public String sendToCorrect(int newsId) throws ServiseException {
		try {
			return NEWS_DAO.sendToCorrect(newsId);
		} catch (DAOException e) {
			throw new ServiseException(e);
		}
		
	}

	@Override
	public String publish(News news) throws ServiseException {
		try {
			return NEWS_DAO.publish(news);
		} catch (DAOException e) {
			throw new ServiseException(e);
		}
		
	}
}
