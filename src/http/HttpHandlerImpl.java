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
			String result = OntologyLink.getInstance().bestSeason(map.get("season"));
			
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
			String result = OntologyLink.getInstance().hasCategory(map.get("category"));

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
			String result = OntologyLink.getInstance().includesActivityWithCategory(map.get("category"));

			outputResult(t, 200, result);
		}
	};
	
	public static HttpHandler querySixteen = new HttpHandler() {
		@Override
		public void handle(HttpExchange t) throws IOException {
			Map<String, String> map = QueryParser.getInstance().parse(t.getRequestURI().getQuery());
			if(map == null || map.get("name") == null) {
				outputResult(t, 400, "Bad Request, Missing Parameters");
				return;
			}
			String result = OntologyLink.getInstance().querySixteen(map.get("name")); 
			outputResult(t, 200, result);
		}
	};
	

	
	public static void outputResult(HttpExchange t, int status, String result) throws IOException {
		t.sendResponseHeaders(status, result.length());
		OutputStream os = t.getResponseBody();
		os.write(result.getBytes());
		os.close();
	}
}