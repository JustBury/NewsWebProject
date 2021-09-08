package by.myproject.news.controller.impl.gotopage;

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
	private static final String GO_TO_AUTHORIZATION_PAGE = "Controller?command=GO_TO_AUTHORIZATION_PAGE";
	private static final String ERROR_PAGE = "Controller?command=UNKNOWN_COMMAND";
	
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
				response.sendRedirect(GO_TO_AUTHORIZATION_PAGE);
				return;
			}

			User user = (User) session.getAttribute(USER);
			
			if (user == null) {
				response.sendRedirect(GO_TO_AUTHORIZATION_PAGE);
				return;
			}
			
			List<User> users = USER_SERVISE.getListUsers();
			session.setAttribute(LIST_USERS, users);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(LIST_USERS_PAGE);
			requestDispatcher.forward(request, response);
		} catch (ServiseException e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
			requestDispatcher.forward(request, response);
			
		}
		
		
		
		
		
		
	}

}
