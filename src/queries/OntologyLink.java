package queries;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

public class OntologyLink {

	private Model model;
	String prefix = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
			+ "PREFIX owl: <http://www.w3.org/2002/07/owl#>" + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
			+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
			+ "PREFIX trip: <http://www.semanticweb.org/tripAdvisor/Trip#>";
	
	private static OntologyLink instance;
	
	private OntologyLink(){
		model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		model.read("tripFinal.owl");
	}
	
	public static synchronized OntologyLink getInstance() {
		if(instance == null) {
			instance = new OntologyLink();
		}
		
		return instance;
	}

	public String queryOne(String season) {
		String query = prefix + "SELECT ?city" + "\n" + "WHERE { ?city trip:bestSeason ?season." + "FILTER regex(?season, "
				+ '"' + season + '"' + ")}";
		return executeQuery(query);
	}

	public String queryTwo(String cat) {
		String query = prefix + "SELECT ?subject" + "\n" + "WHERE {"
				+ "?subject rdf:type trip:City."
				+ "?subject trip:hasCategory trip:" + cat
				+ "}";
		return executeQuery(query);
	}

	public String queryThree(String cat) {
		String query = prefix + "SELECT ?city " + "\n" + "WHERE {" + "?city rdf:type trip:City.\n"
				+ "?city trip:includes ?activity.\n" + "?activity trip:hasCategory trip:" + cat + "}";
		return executeQuery(query);
	}

	public String queryFour(String cat) {
		String query = prefix + "SELECT ?activity ?rate " + "\n" 
		+ "WHERE { "
		+ "?activity trip:hasCategory trip:" + cat + ".\n"
		+ "?review trip:rated ?activity.\n"
		+ "?review trip:rate ?rate.\n"
		+ "}\n"
		+ "ORDER BY DESC(?rate)";
					
		return executeQuery(query);
	}

	public String queryFive(String cat, int rank) {
		String query = prefix + "SELECT ?activity ?rank " +"\n" 
				+" WHERE{?activity trip:hasCategory trip:"+cat+"."
				+ "?review trip:rated ?activity."
				+ "?review trip:rate "+rank+".}";
					
		return executeQuery(query);
	}
	
	public String querySix(String cat, int maxPrice, int duration) {
		String query = prefix + "SELECT ?activity ?start ?end \n "
				+ "WHERE { "
				+ "?activity trip:hasCategory trip:"+cat+"."
				+ "?activity trip:startTime ?start."
				+ "?activity trip:endTime ?end."
				+ "?activity trip:price ?price."
				+ "?activity trip:duration ?duration."
				+ "FILTER(?start < 17) ."
				+ "FILTER(?end > 6) ";
		if (maxPrice != -1)
			query +=  ".FILTER(?price < "+ maxPrice +")";
		if (duration != -1)
			query +=  ".FILTER(?duration < "+ duration+")";
		
		query += "}";
					
		return executeQuery(query);
	}
	
	public String querySeven(String cat) {
		String query = prefix + "SELECT ?activity ?start ?end \n "
				+ "WHERE { "
				+ "?activity trip:hasCategory trip:"+cat+"."
				+ "?activity trip:startTime ?start."
				+ "?activity trip:endTime ?end."
				+ "FILTER(?start < 24) ."
				+ "FILTER(?end > 18) }";
					
		return executeQuery(query);
	}
	
	public String queryEight(String cat) {
		String query = prefix + "SELECT ?activity ?price \n "
				+ "WHERE { "
				+ "?activity trip:hasCategory trip:"+cat+"."
				+ "?activity trip:price ?price.}";
					
		return executeQuery(query);
	}
	
	public String queryNine(String cat) {
		String query = prefix + "SELECT ?activity ?price \n "
				+ "WHERE { "
				+ "?activity trip:hasCategory trip:"+cat+"."
				+ "?activity trip:price ?price.}"
				+ "\nORDER BY DESC(?price)";
					
		return executeQuery(query);
	}

