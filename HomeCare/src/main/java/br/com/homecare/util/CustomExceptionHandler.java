package br.com.homecare.util;

import java.util.Iterator;
import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

public class CustomExceptionHandler extends ExceptionHandlerWrapper {

    private ExceptionHandler wrapped;

    public CustomExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return this.wrapped;
    }
    
    
    /**
     * quando a excecao viewExpiredException for lancada ele passara para o arquivo autenticacao (phaseListener) um parametro no escope de request informando que a sessao expirou
     * e la sera feito o redirecionamento com o alerta para o usuario refazer o login
     */
    @Override
    public void handle() throws FacesException {
        Iterable<ExceptionQueuedEvent> events = this.wrapped.getUnhandledExceptionQueuedEvents();
        for(Iterator<ExceptionQueuedEvent> it = events.iterator(); it.hasNext();) {
            ExceptionQueuedEvent event = it.next();
            ExceptionQueuedEventContext eqec = event.getContext();
            if(eqec.getException() instanceof ViewExpiredException) {          
                try {
                	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sessaoExpirou", true);  
                } finally {
                    it.remove();
                }
            }
        }

        this.wrapped.handle();;
    }
}
