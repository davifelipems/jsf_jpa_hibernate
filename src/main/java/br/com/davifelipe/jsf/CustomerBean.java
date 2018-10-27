package br.com.davifelipe.jsf;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.davifelipe.dao.CustomerDao;
import br.com.davifelipe.model.Customer;

@RequestScoped
@Named
public class CustomerBean {
	
	@Inject
	private Customer customer;
	@Inject
	private CustomerDao dao;
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public CustomerDao getDao() {
		return dao;
	}
	public void setDao(CustomerDao dao) {
		this.dao = dao;
	}
	
	public String save(){
		this.dao.save(this.customer);
		this.customer = new Customer();
		return "";
	}
	
}
