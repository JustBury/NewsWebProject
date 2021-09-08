package by.myproject.news.controller.impl.news;

import java.io.IOException;

import by.myproject.news.bean.User;
import by.myproject.news.controller.Command;
import by.myproject.news.servise.CommentServise;
import by.myproject.news.servise.NewsServise;
import by.myproject.news.servise.ServiseProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DeleteNews implements Command {
	
	private static DeleteNews instance = new DeleteNews();
	private static final ServiseProvider PROVIDER = ServiseProvider.getInstance();
	private static final NewsServise NEWS_SERVISE = PROVIDER.getNewsServise();
	private static final CommentServise COMMENT_SERVISE = PROVIDER.getCommentServise();
	private static final String NEWS_ID = "newsId";
	private static final String GO_TO_AUTHORIZATION_PAGE = "Controller?command=GO_TO_AUTHORIZATION_PAGE";
	private static final String USER = "user";
	private static final String GO_TO_ADMIN_PAGE = "Controller?command=GO_TO_ADMIN_PAGE";
	private static final String PART_DELETE_MESSEGE = "&message=";
	private static final String MESSAGE_FAIL = "Couldn't delete the news";
		
	private DeleteNews() {
	}

	public static DeleteNews getInstance() {
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idNews = Integer.parseInt(request.getParameter(NEWS_ID));
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
			
			COMMENT_SERVISE.deleteAllComments(idNews);
			String messegeDelete = NEWS_SERVISE.delete(idNews);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(GO_TO_ADMIN_PAGE + PART_DELETE_MESSEGE + messegeDelete);
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(GO_TO_ADMIN_PAGE + PART_DELETE_MESSEGE + MESSAGE_FAIL);
			requestDispatcher.forward(request, response);
		}
		
		
	}

}
