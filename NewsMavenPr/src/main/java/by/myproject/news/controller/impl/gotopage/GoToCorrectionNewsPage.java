package by.myproject.news.controller.impl.gotopage;

import java.io.IOException;

import by.myproject.news.bean.News;
import by.myproject.news.bean.User;
import by.myproject.news.controller.Command;
import by.myproject.news.servise.NewsServise;
import by.myproject.news.servise.ServiseProvider;
import by.myproject.news.servise.exception.ServiseException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToCorrectionNewsPage implements Command{
	
	private static final GoToCorrectionNewsPage instance = new GoToCorrectionNewsPage();
	private static final ServiseProvider PROVIDER = ServiseProvider.getInstance();
	private static final NewsServise NEWS_SERVISE = PROVIDER.getNewsServise();
	private static final String CORRECTION_NEWS_PAGE = "/WEB-INF/jsp/CorrectionNewsPage.jsp";
	private static final String NEWS_ID = "newsId";
	private static final String NEWS = "news";
	private static final String GO_TO_AUTHORIZATION_PAGE = "Controller?command=GO_TO_AUTHORIZATION_PAGE";
	private static final String MESSEGE_PLEASE_LOGIN = "&please_login=Please log in to view the content";
	private static final String USER = "user";
	private static final String ERROR_PAGE = "Controller?command=UNKNOWN_COMMAND";
	
	private GoToCorrectionNewsPage() {}

	public static GoToCorrectionNewsPage getInstance() {
		return instance;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idNews = Integer.parseInt(request.getParameter(NEWS_ID));
		
		try {
			HttpSession session = request.getSession(false);
			if (session == null) {
				response.sendRedirect(GO_TO_AUTHORIZATION_PAGE + MESSEGE_PLEASE_LOGIN);
				return;
			}

			User user = (User) session.getAttribute(USER);

			if (user == null) {
				response.sendRedirect(GO_TO_AUTHORIZATION_PAGE + MESSEGE_PLEASE_LOGIN);
				return;
			}
			News news = NEWS_SERVISE.getNews(idNews);
			
			request.setAttribute(NEWS, news);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(CORRECTION_NEWS_PAGE);
			requestDispatcher.forward(request, response);
			
		} catch (ServiseException e) {
			//log
			e.printStackTrace();
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
			requestDispatcher.forward(request, response);
		}	
	}
}
