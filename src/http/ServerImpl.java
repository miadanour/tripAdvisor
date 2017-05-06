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
		server.createContext("/query5", HttpHandlerImpl.queryFive);
		server.createContext("/query6", HttpHandlerImpl.querySix);
		server.createContext("/query7", HttpHandlerImpl.querySeven);
		server.createContext("/query8", HttpHandlerImpl.queryEight);
		server.createContext("/query9", HttpHandlerImpl.queryNine);
		server.createContext("/query10", HttpHandlerImpl.queryTen);
		//query 11 goes to query 8 with category Sport
		server.createContext("/query12", HttpHandlerImpl.queryTwelve);
		server.createContext("/query13", HttpHandlerImpl.queryThirteen);
		server.createContext("/query14", HttpHandlerImpl.queryFourteen);
		server.createContext("/query15", HttpHandlerImpl.queryFifteen);
		
		server.createContext("/query16", HttpHandlerImpl.querySixteen);
		// query 17 goes to query 6 with category parameter as Entertainment
		// query 18 goes to query 6 with category parameter as Entertainment and maxPrice with required value
		
		server.start();
		

	}
}