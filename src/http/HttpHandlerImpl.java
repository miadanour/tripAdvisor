package http;



import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import queries.OntologyLink;

@SuppressWarnings("restriction")
public class HttpHandlerImpl {
	public static HttpHandler queryOne = new HttpHandler() {
		@Override
		public void handle(HttpExchange t) {
			
		}
	};
	
	public static HttpHandler queryTwo = new HttpHandler() {
		@Override
		public void handle(HttpExchange t) {
			
		}
	};
	
	public static HttpHandler querySixteen = new HttpHandler() {
		@Override
		public void handle(HttpExchange t) {
			String r = OntologyLink.getInstance().querySixteen(); 
			
			t.sendResponseHeaders(200, result.length());
			OutputStream os = t.getResponseBody();
			os.write(result.getBytes());
			os.close();
		}
	};
	
	private static void outputResult(HttpExchange t, int status, String result) throws IOException {
		t.sendResponseHeaders(status, result.length());
		OutputStream os = t.getResponseBody();
		os.write(result.getBytes());
		os.close();
	}
}