package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private BordersDAO bDAO;
	private SimpleGraph <Country, DefaultEdge> grafo ;
	private List <Country> nazioni;
	private List <Country> confinanti;
	private List<CountryTot> elencoConfini;
	private CountryIDMap cMap;
	private List <String> risultato;

	
	public Model() {
		
		bDAO = new BordersDAO();
		
		
	}

	public List <String> calcolaGrafo(int anno) {
		
		risultato= new ArrayList<>();
		
		cMap = new CountryIDMap();
		
		nazioni = bDAO.getCountryByYear(anno,cMap);
		
		grafo =  new SimpleGraph<>(DefaultEdge.class);
	
		Graphs.addAllVertices(grafo, nazioni);
		
		for (Country c : nazioni) {
			
			confinanti = bDAO.getEdge(anno,c);
			

			for (Country arrivo : confinanti)
				
				Graphs.addEdgeWithVertices(grafo, c, arrivo);
			
		}
		
		System.out.println(grafo.vertexSet().size());
		System.out.println(grafo.edgeSet().size());

			elencoConfini = bDAO.getElenco(anno);
			
			Collections.sort(elencoConfini);
		
			for(CountryTot c : elencoConfini)
				
			risultato.add( ""+cMap.get(c.getCodiceCountry()).toString()+" "+c.getTotConfini()+"\n");
			
			
			return risultato;
			
	}
}
