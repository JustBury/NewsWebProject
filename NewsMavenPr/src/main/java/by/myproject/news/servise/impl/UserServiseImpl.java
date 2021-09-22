package by.myproject.news.servise.impl;


import java.util.List;
import by.myproject.news.bean.RegistrationInfo;
import by.myproject.news.bean.User;
import by.myproject.news.dao.DAOProvider;
import by.myproject.news.dao.UserDAO;
import by.myproject.news.dao.exception.DAOException;
import by.myproject.news.servise.UserServise;
import by.myproject.news.servise.exception.ServiseException;


public class UserServiseImpl implements UserServise {
	private static final DAOProvider PROVIDER = DAOProvider.getInstance();
	private static final UserDAO USER_DAO = PROVIDER.getUserDAO();

	@Override
	public User registration(RegistrationInfo info) throws ServiseException {
		try {
			return USER_DAO.registration(info);
		} catch (DAOException e) {
			// log
			throw new ServiseException(e);
		}
	}

	@Override
	public User authorization(RegistrationInfo info) throws ServiseException {
	
		try {
			return USER_DAO.authorization(info);
		} catch (DAOException e) {
			// log
			throw new ServiseException(e);
		}
	}

	@Override
	public User changeData(User user,String changeData, String newData,String password) throws ServiseException {
		
		try {
			return USER_DAO.changeData(user,changeData,newData,password);
		} catch (DAOException e) {
			// log
			throw new ServiseException(e);
		}
	}

	@Override
	public List<User> getListUsers() throws ServiseException {
		try {
			return USER_DAO.getListUsers();
		} catch (DAOException e) {
			// log
			throw new ServiseException(e);
		}
	}

	@Override
	public void delete(int idUser) throws ServiseException {
		try {
			 USER_DAO.delete(idUser);
		} catch (DAOException e) {
			// log
			throw new ServiseException(e);
		}
		
	}
}
