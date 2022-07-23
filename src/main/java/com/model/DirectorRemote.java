package com.model;

import java.util.List;

import javax.ejb.Remote;

import com.entity.Director;

@Remote
public interface DirectorRemote {
	public boolean validateCredentials(String username, String password) throws Exception;
	public List<Director> getAllDirectors() throws Exception;
	public String deleteDirector(String username);
}
