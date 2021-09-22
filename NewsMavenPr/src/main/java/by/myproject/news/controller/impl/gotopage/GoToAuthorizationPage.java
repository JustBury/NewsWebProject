package by.myproject.news.controller.impl.gotopage;

import java.io.IOException;


import by.myproject.news.controller.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToAuthorizationPage implements Command {

	private static final GoToAuthorizationPage instance = new GoToAuthorizationPage();
	private static final String AUTHORIZATION_PAGE = "/WEB-INF/jsp/AuthorizationPage.jsp";

	public static GoToAuthorizationPage getInstance() {
		return instance;
	}
	
	private GoToAuthorizationPage() {}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(AUTHORIZATION_PAGE);
		requestDispatcher.forward(request, response);
		
	}

}
