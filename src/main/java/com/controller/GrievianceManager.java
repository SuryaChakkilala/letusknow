package com.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.entity.Grieviance;
import com.model.GrievianceRemote;
import com.utils.SessionUtils;

@ManagedBean(name="grieviance", eager=true)
public class GrievianceManager {
	int id;
	String subject;
	String issue;
	String department;
	String organization;
	String type;
	String ack;
	List<Grieviance> list;
	
	@EJB(lookup="java:global/letusknow/GrievianceModel")
	GrievianceRemote gr;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAck() {
		return ack;
	}
	public void setAck(String ack) {
		this.ack = ack;
	}
	public String addGrieviance() {
		Grieviance g = new Grieviance();
		g.setId(id);
		g.setSubject(subject);
		g.setIssue(issue);
		g.setOrganization(organization);
		g.setDepartment(department);
		g.setType(type);
		g.setUsername(SessionUtils.getUserName());
		try {
			gr.insertGrieviance(g);
			this.ack = "Grieviance Submitted Successfully";
			return "grieviances";
		} catch(Exception e) {
			this.ack = e.getMessage();
			return "grievianceform";
		}
	}
	public List<Grieviance> getGrieviances() {
		try {
			list = gr.getGrieviances();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public String delete() {
		try {
			this.ack =  gr.removeGrieviance(id);
		} catch(Exception e) {
			this.ack = e.getMessage();
		}
		return "admin";
	}
	
}
