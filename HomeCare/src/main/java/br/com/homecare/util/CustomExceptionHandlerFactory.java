package br.com.homecare.util;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class CustomExceptionHandlerFactory extends ExceptionHandlerFactory {

    private ExceptionHandlerFactory base;
    
    public CustomExceptionHandlerFactory(ExceptionHandlerFactory base) {
        this.base = base;
    }
    
    @Override
    public ExceptionHandler getExceptionHandler() {
        return new CustomExceptionHandler(base.getExceptionHandler());
    }
}
