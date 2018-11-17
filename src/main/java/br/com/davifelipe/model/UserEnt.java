package br.com.davifelipe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import br.com.davifelipe.enums.Profile;

@Entity
public class UserEnt implements EntityInterface<Integer>{
	
	@Id
	@GeneratedValue(generator="user_id_seq")
	@SequenceGenerator(name="user_id_seq",sequenceName="user_id_seq",allocationSize=1)
	private Integer id;
	
	@NotEmpty(message="Name can not be empty")
	@Size(min=4,max=50, message="Name should have between 4 and 50 characters")
	@Column(unique = true)
	private String name;
	
	private String password;
	
	@Column(nullable=false)
	@Range(min=1,max=2)
	@NotNull(message="Profile can not be empty")
	private Integer profile;
	
	@Transient
	private Profile profileEnum;
	
	public Integer getProfile() {
		return profile;
	}

	public void setProfile(Integer profile) {
		this.profile = profile;
	}

	public Profile getProfileEnum() {
		return profileEnum;
	}

	public void setProfileEnum(Profile profileEnum) {
		this.profileEnum = profileEnum;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
