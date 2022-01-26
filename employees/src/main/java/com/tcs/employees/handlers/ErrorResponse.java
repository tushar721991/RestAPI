package com.tcs.employees.handlers;

import java.util.List;

/*
* Error response class
* */
public class ErrorResponse {

	private List<String> details;

	public ErrorResponse(List<String> details) {
		super();
		this.details = details;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

}
