package by.myproject.news.controller;

import java.util.HashMap;
import java.util.Map;

import by.myproject.news.controller.impl.AfterAuthorization;
import by.myproject.news.controller.impl.ChangeData;
import by.myproject.news.controller.impl.UnknowCommand;
import by.myproject.news.controller.impl.comment.AddCommentNews;
import by.myproject.news.controller.impl.comment.DeleteComment;
import by.myproject.news.controller.impl.gotopage.GoToAdminPage;
import by.myproject.news.controller.impl.gotopage.GoToAuthorizationPage;
import by.myproject.news.controller.impl.gotopage.GoToChangeDataPage;
import by.myproject.news.controller.impl.gotopage.GoToCorrectionNewsPage;
import by.myproject.news.controller.impl.gotopage.GoToCreateNewsPage;
import by.myproject.news.controller.impl.gotopage.GoToLastPage;
import by.myproject.news.controller.impl.gotopage.GoToListUsersPage;
import by.myproject.news.controller.impl.gotopage.GoToMainPage;
import by.myproject.news.controller.impl.gotopage.GoToNewsContentPage;
import by.myproject.news.controller.impl.gotopage.GoToRegistrationPage;
import by.myproject.news.controller.impl.gotopage.GoToUserPage;
import by.myproject.news.controller.impl.gotopage.GoToUserSetupPage;
import by.myproject.news.controller.impl.news.CreateNewNews;
import by.myproject.news.controller.impl.news.DeleteNews;
import by.myproject.news.controller.impl.news.PublishNews;
import by.myproject.news.controller.impl.news.SendTo—orrectNews;
import by.myproject.news.controller.impl.user.AutorizationUser;
import by.myproject.news.controller.impl.user.DeleteUser;
import by.myproject.news.controller.impl.user.Logout;
import by.myproject.news.controller.impl.user.RegistrationNewUser;
import by.myproject.news.controller.impl.ChangeLocal;

public class CommandProvider {

	private Map<CommandName, Command> commands = new HashMap<>();

	public CommandProvider() {
		//Go to page command
		commands.put(CommandName.GO_TO_AUTHORIZATION_PAGE, GoToAuthorizationPage.getInstance());
		commands.put(CommandName.GO_TO_REGISTRATION_PAGE, GoToRegistrationPage.getInstance());
		commands.put(CommandName.GO_TO_MAIN_PAGE, GoToMainPage.getInstance());
		commands.put(CommandName.GO_TO_USER_PAGE, GoToUserPage.getInstance());
		commands.put(CommandName.GO_TO_USER_SETUP_PAGE, GoToUserSetupPage.getInstance());
		commands.put(CommandName.GO_TO_CONTENT_NEWS_PAGE, GoToNewsContentPage.getInstance());
		commands.put(CommandName.GO_TO_CREATE_NEWS_PAGE, GoToCreateNewsPage.getInstance());
		commands.put(CommandName.GO_TO_CHANGE_DATA_PAGE, GoToChangeDataPage.getInstance());	
		commands.put(CommandName.GO_TO_LAST_PAGE, GoToLastPage.getInstance());
		commands.put(CommandName.GO_TO_ADMIN_PAGE, GoToAdminPage.getInstance());
		commands.put(CommandName.GO_TO_CORRECTION_NEWS_PAGE, GoToCorrectionNewsPage.getInstance());
		commands.put(CommandName.GO_TO_LIST_USERS_PAGE, GoToListUsersPage.getInstance());
		
		//User command
		commands.put(CommandName.AUTHORIZATION_USER, AutorizationUser.getInstance());
		commands.put(CommandName.REGISTRATION_NEW_USER, RegistrationNewUser.getInstance());
		commands.put(CommandName.DELETE_USER, DeleteUser.getInstance());
		commands.put(CommandName.LOGOUT_USER, Logout.getInstance());
		
		//News command
		commands.put(CommandName.CREATE_NEW_NEWS, CreateNewNews.getInstance());
		commands.put(CommandName.DELETE_NEWS, DeleteNews.getInstance());
		commands.put(CommandName.SEND_TO_CORRECT_NEWS, SendTo—orrectNews.getInstance());
		commands.put(CommandName.PUBLISH_NEWS, PublishNews.getInstance());
			
		//Comment command
		commands.put(CommandName.DELETE_COMMENT, DeleteComment.getInstance());
		commands.put(CommandName.ADD_COMMENT_NEWS, AddCommentNews.getInstance());
		
		// Other command
		commands.put(CommandName.CHANGE_LOCAL, ChangeLocal.getInstance());
		commands.put(CommandName.CHANGE_DATA, ChangeData.getInstance());
		commands.put(CommandName.UNKNOWN_COMMAND, UnknowCommand.getInstance());
		
		//Unused command
		commands.put(CommandName.AFTER_AUTHORIZATION, AfterAuthorization.getInstance());
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
