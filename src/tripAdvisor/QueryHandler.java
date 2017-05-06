package tripAdvisor;

import java.io.FileNotFoundException;

import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;

public class QueryHandler {
	private Model model;
	String prefix = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
			+ "PREFIX owl: <http://www.w3.org/2002/07/owl#>" + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
			+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
			+ "PREFIX trip: <http://www.semanticweb.org/tripAdvisor/Trip#>";

	public void createModel() {
		model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		model.read("tripFinal.owl");
	}

	public void bestSeason(String season) {
		String query = prefix + "SELECT ?city" + "WHERE { ?city trip:bestSeason ?season." + "FILTER regex(?season, "
				+ '"' + season + '"' + ")}";
		executeQuery(query, "city");

	}

	public void hasCategory(String cat) {
		String query = prefix + "SELECT ?subject" + "WHERE {?subject trip:hasCategory trip:" + cat + " }";
		executeQuery(query, "ativity");
	}

	public void includes(String cat) {
		String query = prefix + "SELECT *" + "WHERE {" + "?city rdf:type trip:City.\n"
				+ "?city trip:includes ?activity.\n" + "?activity trip:hasCategory trip:" + cat + "}";
		executeQuery(query, "city");
	}

	public void orderCatbyRank(String cat) {
		String query = prefix + "SELECT ?activity ?rank" + "WHERE { ?activity trip:hasCategory trip:" + cat + "."
				+ "?review trip:rated ?activity." + "?review trip:rate ?rank }";
		executeQuery(query, "activity");
	}

	public void executeQuery(String querystr, String wanted) {
		Query query = QueryFactory.create(querystr);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);

		ResultSet results = qexec.execSelect();

		while (results.hasNext()) {
			QuerySolution sol = results.nextSolution();
			RDFNode items = sol.get(wanted);
			System.out.println(items);
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		QueryHandler x = new QueryHandler();
		x.createModel();
		x.bestSeason("Winter");
	}
}
