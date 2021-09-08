package edu.eci.HttpWeatherServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import edu.eci.conection.AlphaHttpConectionWeather;

public class HttpServerWeatherServer {
	
	private static final HttpServerWeatherServer instance=new HttpServerWeatherServer();
	private final AlphaHttpConectionWeather appWeather;
	public static HttpServerWeatherServer getInstance() {
		
		return instance;
	}
	
	private HttpServerWeatherServer() {
		appWeather=AlphaHttpConectionWeather.getInstance();
	}
	public void start(Integer puerto) throws IOException, URISyntaxException {
		
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(puerto);
		} catch (IOException e) {
			System.err.println("Could not listen on port:."+ puerto);
			System.exit(1);
		}
		boolean running=true;
		while (running){
			Socket clientSocket = null;
			try {
				System.out.println("Listo para recibir ...");
				clientSocket = serverSocket.accept();
			} catch (IOException e) {
				System.err.println("Accept failed.");
				System.exit(1);
			}
			serveConnection(clientSocket);
		}
		serverSocket.close();
	}
	
public void serveConnection(Socket clientSocket) throws IOException, URISyntaxException {
		
		OutputStream outStream=clientSocket.getOutputStream();
		PrintWriter out = new PrintWriter(outStream, true);
		InputStream inputStream=clientSocket.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
		String inputLine, outputLine;
		ArrayList<String> request=new ArrayList<String>();
		
		while ((inputLine = in.readLine()) != null) {
			System.out.println("Received: " + inputLine);
			request.add(inputLine);
			if (!in.ready()) {
				break;
			}
		}
		String uriStr="";
		
		try {
			
			uriStr=request.get(0).split(" ")[1];
		
			URI resourceURI = new URI(uriStr);
			String query=resourceURI.getQuery();
			buscarQuery(query);
			
			
			System.out.println(query);
			
		}catch(Exception e) {
			
		}
			
		out.close();
		in.close();
		clientSocket.close();
		
	}

private void buscarQuery(String query) throws IOException {
	String consulta=query.split("=")[1];
	
	appWeather.getDates(consulta);
	
	
}

	
}
