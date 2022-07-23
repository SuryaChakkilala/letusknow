package com.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import com.entity.User;
import com.model.JMSService;
import com.model.UserRemote;
import com.utils.SessionUtils;

@ManagedBean(name="user", eager=true)
public class UserManager {
	String firstName;
	String lastName;
	String dateOfBirth;
	String organization;
	String department;
	String designation;
	String address;
	String username;
	String password;
	String ack;
	List<User> list;
	
	@EJB(lookup="java:global/letusknow/UserModel")
	UserRemote ur;
	
	@Inject
	JMSService jms;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

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
	
	public String register() {
		User u = new User();
		u.setFirstName(firstName);
		u.setLastName(lastName);
		u.setDateOfBirth(dateOfBirth);
		u.setDepartment(department);
		u.setDesignation(designation);
		u.setOrganization(organization);
		u.setAddress(address);
		u.setUsername(username);
		u.setPassword(password);
		try {
			this.ack = ur.registerUser(u);
		} catch(Exception e) {
			this.ack = e.getMessage();
		}
		return "login";
	}
	
	public String validateUsernamePassword() {
		boolean valid = false;
		try {
			valid = ur.validateUserCredentials(username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(valid) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", username);
			System.out.println("VALID");
			return "index";
		} else {
//			FacesContext.getCurrentInstance().addMessage(
//					null,
//					new FacesMessage(FacesMessage.SEVERITY_WARN,
//							"Incorrect Username and Passowrd",
//							"Please enter correct username and Password"));
//			jms.sendMessage("Incorrect credentials. Please enter valid username and password");
			this.ack = "Incorrect credentials. Please enter valid username and password";
			return "login";
		}
	}
	public String logout() {
		jms.sendMessage(SessionUtils.getSession().getAttribute("username") + " successfully logged out");
		SessionUtils.getSession().invalidate();
		return "login";
	}
	public boolean isLoggedIn() {
		if(SessionUtils.getSession().getAttribute("username") == null) {
			return false;
		}
		return true;
	}
	public String getUserNameSession() {
		return SessionUtils.getSession().getAttribute("username").toString();
	}
	public void getProfileDetails() {
		User u;
		try {
			u = ur.getUser(SessionUtils.getUserName());
			firstName = u.getFirstName();
			lastName = u.getLastName();
			dateOfBirth = u.getDateOfBirth();
			address = u.getAddress();
			username = u.getUsername();
			department = u.getDepartment();
			organization = u.getOrganization();
			designation = u.getDepartment();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<User> getUsers() {
		try {
			list = ur.getUsers();
		} catch(Exception e) {
			this.ack = e.getMessage();
		}
		return list;
	}
	public String delete() {
		System.out.println("H");
		try {
			this.ack = ur.deleteUser(username);
		} catch(Exception e) {
			this.ack = e.getMessage();
		}
		return "admin";
	}
	
	public String delete(String username) {
		try {
			this.ack = ur.deleteUser(username);
		} catch(Exception e) {
			this.ack = e.getMessage();
		}
		return "admin";
	}
	
}
