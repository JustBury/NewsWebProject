package by.myproject.news.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import by.myproject.news.controller.CommandName;

public class CommandFilter implements Filter {

	private static final String COMMAND_REQUEST_PARAM = "command";

	private static final String LAST_COMMAND_SESSSION_PARAM = "lastCommand";
	

	private ServletContext context;

	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log("Filter initialized...");
	}
	@Override

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		HttpSession session = req.getSession(false);

		if (session == null) {
			session = req.getSession(true);
		}
		
		String lastCommand = req.getParameter(COMMAND_REQUEST_PARAM);
		
		if (lastCommand != null && !(lastCommand.equalsIgnoreCase(CommandName.CHANGE_LOCAL.toString()))){	
			this.context.log("Last command is: " + lastCommand);
			session.setAttribute(LAST_COMMAND_SESSSION_PARAM, lastCommand);
		}
		
		chain.doFilter(request, response);
	}

	public void destroy() {
	}

}
