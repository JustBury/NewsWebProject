package by.myproject.news.dao.exception;

public class DAOExsepsionClose extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DAOExsepsionClose() {
		super();
	}

	public DAOExsepsionClose(String message) {
		super(message);
	}

	public DAOExsepsionClose(Exception e) {
		super(e);
	}

	public DAOExsepsionClose(String message, Exception e) {
		super(message, e);
	}

}
