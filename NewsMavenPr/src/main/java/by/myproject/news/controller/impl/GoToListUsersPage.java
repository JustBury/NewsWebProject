package by.myproject.news.controller.impl;

import java.io.IOException;
import java.util.List;

import by.myproject.news.bean.User;
import by.myproject.news.controller.Command;
import by.myproject.news.servise.ServiseProvider;
import by.myproject.news.servise.UserServise;
import by.myproject.news.servise.exception.ServiseException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToListUsersPage implements Command {
	
	private static final GoToListUsersPage instance = new GoToListUsersPage();
	private static final ServiseProvider PROVIDER = ServiseProvider.getInstance();
	private static final UserServise USER_SERVISE = PROVIDER.getUserServise();
	
	private static final String LIST_USERS_PAGE = "/WEB-INF/jsp/ListUsersPage.jsp";
	private static final String USER = "user";
	private static final String LIST_USERS = "listUsers";
	private static final String AUTHORIZATION_PAGE = "Controller?command=AUTHORIZATION_PAGE";
	
	private GoToListUsersPage() {
		
	}
	
	public static GoToListUsersPage getInstance() {
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			HttpSession session = request.getSession(false);


			if (session == null) {
				response.sendRedirect(AUTHORIZATION_PAGE);
				return;
			}

			User user = (User) session.getAttribute(USER);
			
			if (user == null) {
				response.sendRedirect(AUTHORIZATION_PAGE);
				return;
			}
			
			List<User> users = USER_SERVISE.getListUsers();
			session.setAttribute(LIST_USERS, users);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(LIST_USERS_PAGE);
			requestDispatcher.forward(request, response);// TODO Auto-generated method stub
		} catch (ServiseException e) {
			// log
			e.printStackTrace();
		}
		
		
		
		
		
		
	}

}
