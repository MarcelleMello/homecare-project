package br.com.homecare.util;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletResponse;

public class LimpaCache implements PhaseListener {

	private static final long serialVersionUID = -189647446534290062L;

	@Override
	public void afterPhase(PhaseEvent arg0) {
		
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		FacesContext fc = event.getFacesContext();
		HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
		response.addHeader("Pragma", "no-cache");
		response.addHeader("Cache-Control", "no-cache");
		response.addHeader("Cache-Control", "no-store");
		response.addHeader("Cache-Control", "must-revalidate");
		response.addHeader("Expires", "0");
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}
}
