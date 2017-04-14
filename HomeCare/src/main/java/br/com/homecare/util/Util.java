package br.com.homecare.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Util {
	
	public static String retirarMascara(String valor) {
		
		valor = valor.replace('.',' ');  
        valor = valor.replace('/',' ');  
        valor = valor.replace('-',' ');  
        valor = valor.replaceAll(" ",""); 
		
		return valor;
	}

	public static String gerarSenhaAleatoria(Integer tamanho) {
		
		char[] caracteres = "23456789abcdefghjmnpqrstuvxz".toCharArray();  
		List<Character> ocharaters  = new ArrayList<Character>(caracteres.length);  
		
		for (char c:  caracteres ){  
		  ocharaters.add(c); 
		}  
		Collections.shuffle(ocharaters);  
	
		for ( int i =0; i < caracteres.length ; i++ ){  
		     caracteres[i] = ocharaters.get(i); 
		}  
		String strEmbaralhada = new String(caracteres); 
		String srtLimitada = strEmbaralhada.substring(0, 6);
	
		return srtLimitada;
	}
	
	/**
	 * retorna verdadeido se o valor estiver no formato da mascara passada
	 * @param valor
	 * @param mascara
	 * @return
	 */
	public static boolean verificarFormato(String valor, String mascara) {
		boolean valido = false;	
		try {
			if (valor.matches(mascara)) {
			  valido = true;	
			} 
		} catch (Exception e) {
			
		}
		
		return valido;
	}
	
	public static byte[] converterFileParaByte(String caminho) {

		File file = new File(caminho);
		byte[] bFile = new byte[(int) file.length()];

	    try {
	    	FileInputStream fileInputStream = new FileInputStream(file);
	    	fileInputStream.read(bFile);
	    	fileInputStream.close();
	    	
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
	    return bFile;
	}
	
	public static void converterByteParaFile(String caminho, byte[] bArquivo) { //caminho + nome
		try {
			 FileOutputStream fos = new FileOutputStream(caminho); //armazena na pasta o arquivo
		     fos.write(bArquivo);
		     fos.close();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String obterAmbiente() {
		String nomeServidor = null;
		String ambienteServidor = null;
		try {
			InetAddress address = InetAddress.getLocalHost();
			nomeServidor = address.getHostName();
			
			if("WAPHDES17V".equals(nomeServidor)){
				ambienteServidor = "DESENVOLVIMENTO";
			} else if("WAPHHOM16V".equals(nomeServidor)){
				ambienteServidor = "HOMOLOGACAO";
			} else if("WAPHPRD10V".equals(nomeServidor)){
				ambienteServidor = "PRODUCAO";
			} else {
				ambienteServidor = "LOCALHOST";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ambienteServidor;
	}
	
	public static String obterUrl() {
		String url = null;
		String ambienteServidor = null;
		ambienteServidor = obterAmbiente();
		if("DESENVOLVIMENTO".equals(ambienteServidor)){
			url = "desenvolvimento.thiago10gr.com.br";
		} else if("HOMOLOGACAO".equals(ambienteServidor)){
			url = "homologacao.thiago10gr.com.br";
		} else if("PRODUCAO".equals(ambienteServidor)){
			url = "producao.thiago10gr.com.br";
		} else if("LOCALHOST".equals(ambienteServidor)){
			url = "localhost:8080/PadraoJDBC";
		}
		return url;
	}
	
	public static String obterNomeServidor() {
		String nomeServidor = null;
		try {
			InetAddress address = InetAddress.getLocalHost();
			nomeServidor = address.getHostName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nomeServidor;
	}
	
	public static String obterIp() {
		String ip = null;
		try {
			InetAddress address = InetAddress.getLocalHost();
			ip = address.getHostAddress();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ip;
	}
	
	public static String obterMineTypes(String fileName) {
		String mineType = null;
		
		if (fileName.endsWith(".xls")) {
			mineType = "application/vnd.ms-excel";
        } else if (fileName.endsWith(".xlsx")) {
        	mineType =  "application/vnd.openxml";
        } else if (fileName.endsWith(".docx")) {
        	mineType =  "application/vnd.openxmlf";
        } else if (fileName.endsWith(".doc")) {
        	mineType =  "application/msword";
        } else if (fileName.endsWith(".pdf")) {
        	mineType = "application/pdf";
        }
		return mineType;
	}
	
	
}
