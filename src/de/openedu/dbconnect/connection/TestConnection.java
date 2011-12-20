package de.openedu.dbconnect.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TestConnection {

	/**
	 * Konstruktor mit uebergebenem SQL-Statement
	 * @param request
	 */
	public TestConnection(String request)
	{
		doTestRequest(request);	// Abfrage mit eingegebenem Statement durchfuehren
	}
	
	/**
	 * Konstruktor ohne uebergebenem SQL-Statement
	 */
	public TestConnection()
	{
		doTestRequest("SELECT * FROM adressen");	// Abfrage mit vorgegebenem Statement durchfuehren
	}

	private void doTestRequest(String request)
	{
		System.out.println(request);
		
		Connection con = null;		// Connection-Objekt, stellt Verbindung mit Datenbank her
		Statement statement = null;	// Statement-Objekt, fuer SQL-Ausdruecke und Abgragen zustaendig
		ResultSet rSet = null;		// ResultSet-Objekt nimmt die angefragten Daten auf
		
		try {
			Class.forName("com.mysql.jdbc.Driver");	// Der JDBC-Treiber wird geladen 
		}
		catch (ClassNotFoundException e1) { e1.printStackTrace(); }	// Fehlerbehandlung
		
		try {
			// Stelle Verbindung mit MySQL-Datenbank wvs-java-db her.
			// Benutzername=user, Kennwort=wvs
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wvs-java-db", "user", "wvs");
		}
		catch (SQLException e1) { e1.printStackTrace(); }	// Fehlerbehandlung
		
		try {
			statement = con.createStatement();	// initialisiere statemanent-Instanz ueber Factory-Methode von Connection-Objekt
			
			// Erzeuge SQL-Statement mittels Zeichenkette ...
			// und fuere die Anfrage direkt aus ...
			// schreibe das Ergebnis der Anfrage in den RecordSet rSet
			rSet = statement.executeQuery(request);	
			
			while(rSet.next())	// Iteriere ueber den RecordSet, solange er bis ans Ende durchlaufen wurde 
			{
				System.out.println(rSet.getObject("id")+","+rSet.getObject("anrede")
									+","+rSet.getObject("anrede")+","+rSet.getObject("vorname")+
									","+rSet.getObject("adresse")+","+rSet.getObject("plz")+","+rSet.getObject("ort"));
			}
				
		}
		catch (SQLException e) { e.printStackTrace(); }	// Fehlerbehandlung
	}
	
	public static void main(String[] args) {
		
		new TestConnection();					// Aufruf des Konstruktors der Testklasse ohne SQL-Abfrage
		
		String request;							// String für Konsoleneingabe eines SQL-Statements
		Scanner input = new Scanner(System.in);	// Scanner-Objekt, fuer die Tasttatureingabe
		
		System.out.println("Geben Sie einen SQL-Abfragebefehl ein:");	// Aufforderung zur Eingabe
		
		request = input.nextLine();		 // Folgendes Beispiel eingeben: SELECT * FROM adressen WHERE anrede='Herr'
		
		new TestConnection(request);	// Aufruf des Konstruktors der Testklasse
	}
}
