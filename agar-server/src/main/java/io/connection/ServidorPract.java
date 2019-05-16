package io.connection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.net.ServerSocket;
import java.util.ArrayList;


public class ServidorPract {
	
	public boolean webService;
	public ServerSocket serverSocketWebService;
	private HiloDespliegue hiloDespliegueAppWeb;
	public static final int PORT_WEB_SERVICE = 7000;
	private ArrayList<Persona> lista;
	
	
	public ServidorPract() throws InterruptedException, IOException {
		
		
		webService = true;
		try {
			serverSocketWebService = new ServerSocket(PORT_WEB_SERVICE);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
		hiloDespliegueAppWeb = new HiloDespliegue(this);
		hiloDespliegueAppWeb.start();
		lista = new ArrayList<Persona>();
		
	}
	
	public ServerSocket getServerSocketWebService() {
		return serverSocketWebService;
	}
	
	
	public void leerUsuarios() {
	
		File archivo = null;
	      FileReader fr = null;
	      BufferedReader br = null;

	      try {
	         
	         archivo = new File ("../data/usuarios.txt");
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);

	         
	         String linea;
	         while((linea=br.readLine())!=null) {
//	            String info[] = linea.split("\n");
	            
//	            for(int i = 0; i<info.length;i++) {
	            	
	            	
//	            	System.out.println(info[i]);
	            	String data[] = linea.split(";");     	
	            	Persona usu = new Persona(data[0], data[1]);
	            	lista.add(usu);
	            	
//	            }
	         }  
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	        
	    	  
	         try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
	
	
	}
	
	public boolean comprobar(String email, String pass) {
		
		leerUsuarios();
		boolean respuesta = false;
		for(int i=0; i<lista.size(); i++) {
			
			if(email.equals(lista.get(i).getEmail()) && pass.equals(lista.get(i).getPass())){
				
				respuesta = true;
				
			}
			
		}
		
		return respuesta;
		
	}

	public String leerPartidas(String correo) {
		
		File archivo = null;
	      FileReader fr = null;
	      BufferedReader br = null;
	      String todo = "";
	      String nombres = ";";
	      String todo2 = "";
	      boolean correcto = false;
	      
	      try {
	         
	         archivo = new File ("../data/partidas.txt");
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);

	         
	         String linea;
	         String linea2;
	         
	         while((linea=br.readLine())!=null) {
	        	 
	        	if(linea.equals("PP")) {
	        		
	        		linea2 = br.readLine();
	        		
	        		while(linea2!=null) {
	        			
	        			nombres = ";";
	        			correcto = false;
	        			
	        			while(linea2 != null && !linea2.equals("PP")) {
	        				
	        				if(linea2.contains(correo)) {
	        					
	        					todo = linea2;
	        					correcto = true;
	        					
	        				} else {
	        					
	        					String[] info = linea2.split(";");
	        					nombres += info[2] + ","; 
	        					
	        				}
	        				
	        				linea2 = br.readLine();
	        				
	        			}
	        			
	        			linea2 = br.readLine();
	        			if(correcto) {
	        				
	        				todo2 += todo + nombres + "¿";
	        				
	        			}
	        			//no puede ir nadaaa
	        		}
	        		
	        		
	        	}
	        	 
	         }
	         
	         
//	         String linea2 = br.readLine();
//	         boolean contiene = false;
//	         while((linea=br.readLine())!=null) {
//	        	 
//	        	 while(!linea.equals("P")) {
//	        		   
//	        		   
//	        		   linea = br.readLine();
//	        	   }
//	        	 
//	        	 
//
//	           if(linea.equals("P")) {
//	        	   
//	        	   linea= br.readLine();
//	        	   linea2=br.readLine();
//
//	        	   if(linea2 != null) {
//	        		   
//	        		   while(!linea2.equals("P") && linea2 != null && contiene == false) {
//		        		   
//		        		   contiene = linea2.contains(correo);
//		        		   linea2 = br.readLine();
//		        		   
//		        	   }
//		        	   
//		        	   if(contiene) {
//		        		   
//		        		   while(!linea.equals("P") && linea != null) {  
//		        			   
//		        			   if(linea.contains(correo)) {
//		        				   
//		        				   todo = linea;
//		        				   
//		        			   } else {
//		        				   
//		        				   String[] a = linea.split(";");
//			        			   nombres += a[2] + ",";
//		        				   
//		        			   }
//		        			   
//		        			   br.readLine();
//		        			   
//		        		   }
//		        		   
//		        		   todo2 += todo + nombres + "¿";
//		        		   
//		        	   }
//	        		   
//	        	   }
	        	   
	        	   
	        	   
	        	   
	        	   
//	        	   while(!linea.equals("P") && linea != null) {
//	        		   
//	        		   if(linea.contains(correo)) {
//	        			   
//	        			   todo = linea;
//	        			   
//	        		   } else {
//	        			   
//	        			   String[] a = linea.split(";");
//	        			   nombres += a[2] + ",";
//	        			   
//	        		   }
//	        		   
//	        		   linea = br.readLine();
//	        		   
//	        	   }
//	        	   
//	        	   todo2 += todo + nombres + "¿";
//	        	   
//	           }
//  	
//	         }  
	         
	       
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	        
	    	  
	         try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
	
	      System.out.println("Todo2   " + todo2);
	      return todo2;
	}
	
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		ServidorPract serv = new ServidorPract();
		
	}

}

