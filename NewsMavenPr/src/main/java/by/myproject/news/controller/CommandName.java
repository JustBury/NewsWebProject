package by.myproject.news.controller;

import javax.print.attribute.standard.MediaSize.Other;

public enum CommandName {
	//GO TO command
	GO_TO_MAIN_PAGE,
	GO_TO_USER_PAGE, 
	GO_TO_USER_SETUP_PAGE,
	GO_TO_CONTENT_NEWS_PAGE,
	GO_TO_CREATE_NEWS_PAGE,
	GO_TO_CHANGE_DATA_PAGE,
	GO_TO_LAST_PAGE,
	GO_TO_ADMIN_PAGE,
	GO_TO_CORRECTION_NEWS_PAGE,
	GO_TO_REGISTRATION_PAGE, 
	GO_TO_AUTHORIZATION_PAGE,
	GO_TO_LIST_USERS_PAGE,
	
	//News command
	CREATE_NEW_NEWS,
	DELETE_NEWS,
	SEND_TO_CORRECT_NEWS,
	PUBLISH_NEWS,
	
	//User command
	DELETE_USER,
	AUTHORIZATION_USER,
	REGISTRATION_NEW_USER,
	LOGOUT_USER,
	
	//Comment command
	ADD_COMMENT_NEWS,
	DELETE_COMMENT,
	
	// Other command
	CHANGE_DATA,
	CHANGE_LOCAL,
	UNKNOWN_COMMAND,
	
	//Unused command
	 
	AFTER_AUTHORIZATION
}
