package de.openedu.dbconnect.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import de.openedu.dbconnect.connection.DBConnection;

public class TestConnection {

	/**
	 * Konstruktor mit uebergebenem SQL-Statement
	 * @param request
	 */
	public TestConnection()
	{

		String request;									// String für Konsoleneingabe eines SQL-Statements
		DBConnection dbConnection = new DBConnection();	// Neues Connection-Objekt instanzieren
		Scanner input = new Scanner(System.in);			// Scanner-Objekt, fuer die Tasttatureingabe
		ResultSet rSet = null;							// ResultSet-Objekt nimmt die angefragten Daten auf
				
		dbConnection.setDriver("com.mysql.jdbc.Driver");	// Datenbank-Treiber
		dbConnection.setDbTyp("jdbc:mysql");	// DB-COnnection-Prefix
		dbConnection.setDbPath("localhost");	// Adresse der Datenbank
		dbConnection.setDbPort("3306");			// Port der Datenbank
		dbConnection.setDbName("wvs-java-db");	// Datenbankname
		dbConnection.setDbUser("user");			// Benutzername
		dbConnection.setDbUserPasswd("wvs");	// Kennwort des Benutzers
		dbConnection.setSqlStatement("SELECT * FROM adressen");	// SQL-Statement
		
//		System.out.println("Geben Sie einen SQL-Abfragebefehl ein:");	// Aufforderung zur Eingabe
//		
//		request = input.nextLine();		 // Folgendes Beispiel eingeben: SELECT * FROM adressen WHERE anrede='Herr'
//
//		dbConnection.setSqlStatement("SELECT * FROM adressen");	// SQL-Statement
		
		rSet = dbConnection.doRequest();
		
		try {
			while(rSet.next())	// Iteriere ueber den RecordSet, solange er bis ans Ende durchlaufen wurde 
			{
				System.out.println(rSet.getObject("id")+","+rSet.getObject("anrede")
									+","+rSet.getObject("anrede")+","+rSet.getObject("vorname")+
									","+rSet.getObject("adresse")+","+rSet.getObject("plz")+","+rSet.getObject("ort"));
			}
		} catch (SQLException e) { e.printStackTrace(); }
	}
	
	public static void main(String[] args) {
		
		new TestConnection();					// Aufruf des Konstruktors der Testklasse ohne SQL-Abfrage
	}
}
