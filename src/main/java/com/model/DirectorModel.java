package com.model;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.entity.Director;

@Stateless
@TransactionManagement(value=TransactionManagementType.BEAN)
public class DirectorModel implements DirectorRemote {

	@Override
	public boolean validateCredentials(String username, String password) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("letusknow");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Director d = em.find(Director.class, username);
		em.getTransaction().commit();
		em.close();
		emf.close();
		if(d.getPassword().equals(password)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Director> getAllDirectors() throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("letusknow");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<Director> list = em.createQuery("from Director").getResultList();
		em.getTransaction().commit();
		em.close();
		emf.close();
		return list;
	}

	@Override
	public String deleteDirector(String username) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("letusknow");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Director d = em.find(Director.class, username);
		em.remove(d);
		em.getTransaction().commit();
		em.close();
		emf.close();
		return username + " successfully deleted";
	}

}
