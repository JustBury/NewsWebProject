package by.myproject.news.controller.impl.gotopage;

import java.io.IOException;

import by.myproject.news.controller.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToRegistrationPage implements Command {

	private static GoToRegistrationPage instance = new GoToRegistrationPage();
	
	private static final String REGISTRATION_PAGE = "/WEB-INF/jsp/RegistrationPage.jsp";

	private GoToRegistrationPage() {}

	public static GoToRegistrationPage getInstance() {
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(REGISTRATION_PAGE);
		requestDispatcher.forward(request, response);

	}

}
