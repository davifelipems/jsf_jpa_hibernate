package br.com.davifelipe.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class Util {

	private  EntityManagerFactory factory;
	
	public Util(){
		if(factory == null){
			factory = Persistence.createEntityManagerFactory("default");
		}
	}
	
	@Produces
	@RequestScoped
	public  EntityManager getEntityManager(){
		return factory.createEntityManager();
	}
}
