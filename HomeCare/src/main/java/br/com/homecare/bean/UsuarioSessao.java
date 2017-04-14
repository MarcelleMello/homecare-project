package br.com.homecare.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@SessionScoped
@ManagedBean(name="usuarioSessao")
public class UsuarioSessao extends AbstractBean implements Serializable{

	private static final long serialVersionUID = -7842545553301610129L;
	
	
	public String logOut() {
		getRequest().getSession().invalidate();
		return "login";
	}
	
	
	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}


	
}
