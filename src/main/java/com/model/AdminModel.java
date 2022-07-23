package com.model;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.entity.Admin;

@Stateless
@TransactionManagement(value=TransactionManagementType.BEAN)
public class AdminModel implements AdminRemote {

	@Override
	public boolean validateCredentials(String username, String password) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("letusknow");
		EntityManager em = emf.createEntityManager();
		Admin a = em.find(Admin.class, username);
		return (a.getPassword().equals(password));
	}
	
	
}
