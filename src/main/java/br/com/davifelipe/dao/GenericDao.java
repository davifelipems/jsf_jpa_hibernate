package br.com.davifelipe.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;

import br.com.davifelipe.util.Util;

public class GenericDao<T, PK extends Serializable> {

	private EntityManager entityManager;
	
	protected Class<T> entityClass;
	
	EntityManagerFactory factory;
	
	@SuppressWarnings("unchecked")
	public GenericDao(){
		
		entityManager = Util.getEntityManager();
		ParameterizedType genericSuperclass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		
		this.entityClass = ((Class<T>) genericSuperclass
				.getActualTypeArguments()[0]);
	}
	
	public T save(final T entity){
		try {
			
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
			entityManager.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return entity;
	}
	
	public T getById(final PK id){
		T t = null;
		
		try {
			t = entityManager.find(entityClass, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return t;
	}
	
	public void delete(final T entity){
		
		entityManager.remove(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll(){
		return entityManager.createQuery("FROM "+entityClass.getName()).getResultList();
	}
	
	public Session getSession(){
		return (Session) entityManager.getDelegate();
	}
	
	
}
