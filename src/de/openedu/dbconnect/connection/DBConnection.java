/*
DBConnection: Eine Klasse um eine Datenbankverbindung
zu erstellen und SQL-Abfragen durchzufuehren.

Copyright (C) 2011 Karsten Blauel

Dieses Programm ist freie Software. Sie koennen es unter den Bedingungen der GNU General Public License,
wie von der Free Software Foundation veroeffentlicht, weitergeben und/oder modifizieren, entweder gemaess
Version 3 der Lizenz oder (nach Ihrer Option) jeder spaeteren Version.
Die Veroeffentlichung dieses Programms erfolgt in der Hoffnung, dass es Ihnen von Nutzen sein wird, aber
OHNE IRGENDEINE GARANTIE, sogar ohne die implizite Garantie der MARKTREIFE oder der VERWENDBARKEIT FUER
EINEN BESTIMMTEN ZWECK. Details finden Sie in der GNU General Public License.
Sie sollten ein Exemplar der GNU General Public License zusammen mit diesem Programm erhalten haben.
Falls nicht, siehe <http://www.gnu.org/licenses/>.
*/

package de.openedu.dbconnect.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DBConnection {
	
	private String driver = "com.mysql.jdbc.Driver";	// Datenbank-Treiber
	private String dbTyp = "jdbc:mysql";		// DB-COnnection-Prefix
	private String dbIpAdress = "localhost";	// Adresse der Datenbank
	private String dbPort = "3306";				// Port der Datenbank
	private String dbName = "wvs-java-db";		// Datenbankname
	private String dbUser = "user";				// Benutzername
	private String dbUserPasswd = "wvs";		// Kennwort des Benutzers
	
	private String sqlStatement = "SELECT * FROM adressen";	// SQL-Statement

	/**
	 * Anfrage senden und RecordSet zurueckgeben
	 * @return
	 */
	public ResultSet doRequest()
	{
		return doRequest(sqlStatement);
	}
	
	/**
	 * Anfrage senden und RecordSet zurueckgeben
	 * @param sqlStatement
	 * @return
	 */
	public ResultSet doRequest(String sqlStatement)
	{
		System.out.println(sqlStatement);
		
		Connection con = null;		// Connection-Objekt, stellt Verbindung mit Datenbank her
		Statement statement = null;	// Statement-Objekt, fuer SQL-Ausdruecke und Abgragen zustaendig
		ResultSet rSet = null;		// ResultSet-Objekt nimmt die angefragten Daten auf
		
		try {
			Class.forName(driver);	// Der JDBC-Treiber wird geladen 
		}
		catch (ClassNotFoundException e1) { e1.printStackTrace(); }	// Fehlerbehandlung
		
		try {
			// Stelle Verbindung mit MySQL-Datenbank wvs-java-db her.
			// Benutzername=user, Kennwort=wvs
			con = DriverManager.getConnection(dbTyp+"://"+dbIpAdress+":"+dbPort+"/"+dbName, dbUser, dbUserPasswd);
		}
		catch (SQLException e1) { e1.printStackTrace(); }	// Fehlerbehandlung
		
		try {
			statement = con.createStatement();	// initialisiere statemanent-Instanz ueber Factory-Methode von Connection-Objekt
			
			// Erzeuge SQL-Statement mittels Zeichenkette ...
			// und fuere die Anfrage direkt aus ...
			// schreibe das Ergebnis der Anfrage in den RecordSet rSet
			rSet = statement.executeQuery(sqlStatement);
		}
		catch (SQLException e) { e.printStackTrace(); }	// Fehlerbehandlung
		
		return rSet;	// Ergebniss-Set zurueckgeben
	}
	
	// Getter und Setter fuer die benoetigten Connection-Variablen
	public void setDriver(String driver) {
		this.driver = driver;
	}

	public void setDbTyp(String dbTyp) {
		this.dbTyp = dbTyp;
	}

	public void setDbIpAdress(String dbIpAdress) {
		this.dbIpAdress = dbIpAdress;
	}

	public void setDbPort(String dbPort) {
		this.dbPort = dbPort;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public void setDbUserPasswd(String dbUserPasswd) {
		this.dbUserPasswd = dbUserPasswd;
	}

	public void setSqlStatement(String sqlStatement) {
		this.sqlStatement = sqlStatement;
	}
}
