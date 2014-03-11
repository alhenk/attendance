package kz.trei.office.parser;

public class SaxParserException extends Exception{

	private static final long serialVersionUID = -5147386262734243643L;

	public SaxParserException() {
		super();
	}

	public SaxParserException(String message) {
		super(message);
	}

	public SaxParserException(String message, Throwable cause) {
		super(message, cause);
	}

	public SaxParserException(Throwable cause) {
		super(cause);
	}

	protected SaxParserException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
