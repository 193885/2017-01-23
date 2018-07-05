package it.polito.tdp.borders.db;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.CountryIDMap;
import it.polito.tdp.borders.model.CountryTot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BordersDAO {
	
	public List<Country> loadAllCountries() {
		
		String sql = 
				"SELECT ccode,StateAbb,StateNme " +
				"FROM country " +
				"ORDER BY StateAbb " ;

		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet rs = st.executeQuery() ;
			
			List<Country> list = new LinkedList<Country>() ;
			
			while( rs.next() ) {
				
				Country c = new Country(
						rs.getInt("ccode"),
						rs.getString("StateAbb"), 
						rs.getString("StateNme")) ;
				
				list.add(c) ;
			}
			
			conn.close() ;
			
			return list ;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null ;
	}
	
	public List<Country> getCountryByYear(int anno, CountryIDMap cmap){
		
		String sql =    "select * from country where ccode in (select state1no from contiguity where year <? and conttype = 1 group by state1no) ";

		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			st.setInt(1, anno+1);
			
			ResultSet rs = st.executeQuery() ;
			
			List<Country> result = new LinkedList<Country>() ;
			
			while( rs.next() ) {
				
				Country c = new Country(
						rs.getInt("ccode"),
						rs.getString("StateAbb"), 
						rs.getString("StateNme")) ;
				
				result.add(cmap.get(c)) ;
			}
			
			conn.close() ;
			
			return result ;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null ;
	
	}
	
	public List<Country> getEdge (int anno, Country c){
		
		List <Country> result = new ArrayList <>();
		
		String sql =    "select * from country where ccode in ( select  state2no " + 
						"from contiguity " + 
						"where conttype = 1 " + 
						"and contiguity.year < ? " + 
						"and state1no < state2no " + 
						"and state1no = ? " + 
						"group by state1no)" ;

		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			st.setInt(1,anno+1);
			st.setInt(2, c.getcCode());
			
			ResultSet rs = st.executeQuery() ;
			
			while(rs.next()) {
				
				Country c1 = new Country(
						rs.getInt("ccode"),
						rs.getString("StateAbb"), 
						rs.getString("StateNme")) ;
				
				result.add(c1);
				
			}
		
			conn.close() ;
			
			return result ;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null ;		
	}
	
	public static void main(String[] args) {
		List<Country> list ;
		BordersDAO dao = new BordersDAO() ;
		list = dao.loadAllCountries() ;
		for(Country c: list) {
			System.out.println(c);
		}
	}

	public List<CountryTot> getElenco(int anno) {
				
		List <CountryTot> result = new ArrayList <>();
		
		String sql =    	"select c.state1no as cod ,count(*)  as tot " + 
							"from contiguity c, contiguity d " + 
							"where c.state1no = d.state1no and c.state2no = d.state2no " + 
							"and c.conttype = 1 and d.conttype = 1 " + 
							"and c.year < ? and d.year < ? " + 
							"and c.state1no < c.state2no " + 
							"group by c.state1no ";

		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			st.setInt(1,anno+1);
			st.setInt(2, anno+1);
			
			ResultSet rs = st.executeQuery() ;
			
			while(rs.next()) {
				
				CountryTot ctot = new CountryTot(rs.getInt("cod"),rs.getInt("tot"));
				
				result.add(ctot);
				
			}
		
			conn.close() ;
			
			return result ;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null ;		
	}
	
	
}
