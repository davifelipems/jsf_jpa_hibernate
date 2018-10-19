package br.com.davifelipe.jsf;

import javax.faces.bean.ManagedBean;

import br.com.davifelipe.dao.CustomerDao;
import br.com.davifelipe.model.Customer;

@ManagedBean
public class CustomerBean {
	
	private Customer customer = new Customer();
	private CustomerDao dao = new CustomerDao();
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
