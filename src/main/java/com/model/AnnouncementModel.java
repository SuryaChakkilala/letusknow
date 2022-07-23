package com.model;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.entity.Announcement;

@Stateless
@TransactionManagement(value=TransactionManagementType.BEAN)
public class AnnouncementModel implements AnnouncementRemote {

	@Override
	public String addAnnouncement(Announcement a) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("letusknow");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(a);
		em.getTransaction().commit();
		em.close();
		emf.close();
		return "record inserted successfully";
	}

	@Override
	public List<Announcement> getAnnouncements() throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("letusknow");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<Announcement> list = em.createQuery("from Announcement").getResultList();
		em.getTransaction().commit();
		em.close();
		emf.close();
		return list;
	}
	
}
