package exception;

@SuppressWarnings("serial")
public class TemplateNotFoundException extends Exception {

	public TemplateNotFoundException() {
		super();
	}

	public TemplateNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TemplateNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public TemplateNotFoundException(String message) {
		super(message);
	}

	public TemplateNotFoundException(Throwable cause) {
		super(cause);
	}

}
