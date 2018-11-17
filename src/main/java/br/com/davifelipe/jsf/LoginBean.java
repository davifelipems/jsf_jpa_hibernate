package br.com.davifelipe.jsf;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.davifelipe.dao.UserDao;
import br.com.davifelipe.model.UserEnt;

@ManagedBean
public class LoginBean extends GenericBean<UserEnt>{
	
	private UserEnt loggedUser;
	
	public LoginBean() {
		this.dao = new UserDao();
		this.model = new UserEnt();
	}
	
	public String logOut(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		externalContext.getSessionMap().clear();
		
		return "login.jsf";
	}
	
	public String login(){
		List<UserEnt> list = ((UserDao) dao).findByUsernamePassword(this.model.getName(),DigestUtils.shaHex(this.model.getPassword()));
		
		if(list.size() > 0){
			this.loggedUser = list.get(0);
			this.model.setPassword("");
			
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			externalContext.getSessionMap().put("loggedUser", this.model);
			
			return "index.jsf";
		}else{
			this.showMessage("Username or password incorrect!");	
		}
		return "";
	}
	
	public UserEnt getLoggedUser() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		UserEnt loggedUser = (UserEnt) externalContext.getSessionMap().get("loggedUser");
		
		return loggedUser;
	}

	public void setLoggedUser(UserEnt loggedUser) {
		this.loggedUser = loggedUser;
	}

	
	
	@Override
	public void clearForm() {
		this.model = new UserEnt();
		
	}

	@Override
	public void setModel(UserEnt model) {
		this.model = model;
		
	}

	@Override
	public UserEnt getModel() {
		
		return this.model;
	}
	
}
