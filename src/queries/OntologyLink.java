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

	public String bestSeason(String season) {
		String query = prefix + "SELECT ?city" + "\n" + "WHERE { ?city trip:bestSeason ?season." + "FILTER regex(?season, "
				+ '"' + season + '"' + ")}";
		return executeQuery(query);
	}

	public String hasCategory(String cat) {
		String query = prefix + "SELECT ?subject" + "WHERE {?subject trip:hasCategory trip:" + cat + " }";
		return executeQuery(query);
	}

	public String includesActivityWithCategory(String cat) {
		String query = prefix + "SELECT *" + "WHERE {" + "?city rdf:type trip:City.\n"
				+ "?city trip:includes ?activity.\n" + "?activity trip:hasCategory trip:" + cat + "}";
		return executeQuery(query);
	}

	public String orderCatbyRank(String cat) {
		String query = prefix + "SELECT ?activity ?rank" + "WHERE { ?activity trip:hasCategory trip:" + cat + "."
				+ "?review trip:rated ?activity." + "?review trip:rate ?rank }";
		return executeQuery(query);
	}

	public String querySixteen(String me){
		String query = prefix + "SELECT ?me ?activity ?interest" + "\n" +
				"WHERE { ?me trip:interestedIn ?interest ." + "\n" + 
				"?interest trip:type ?type ." + "\n" + 
				"?activity trip:hasCategory trip:Entertainment ." + "\n" +  
				"FILTER(REGEX(str(?activity), str(?interest)))}";
		
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
		System.out.println(OntologyLink.getInstance().hasCategory("Entertainment"));
	}
}
