package by.myproject.news.servise;



import java.util.List;

import by.myproject.news.bean.RegistrationInfo;
import by.myproject.news.bean.User;
import by.myproject.news.servise.exception.ServiseException;

public interface UserServise {

	User registration(RegistrationInfo info) throws ServiseException;

	User authorization(RegistrationInfo info) throws ServiseException;
	
	User changeData(User user,String changeData, String newData, String password) throws ServiseException;
	
	List<User> getListUsers() throws ServiseException;
	
	void delete(int idUser) throws ServiseException;

}
