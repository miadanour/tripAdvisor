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
			if (map == null || map.get("season") == null) {
				outputResult(t, 400, "Bad Request, Missing Parameters");
				return;
			}
			String result = "city(" + OntologyLink.getInstance().queryOne(map.get("season")) + ");";
			outputResult(t, 200, result);
		}
	};

	public static final HttpHandler queryTwo = new HttpHandler() {
		@Override
		public void handle(HttpExchange t) throws IOException {
			Map<String, String> map = QueryParser.getInstance().parse(t.getRequestURI().getQuery());
			if (map == null || map.get("category") == null) {
				outputResult(t, 400, "Bad Request, Missing Parameters");
				return;
			}
			String result = "category(" + OntologyLink.getInstance().queryTwo(map.get("category")) + ");";
			outputResult(t, 200, result);
		}
	};

	public static final HttpHandler queryThree = new HttpHandler() {
		@Override
		public void handle(HttpExchange t) throws IOException {
			Map<String, String> map = QueryParser.getInstance().parse(t.getRequestURI().getQuery());
			if (map == null || map.get("category") == null) {
				outputResult(t, 400, "Bad Request, Missing Parameters");
				return;
			}
			String result = "city(" + OntologyLink.getInstance().queryThree(map.get("category")) + ");";
			outputResult(t, 200, result);
		}
	};

	public static final HttpHandler queryFour = new HttpHandler() {
		@Override
		public void handle(HttpExchange t) throws IOException {
			Map<String, String> map = QueryParser.getInstance().parse(t.getRequestURI().getQuery());
			if (map == null || map.get("category") == null) {
				outputResult(t, 400, "Bad Request, Missing Parameters");
				return;
			}
			String result = "activity(" + OntologyLink.getInstance().queryFour(map.get("category")) + ");";
			outputResult(t, 200, result);
		}
	};

	public static final HttpHandler queryFive = new HttpHandler() {
		@Override
		public void handle(HttpExchange t) throws IOException {
			Map<String, String> map = QueryParser.getInstance().parse(t.getRequestURI().getQuery());
			if (map == null || map.get("category") == null || map.get("rank") == null) {
				outputResult(t, 400, "Bad Request, Missing Parameters");
				return;
			}
			String result = "activity("
					+ OntologyLink.getInstance().queryFive(map.get("category"), Integer.parseInt(map.get("rank")))
					+ ");";
			outputResult(t, 200, result);
		}
	};

	public static final HttpHandler querySix = new HttpHandler() {
		@Override
		public void handle(HttpExchange t) throws IOException {
			Map<String, String> map = QueryParser.getInstance().parse(t.getRequestURI().getQuery());
			if (map == null || map.get("category") == null) {
				outputResult(t, 400, "Bad Request, Missing Parameters");
				return;
			}
			String maxP = map.get("maxPrice");
			String duration = map.get("duration");

			int iDuration = duration == null ? -1 : Integer.parseInt(duration);
			int iMaxP = maxP == null ? -1 : Integer.parseInt(maxP);
			String result = "activity(" + OntologyLink.getInstance().querySix(map.get("category"), iMaxP, iDuration)
					+ ");";

			outputResult(t, 200, result);
		}
	};

	public static final HttpHandler querySeven = new HttpHandler() {
		@Override
		public void handle(HttpExchange t) throws IOException {
			Map<String, String> map = QueryParser.getInstance().parse(t.getRequestURI().getQuery());
			if (map == null || map.get("category") == null) {
				outputResult(t, 400, "Bad Request, Missing Parameters");
				return;
			}
			String result = "activity(" + OntologyLink.getInstance().querySeven(map.get("category")) + ");";
			outputResult(t, 200, result);
		}
	};

	public static final HttpHandler queryEight = new HttpHandler() {
		@Override
		public void handle(HttpExchange t) throws IOException {
			Map<String, String> map = QueryParser.getInstance().parse(t.getRequestURI().getQuery());
			if (map == null || map.get("category") == null) {
				outputResult(t, 400, "Bad Request, Missing Parameters");
				return;
			}
			String result = "activity(" +OntologyLink.getInstance().queryEight(map.get("category")) + ");";
			outputResult(t, 200, result);
		}
	};

	public static final HttpHandler queryNine = new HttpHandler() {
		@Override
		public void handle(HttpExchange t) throws IOException {
			Map<String, String> map = QueryParser.getInstance().parse(t.getRequestURI().getQuery());
			if (map == null || map.get("category") == null) {
				outputResult(t, 400, "Bad Request, Missing Parameters");
				return;
			}
			String result = "activity(" +OntologyLink.getInstance().queryNine(map.get("category")) + ");";
			outputResult(t, 200, result);
		}
	};

	public static final HttpHandler queryTen = new HttpHandler() {
		@Override
		public void handle(HttpExchange t) throws IOException {
			Map<String, String> map = QueryParser.getInstance().parse(t.getRequestURI().getQuery());
			if (map == null || map.get("category") == null || map.get("city") == null) {
				outputResult(t, 400, "Bad Request, Missing Parameters");
				return;
			}
			String result = "activity(" +OntologyLink.getInstance().queryTen(map.get("category"), map.get("city")) + ");";
			outputResult(t, 200, result);
		}
	};

	public static final HttpHandler queryTwelve = new HttpHandler() {
		@Override
		public void handle(HttpExchange t) throws IOException {
			Map<String, String> map = QueryParser.getInstance().parse(t.getRequestURI().getQuery());
			if (map == null || map.get("category") == null || map.get("match") == null) {
				outputResult(t, 400, "Bad Request, Missing Parameters");
				return;
			}
			String result = "activity(" + OntologyLink.getInstance().queryTwelve(map.get("category"), map.get("match")) + ");";
			outputResult(t, 200, result);
		}
	};

	public static final HttpHandler queryThirteen = new HttpHandler() {
		@Override
		public void handle(HttpExchange t) throws IOException {
			Map<String, String> map = QueryParser.getInstance().parse(t.getRequestURI().getQuery());
			if (map == null || map.get("category") == null || map.get("maxPrice") == null) {
				outputResult(t, 400, "Bad Request, Missing Parameters");
				return;
			}
			String result = "activity(" +OntologyLink.getInstance().queryThirteen(map.get("category"),
					Integer.parseInt(map.get("maxPrice"))) + ");";
			outputResult(t, 200, result);
		}
	};

	public static final HttpHandler queryFourteen = new HttpHandler() {
		@Override
		public void handle(HttpExchange t) throws IOException {
			Map<String, String> map = QueryParser.getInstance().parse(t.getRequestURI().getQuery());
			if (map == null || map.get("category") == null || map.get("user") == null) {
				outputResult(t, 400, "Bad Request, Missing Parameters");
				return;
			}
			String result = "activity(" +OntologyLink.getInstance().queryFourteen(map.get("user"), map.get("category")) + ");";
			outputResult(t, 200, result);
		}
	};

	public static final HttpHandler queryFifteen = new HttpHandler() {
		@Override
		public void handle(HttpExchange t) throws IOException {
			Map<String, String> map = QueryParser.getInstance().parse(t.getRequestURI().getQuery());
			if (map == null || map.get("category") == null) {
				outputResult(t, 400, "Bad Request, Missing Parameters");
				return;
			}
			String result = "activity(" + OntologyLink.getInstance().queryFifteen(map.get("category")) + ");";
			outputResult(t, 200, result);
		}
	};

	public static HttpHandler querySixteen = new HttpHandler() {
		@Override
		public void handle(HttpExchange t) throws IOException {
			Map<String, String> map = QueryParser.getInstance().parse(t.getRequestURI().getQuery());
			if (map == null || map.get("name") == null) {
				outputResult(t, 400, "Bad Request, Missing Parameters");
				return;
			}
			String result = "activity(" +OntologyLink.getInstance().querySixteen(map.get("name"), map.get("category")) + ");";
			outputResult(t, 200, result);
		}
	};

	public static HttpHandler querySeventeen = new HttpHandler() {
		@Override
		public void handle(HttpExchange t) throws IOException {
			Map<String, String> map = QueryParser.getInstance().parse(t.getRequestURI().getQuery());
			if (map == null || map.get("name") == null) {
				outputResult(t, 400, "Bad Request, Missing Parameters");
				return;
			}
			String result = "activity(" +OntologyLink.getInstance().querySixteen(map.get("name"), map.get("category")) + ");";
			outputResult(t, 200, result);
		}
	};
	
	public static HttpHandler activities = new HttpHandler() {
		@Override
		public void handle(HttpExchange t) throws IOException {
			String result = "activity("+ OntologyLink.getInstance().activities() + ");";
			outputResult(t, 200, result);
		}
	};
	
	
	public static HttpHandler includes = new HttpHandler() {
		@Override
		public void handle(HttpExchange t) throws IOException {
			Map<String, String> map = QueryParser.getInstance().parse(t.getRequestURI().getQuery());
			if (map == null || map.get("city") == null) {
				outputResult(t, 400, "Bad Request, Missing Parameters");
				return;
			}
			String result = "activity("+ OntologyLink.getInstance().includes(map.get("city")) + ");";
			outputResult(t, 200, result);
		}
	};
	public static HttpHandler cities = new HttpHandler() {
		@Override
		public void handle(HttpExchange t) throws IOException {
			String result = "city("+ OntologyLink.getInstance().cities() + ");";
			outputResult(t, 200, result);
		}
	};
	
	public static HttpHandler queryTwentyOne = new HttpHandler() {
		@Override
		public void handle(HttpExchange t) throws IOException {
			Map<String, String> map = QueryParser.getInstance().parse(t.getRequestURI().getQuery());
			if(map == null || map.get("cuisine") == null) {
				outputResult(t, 400, "Bad Request, Missing Parameters");
				return;
			}
			String cheapS = map.get("cheap");
			
			Boolean cheap = cheapS == null? false: Boolean.parseBoolean(cheapS);
			
			String result = OntologyLink.getInstance().queryTwentyOne(map.get("cuisine"), cheap)+");"; 
			outputResult(t, 200, result);
		}
	};
	
	public static HttpHandler queryTwentyThree = new HttpHandler() {
		@Override
		public void handle(HttpExchange t) throws IOException {
			Map<String, String> map = QueryParser.getInstance().parse(t.getRequestURI().getQuery());
			String cuisine;
			if(map == null) {
				cuisine = "";
			}
			else{
				cuisine = map.get("cuisine");
				cuisine = (cuisine == null) ? "": cuisine;
			}
			String result = OntologyLink.getInstance().queryTwentyThree(cuisine)+");"; 
			outputResult(t, 200, result);
		}
	};
	
	public static HttpHandler login = new HttpHandler() {
		@Override
		public void handle(HttpExchange t) throws IOException {
		}
	};
	
	public static HttpHandler user = new HttpHandler() {
		@Override
		public void handle(HttpExchange t) throws IOException {
			Map<String, String> map = QueryParser.getInstance().parse(t.getRequestURI().getQuery());
			if (map == null || map.get("username") == null) {
				outputResult(t, 400, "Bad Request, Missing Parameters");
				return;
			}
			String result = "user("+ OntologyLink.getInstance().user(map.get("username")) + ");";
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