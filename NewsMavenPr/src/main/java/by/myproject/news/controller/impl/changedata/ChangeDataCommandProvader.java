package by.myproject.news.controller.impl.changedata;

import java.util.HashMap;
import java.util.Map;

public class ChangeDataCommandProvader {
	private Map<ChangeDataCommandName, String> changeDataName = new HashMap<>();

	public ChangeDataCommandProvader() {
		changeDataName.put(ChangeDataCommandName.CHANGE_EMAIL, "email");
		changeDataName.put(ChangeDataCommandName.CHANGE_NAME, "name");
		changeDataName.put(ChangeDataCommandName.CHANGE_PASSWORD, "password");	
		changeDataName.put(ChangeDataCommandName.CHANGE_SURNAME, "surname");	
	}

	public String findData(String name) {
		if(name == null) {
			name = "email";
		}
		ChangeDataCommandName changeDataCommandName = null;
		try {
			changeDataCommandName = ChangeDataCommandName.valueOf(name.toUpperCase());
		} catch (IllegalArgumentException e) {
			
		}
		String data = changeDataName.get(changeDataCommandName);
		return data;
	}
}
