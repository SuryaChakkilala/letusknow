package com.model;

import java.util.List;

import javax.ejb.Remote;

import com.entity.Announcement;

@Remote
public interface AnnouncementRemote {
	public String addAnnouncement(Announcement e) throws Exception;
	public List<Announcement> getAnnouncements() throws Exception;
}
