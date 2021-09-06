package by.myproject.news.dao.connection;

import java.util.ResourceBundle;

public class DBResurceManager {
	
	public static final String DB_NEWS = "resources.properties.db";

	private final static DBResurceManager instance = new DBResurceManager();
	
	ResourceBundle bundle = ResourceBundle.getBundle(DB_NEWS);
	
	private DBResurceManager() {}
	
	public static DBResurceManager getInstance() {
		return instance;
	}
	
	public String getValue(String key) {
		return bundle.getString(key);		
	}
}
