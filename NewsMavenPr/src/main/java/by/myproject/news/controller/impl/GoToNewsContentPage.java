package by.myproject.news.controller.impl;

import java.io.IOException;
import java.util.List;
import by.myproject.news.bean.Comment;
import by.myproject.news.bean.News;
import by.myproject.news.bean.User;
import by.myproject.news.controller.Command;
import by.myproject.news.servise.CommentServise;
import by.myproject.news.servise.NewsServise;
import by.myproject.news.servise.ServiseProvider;
import by.myproject.news.servise.exception.ServiseException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToNewsContentPage implements Command {
	
	private static GoToNewsContentPage instance = new GoToNewsContentPage();
	private static final ServiseProvider PROVIDER = ServiseProvider.getInstance();
	private static final NewsServise NEWS_SERVISE = PROVIDER.getNewsServise();
	private static final CommentServise COMMENT_SERVISE = PROVIDER.getCommentServise();
	private static final String NEWS_ID = "newsId";
	private static final String USER = "user";
	private static final String NEWS = "news";
	private static final String COMMENTS = "comments";
	private static final String PART_PATH = "Controller?command=";
	private static final String AUTHORIZATION_PAGE = "AUTHORIZATION_PAGE";
	public static final String NEWS_CONTENT_PAGE = "/WEB-INF/jsp/NewsContentPage.jsp";
	private static final String MESSEGE_PLEASE_LOGIN = "&please_login=Please log in to view the content";
	
	
	private GoToNewsContentPage() {	}
	
	public static GoToNewsContentPage getInstance() {
		return instance;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		int idNews = Integer.parseInt(request.getParameter(NEWS_ID));
		
		try {
			
			
						
			HttpSession session = request.getSession(false);
			if (session == null) {
				response.sendRedirect(AUTHORIZATION_PAGE + MESSEGE_PLEASE_LOGIN);
				return;
			}

			User user = (User) session.getAttribute(USER);

			if (user == null) {
				response.sendRedirect(PART_PATH + AUTHORIZATION_PAGE + MESSEGE_PLEASE_LOGIN);
				return;
			}
			
			News news = NEWS_SERVISE.getNews(idNews);
			List<Comment> comments = COMMENT_SERVISE.getComments(idNews);
			
			request.setAttribute(COMMENTS, comments);
			request.setAttribute(NEWS, news);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(NEWS_CONTENT_PAGE);
			requestDispatcher.forward(request, response);
			
		} catch (ServiseException e) {
			//log
			e.printStackTrace();
		}
		
		
	}

}
