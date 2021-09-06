package by.myproject.news.dao;

import java.util.List;

import by.myproject.news.bean.RegistrationInfo;
import by.myproject.news.bean.User;
import by.myproject.news.dao.exception.DAOException;

public interface UserDAO {

	User registration(RegistrationInfo info) throws DAOException;

	User authorization(RegistrationInfo info) throws DAOException;

	User changeData(User user, String changeData, String newData, String password) throws DAOException;

	List<User> getListUsers() throws DAOException;
	
	void delete(int idUser) throws DAOException;
}
