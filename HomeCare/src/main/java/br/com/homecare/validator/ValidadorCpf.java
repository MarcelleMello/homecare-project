package br.com.homecare.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.homecare.util.Util;

@FacesValidator("ValidadorCpf")
public class ValidadorCpf implements Validator {
	
	@Override
	public void validate(FacesContext fc, UIComponent ui, Object valor) throws ValidatorException {
		
		String message = "";
		
		if(valor != null && !"".equals(valor)) {
			
			String cpf = valor.toString();

			if(  	cpf.equals("777.777.777-77") || 
					cpf.equals("999.999.999-99") || 
					cpf.equals("888.888.888-88") || 
					cpf.equals("666.666.666-66") ||
					cpf.equals("555.555.555-55") || 
					cpf.equals("444.444.444-44") || 
					cpf.equals("333.333.333-33") || 
					cpf.equals("222.222.222-22") ||
					cpf.equals("111.111.111-11") || 
					cpf.equals("000.000.000-00") || 
					cpf.equals("012.345.678-90") ) 
			{
		        		 
						message = "CPF Inv�lido.";
		        	   	FacesMessage msg = new FacesMessage(message);
		        	   	msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		   				throw new ValidatorException(msg);
		        	   
		    }
			
			
			if(!this.valido(valor.toString())) {
				message = "CPF Inv�lido.";
				FacesMessage msg = new FacesMessage(message);
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
				
			}
		}
	}
	
	
	private boolean valido(String strCpf) {
		
	    int iDigito1Aux = 0, iDigito2Aux = 0, iDigitoCPF;
	    int iDigito1 = 0, iDigito2 = 0, iRestoDivisao = 0;
	    String strDigitoVerificador, strDigitoResultado;
	 
	    if (! strCpf.substring(0,1).equals("")){
	        
	    	try{
	           
	    		strCpf = Util.retirarMascara(strCpf);
	            
	            for (int iCont = 1; iCont < strCpf.length() -1; iCont++) {
	            	iDigitoCPF = Integer.valueOf(strCpf.substring(iCont -1, iCont)).intValue();
	                iDigito1Aux = iDigito1Aux + (11 - iCont) * iDigitoCPF;
	                iDigito2Aux = iDigito2Aux + (12 - iCont) * iDigitoCPF;
	            }
	            
	            iRestoDivisao = (iDigito1Aux % 11);
	            
	            if (iRestoDivisao < 2) {
	            	iDigito1 = 0;
	            } else {
	            	iDigito1 = 11 - iRestoDivisao;
	            }
	            
	            iDigito2Aux += 2 * iDigito1;
	            iRestoDivisao = (iDigito2Aux % 11);
	            
	            if (iRestoDivisao < 2) {
	            	iDigito2 = 0;
	            } else {
	            	iDigito2 = 11 - iRestoDivisao;
	            }
	           
	            strDigitoVerificador = strCpf.substring(strCpf.length()-2, strCpf.length());        
	            strDigitoResultado = String.valueOf(iDigito1) + String.valueOf(iDigito2);
	            
	            return strDigitoVerificador.equals(strDigitoResultado);
	            
	        } catch (Exception e) {
	            return false;
	        }
	    	
	    } else {
	        return false;
	    }
		
	}
}
