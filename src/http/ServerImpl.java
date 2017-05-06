package http;

import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

@SuppressWarnings("restriction")
public class ServerImpl {
	public static void main(String[] args) throws Exception {
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

		server.createContext("/query1", HttpHandlerImpl.queryOne);
		server.createContext("/query2", HttpHandlerImpl.queryTwo);
		server.createContext("/query3", HttpHandlerImpl.queryThree);
		server.createContext("/query4", HttpHandlerImpl.queryFour);
		server.createContext("/query16", HttpHandlerImpl.querySixteen);
		
		server.start();
		

	}
}