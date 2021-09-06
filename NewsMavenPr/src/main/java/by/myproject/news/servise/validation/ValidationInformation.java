package by.myproject.news.servise.validation;

import by.myproject.news.bean.RegistrationInfo;
import by.myproject.news.servise.exception.ServiseException;

public class ValidationInformation {

	private static final String CHECK_EMAIL = "\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}";
	private static final String CHECK_PASSWORD = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";
	private static final String CHECK_NAME_SURNAME = "^[a-zA-Zà-ÿÀ-ß][a-zA-Zà-ÿÀ-ß0-9-_\\.]{1,20}$";

	private ValidationInformation() {
	}

	public static boolean validationRegistrationInformation(RegistrationInfo info) throws ServiseException {

		if (!info.getFirstPassword().equals(info.getSecondPassword())) {
			return false;
		}

		return checkEmail(info.getEmail()) && checkPassword(info.getFirstPassword()) && checkName(info.getName())
				&& checkSurname(info.getSurname());
	}

	public static boolean validationAuthorizationsInformation(RegistrationInfo info) throws ServiseException {
		return checkEmail(info.getEmail()) && checkPassword(info.getFirstPassword());
	}

	public static boolean checkEmail(String email) {
		return email.matches(CHECK_EMAIL);
	}

	public static boolean checkPassword(String password) {
		return password.matches(CHECK_PASSWORD);
	}

	public static boolean checkName(String name) {
		return name.matches(CHECK_NAME_SURNAME);
	}

	public static boolean checkSurname(String surname) {
		return surname.matches(CHECK_NAME_SURNAME);
	}

}
