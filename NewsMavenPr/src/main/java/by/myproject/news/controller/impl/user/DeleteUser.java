package by.myproject.news.controller.impl.user;

import java.io.IOException;

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

public class DeleteUser implements Command {
	
	private static final DeleteUser instance = new DeleteUser();
	private static final ServiseProvider PROVIDER = ServiseProvider.getInstance();
	private static final UserServise USER_SERVISE = PROVIDER.getUserServise();
	private static final String ID_USER = "idUser";
	private static final String LIST_USERS_PAGE = "Controller?command=GO_TO_LIST_USERS_PAGE";
	private static final String GO_TO_AUTHORIZATION_PAGE = "Controller?command=GO_TO_AUTHORIZATION_PAGE";
	private static final String MESSEGE_PLEASE_LOGIN = "&please_login=Please log in to view the content";
	private static final String USER = "user";
	private static final String ERROR_PAGE = "Controller?command=UNKNOWN_COMMAND";
	
	private DeleteUser () {}
	
	public static DeleteUser getInstance() {
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idUser = Integer.parseInt(request.getParameter(ID_USER));
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
			
			
			USER_SERVISE.delete(idUser);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(LIST_USERS_PAGE);
			requestDispatcher.forward(request, response);
		} catch (ServiseException e) {
			// log
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
			requestDispatcher.forward(request, response);
		}
		
		
	}

}
