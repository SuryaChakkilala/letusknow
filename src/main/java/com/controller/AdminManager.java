package com.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.model.AdminRemote;
import com.utils.SessionUtils;

@ManagedBean(name="admin")
public class AdminManager {
	String username;
	String password;
	String ack;
	
	@EJB(lookup="")
	AdminRemote ar;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAck() {
		return ack;
	}
	public void setAck(String ack) {
		this.ack = ack;
	}
	public String login() {
		try {
			if(ar.validateCredentials(username, password)) {
				SessionUtils.getSession().setAttribute("adminusername", username);
				return "admin";
			} else {
				this.ack = "Incorrect credentials";
			}
		} catch(Exception e) {
			this.ack = e.getMessage();
		}
		return "adminlogin";
	}
	public String logout() {
		SessionUtils.getSession().invalidate();
		return "login";
	}
	public boolean isAdminLoggedIn() {
		if(SessionUtils.getSession().getAttribute("adminusername") == null) {
			return false;
		}
		return true;
	}
	public String getAdminUserNameSession() {
		return SessionUtils.getSession().getAttribute("adminusername").toString();
	}
}
