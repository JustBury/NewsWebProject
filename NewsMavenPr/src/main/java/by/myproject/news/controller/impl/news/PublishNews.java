package by.myproject.news.controller.impl.news;

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
	private static final String GO_TO_ADMIN_PAGE = "Controller?command=GO_TO_ADMIN_PAGE";
	private static final String PART_PUBLISH_MESSEGE = "&message=";
	private static final String USER = "user";
	private static final String MESSAGE_FAIL = "Couldn't publish the news";
	private static final String GO_TO_AUTHORIZATION_PAGE = "Controller?command=GO_TO_AUTHORIZATION_PAGE";
	
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
				response.sendRedirect(GO_TO_AUTHORIZATION_PAGE);
				return;
			}

			User user = (User) session.getAttribute(USER);

			if (user == null) {
				response.sendRedirect(GO_TO_AUTHORIZATION_PAGE);
				return;
			}
			
			String messegePublish = SERVISE.publish(news);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(GO_TO_ADMIN_PAGE + PART_PUBLISH_MESSEGE + messegePublish);
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(GO_TO_ADMIN_PAGE + PART_PUBLISH_MESSEGE + MESSAGE_FAIL);
			requestDispatcher.forward(request, response);
		}
		
	}

}
