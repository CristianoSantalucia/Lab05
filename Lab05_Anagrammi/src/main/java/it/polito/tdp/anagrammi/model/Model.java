package it.polito.tdp.anagrammi.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.anagrammi.DB.DizionarioDAO;

public class Model
{
	DizionarioDAO dao = new DizionarioDAO();
	Integer lunghezzaParolaOriginale;
	
	public List<String> anagrammi(String parola)
	{
		this.lunghezzaParolaOriginale = parola.length();
		List<String> risultato = new ArrayList<>();
		this.permuta("", parola, 0, risultato); 
		return risultato; 
	}
	public List<String> anagrammiConControllo(String parola)
	{
		this.lunghezzaParolaOriginale = parola.length();
		List<String> risultato = new ArrayList<>();
		this.permutaConControllo("", parola, 0, risultato); 
		return risultato; 
	}

	private void permuta(String parziale, String lettere, int livello, List<String> risultato)
	{
		if (lettere.length() == 0)
		{
			if(!risultato.contains(parziale))
				risultato.add(parziale);
		}
		else
		{ 
			for (int pos = 0; pos < lettere.length(); pos++)
			{ 
				String nuovaParziale = parziale + lettere.charAt(pos);
				String nuovaLettere = lettere.substring(0, pos) + lettere.substring(pos + 1); 

				permuta(nuovaParziale, nuovaLettere, livello + 1, risultato); 
			}
		}
	}
	private void permutaConControllo(String parziale, String lettere, int livello, List<String> risultato)
	{
		if (lettere.length() == 0)
		{
			if(!risultato.contains(parziale))
				risultato.add(parziale);
		}
		else
		{ 
			for (int pos = 0; pos < lettere.length(); pos++)
			{ 
				String nuovaParziale = parziale + lettere.charAt(pos);
				String nuovaLettere = lettere.substring(0, pos) + lettere.substring(pos + 1); 

				if(valida(nuovaParziale)) //taglia branch inutili
					permutaConControllo(nuovaParziale, nuovaLettere, livello + 1, risultato); 
				else return;
			}
		}
	}
	
	private boolean valida(String lettere)
	{
		return dao.isParolaParziale(lettere, this.lunghezzaParolaOriginale);
	}
	public boolean getParola(String parolaDaConfermare)
	{
		return dao.getParola(parolaDaConfermare);
	}
}