package br.com.davifelipe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Customer implements EntityInterface<Integer>{
	
	@Id
	@GeneratedValue(generator="customer_id_seq")
	@SequenceGenerator(name="customer_id_seq",sequenceName="customer_id_seq",allocationSize=1)
	private Integer id;
	
	@NotEmpty(message="First Name can not be empty")
	@Size(min=10,max=50, message="First Name should have between 10 and 50 characters")
	private String firstName;
	
	@NotEmpty(message="Last Name can not be empty")
	@Size(min=10,max=50, message="Last Name should have between 10 and 50 characters")
	private String lastName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
