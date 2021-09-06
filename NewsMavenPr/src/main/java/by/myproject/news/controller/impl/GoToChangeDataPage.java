package by.myproject.news.controller.impl;

import java.io.IOException;

import by.myproject.news.controller.Command;
import by.myproject.news.controller.impl.changedata.ChangeDataCommandProvader;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToChangeDataPage implements Command {
	
	private static final GoToChangeDataPage instance = new GoToChangeDataPage();
	private static final String CHANGE_DATA_PAGE = "/WEB-INF/jsp/ChangeDataPage.jsp";
	private static final String SHANGE_DATA = "change_data";
	private static final ChangeDataCommandProvader PROVADER = new ChangeDataCommandProvader();
	private GoToChangeDataPage() {}
	
	public static GoToChangeDataPage getInstance() {
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String changeCommand = request.getParameter(SHANGE_DATA);
		String changeData = PROVADER.findData(changeCommand);
		request.getSession(false).setAttribute(SHANGE_DATA, changeData);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(CHANGE_DATA_PAGE);
		requestDispatcher.forward(request, response);
		
	}

}
