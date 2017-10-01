package com.rent.view;

import java.io.Serializable;

public class UserDetails implements Serializable {


	private static final long serialVersionUID = -8196581876425313911L;
	
	private String userId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String password;

    public UserDetails() {
    	userId = "";
    	firstName = "";
    	lastName = "";
    	phoneNumber = "";
    	address = "";
    	setPassword("");
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
