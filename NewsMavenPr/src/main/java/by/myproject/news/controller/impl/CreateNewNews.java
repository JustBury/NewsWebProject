package by.myproject.news.controller.impl;

import java.io.IOException;

import by.myproject.news.bean.News;
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

public class CreateNewNews implements Command{
	
	private static final CreateNewNews inctense = new CreateNewNews();
	private static final ServiseProvider PROVIDER = ServiseProvider.getInstance();
	private static final NewsServise NEWS_SERVISE = PROVIDER.getNewsServise();
	private static final String PART_PATH = "Controller?command=";
	private static final String AUTHORIZATION_PAGE = "AUTHORIZATION_PAGE";
	private static final String USER_PAGE = "GO_TO_USER_PAGE";
	private static final String ERROR_PAGE = "UNKNOWN_COMMAND";
	private static final String USER = "user";
	private static final String STATUS_PUB = "Published";
	private static final String TITLE = "title";
	private static final String CONTENT = "content";
	private static final String BRIEF = "brief";
	private static final String LINK_IMAGE = "link_image";
	private static final String ADMIN = "admin";
	private static final String STATUS_VER = "Verification";
	
	private CreateNewNews() {}
	
	public static CreateNewNews getInstance() {
		return inctense;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect(PART_PATH + AUTHORIZATION_PAGE);
			return;
		}
		
		User user = (User) session.getAttribute(USER);
		
		if(user == null) {
			response.sendRedirect(PART_PATH + AUTHORIZATION_PAGE);
			return;
		}
		
		News news = new News();
		news.setTitle(request.getParameter(TITLE));
		news.setBrief(request.getParameter(BRIEF));
		news.setContent(request.getParameter(CONTENT));
		news.setLinkImage(request.getParameter(LINK_IMAGE));
		if(user.getRole().equals(ADMIN)) {
			news.setStatus(STATUS_PUB);
		}else {
			news.setStatus(STATUS_VER);;
		}
		news.setAuthor(user);	
		try {
			NEWS_SERVISE.addNews(news);		
			response.sendRedirect(PART_PATH + USER_PAGE);
		} catch (ServiseException e) {
			//log
			System.out.println(e.getMessage());
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(PART_PATH + ERROR_PAGE);
			requestDispatcher.forward(request, response);
		}
	}
}
