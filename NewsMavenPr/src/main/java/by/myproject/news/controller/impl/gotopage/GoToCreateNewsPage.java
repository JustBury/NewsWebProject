package by.myproject.news.controller.impl.gotopage;

import java.io.IOException;

import by.myproject.news.controller.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class GoToCreateNewsPage implements Command {
	
	private static final GoToCreateNewsPage instance = new GoToCreateNewsPage();
	private static final String CREATE_NEWS_PAGE = "/WEB-INF/jsp/CreateNewsPage.jsp";
	
	private  GoToCreateNewsPage() {	}
	
	public static GoToCreateNewsPage getInstance() {
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(CREATE_NEWS_PAGE);
		requestDispatcher.forward(request, response);
	}
}
