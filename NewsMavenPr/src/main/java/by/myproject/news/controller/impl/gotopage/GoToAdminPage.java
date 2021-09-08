package by.myproject.news.controller.impl.gotopage;

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

public class GoToAdminPage implements Command {

	private static final GoToAdminPage instance = new GoToAdminPage();
	private static final ServiseProvider PROVIDER = ServiseProvider.getInstance();
	private static final NewsServise NEWS_SERVISE = PROVIDER.getNewsServise();
	private static final String ADMIN_PAGE = "/WEB-INF/jsp/AdminPage.jsp";
	private static final String ERROR_PAGE = "Controller?command=UNKNOWN_COMMAND";
	private static final String NEWSES = "newses";
	private static final String USER = "user";
	private static final String STATUS_VER = "Verification";
	private static final String STATUS_PUB = "Published";
	private static final String STATUS_REV = "Revision";
	
	private static final String GO_TO_AUTHORIZATION_PAGE = "Controller?command=GO_TO_AUTHORIZATION_PAGE";
	private static final String MESSEGE_PLEASE_LOGIN = "&please_login=Please log in to view the content";

	private GoToAdminPage() {
	}

	public static GoToAdminPage getInstance() {
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

		try {
			String[] stutusMass = new String[3];
			stutusMass[0] = STATUS_PUB;
			stutusMass[1] = STATUS_VER;
			stutusMass[2] = STATUS_REV;
			request.setAttribute(NEWSES, NEWS_SERVISE.getNewses(stutusMass));
		} catch (ServiseException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
			requestDispatcher.forward(request, response);
		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(ADMIN_PAGE);
		requestDispatcher.forward(request, response);

	}

}
