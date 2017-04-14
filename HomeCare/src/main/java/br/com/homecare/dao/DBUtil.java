package br.com.homecare.dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBUtil {
	
	private static Connection con;
	
	/**
	 * Obtem uma conexao
	 */
	public static Connection getConnection() {
        
		try {
        	InitialContext ic = new InitialContext();
       	DataSource ds = (DataSource) ic.lookup("java:/comp/env/jdbc/PadraoJDBC");
    		con = ds.getConnection();
       } catch (Exception ex) {
        	System.out.println("Falha ao na criacao da conexao com a base de dados."); 
        	ex.printStackTrace();
        }
		
        return con;
    }
	    
	    /**
	     * Inicia uma transacao
	     */
	    public static void beginTransaction(Connection con){ 
	        if(con!=null){ 
	            try { 
	                con.setAutoCommit(false); 
	            } catch (SQLException ex) { 
	                ex.printStackTrace(); 
	            } 
	        } 
	    } 
	    
	    /**
	     * Comita uma transacao 
	     */
	    public static void commit(Connection con){ 
	        if(con!=null){ 
	            try { 
	                con.commit(); 
	            } catch (SQLException ex) { 
	                ex.printStackTrace(); 
	            } 
	        } 
	    } 
	    
	    /**
	     * Desfaz uma transacao
	     */
	    public static void rollback(Connection con){ 
	        if(con!=null){ 
	            try { 
	                con.rollback(); 
	            } catch (SQLException ex) { 
	                ex.printStackTrace(); 
	            } 
	        } 
	    } 
	    
	    /**
	     * Realiza o rollback e fecha a conexao
	     */
	    public static void rollbackAndCloseConnection(Connection con){ 
	        if(con!=null){ 
	            try { 
	                con.rollback(); 
	                con.close();
	            } catch (SQLException ex) { 
	                ex.printStackTrace(); 
	            } 
	        } 
	    } 
	    
	    /**
	     * Realiza o commit e fecha uma conexao
	     */
	    public static void commitAndCloseConnection(Connection con) {
	    	if(con!=null){ 
	            try { 
		            con.commit(); 
	                con.close(); 
	            } catch (SQLException ex) { 
	                ex.printStackTrace(); 
	            } 
	        } 
	    }
	    
	    /**
	     * Fecha a conexao
	     */
	    public static void closeConnection(Connection con) {
	    	if(con!=null){ 
	            try { 
	                con.close(); 
	            } catch (SQLException ex) { 
	                ex.printStackTrace(); 
	            } 
	        } 
	    }
	    
	  
	    
}