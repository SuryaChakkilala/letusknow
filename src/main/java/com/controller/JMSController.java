package com.controller;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import com.model.JMSService;

@ManagedBean(name="jms", eager=true)
public class JMSController {
	String msg;
	String response;
	
	@Inject
	JMSService jms;
	
	public void sendMessage() {
		jms.sendMessage(msg);
		this.response = "message sent";
	}
	
	public void receiveMessage() {
		String message = jms.receiveMessage();
		this.response = message;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
}
