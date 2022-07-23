package com.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.entity.Reply;
import com.model.ReplyRemote;

@ManagedBean(name="reply", eager=true)
public class ReplyManager {
	int id;
	String subject;
	String reply;
	String postedby;
	String ack;
	
	@EJB(lookup="")
	ReplyRemote rr;
	
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

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}
	
	public String insert() {
		try {
			Reply r = new Reply();
			r.setId(id);
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "SuryaTeja@9192");
			PreparedStatement st = con.prepareStatement("select subject, username from grieviances where id=?");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				r.setSubject(rs.getString(1));
				r.setPostedby(rs.getString(2));
			}
			r.setReply(reply);
			rr.addReply(r);
			return "directorhome";
		} catch(Exception e) {
			this.ack = e.getMessage();
		}
		return "replyform";
	}
	
	public List<Reply> getReplies() {
		List<Reply> list = null;
		try {
			list = rr.getReplies();
			return list;
		} catch(Exception e) {
			this.ack = e.getMessage();
		}
		return list;
	}
	
	public String delete() {
		try {
			this.ack = rr.deleteReply(id);
		} catch(Exception e) {
			this.ack = e.getMessage();
		}
		return "admin";
	}
}