	public String queryTen(String cat, String city) {
		String query = prefix + "SELECT ?activity ?city \n"
				+ "WHERE {"
				+ "?activity rdf:type trip:Activity. \n"
				+ "?activity trip:hasCategory trip:" + cat + ". \n"
				+ "?city rdf:type trip:City. \n"
				+ "?city trip:includes ?activity. \n"
				+ "?city trip:name ?name. \n"
				+ "FILTER regex(?name, \"" + city + "\", \"i\"). }";
					
		return executeQuery(query);
	}

	public String queryTwelve(String cat, String match) {
		String query = prefix + "SELECT ?subject \n"
				+ "WHERE { "
				+ "?subject trip:hasCategory trip:" + cat + "."
				+ "?subject trip:name ?name."
				+ "FILTER regex(?name, \"" + match +"\", \"i\"). }"; 
					
		return executeQuery(query);
	}

	public String queryThirteen(String cat, int maxPrice) {
		String query = prefix + "SELECT ?subject ?price \n"
				+ "WHERE { "
				+ "?subject trip:hasCategory trip:" + cat + "."
				+ "?subject trip:price ?price."
				+ "FILTER(?price < " + maxPrice + ")."
				+ "}"; 
					
		return executeQuery(query);
	}

	public String queryFourteen(String user, String cat) {
		String query = prefix + "SELECT ?me ?category ?activity \n"
				+ "WHERE {"
				+ "?me rdf:type trip:User."
				+ "?me trip:name ?usrname."
				+ "?me trip:interestedIn ?interest."
				+ "?interest rdf:type trip:Interest."
				+ "?interest trip:hasCategory trip:" + cat + "."
				+ "?activity trip:hasCategory trip:" + cat + "."
				+ "?activity trip:name ?name."
				+ "?interest trip:type ?type."
				+ "FILTER regex(str(?name),  str(?type), \"i\")."
				+ "FILTER regex(str(?usrname),  \"" + user + "\", \"i\")."
				+ "}"; 
					
		return executeQuery(query);
	}

	public String queryFifteen(String cat) {
		String query = prefix + "SELECT * \n"
				+ "WHERE { "
				+ "?activity trip:hasCategory trip:" + cat + "."
				+ "?activity trip:type ?type}";
					
		return executeQuery(query);
	}

	public String querySixteen(String me, String category){
		String query = prefix + "SELECT ?me ?activity ?interest" + "\n" +
				"WHERE { ?me rdf:type trip:User. " + "\n" + 
				"?me trip:name ?x. "+ "\n" +
				"?me trip:interestedIn ?interest. " + "\n" + 
				"?interest rdf:type trip:Interest." + "\n" +
				"?interest trip:hasCategory ?category." + "\n" +
				"?activity trip:hasCategory ?category." + "\n" +
				"filter regex (?x, \""+ me +"\")" + "\n" +
				"filter regex (?category, \""+ category +"\")" + "\n" +
				"}";
		System.out.println(query);
		
		return executeQuery(query);
	}
	
	public String queryTwentyOne(String cuisine, Boolean cheap){
		String query = prefix + "SELECT * \n"
				+ "WHERE { "
				+ "?subject trip:hasCategory trip:Food."
				+ "?subject trip:type ?type."
				+ "?subject trip:price ?price."
				+ "FILTER regex(?type, \"" + cuisine + "\").";
		if (cheap)
			query += "FILTER(?price < 100).";
		query += "}"; 
		System.out.println(query);
		
		return executeQuery(query);
	}
	
	public String queryTwentyThree(String cuisine){
		String query = prefix + "SELECT * \n"
				+ "WHERE { "
				+ "?rating rdf:type trip:Review."
				+ "?rating trip:rated ?activity."
				+ "?rating trip:rate ?rate."
				+ "?activity trip:hasCategory trip:Food.";
		if (cuisine != ""){
			query += "?activity trip:type ?type.";
			query += "FILTER regex(?type, \"" + cuisine + "\").";
		}
		query += "}"; 
		System.out.println(query);
		
		return executeQuery(query);
	}
	
	public String executeQuery(String querystr) {
		Query query = QueryFactory.create(querystr);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);

		ResultSet results = qexec.execSelect();
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ResultSetFormatter.outputAsJSON(outputStream, results);
		String json = new String(outputStream.toByteArray());

		return json;
	}

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println(OntologyLink.getInstance().queryTwentyThree(""));
	}
}
