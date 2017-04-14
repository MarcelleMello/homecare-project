package br.com.homecare.service;

import java.io.Serializable;


public class ServiceException extends Exception implements Serializable{

	private static final long serialVersionUID = 2136499202222352266L;
	
	public ServiceException(String msg){
		super(msg);
	}
	
	

}
