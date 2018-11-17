package br.com.davifelipe.jsf;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.davifelipe.dao.UserDao;
import br.com.davifelipe.enums.Profile;
import br.com.davifelipe.model.UserEnt;

@ManagedBean
public class UserBean extends GenericBean<UserEnt>{
	
	@SuppressWarnings("unused")
	private Integer adminCode;
	@SuppressWarnings("unused")
	private Integer userCode;
	
	public Integer getAdminCode() {
		return Profile.Admin.getCode();
	}

	public Integer getUserCode() {
		return Profile.User.getCode();
	}

	public UserBean() {
		this.dao = new UserDao();
		this.model = new UserEnt();
		this.listOfElements = new ArrayList<UserEnt>();
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
	
	public String save(){
		
		if(this.model.getId() == null
	    && this.model.getPassword().isEmpty()){
			this.showMessage("Password can't be empty!");
		}else{
			if(this.model.getPassword().isEmpty()
		    && this.model.getId() != null){
				UserEnt user = this.dao.getById(this.model.getId());
				this.model.setPassword(user.getPassword());
			}else{
				this.model.setPassword(DigestUtils.shaHex(this.model.getPassword()));
			}
			this.dao.save(this.model);
			this.loadList();
			this.model.setPassword("");
			this.showMessage("Record saved!");
		}
		
		return "";
	}
	
	public void clearPassword(){
		this.model.setPassword("");
	}
	
}
