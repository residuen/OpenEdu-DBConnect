package de.openedu.dbconnect.test;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import de.openedu.dbconnect.connection.DBConnection;

public class TestPanel extends JPanel implements ActionListener {

	// Die beiden TExtfelder
	private JTextField statement1;	// Textfeld fuer Abfrage 1
	private JTextField statement2;	// Textfeld fuer Abfrage 2
		
	private JPanel center = new JPanel(new GridLayout(1,1));	// COntainer fuer Tabelle
	
	public TestPanel()
	{
		setLayout(new BorderLayout());	// Layout festlegen
		
		init();	// Initialisierung aufrufen
	}
	
	/**
	 * Panel-Konponenten initialisieren
	 */
	private void init()
	{
		JButton button;	// Eine Schaltflaeche
		
		// Mit Box-Containern kann man Komponenten waagerecht und senkrecht anordnen
		Box hBox = Box.createHorizontalBox();
		Box vBox = Box.createVerticalBox();
		
		// Das Panel center bekommt einen Rahmen mit Titel
		center.setBorder(BorderFactory.createTitledBorder("Abfrageergebnis "));
		
		// Die beiden Textfelder werden initialisiert und mit einem Abfragetext "vorgefuellt"
		statement1 = new JTextField("SELECT * FROM adressen");
		statement2 = new JTextField("SELECT * FROM adressen WHERE anrede='Frau'");
			
		button = new JButton("Abfrage 1");	// Button 1 initialisieren ...
		button.addActionListener(this);		// und Ereignis-Listener zufuegen
		
		hBox.add(statement1);	// Textfeld ...
		hBox.add(button);		// und Button ...
		vBox.add(hBox);			// zufuegen
		
		hBox = Box.createHorizontalBox();
		button = new JButton("Abfrage 2");	// Button 1 initialisieren ...
		button.addActionListener(this);		// und Ereignis-Listener zufuegen
		hBox.add(statement2);	// Textfeld ...
		hBox.add(button);		// und Button ...
		vBox.add(hBox);			// zufuegen
		
		add(vBox, BorderLayout.NORTH);		// Textfelder und Buttons zufuegen
		add(center, BorderLayout.CENTER);	// Conteiner fuer Tabelle zufuegen
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		String cmd = arg0.getActionCommand();	// Text des gedrueckten Buttons auslesen

		JTable table = null;	// Tabelle deklarieren
		
		Vector<String> headlineVector = new Vector<String>();	// Collection fuer Tabellenelementnamen
		Vector<Vector<String>> dataVector = new Vector<Vector<String>>();	// Collection fuer ausgelesene Datensaetze
		Vector<String> tmp = null;
		
		for(String s : new String[] {"id", "anrede", "vorname", "nachname", "adresse", "plz", "ort"})	// Headlinevector initialisieren
			headlineVector.add(s);
		
		DBConnection dbConnection = new DBConnection();	// Neues Connection-Objekt instanzieren
		ResultSet rSet = null;							// ResultSet-Objekt nimmt die angefragten Daten auf
				
		dbConnection.setDriver("com.mysql.jdbc.Driver");	// Datenbank-Treiber
		dbConnection.setDbTyp("jdbc:mysql");	// DB-COnnection-Prefix
		dbConnection.setDbPath("localhost");	// Adresse der Datenbank
		dbConnection.setDbPort("3306");			// Port der Datenbank
		dbConnection.setDbName("wvs-java-db");	// Datenbankname
		dbConnection.setDbUser("user");			// Benutzername
		dbConnection.setDbUserPasswd("wvs");	// Kennwort des Benutzers
		dbConnection.setSqlStatement(cmd.contains("1") ? statement1.getText() : statement2.getText());	// SQL-Statement aus Textfeld 1 oder 2 auslesen
		
		rSet = dbConnection.doRequest();	// Abfrage durchfuehren
		
		try
		{
			while(rSet.next())	// Iteriere ueber den RecordSet, solange er bis ans Ende durchlaufen wurde 
			{
				tmp = new Vector<String>();	// temp-Vector, wird mit Tabellendatensatz gefuellt
				
				for(String s : headlineVector)
					tmp.add((String)rSet.getString(s));	// Datensatzelemente in Vector speichern

				dataVector.add(tmp);	// Datensatz speichern
			}
		} catch (SQLException e) { e.printStackTrace(); }

		table = new JTable(dataVector, headlineVector);	// Tabelle erzeugen
		
		center.removeAll();					// Tabelle aus center entfernen
		center.add(new JScrollPane(table));	// Tabelle in Scrollpane packen und in center uebergeben
		center.revalidate();				// center neu zeichnen
	}	
}
