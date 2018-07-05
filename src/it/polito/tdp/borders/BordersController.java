/**
 * Skeleton for 'Borders.fxml' Controller Class
 */

package it.polito.tdp.borders;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BordersController {
	
	private Model m;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="boxNazione"
    private ComboBox<?> boxNazione; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	
    	txtResult.clear();
    	
    	try{
    		
    		int anno =  Integer.parseInt(txtAnno.getText());
    		
    		for(String s : m.calcolaGrafo(anno) )
    	
    		txtResult.appendText(s);
    		
    	}
    	catch(NumberFormatException e) {
    		e.printStackTrace();
    		System.out.println("Inserire un valore corretto per l'anno");
    		
    	}
    }

    @FXML
    void doSimula(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Borders.fxml'.";
        assert boxNazione != null : "fx:id=\"boxNazione\" was not injected: check your FXML file 'Borders.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Borders.fxml'.";

    }

	public void setModel(Model m) {
		
		this.m = m;
	}
    
    
}
