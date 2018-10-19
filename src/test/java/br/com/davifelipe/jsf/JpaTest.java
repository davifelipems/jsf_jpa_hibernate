package br.com.davifelipe.jsf;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class JpaTest {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
		

	}

}
