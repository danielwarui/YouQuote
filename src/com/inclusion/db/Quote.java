package com.inclusion.db;

public class Quote {
	public Quote() {
		super();
		// TODO Auto-generated constructor stub
	}

	private int id;
	private String quote;
	private String authority;
	private String date;

	public Quote(String quote, String authority, String date) {
		super();
		this.quote = quote;
		this.authority = authority;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public String getDate() {
		return date;
	}

	public String getQuote() {
		return quote;
	}

	public String getAuthority() {
		return authority;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
