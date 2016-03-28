package br.com.infowaypi.jheatbased.usage.api;

/**
 * Envolve a mensagem de resposta à requisição. 
 */
public class Result {

	private String message;
	private int httpCode;
	private Object outcome;

	public Result() {
		super();
	}

	public Result(String message, int httpCode, Object outcome) {
		super();
		this.message = message;
		this.httpCode = httpCode;
		this.outcome = outcome;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}

	public Object getOutcome() {
		return outcome;
	}

	public void setOutcome(Object outcome) {
		this.outcome = outcome;
	}
	
	
}
