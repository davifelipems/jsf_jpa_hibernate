package br.com.davifelipe.jsf;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;

import br.com.davifelipe.dao.CustomerDao;
import br.com.davifelipe.model.Customer;

@ManagedBean
public class CustomerBean extends GenericBean<Customer>{
	
	public CustomerBean(){
		this.dao = new CustomerDao();
		this.model = new Customer();
		this.listOfElements = new ArrayList<Customer>();
	}
	
	public void clearForm(){
		this.model = new Customer();
	}
	
	public Customer getModel(){
		return this.model;
	}
	
	public void setModel(Customer model){
		this.model = model;
	}

	
	
}
