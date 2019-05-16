package io.connection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;

public class HiloCliente extends Thread {
	
	private Socket socket;
	private ServidorPract server;
	private int contador = 0;
	
	public HiloCliente(Socket socket, ServidorPract server) {
		
		this.socket = socket;
		this.server = server;
	}
	
	public void run() {

			handleRequest(this.socket);
	}
	
	private void handleRequest(Socket socket2) {
		
		
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String headerLine = in.readLine();
			// A tokenizer is a process that splits text into a series of tokens
			StringTokenizer tokenizer =  new StringTokenizer(headerLine);
			//The nextToken method will return the next available token
			String httpMethod = tokenizer.nextToken();
			// The next code sequence handles the GET method. A message is displayed on the
			// server side to indicate that a GET method is being processed
			if(httpMethod.equals("GET"))
			{
				
				System.out.println("Get method processed 1");
				String httpQueryString = tokenizer.nextToken();
				System.out.println(httpQueryString + " Esta es la queryString " + contador);
				if(httpQueryString.equals("/"))
				{
					contador++;
					System.out.println(contador + "Este es el contador");
					StringBuilder responseBuffer =  new StringBuilder();
					String str="";
					BufferedReader buf = new BufferedReader(new FileReader("web/Formulario.html"));			
					while ((str = buf.readLine()) != null) {
						responseBuffer.append(str);
				    }
					sendResponse(socket, 200, responseBuffer.toString());					
				    buf.close();
				}
				//permite obtener el dato ingresado en el submit
				if(httpQueryString.contains("/?mail="))
				{
					System.out.println("Get method processed 2");
					String[] response =  httpQueryString.split("=");
					String abc = response[1];
					String aaa[] = abc.split("&");
					String aaax2 = aaa[0];
					String cdee[] = aaax2.split("%40");
					String correo = cdee[0] + "@" + cdee[1];
					String cde = response[2];
//					String abc2[] = cde.split("&");
//					String pass = abc2[0];
//					System.out.println(correo + " " + pass);
					boolean res = server.comprobar(correo, cde);
					
					
					
					if(res == true) {
						
						String info = server.leerPartidas(correo);
						System.out.println(info);
						String[] partidas = info.split("¿");
						StringBuilder responseBuffer =  new StringBuilder();
						responseBuffer
						.append("<html>")
						.append("<head>")
						.append("<style>")
						.append("body{")
						.append("font-family: Arial;")
						.append("	background-image: url(\"https://i1.wp.com/wallpapersite.com/images/wallpapers/pikachu-playboy-1920x1080-pokeman-minimal-hd-4k-10475.jpg\");")
						.append("color: white")
						.append("}")
						.append("</style>")
						.append("<title>Statics</title>")
						.append("</head>")
						.append("<body>")
						.append("<br>")
						.append("<h1 align=center>")
						.append("Statics")
						.append("</h1>")
						.append("<br>")
						.append("<br>")
						.append("<br>")
						.append("<table border=2 align=center> ")
						.append("<tr>")
						.append("<td>")
						.append("Username")
						.append("</td>")
						.append("<td>")
						.append("Score")
						.append("</td>")
						.append("<td>")
						.append("date")
						.append("</td>")
						.append("<td>")
						.append("Winner?")
						.append("</td>")
						.append("<td>")
						.append("Players")
						.append("</td>")
						.append("</tr>");
						for(int i=0; i<partidas.length; i++) {
							
							String[] infoUsu = partidas[i].split(";");
							
							responseBuffer.append("<tr>")
							.append("<tr>")
							.append("<td>")
							.append(infoUsu[2])
							.append("</td>")
							.append("<td>")
							.append(infoUsu[3])
							.append("</td>")
							.append("<td>")
							.append(infoUsu[4])
							.append("</td>")
							.append("<td>");
							
							
							
							if(infoUsu[5].equals("Y@")) {
								
								
								responseBuffer.append("Yes! :D")
								.append("</td>");
								
							} else {
								
								responseBuffer.append("No :(")
								.append("</td>");
								
							}
							
							responseBuffer.append("<td>")
							.append(infoUsu[6])
							.append("</td>");
							
						}
						
						
						responseBuffer.append("</table>")
						.append("</body>")
						.append("</html>");
						
						sendResponse(socket, 200, responseBuffer.toString());		
					    
					} else {
						
						StringBuilder responseBuffer =  new StringBuilder();
						responseBuffer
						.append("<html>")
						.append("<head>")
						.append("<style>")
						.append("body{")
						.append("font-family: Arial;")
						.append("	background-image: url(\"http://www.larutadelsorigens.cat/filelook/full/57/575232/aqua-blue-wallpaper.jpg\");")
						.append("color: white")
						.append("}")
						.append("</style>")
						.append("<title>Statics</title>")
						.append("</head>")
						.append("<body>")
						.append("<br>")
						.append("<h1 align=center>")	
						.append("Error. Email o contraseña incorrectos :(")	
						.append("</h1>")		
						.append("<br>")
						.append("<br>")
						.append("<div align=center>")
						.append("<img src= \" https://ih0.redbubble.net/image.492975046.7150/flat,550x550,075,f.u1.jpg\" >")
						.append("</div>")
						.append("</body>")
						.append("</html>");
						
						sendResponse(socket, 200, responseBuffer.toString());	
						
						
					}
					
				}
			}
			else
			{
				System.out.println("The HTTP method is not recognized");
				sendResponse(socket, 405, "Method Not Allowed");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	
	public void sendResponse(Socket socket, int statusCode, String responseString)
	{
		String statusLine;
		String serverHeader = "Server: WebServer\r\n";
		String contentTypeHeader = "Content-Type: text/html\r\n";
		
		try {
			DataOutputStream out =  new DataOutputStream(socket.getOutputStream());
			if (statusCode == 200) 
			{
				statusLine = "HTTP/1.0 200 OK" + "\r\n";
				String contentLengthHeader = "Content-Length: "
				+ responseString.length() + "\r\n";
				out.writeBytes(statusLine);
				out.writeBytes(serverHeader);
				out.writeBytes(contentTypeHeader);
				out.writeBytes(contentLengthHeader);
				out.writeBytes("\r\n");
				out.writeBytes(responseString);
				} 
			else if (statusCode == 405) 
			{
				statusLine = "HTTP/1.0 405 Method Not Allowed" + "\r\n";
				out.writeBytes(statusLine);
				out.writeBytes("\r\n");
			} 
			else 
			{
				statusLine = "HTTP/1.0 404 Not Found" + "\r\n";
				out.writeBytes(statusLine);
				out.writeBytes("\r\n");
			}
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}

