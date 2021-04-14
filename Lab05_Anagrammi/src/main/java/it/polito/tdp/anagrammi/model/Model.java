package it.polito.tdp.anagrammi.model;

import java.util.HashSet;
import java.util.Set;

import it.polito.tdp.anagrammi.DB.DizionarioDAO;

public class Model
{
	DizionarioDAO dao = new DizionarioDAO();
	Integer lunghezzaParolaOriginale;
	
	public Set<String> anagrammi(String parola)
	{
		this.lunghezzaParolaOriginale = parola.length();
		Set<String> risultato = new HashSet<>();
		this.permuta("", parola, 0, risultato); 
		return risultato; 
	} 
	private void permuta(String parziale, String lettere, int livello, Set<String> risultato)
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
	// usata dal controller per aggiungere alla lista delle corrette o meno
	public boolean getParola(String parolaDaConfermare)
	{
		return dao.getParola(parolaDaConfermare);
	}
	//
	//
	//*****************************CONTROLLO DURANTE RICORSIONE*******************************************
	//
	//
	public Set<String> anagrammiConControllo(String parola)
	{
		this.lunghezzaParolaOriginale = parola.length();
		Set<String> risultato = new HashSet<>();
		this.permutaConControllo("", parola, 0, risultato); 
		return risultato; 
	}
	private void permutaConControllo(String parziale, String lettere, int livello, Set<String> risultato)
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
		System.out.println(lettere);
		return dao.isParolaParziale(lettere, this.lunghezzaParolaOriginale);
	}
}