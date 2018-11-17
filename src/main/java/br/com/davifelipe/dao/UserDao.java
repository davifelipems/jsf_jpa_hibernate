package br.com.davifelipe.dao;

import java.util.List;

import br.com.davifelipe.model.UserEnt;

public class UserDao extends GenericDao<UserEnt, Integer>{
	
	public List<UserEnt> findByUsernamePassword(String userName,String password){
		
		this.entityManager.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<UserEnt> result = entityManager
							   .createQuery("FROM "+entityClass.getName()+
									   " WHERE name = :userName AND password = :password")
									   .setParameter("userName", userName)
									   .setParameter("password", password)
									   .getResultList();
		
		entityManager.getTransaction().commit();
		
		return result;
	}
	
}
