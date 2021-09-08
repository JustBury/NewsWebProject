package by.myproject.news.controller.impl.user;

import java.io.IOException;

import by.myproject.news.bean.RegistrationInfo;
import by.myproject.news.bean.User;
import by.myproject.news.controller.Command;
import by.myproject.news.servise.ServiseProvider;
import by.myproject.news.servise.UserServise;
import by.myproject.news.servise.exception.ServiseException;
import by.myproject.news.servise.validation.ValidationInformation;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AutorizationUser implements Command {

	private static AutorizationUser instance = new AutorizationUser();
	private static final ServiseProvider PROVIDER = ServiseProvider.getInstance();
	private static final UserServise USER_SERVISE = PROVIDER.getUserServise();
	private static final String GO_TO_AUTHORIZATION_PAGE = "Controller?command=GO_TO_AUTHORIZATION_PAGE";
	public static final String GO_TO_USER_PAGE = "Controller?command=GO_TO_USER_PAGE";
	public static final String INCORRECT_DATA_MESSAGE = "&incorrect_data_message=Incorrect data was entered";
	public static final String USER_NOT_FOUND_MESSAGE = "&user_not_found=There is no such user";
	private static final String FAILED_SIGNIN = "Failed to sign in, please try again";
	private static final String USER = "user";
	private static final String EMAIL = "email";
	private static final String FIRST_PASSWORD = "firstPassword";

	private AutorizationUser() {
	}

	public static AutorizationUser getInstance() {
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RegistrationInfo info = new RegistrationInfo();
	
		info.setEmail(request.getParameter(EMAIL));
		info.setFirstPassword(request.getParameter(FIRST_PASSWORD));
	
					
		try {
			if(!ValidationInformation.validationAuthorizationsInformation(info)) {
				response.sendRedirect(GO_TO_AUTHORIZATION_PAGE + INCORRECT_DATA_MESSAGE);
				return;
			}	
			
			User user = USER_SERVISE.authorization(info);
			
			if (user == null) {
				response.sendRedirect(GO_TO_AUTHORIZATION_PAGE + USER_NOT_FOUND_MESSAGE);
				return;
			}

				request.getSession(true).setAttribute(USER, user);
				response.sendRedirect(GO_TO_USER_PAGE);
			
		} catch (ServiseException e) {
			e.printStackTrace();
			response.sendRedirect(GO_TO_AUTHORIZATION_PAGE + FAILED_SIGNIN);
			
		}

		
	}
}
