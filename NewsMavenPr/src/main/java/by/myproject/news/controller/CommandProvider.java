package by.myproject.news.controller;

import java.util.HashMap;
import java.util.Map;

import by.myproject.news.controller.impl.AfterAuthorization;
import by.myproject.news.controller.impl.AddCommentNews;
import by.myproject.news.controller.impl.AutorizationUser;
import by.myproject.news.controller.impl.ChangeData;
import by.myproject.news.controller.impl.GoToAuthorizationPage;
import by.myproject.news.controller.impl.GoToChangeDataPage;
import by.myproject.news.controller.impl.GoToCorrectionNewsPage;
import by.myproject.news.controller.impl.GoToCreateNewsPage;
import by.myproject.news.controller.impl.GoToLastPage;
import by.myproject.news.controller.impl.GoToListUsersPage;
import by.myproject.news.controller.impl.GoToMainPage;
import by.myproject.news.controller.impl.GoToNewsContentPage;
import by.myproject.news.controller.impl.GoToUserSetupPage;
import by.myproject.news.controller.impl.GoToRegistrationPage;
import by.myproject.news.controller.impl.GoToUserPage;
import by.myproject.news.controller.impl.Logout;
import by.myproject.news.controller.impl.PublishNews;
import by.myproject.news.controller.impl.RegistrationNewUser;
import by.myproject.news.controller.impl.SendTo—orrect;
import by.myproject.news.controller.impl.UnknowCommand;
import by.myproject.news.controller.impl.ChangeLocal;
import by.myproject.news.controller.impl.CreateNewNews;
import by.myproject.news.controller.impl.DeleteNews;
import by.myproject.news.controller.impl.DeleteUser;
import by.myproject.news.controller.impl.GoToAdminPage;

public class CommandProvider {

	private Map<CommandName, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(CommandName.AUTHORIZATION_PAGE, GoToAuthorizationPage.getInstance());
		commands.put(CommandName.REGISTRATION_PAGE, GoToRegistrationPage.getInstance());
		commands.put(CommandName.REGISTRATION_NEW_USER, RegistrationNewUser.getInstance());
		commands.put(CommandName.AUTHORIZATION_USER, AutorizationUser.getInstance());
		commands.put(CommandName.UNKNOWN_COMMAND, UnknowCommand.getInstance());
		commands.put(CommandName.GO_TO_MAIN_PAGE, GoToMainPage.getInstance());
		commands.put(CommandName.AFTER_AUTHORIZATION, AfterAuthorization.getInstance());
		commands.put(CommandName.CHANGE_LOCAL, ChangeLocal.getInstance());
		commands.put(CommandName.GO_TO_USER_PAGE, GoToUserPage.getInstance());
		commands.put(CommandName.LOGOUT, Logout.getInstance());
		commands.put(CommandName.GO_TO_USER_SETUP_PAGE, GoToUserSetupPage.getInstance());
		commands.put(CommandName.GO_TO_CONTENT_NEWS_PAGE, GoToNewsContentPage.getInstance());
		commands.put(CommandName.GO_TO_CREATE_NEWS_PAGE, GoToCreateNewsPage.getInstance());
		commands.put(CommandName.CREATE_NEW_NEWS, CreateNewNews.getInstance());
		commands.put(CommandName.CHANGE_DATA, ChangeData.getInstance());
		commands.put(CommandName.GO_TO_CHANGE_DATA_PAGE, GoToChangeDataPage.getInstance());	
		commands.put(CommandName.GO_TO_LAST_PAGE, GoToLastPage.getInstance());
		commands.put(CommandName.GO_TO_ADMIN_PAGE, GoToAdminPage.getInstance());
		commands.put(CommandName.GO_TO_CORRECTION_NEWS_PAGE, GoToCorrectionNewsPage.getInstance());
		commands.put(CommandName.DELETE_NEWS, DeleteNews.getInstance());
		commands.put(CommandName.SEND_TO_–°ORRECT, SendTo—orrect.getInstance());
		commands.put(CommandName.PUBLISH_NEWS, PublishNews.getInstance());
		commands.put(CommandName.APPEND_COMMENT_NEWS, AddCommentNews.getInstance());
		commands.put(CommandName.GO_TO_LIST_USERS_PAGE, GoToListUsersPage.getInstance());
		commands.put(CommandName.DELETE_USER, DeleteUser.getInstance());
	}

	public Command findCommand(String name) {
		if (name == null) {
			System.out.println(name);
			name = CommandName.UNKNOWN_COMMAND.toString();
		}
		CommandName commandName;
		try {
			commandName = CommandName.valueOf(name.toUpperCase());
		} catch (IllegalArgumentException e) {
			commandName = CommandName.UNKNOWN_COMMAND;
		}
		Command command = commands.get(commandName);
		return command;
	}

}
