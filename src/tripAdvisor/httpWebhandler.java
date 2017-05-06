package tripAdvisor;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import utils.QueryParser;

@SuppressWarnings("restriction")
public class httpWebhandler implements HttpHandler {

	public static void main(String[] args) throws Exception {
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
		
		server.createContext("/home1", new HttpHandler(){
			@Override
			public void handle(HttpExchange t) throws IOException {
				String x = "hello world";
				t.sendResponseHeaders(200, x.length());
				OutputStream os = t.getResponseBody();
				os.write(x.getBytes());
				os.close();

				System.out.println(QueryParser.getInstance().parse(t.getRequestURI().getQuery()));
			}
			
		});

		
		server.start();
	}

	@Override
	public void handle(HttpExchange arg0) throws IOException {
		// TODO Auto-generated method stub
	}
}
