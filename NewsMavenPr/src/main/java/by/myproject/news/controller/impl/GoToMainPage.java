package by.myproject.news.controller.impl;

import java.io.IOException;

import by.myproject.news.bean.User;
import by.myproject.news.controller.Command;
import by.myproject.news.servise.NewsServise;
import by.myproject.news.servise.ServiseProvider;
import by.myproject.news.servise.exception.ServiseException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToMainPage implements Command {
	private static GoToMainPage instance = new GoToMainPage();
	private static final ServiseProvider PROVIDER = ServiseProvider.getInstance();
	private static final NewsServise NEWS_SERVISE = PROVIDER.getNewsServise();

	private static final String MAIN_PAGE = "/WEB-INF/jsp/MainPage.jsp";
	private static final String ERROR_PAGE = "Controller?command=UNKNOWN_COMMAND";
	private static final String NEWSES = "newses";
	private static final String PART_PATH = "Controller?command=";
	private static final String USER_PAGE = "GO_TO_USER_PAGE";
	private static final String USER = "user";
	private static final String STATUS_PUB = "Published";

	private GoToMainPage() {

	}

	public static GoToMainPage getInstance() {
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session != null) {
			User user = (User) session.getAttribute(USER);
			if (user != null) {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(PART_PATH + USER_PAGE);
				requestDispatcher.forward(request, response);
				return;
			}
		}

		try {
			String[] stutusMass = new String[1];
			stutusMass[0] = STATUS_PUB;
			request.setAttribute(NEWSES, NEWS_SERVISE.getNewses(stutusMass));
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(MAIN_PAGE);
			requestDispatcher.forward(request, response);
		} catch (ServiseException e) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(MAIN_PAGE);
			requestDispatcher.forward(request, response);
		}

	}
}
