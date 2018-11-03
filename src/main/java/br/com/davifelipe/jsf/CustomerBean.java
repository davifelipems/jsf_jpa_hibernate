package br.com.davifelipe.jsf;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.davifelipe.dao.CustomerDao;
import br.com.davifelipe.model.Customer;

@ManagedBean
public class CustomerBean {
	
	private Customer customer = new Customer();
	private CustomerDao dao = new CustomerDao();
	private List<Customer> customers = new ArrayList<Customer>();
	
	@PostConstruct
	public void loadCustomers(){
		this.customers = dao.findAll();
	}
	
	public String removeRecord(){
		
		this.dao.delete(this.customer);
		this.loadCustomers();
		this.customer = new Customer();
		this.showMessage("Record deleted!");
		return "";
	}
	
	public List<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
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
		this.loadCustomers();
		this.showMessage("Record saved!");
		return "";
	}
	
	public String newRecord(){
		this.customer = new Customer();
		return "";
	}
	
	public void showMessage(String msg){
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage("Notice",msg);
		context.addMessage(null, message);
	}
	
}
