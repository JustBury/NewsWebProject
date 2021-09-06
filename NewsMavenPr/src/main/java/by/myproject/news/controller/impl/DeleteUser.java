package by.myproject.news.controller.impl;

import java.io.IOException;

import by.myproject.news.controller.Command;
import by.myproject.news.servise.ServiseProvider;
import by.myproject.news.servise.UserServise;
import by.myproject.news.servise.exception.ServiseException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteUser implements Command {
	
	private static final DeleteUser instance = new DeleteUser();
	private static final ServiseProvider PROVIDER = ServiseProvider.getInstance();
	private static final UserServise USER_SERVISE = PROVIDER.getUserServise();
	
	private static final String LIST_USERS_PAGE = "Controller?command=GO_TO_LIST_USERS_PAGE";
	
	private DeleteUser () {}
	
	public static DeleteUser getInstance() {
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idUser = Integer.parseInt(request.getParameter("idUser"));
		try {
			USER_SERVISE.delete(idUser);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(LIST_USERS_PAGE);
			requestDispatcher.forward(request, response);
		} catch (ServiseException e) {
			// log
			e.printStackTrace();
		}
		
		
	}

}
