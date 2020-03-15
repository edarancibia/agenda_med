package com.calendar.entities;

public class MailForm {

	private String name;
	private String mail;
	private String subject;
	private String body;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public MailForm(String name, String mail, String subject, String body) {
		super();
		this.name = name;
		this.mail = mail;
		this.subject = subject;
		this.body = body;
	}
	public MailForm() {
		
	}
		
}

