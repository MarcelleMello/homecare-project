package br.com.homecare.bean;

import java.io.Serializable;

import org.primefaces.context.RequestContext;

import br.com.homecare.util.JSFMessageUtil;

public class AbstractBean implements Serializable{
	
	private static final long serialVersionUID = 4777371977521805277L;
	
	private static final String KEEP_DIALOG_OPENED = "KEEP_DIALOG_OPENED";

	public AbstractBean() {
		super();
	}
	/**
	 * exibe uma mensagem de alerta/erro 
	 * @param message 
	 */
	protected void displayErrorMessage(String message) {
		JSFMessageUtil messageUtil = new JSFMessageUtil();
		messageUtil.sendErrorMessageToUser(message);
	}
	/**
	 * exibe uma mensagem de sucesso
	 * @param message
	 */
	protected void displayInfoMessage(String message) {
		JSFMessageUtil messageUtil = new JSFMessageUtil();
		messageUtil.sendInfoMessageToUser(message);
	}
	
	protected void closeDialog(){
		getRequestContext().addCallbackParam(KEEP_DIALOG_OPENED, false);
	}
	
	protected void keepDialogOpen(){
		getRequestContext().addCallbackParam(KEEP_DIALOG_OPENED, true);
	}
	
	protected RequestContext getRequestContext(){
		return RequestContext.getCurrentInstance();
	}
}