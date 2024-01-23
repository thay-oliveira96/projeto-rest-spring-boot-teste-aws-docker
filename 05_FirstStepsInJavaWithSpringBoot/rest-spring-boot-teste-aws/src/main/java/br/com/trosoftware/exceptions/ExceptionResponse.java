package br.com.trosoftware.exceptions;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Date timestemp;
	private String message;
	private String datails;
	
	public ExceptionResponse(Date timestemp, String message, String datails) {
		super();
		this.timestemp = timestemp;
		this.message = message;
		this.datails = datails;
	}

	public Date getTimestemp() {
		return timestemp;
	}

	public void setTimestemp(Date timestemp) {
		this.timestemp = timestemp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDatails() {
		return datails;
	}

	public void setDatails(String datails) {
		this.datails = datails;
	}
	
}
