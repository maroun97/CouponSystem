package com.cmw.services;

public class User {
public String email;
public String LOGIN="false";
public String type;
public User(String email,String type) {
	this.LOGIN="true";
	this.email=email;
	this.type=type;
}
}
