/*
TestConnection: Testklasse fuer DBConnection.

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

package de.openedu.dbconnect.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JFrame;

import de.openedu.dbconnect.connection.DBConnection;

public class TestConnectionGui extends JFrame {

	/**
	 * Konstruktor mit uebergebenem SQL-Statement
	 * @param request
	 */
	public TestConnectionGui()
	{
		super("TestGUI");	// Fenstertitel
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// Festlegen was tun, wenn Fenster-Schliessen-Icon betaetigt wurde
		setSize(700, 480);	// Fenstergroesse

		getContentPane().add(new TestPanel());	// Fenster mit TestPanel-Objekt fuellen, da passiert der Rest
		
		setVisible(true);	// Fenster sichtbar machen
	}
	
	public static void main(String[] args) {
		
		new TestConnectionGui();					// Aufruf des Konstruktors der Testklasse
	}
}
