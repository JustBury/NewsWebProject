package by.myproject.news.controller.impl.news;

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

public class SendTo—orrectNews implements Command {

	private static SendTo—orrectNews instance = new SendTo—orrectNews();
	private static final ServiseProvider PROVIDER = ServiseProvider.getInstance();
	private static final NewsServise SERVISE = PROVIDER.getNewsServise();
	private static final String NEWS_ID = "newsId";
	private static final String USER = "user";
	private static final String GO_TO_ADMIN_PAGE = "Controller?command=GO_TO_ADMIN_PAGE";
	private static final String PART_CORRECT_MESSEGE = "&message=";
	private static final String MESSAGE_FAIL = "Couldn't send the news for revision";
	private static final String GO_TO_AUTHORIZATION_PAGE = "Controller?command=GO_TO_AUTHORIZATION_PAGE";
	
	
	private SendTo—orrectNews() {
	}

	public static SendTo—orrectNews getInstance() {
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
			
			String messegeCorrect = SERVISE.sendToCorrect(idNews);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(GO_TO_ADMIN_PAGE + PART_CORRECT_MESSEGE + messegeCorrect);
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(GO_TO_ADMIN_PAGE + PART_CORRECT_MESSEGE + MESSAGE_FAIL);
			requestDispatcher.forward(request, response);
		}
		
		
	}
}
