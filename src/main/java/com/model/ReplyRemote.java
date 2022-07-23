package com.model;

import java.util.List;

import javax.ejb.Remote;

import com.entity.Reply;

@Remote
public interface ReplyRemote {
	public String addReply(Reply r) throws Exception;
	public List<Reply> getReplies() throws Exception;
	public String deleteReply(int id) throws Exception;
}
