package com.model;

import java.util.List;

import com.entity.User;

public interface UserRemote {
	public String registerUser(User u) throws Exception;
	public boolean validateUserCredentials(String username, String password) throws Exception;
	public User getUser(String username) throws Exception;
	public List<User> getUsers() throws Exception;
	public String deleteUser(String username) throws Exception;
}
