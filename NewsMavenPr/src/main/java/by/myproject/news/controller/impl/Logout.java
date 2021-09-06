package by.myproject.news.controller.impl;

import java.io.IOException;

import by.myproject.news.controller.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Logout implements Command{
	
	private static final Logout instance = new Logout();
	private static final String PART_PATH = "Controller?command=";
	private static final String AUTHORIZATION_PAGE = "AUTHORIZATION_PAGE";
	private static final String USER = "user";
	
	
	public static Logout getInstance() {
		return instance;
	}
	
	private Logout() {}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			response.sendRedirect(PART_PATH + AUTHORIZATION_PAGE);
			return;
		}
		session.removeAttribute(USER);
					
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(PART_PATH + AUTHORIZATION_PAGE);
		requestDispatcher.forward(request, response);
		
	}

}
