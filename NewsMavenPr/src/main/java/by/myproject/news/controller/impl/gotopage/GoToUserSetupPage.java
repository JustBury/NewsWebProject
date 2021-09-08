package by.myproject.news.controller.impl.gotopage;

import java.io.IOException;

import by.myproject.news.bean.User;
import by.myproject.news.controller.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class GoToUserSetupPage implements Command {
	
	private static GoToUserSetupPage instance = new GoToUserSetupPage();
	private static final String USER_SETUP_PAGE = "/WEB-INF/jsp/UserSetup.jsp";
	private static final String GO_TO_AUTHORIZATION_PAGE = "Controller?command=GO_TO_AUTHORIZATION_PAGE";
	private static final String USER = "user";
	
	private GoToUserSetupPage() {
		// TODO Auto-generated constructor stub
	}
	
	public static GoToUserSetupPage getInstance() {
		return instance;
	}
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			response.sendRedirect(GO_TO_AUTHORIZATION_PAGE);
			return;
		}
		User user = (User) session.getAttribute(USER);
		
		if(user == null) {
			response.sendRedirect(GO_TO_AUTHORIZATION_PAGE);
			return;
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(USER_SETUP_PAGE);
		requestDispatcher.forward(request, response);
		
	}

}
