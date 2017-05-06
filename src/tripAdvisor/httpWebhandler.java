package tripAdvisor;

import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import http.HttpHandlerImpl;

@SuppressWarnings("restriction")
public class httpWebhandler {
	public static void main(String[] args) throws Exception {
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

		server.createContext("/query1", HttpHandlerImpl.queryOne);
		server.createContext("/query2", HttpHandlerImpl.queryTwo);
		
		server.start();
	}
}
