package by.myproject.news.servise;


import java.util.List;
import java.util.Map;

import by.myproject.news.bean.Comment;
import by.myproject.news.bean.News;
import by.myproject.news.servise.exception.ServiseException;

public interface NewsServise {

	void addNews(News news) throws ServiseException;
	
	List<News> getNewses(String[] status) throws ServiseException;
	
	News getNews(int idNews) throws ServiseException; 
	
	String delete(int newsId) throws ServiseException;
	
	String sendToCorrect(int newsId) throws ServiseException;
	
	String publish(News news) throws ServiseException;
}
