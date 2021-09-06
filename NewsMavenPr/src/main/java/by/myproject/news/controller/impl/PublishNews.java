package by.myproject.news.controller.impl;

import java.io.IOException;

import by.myproject.news.bean.News;
import by.myproject.news.bean.User;
import by.myproject.news.controller.Command;
import by.myproject.news.servise.NewsServise;
import by.myproject.news.servise.ServiseProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PublishNews implements Command {
	
	private static final PublishNews instance = new PublishNews();
	private static final ServiseProvider PROVIDER = ServiseProvider.getInstance();
	private static final NewsServise SERVISE = PROVIDER.getNewsServise();
	private static final String NEWS_ID = "newsId";
	private static final String TITLE = "title";
	private static final String CONTENT = "content";
	private static final String BRIEF = "brief";
	private static final String LINK_IMAGE = "link_image";
	private static final String ADMIN_PAGE = "Controller?command=GO_TO_ADMIN_PAGE";
	private static final String PART_PUBLISH_MESSEGE = "&message=";
	private static final String AUTHORIZATION_PAGE = "Controller?command=AUTHORIZATION_PAGE";
	private static final String USER = "user";
	
	private PublishNews() {
	}

	public static PublishNews getInstance() {
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idNews = Integer.parseInt(request.getParameter(NEWS_ID));
		
		News news = new News();
		news.setNewsId(idNews);
		news.setTitle(request.getParameter(TITLE));
		news.setBrief(request.getParameter(BRIEF));
		news.setContent(request.getParameter(CONTENT));
		news.setLinkImage(request.getParameter(LINK_IMAGE));
		
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
			
			String messege = SERVISE.publish(news);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(ADMIN_PAGE + PART_PUBLISH_MESSEGE + messege);
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		
	}

}
