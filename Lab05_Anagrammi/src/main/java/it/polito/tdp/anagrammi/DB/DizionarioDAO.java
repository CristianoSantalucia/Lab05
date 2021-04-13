package it.polito.tdp.anagrammi.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DizionarioDAO
{
	/**
	 * Controlla che la parola passata come @param parola sia presente nel db
	 * @return {@code TRUE} se presente {@code FALSE} altrimenti
	 */
	public boolean getParola(String parola)
	{
		String sql = "SELECT * FROM parola WHERE nome = ?";
		boolean trovata = false;

		try
		{
			Connection conn = DBCconnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, parola);
			ResultSet rs = st.executeQuery();

			trovata = rs.next();

			rs.close();
			st.close();
			conn.close();

			return trovata;
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * Controlla se le lettere passate come @param inziali siano lettere di una
	 * parola esistente nel db
	 * @return {@code TRUE} se presente {@code FALSE} altrimenti
	 */
	public boolean isParolaParziale(String iniziali, int lunghezzaParolaOriginale)
	{
		iniziali = iniziali + "%";
		String sql = "SELECT * FROM parola WHERE nome LIKE ? AND LENGTH(nome) = ?";
		boolean trovata = false;

		try
		{
			Connection conn = DBCconnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, iniziali);
			st.setInt(2, lunghezzaParolaOriginale); //così guardo solo parole della stessa lunghezza
			ResultSet rs = st.executeQuery();

			trovata = rs.next();

			rs.close();
			st.close();
			conn.close();

			return trovata;
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
	}
}
