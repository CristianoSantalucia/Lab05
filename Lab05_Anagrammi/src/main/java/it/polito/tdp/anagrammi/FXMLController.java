package it.polito.tdp.anagrammi;

import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

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
		this.reset();
		//
		String input = txtInput.getText();
		if(this.controllaInput(input))
		{
			txtAreaCorretti.setText("INSERIRE UNA PAROLA CHE COMPRENDA TRA 3 E 7 CARATTERI");
			return;
		}
		//
		long in = System.nanoTime();
		
		Set<String> anagrammi = new HashSet<>(model.anagrammi(input));
		Set<String> corrette = new HashSet<>();
		Set<String> sbagliate = new HashSet<>();
		
		for (String parola : anagrammi)
		{
			if(model.getParola(parola))
				corrette.add(parola);
			else sbagliate.add(parola);
		}
		
		long fi = System.nanoTime();
		System.out.println((fi-in)*10e9);
		
		txtAreaCorretti.setText("dimensione: " + corrette.size() + "\n" + corrette.toString());
		txtAreaSbagliate.setText("dimensione: " + sbagliate.size() + "\n" + sbagliate.toString());
	}
	
	private boolean controllaInput(String input)
	{
		return (input.length() < 3 || input.length() > 7);
	}

	@FXML void doTrovaAnagrammiControllo(ActionEvent event)
	{
		this.reset();
		//
		String input = txtInput.getText();
		if(this.controllaInput(input))
		{
			txtAreaCorretti.setText("INSERIRE UNA PAROLA CHE COMPRENDA TRA 3 E 7 CARATTERI");
			return;
		}
		//
		long in = System.nanoTime();
		Set<String> anagrammi = new HashSet<>(model.anagrammiConControllo(input));
		long fi = System.nanoTime();
		System.out.println((fi-in)*10e9);
		txtAreaCorretti.setText("dimensione: " + anagrammi.size() + "\n" + anagrammi.toString());
	}
	
	@FXML void doReset(ActionEvent event)
	{
		reset();
		txtInput.clear();
	}

	private void reset()
	{
		txtAreaCorretti.clear();
		txtAreaSbagliate.clear();
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