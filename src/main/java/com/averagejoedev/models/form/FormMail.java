package com.averagejoedev.models.form;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FormMail extends Form {
		
	private String from;
	private String[] to;
	private String[] cc;
	private String[] bcc;
	private String subject;
	private File[] files;
	private Map<String, Object> params;

	public FormMail() {
		this.from	= "minhdl9889test@gmail.com";
		this.params = new HashMap<>();
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public String[] getCc() {
		return cc;
	}

	public void setCc(String[] cc) {
		this.cc = cc;
	}

	public String[] getBcc() {
		return bcc;
	}

	public void setBcc(String[] bcc) {
		this.bcc = bcc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public File[] getFiles() {
		return files;
	}

	public void setFiles(File[] files) {
		this.files = files;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public void putParam(String key, Object value) {
		this.params.put(key, value);
	}

}
