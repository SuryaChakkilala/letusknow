package com.model;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.entity.Grieviance;

@Stateless
@TransactionManagement(value=TransactionManagementType.BEAN)
public class GrievianceModel implements GrievianceRemote {

	@Override
	public String insertGrieviance(Grieviance g) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("letusknow");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(g);
		em.getTransaction().commit();
		em.close();
		emf.close();
		return "record inserted successfully";
	}

	@Override
	public List<Grieviance> getGrieviances() throws Exception {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("letusknow");
	    EntityManager entityManager = entityManagerFactory.createEntityManager();
	    
	    
	    entityManager.getTransaction().begin();
	    Query qry=entityManager.createQuery("from Grieviance");
	    @SuppressWarnings("unchecked")
	    List<Grieviance> emplist=qry.getResultList();
	    entityManager.getTransaction().commit();
	    
	    entityManager.close();
	    entityManagerFactory.close();
	    
	    return emplist;
		
	}

	@Override
	public String removeGrieviance(int id) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("letusknow");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Grieviance d = em.find(Grieviance.class,id);
		em.remove(d);
		em.getTransaction().commit();
		em.close();
		emf.close();
		return "Successfully deleted";
	}
	
}
