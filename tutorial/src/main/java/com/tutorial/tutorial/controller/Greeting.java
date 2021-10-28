package com.tutorial.tutorial.controller;

import org.springframework.stereotype.Component;

@Component
public class Greeting {

	private long id;
	private String content;
	
	//Métodos get y set
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
