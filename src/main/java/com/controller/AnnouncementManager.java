package com.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.entity.Announcement;
import com.model.AnnouncementRemote;

@ManagedBean(name="announcement", eager=true)
public class AnnouncementManager {
	int id;
	String title;
	String content;
	String ack;
	
	@EJB(lookup="")
	AnnouncementRemote ar;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAck() {
		return ack;
	}
	public void setAck(String ack) {
		this.ack = ack;
	}
	public String addAnnouncement() {
//		try {
//			Announcement a = new Announcement();
//			a.setId(id);
//			a.setTitle(title);
//			a.setContent(content);
//			this.ack = ar.addAnnouncement(a);
//			return "announcements";
//		} catch(Exception e) {
//			this.ack = e.getMessage();
//		}
//		return "announcementform";
		try {
			Announcement a = new Announcement();
			a.setId(id);
			a.setTitle(title);
			a.setContent(content);
			URL url = new URL("http://localhost:8080/letusknowrestservices/announcement/" + id + "/" + title + "/" + content);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Accept", "application/json");
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			br.readLine();
		} catch(Exception e) {
			this.ack = e.getMessage();
		}
		return "announcements";
	}
	public List<Announcement> getAnnouncements() {
		List<Announcement> list = new ArrayList<Announcement>();
		try {
			list = ar.getAnnouncements();
			return list;
		} catch(Exception e) {
			this.ack = e.getMessage();
		}
		return list;
	}
	
}
