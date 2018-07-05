package it.polito.tdp.borders.model;

import java.util.HashMap;
import java.util.Map;

public class CountryIDMap {
	
	private Map <Integer, Country> map;
		
		public CountryIDMap() {
			
			map = new HashMap<>();
			
		}
		
		public Country get(Country c) {
		
			Country old = map.get(c.getcCode());
		
			if(old == null) {
				
				map.put(c.getcCode(), c);
				
				return c;
			}
			
			return old;
		}
		
		public void put(Integer codCountry,Country country) {
			
			map.put(codCountry, country);
			
		}

		public Country get(Integer codCountry) {

			return map.get(codCountry);
		}
}
