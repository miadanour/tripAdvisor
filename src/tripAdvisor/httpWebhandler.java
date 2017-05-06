package tripAdvisor;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class httpWebhandler implements HttpHandler {

	@Override
	public void handle(HttpExchange t) throws IOException {
		String response = "This is the response";
		t.sendResponseHeaders(200, response.length());
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}

	@SuppressWarnings("restriction")
	public static void main(String[] args) throws Exception {
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
		server.createContext("/home", new httpWebhandler(){
			@Override
			public void handle(HttpExchange t) throws IOException {
				String response = "This is the response";
				
				String r = t.getRequestURI().getQuery();
				t.sendResponseHeaders(200, r.length());
				
				
				OutputStream os = t.getResponseBody();
				os.write(r.getBytes());
				os.close();
				
			}
		});
		server.start();
	}
}
