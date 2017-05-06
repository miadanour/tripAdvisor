package http;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import queries.OntologyLink;

import utils.QueryParser;


@SuppressWarnings("restriction")
public class HttpHandlerImpl {
	
	public static final HttpHandler queryOne = new HttpHandler() {
		@Override
		public void handle(HttpExchange t) throws IOException {
			Map<String, String> map = QueryParser.getInstance().parse(t.getRequestURI().getQuery());
			if(map == null || map.get("season") == null) {
				outputResult(t, 400, "Bad Request, Missing Parameters");
				return;
			}
			String result = OntologyLink.getInstance().queryOne(map.get("season"));
			
			outputResult(t, 200, result);
		}
	};
	
	public static final HttpHandler queryTwo = new HttpHandler() {
		@Override
		public void handle(HttpExchange t) throws IOException {
			Map<String, String> map = QueryParser.getInstance().parse(t.getRequestURI().getQuery());
			if(map == null || map.get("category") == null) {
				outputResult(t, 400, "Bad Request, Missing Parameters");
				return;
			}
			String result = OntologyLink.getInstance().queryTwo(map.get("category"));

			outputResult(t, 200, result);
		}
	};
	

	public static final HttpHandler queryThree = new HttpHandler() {
		@Override
		public void handle(HttpExchange t) throws IOException {
			Map<String, String> map = QueryParser.getInstance().parse(t.getRequestURI().getQuery());
			if(map == null || map.get("category") == null) {
				outputResult(t, 400, "Bad Request, Missing Parameters");
				return;
			}
			String result = OntologyLink.getInstance().queryThree(map.get("category"));

			outputResult(t, 200, result);
		}
	};
	

	public static final HttpHandler queryFour = new HttpHandler() {
		@Override
		public void handle(HttpExchange t) throws IOException {
			Map<String, String> map = QueryParser.getInstance().parse(t.getRequestURI().getQuery());
			if(map == null || map.get("category") == null) {
				outputResult(t, 400, "Bad Request, Missing Parameters");
				return;
			}
			String result = OntologyLink.getInstance().queryFour(map.get("category"));
			System.out.println("1" + result);
			outputResult(t, 200, result);
		}
	};
	
	public static final HttpHandler queryFive = new HttpHandler() {
		@Override
		public void handle(HttpExchange t) throws IOException {
			Map<String, String> map = QueryParser.getInstance().parse(t.getRequestURI().getQuery());
			if(map == null || map.get("category") == null || map.get("rank") == null) {
				outputResult(t, 400, "Bad Request, Missing Parameters");
				return;
			}
			String result = OntologyLink.getInstance()
					.queryFive(map.get("category"), Integer.parseInt(map.get("rank")));
			System.out.println("1" + result);
			outputResult(t, 200, result);
		}
	};
	
	public static HttpHandler querySixteen = new HttpHandler() {
		@Override
		public void handle(HttpExchange t) throws IOException {
			String result = OntologyLink.getInstance().querySixteen("harry"); 
			
			t.sendResponseHeaders(200, result.length());
			OutputStream os = t.getResponseBody();
			os.write(result.getBytes());
			os.close();
		}
	};
	

	
	public static void outputResult(HttpExchange t, int status, String result) throws IOException {
		t.sendResponseHeaders(status, result.length());
		OutputStream os = t.getResponseBody();
		os.write(result.getBytes());
		os.close();
	}
}