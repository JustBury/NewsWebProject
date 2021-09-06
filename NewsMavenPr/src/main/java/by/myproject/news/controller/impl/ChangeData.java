package by.myproject.news.controller.impl;

import java.io.IOException;

import by.myproject.news.bean.User;
import by.myproject.news.controller.Command;
import by.myproject.news.servise.ServiseProvider;
import by.myproject.news.servise.UserServise;
import by.myproject.news.servise.exception.ServiseException;
import by.myproject.news.servise.impl.UserServiseImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import by.myproject.news.servise.validation.ValidationInformation;

public class ChangeData implements Command {

	private static final ChangeData instance = new ChangeData();
	private static final String SHANGE_DATA = "change_data";
	private static final ServiseProvider PROVIDER = ServiseProvider.getInstance();
	private static final UserServise USER_SERVISE = PROVIDER.getUserServise();
	public static final String ERROR_PAGE = "Controller?command=UNKNOWN_COMMAND";
	private static final String USER = "user";
	private static final String EMAIL = "email";
	private static final String PASSWORD = "password";
	private static final String SECOND_PASSWORD = "secondPassword";
	public static final String PART_PATH = "Controller?command=";
	private static final String USER_SETUP_PAGE = "GO_TO_USER_SETUP_PAGE";
	public static final String USER_SETUB_MESSEGE = "&not_valid_data=Incorrect data was entered";
	private static final String AUTHORIZATION_PAGE = "AUTHORIZATION_PAGE";
	private static final String CONFIRM_PASSWORD = "passwordConfirm";

	private ChangeData() {
	}

	public static ChangeData getInstance() {
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect(PART_PATH + AUTHORIZATION_PAGE);
			return;
		}
		User user = (User) session.getAttribute(USER);

		if (user == null) {
			response.sendRedirect(PART_PATH + AUTHORIZATION_PAGE);
			return;
		}

		String changeData = (String) session.getAttribute(SHANGE_DATA);
		String newData = request.getParameter(changeData);
		String passwordConfirm = request.getParameter(CONFIRM_PASSWORD);

		// Проверка email на корректность
		if (changeData.equals(EMAIL)) {
			if (!ValidationInformation.checkEmail(newData)) {
				response.sendRedirect(PART_PATH + USER_SETUP_PAGE + USER_SETUB_MESSEGE);
				return;
			}
		}

		// Проверка пороля на корректность
		if (changeData.equals(PASSWORD)) {
			String secondPassword = request.getParameter(SECOND_PASSWORD);
			if (!ValidationInformation.checkPassword(newData) || !(newData.equals(secondPassword))) {
				response.sendRedirect(PART_PATH + USER_SETUP_PAGE + USER_SETUB_MESSEGE);
				return;
			}
		}
		try {

			User changeUser = USER_SERVISE.changeData(user, changeData, newData, passwordConfirm);
			if (changeUser != null) {
				request.getSession(false).setAttribute(USER, changeUser);
				response.sendRedirect(PART_PATH + USER_SETUP_PAGE);
				return;
			} else {
				response.sendRedirect(PART_PATH + USER_SETUP_PAGE + USER_SETUB_MESSEGE);
				return;
			}

		} catch (ServiseException e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
			requestDispatcher.forward(request, response);
		}

	}
}
