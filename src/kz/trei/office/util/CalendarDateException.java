package kz.trei.office.util;

public class CalendarDateException extends Exception {
	private static final long serialVersionUID = -7301171079459404593L;

	public CalendarDateException() {
		super();
	}

	public CalendarDateException(String message) {
		super(message);
	}

	public CalendarDateException(String message, Throwable cause) {
		super(message, cause);
	}

	public CalendarDateException(Throwable cause) {
		super(cause);
	}

	protected CalendarDateException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
