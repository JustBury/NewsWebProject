package by.myproject.news.controller.impl.comment;

import java.io.IOException;

import by.myproject.news.bean.User;
import by.myproject.news.controller.Command;
import by.myproject.news.servise.CommentServise;
import by.myproject.news.servise.NewsServise;
import by.myproject.news.servise.ServiseProvider;
import by.myproject.news.servise.exception.ServiseException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AddCommentNews implements Command {
	
	private static final AddCommentNews instance = new AddCommentNews();
	private static final ServiseProvider PROVIDER = ServiseProvider.getInstance();
	private static final CommentServise COMMENT_SERVISE = PROVIDER.getCommentServise();
	private static final String CONTENT = "content";
	private static final String GO_TO_CONTENT_NEWS_PAGE = "Controller?command=GO_TO_CONTENT_NEWS_PAGE";
	private static final String USER = "user";
	private static final String ID_NEWS = "newsId";
	private static final String MESSEGE_PLEASE_LOGIN = "&please_login=Please log in";
	private static final String GO_TO_AUTHORIZATION_PAGE = "Controller?command=GO_TO_AUTHORIZATION_PAGE";
	
	
	private AddCommentNews() {}
	
	public static AddCommentNews getInstance() {
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session == null) {
			response.sendRedirect(GO_TO_AUTHORIZATION_PAGE + MESSEGE_PLEASE_LOGIN);
			return;
		}

		User user = (User) session.getAttribute(USER);

		if (user == null) {
			response.sendRedirect(GO_TO_AUTHORIZATION_PAGE + MESSEGE_PLEASE_LOGIN);
			return;
		}
		
		String comment = request.getParameter(CONTENT);
		int idNews = Integer.parseInt(request.getParameter(ID_NEWS));
		
		
		try {
			String message = COMMENT_SERVISE.add(comment, idNews, user.getId());
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(GO_TO_CONTENT_NEWS_PAGE);
			requestDispatcher.forward(request, response);
		} catch (ServiseException e) {
			//log
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(GO_TO_CONTENT_NEWS_PAGE);
			requestDispatcher.forward(request, response);
		}
		
		
		
		
	}
	
	

}
