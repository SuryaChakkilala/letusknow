package com.model;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.entity.Reply;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ReplyModel implements ReplyRemote {

	@Override
	public String addReply(Reply r) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("letusknow");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(r);
		em.getTransaction().commit();
		em.close();
		emf.close();
		return "Record inserted successfully";
	}

	@Override
	public List<Reply> getReplies() throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("letusknow");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<Reply> list = em.createQuery("from Reply").getResultList();
		em.getTransaction().commit();
		em.close();
		emf.close();
		return list;
	}

	@Override
	public String deleteReply(int id) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("letusknow");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Reply r = em.find(Reply.class,id);
		em.remove(r);
		em.getTransaction().commit();
		em.close();
		emf.close();
		return "Successfully deleted";
	}

}
