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
		T entityReturn = null;
		try {
			
			entityManager.getTransaction().begin();
			entityReturn = entityManager.merge(entity);
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return entityReturn;
	}
	
	public T getById(final PK id){
		T t = null;
		
		try {
			entityManager.getTransaction().begin();
			t = entityManager.find(entityClass, id);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return t;
	}
	
	public void delete(final T entity){
		
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll(){
		entityManager.getTransaction().begin();
		List<T> entityReturn = entityManager.createQuery("FROM "+entityClass.getName()).getResultList();
		entityManager.getTransaction().commit();
		return entityReturn;
	}
	
	public Session getSession(){
		return (Session) entityManager.getDelegate();
	}
	
	
}
