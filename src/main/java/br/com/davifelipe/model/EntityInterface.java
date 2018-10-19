package br.com.davifelipe.model;

import java.io.Serializable;

public interface EntityInterface<T extends Serializable> {

	Integer getId();
	
	void setId(T id);
}
