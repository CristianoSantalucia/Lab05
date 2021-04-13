package it.polito.tdp.anagrammi;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.anagrammi.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;  

public class FXMLController
{
	Model model;
	
	@FXML private ResourceBundle resources;
	@FXML private URL location;
	@FXML private TextField txtInput;
	@FXML private TextArea txtAreaCorretti;
	@FXML private TextArea txtAreaSbagliate; 

	@FXML void doTrovaAnagrammi(ActionEvent event)
	{
		List<String> anagrammi = new ArrayList<>(model.anagrammi(txtInput.getText()));
		List<String> corrette = new ArrayList<>();
		List<String> sbagliate = new ArrayList<>();
		
		for (String parola : anagrammi)
		{
			if(model.getParola(parola))
				corrette.add(parola);
			else sbagliate.add(parola);
		}
		
		txtAreaCorretti.setText("dimensione: " + corrette.size() + " " + corrette.toString());
		txtAreaSbagliate.setText("dimensione: " + sbagliate.size() + " " + sbagliate.toString());
	}
	
	@FXML void doTrovaAnagrammiControllo(ActionEvent event)
	{
		List<String> anagrammi = new ArrayList<>(model.anagrammiConControllo(txtInput.getText()));
		txtAreaCorretti.setText("dimensione: " + anagrammi.size() + " " + anagrammi.toString());
	}
	
	@FXML void doReset(ActionEvent event)
	{
		
	}

	@FXML void initialize()
	{ 
		assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtAreaCorretti != null : "fx:id=\"txtAreaCorretti\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtAreaSbagliate != null : "fx:id=\"txtAreaSbagliate\" was not injected: check your FXML file 'Scene.fxml'.";
	}

	public void setModel(Model model)
	{
		this.model = model; 
	}
}