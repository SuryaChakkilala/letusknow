package com.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.entity.Director;
import com.model.DirectorRemote;
import com.utils.SessionUtils;

@ManagedBean(name="d", eager=true)
public class DirectorManager {
	String username;
	String password;
	String ack;
	
	@EJB(lookup="java:global/letusknow/DirectorModel")
	DirectorRemote dr;
	
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
			if(dr.validateCredentials(username, password)) {
				SessionUtils.getSession().setAttribute("directorusername", username);
				return "directorhome";
			} else {
				this.ack = "Incorrect credentials";
			}
		} catch(Exception e) {
			this.ack = e.getMessage();
		}
		return "directorlogin";
	}
	public String logout() {
		SessionUtils.getSession().invalidate();
		return "login";
	}
	public boolean isDirectorLoggedIn() {
		if(SessionUtils.getSession().getAttribute("directorusername") == null) {
			return false;
		}
		return true;
	}
	public String getDirectorUserNameSession() {
		return SessionUtils.getSession().getAttribute("directorusername").toString();
	}
	public List<Director> getDirectors() {
		List<Director> list = null;
		try {
			list = dr.getAllDirectors();
			return list;
		} catch(Exception e) {
			this.ack = "Cannot retrieve values";
		}
		return list;
	}
	public String deleteDirector() {
		try {
			this.ack = dr.deleteDirector(username);
			return "admin";
		} catch(Exception e) {
			this.ack = e.getMessage();
		}
		return "admin";
	}
}
