package by.myproject.news.controller.impl;

import java.io.IOException;

import by.myproject.news.bean.User;
import by.myproject.news.controller.Command;
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
	private static final NewsServise SERVISE = PROVIDER.getNewsServise();
	private static final String NEWS_ID = "newsId";
	private static final String AUTHORIZATION_PAGE = "Controller?command=AUTHORIZATION_PAGE";
	private static final String USER = "user";
	private static final String ADMIN_PAGE = "Controller?command=GO_TO_ADMIN_PAGE";
	private static final String PART_DELETE_MESSEGE = "&message=";
	
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
				response.sendRedirect(AUTHORIZATION_PAGE);
				return;
			}

			User user = (User) session.getAttribute(USER);

			if (user == null) {
				response.sendRedirect(AUTHORIZATION_PAGE);
				return;
			}
			String messegeDelete = SERVISE.delete(idNews);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(ADMIN_PAGE + PART_DELETE_MESSEGE + messegeDelete);
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		
		
	}

}
