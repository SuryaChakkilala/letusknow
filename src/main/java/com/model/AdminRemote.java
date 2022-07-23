package com.model;

import javax.ejb.Remote;

@Remote
public interface AdminRemote {
	public boolean validateCredentials(String username, String password) throws Exception;
}
