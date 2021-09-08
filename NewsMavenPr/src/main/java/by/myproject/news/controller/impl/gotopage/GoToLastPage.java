package by.myproject.news.controller.impl.gotopage;

import java.io.IOException;

import by.myproject.news.controller.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToLastPage implements Command {
	
	private static final GoToLastPage instance = new GoToLastPage();
	private static final String PART_PATH = "Controller?command=";
	private static final String LAST_PAGE_SESSION_PARAM = "lastPage";
	
	private GoToLastPage() {}
	
	public static GoToLastPage getInstance() {
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = PART_PATH + (String)request.getSession(false).getAttribute(LAST_PAGE_SESSION_PARAM);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
		
	}

}
