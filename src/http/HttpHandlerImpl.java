package http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

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
}