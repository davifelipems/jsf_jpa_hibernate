package br.com.davifelipe.enums;

public enum Profile {
	Admin(1),
	User(2);
	
	private Integer code;
	
	Profile(Integer code){
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}
	
}
