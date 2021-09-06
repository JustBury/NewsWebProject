package by.myproject.news.controller.impl;

import java.io.IOException;

import by.myproject.news.bean.News;
import by.myproject.news.controller.Command;
import by.myproject.news.servise.NewsServise;
import by.myproject.news.servise.ServiseProvider;
import by.myproject.news.servise.exception.ServiseException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToCorrectionNewsPage implements Command{
	
	private static final GoToCorrectionNewsPage instance = new GoToCorrectionNewsPage();
	private static final ServiseProvider PROVIDER = ServiseProvider.getInstance();
	private static final NewsServise NEWS_SERVISE = PROVIDER.getNewsServise();
	private static final String CORRECTION_NEWS_PAGE = "/WEB-INF/jsp/CorrectionNewsPage.jsp";
	private static final String NEWS_ID = "newsId";
	private static final String NEWS = "news";
	
	private GoToCorrectionNewsPage() {}

	public static GoToCorrectionNewsPage getInstance() {
		return instance;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idNews = Integer.parseInt(request.getParameter(NEWS_ID));
		
		try {
			News news = NEWS_SERVISE.getNews(idNews);
			
			request.setAttribute(NEWS, news);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(CORRECTION_NEWS_PAGE);
			requestDispatcher.forward(request, response);
			
		} catch (ServiseException e) {
			//log
			e.printStackTrace();
		}	
	}
}
