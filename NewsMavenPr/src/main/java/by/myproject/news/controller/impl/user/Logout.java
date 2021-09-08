package by.myproject.news.controller.impl.user;

import java.io.IOException;

import by.myproject.news.controller.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Logout implements Command{
	
	private static final Logout instance = new Logout();
	private static final String GO_TO_AUTHORIZATION_PAGE = "Controller?command=GO_TO_AUTHORIZATION_PAGE";
	private static final String USER = "user";
	
	
	public static Logout getInstance() {
		return instance;
	}
	
	private Logout() {}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			response.sendRedirect(GO_TO_AUTHORIZATION_PAGE);
			return;
		}
		session.removeAttribute(USER);
					
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(GO_TO_AUTHORIZATION_PAGE);
		requestDispatcher.forward(request, response);
		
	}

}
