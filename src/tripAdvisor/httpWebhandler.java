package tripAdvisor;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class httpWebhandler implements HttpHandler {

	@Override
	public void handle(HttpExchange t) throws IOException {
		System.out.println(t);
		System.out.println(t.getRequestURI().getQuery());
		String response = "hey";
		t.sendResponseHeaders(200, response.length());
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}

		public static void main(String[] args) throws Exception {
			HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
			server.createContext("/", new httpWebhandler());
			
			server.start();
	}
}
