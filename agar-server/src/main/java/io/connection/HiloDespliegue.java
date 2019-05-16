package io.connection;
import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;




public class HiloDespliegue extends Thread{
	
ServidorPract server;
	
	public HiloDespliegue(ServidorPract server) {
		
		this.server = server;
	}
	
	public void run() {
		
		while(server.webService) {
			System.out.println(":::Web Server Started:::");
			ServerSocket serverSocket = server.getServerSocketWebService();
			try {
				Socket cliente = serverSocket.accept();
				HiloCliente hilo = new HiloCliente(cliente, server);
				hilo.start();	
				
			} catch (IOException e) {
				System.out.println("Exception in HiloDespliegueAppWeb");
			}
			
		}
		
	}

}
