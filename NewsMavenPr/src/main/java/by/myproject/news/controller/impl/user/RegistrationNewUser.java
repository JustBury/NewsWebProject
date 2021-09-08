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

public class RegistrationNewUser implements Command {

	private static final RegistrationNewUser instance = new RegistrationNewUser();
	private static final ServiseProvider PROVIDER = ServiseProvider.getInstance();
	private static final UserServise USER_SERVISE = PROVIDER.getUserServise();
	private static final String GO_TO_REGISTRATION_PAGE = "Controller?command=GO_TO_REGISTRATION_PAGE";
	private static final String GO_TO_AUTHORIZATION_PAGE = "Controller?command=GO_TO_AUTHORIZATION_PAGE";
	private static final String INCORRECT_DATA = "&incorrect_data_message=Incorrect data was entered:";
	private static final String FAILED_REGISTER = "Failed to register the user, please try again";
	private static final String EMAIL_IS_BUSY = "&email_is_busy=The user with this Email is already registered";
	private static final String REGISTRATION_MESSAGE = "&registration_message=Congratulations on registering, please log in";
	private static final String NAME = "name";
	private static final String SURNAME = "surname";
	private static final String EMAIL = "email";
	private static final String FIRST_PASSWORD = "firstPassword";
	private static final String SECOND_PASSWORD = "secondPassword";
	
	private RegistrationNewUser() {
	}

	public static RegistrationNewUser getInstance() {
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RegistrationInfo info = new RegistrationInfo();
		info.setName(request.getParameter(NAME));
		info.setSurname(request.getParameter(SURNAME));
		info.setEmail(request.getParameter(EMAIL));
		info.setFirstPassword(request.getParameter(FIRST_PASSWORD));
		info.setSecondPassword(request.getParameter(SECOND_PASSWORD));
				
		try {
			if(!ValidationInformation.validationRegistrationInformation(info)) {
				response.sendRedirect(GO_TO_REGISTRATION_PAGE + INCORRECT_DATA);
				return;
			}
								
			User user = USER_SERVISE.registration(info);
			
			if(user == null) {
				response.sendRedirect(GO_TO_REGISTRATION_PAGE + EMAIL_IS_BUSY);
				return;
			}
			
			response.sendRedirect(GO_TO_AUTHORIZATION_PAGE + REGISTRATION_MESSAGE);
		} catch (ServiseException e) {
			e.printStackTrace();
			response.sendRedirect(GO_TO_REGISTRATION_PAGE + FAILED_REGISTER);		
		}
		
	}
}
