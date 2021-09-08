package by.myproject.news.controller.impl.comment;

import java.io.IOException;

import by.myproject.news.bean.User;
import by.myproject.news.controller.Command;
import by.myproject.news.servise.CommentServise;
import by.myproject.news.servise.ServiseProvider;
import by.myproject.news.servise.exception.ServiseException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DeleteComment implements Command {

	private static final ServiseProvider PROVIDER = ServiseProvider.getInstance();
	private static final CommentServise COMMENT_SERVISE = PROVIDER.getCommentServise();
	private static final DeleteComment instatnce = new DeleteComment();
	private static final String GO_TO_AUTHORIZATION_PAGE = "Controller?command=GO_TO_AUTHORIZATION_PAGE";
	private static final String GO_TO_CONTENT_NEWS_PAGE = "Controller?command=GO_TO_CONTENT_NEWS_PAGE";
	private static final String USER = "user";
	private static final String ID_COMMENT = "idComment";
	private static final String MESSEGE_PLEASE_LOGIN = "&please_login=Please log in";
	private static final String PART_DELETE_MESSEGE = "&messageComment=";
	private static final String MESSAGE_FAIL = "Couldn't delete the news";
	
	private DeleteComment() {}
	
	public static DeleteComment getInstance() {
		return instatnce;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idComment = Integer.parseInt(request.getParameter(ID_COMMENT));
		System.out.println(idComment);
		
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
				
		try {
			String messageDeleteCom =  COMMENT_SERVISE.deleteComment(idComment);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(GO_TO_CONTENT_NEWS_PAGE + PART_DELETE_MESSEGE + messageDeleteCom);
			requestDispatcher.forward(request, response);
		} catch (ServiseException e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(GO_TO_CONTENT_NEWS_PAGE + PART_DELETE_MESSEGE + MESSAGE_FAIL);
			requestDispatcher.forward(request, response);
		}
		
	}
}
