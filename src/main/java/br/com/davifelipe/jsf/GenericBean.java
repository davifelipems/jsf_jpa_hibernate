package br.com.davifelipe.jsf;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.davifelipe.dao.GenericDao;

abstract class GenericBean<T> {
	
	protected T model;
	protected GenericDao<T, Integer> dao;
	protected List<T> listOfElements;
	
	@PostConstruct
	public void loadList(){
		if(dao != null){
			this.listOfElements = dao.findAll();
		}
	}
	
	public abstract void clearForm();
	
	public abstract void setModel(T model);
	
	public abstract T getModel();
	
	public String removeRecord(){
		
		this.dao.delete(this.model);
		this.loadList();
		this.clearForm();
		this.showMessage("Record deleted!");
		return "";
	}
	
	public List<T> getListOfElements() {
		return this.listOfElements;
	}
	public void setListOfElements(List<T> list) {
		this.listOfElements = list;
	}
	
	public String save(){
		this.dao.save(this.model);
		this.loadList();
		this.showMessage("Record saved!");
		return "";
	}
	
	public String newRecord(){
		this.clearForm();
		return "";
	}
	
	public void showMessage(String msg){
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage("Notice",msg);
		context.addMessage(null, message);
	}
	
}
