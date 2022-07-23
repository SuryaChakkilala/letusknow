package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.entity.User;

@Stateless
@TransactionManagement(value=TransactionManagementType.BEAN)
public class UserModel implements UserRemote {

	@Override
	public String registerUser(User u) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("letusknow");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
		em.close();
		emf.close();
		return "record inserted successfully";
	}

	@Override
	public boolean validateUserCredentials(String username, String password) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("letusknow");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		User u = em.find(User.class, username);
		return u.getPassword().equals(password);
	}
	
	public User getUser(String username) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("letusknow");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		User u = em.find(User.class, username);
		em.getTransaction().commit();
		em.close();
		emf.close();
		return u;
	}
	
	public List<User> getUsers() throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("letusknow");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<User> list = em.createQuery("from User").getResultList();
		em.getTransaction().commit();
		em.close();
		emf.close();
		return list;
	}
	
	public String deleteUser(String uname) throws Exception {
		System.out.println("E");
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "SuryaTeja@9192");
		PreparedStatement st = con.prepareStatement("delete from users where username=?");
		st.setString(1, uname);
		st.execute();
		return uname + " deleted successfully";
	}
	
}
