package by.myproject.news.controller.impl;

import java.io.IOException;

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

public class AfterAuthorization implements Command{
	
	private static final AfterAuthorization instance = new AfterAuthorization();
		
	
	public static final ServiseProvider PROVIDER = ServiseProvider.getInstance();
	public static final NewsServise NEWS_SERVISE = PROVIDER.getNewsServise();
	public static final String PART_PATH = "Controller?command=";
	public static final String AUTHORIZATION_PAGE = "AUTHORIZATION_PAGE";
	public static final String AFTER_AUTHORIZATION_PAGE = "/WEB-INF/jsp/AfterAuthorization.jsp";
	public static final String ERROR_PAGE = "UNKNOWN_COMMAND";
	public static final String NEWSES = "newses";
	private static final String USER = "user";
	private static final String STATUS_PUB = "Published";

	private AfterAuthorization() {}

	public static AfterAuthorization getInstance() {
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
				
		if(session == null) {
			response.sendRedirect(PART_PATH + AUTHORIZATION_PAGE);
			return;
		}
		
		User user = (User) session.getAttribute(USER);
		
		if(user == null) {
			response.sendRedirect(PART_PATH + AUTHORIZATION_PAGE);
			return;
		}
//		
//		try {
////			request.setAttribute(NEWSES, NEWS_SERVISE.getNewses(STATUS_PUB));
//		} catch (ServiseException e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//			RequestDispatcher requestDispatcher = request.getRequestDispatcher(PART_PATH + ERROR_PAGE);
//			requestDispatcher.forward(request, response);
//		}
		
		
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(AFTER_AUTHORIZATION_PAGE);
		requestDispatcher.forward(request, response);
		
	}

}
