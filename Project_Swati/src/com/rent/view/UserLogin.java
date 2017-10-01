package com.rent.view;

import java.io.Serializable;

public class UserLogin implements Serializable {

	private static final long serialVersionUID = 6738729708988223104L;
	
	private String userId;
    private String password;

    public UserLogin() {
    	userId = "";
    	password = "";
    }

	public UserLogin(String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
  
}
