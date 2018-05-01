// ----- //
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.io.*;
// ----- //

/*

CLASSE PEDINA:
Rappresenta uno dei 12 pezzi che il giocatore possiede.
Puo' essere di tipo:
vuoto --> vedi PedinaVuota.class
damone ( o dama ) --> vedi Damone.class


--> implements Serializable <--
Caratteristica BETA 1.1: introdotta la possibilità di salvare e caricare il gioco.
Ogni pedina va infatti scritta in un fine strutturato. [ .DAT ]
*/
public class Pedina implements Serializable
{
	/* -- PERCORSI RELATIVI ALLE IMMAGINI --*/
	protected String initPath = "images/pedina";
	protected String estensione = ".gif";
	protected String finalPath;
	/* -------- */

	protected String color;

	/* --PARAMETRI GRAFICI DELLA PEDINA -- */

	/* ------ */

	// posizione nel canvas ( ovvero nella damiera )
	protected Point posizione;

	// identificatore --> indica in che posiziona si trova: [ da 1 .. 32, sono infatti 32 le caselle giocabili ]
	protected int iD;

	// indica se la Pedina è selezionata
	protected boolean selected;
/*
Pedina(..)

Il costruttore inizializza il percorso del file immagine
a partire dei parametri passati in ingresso: colore e stato.
La stringa extra permette di utilizzare lo stesso costruttore
con le sottoclassi.

*/
	public Pedina(String colore,String extra,boolean isSelected)
	{
		color = colore;
		finalPath = initPath+colore+extra;

		if (isSelected)
			finalPath+="Selected";

		finalPath+=estensione;

		posizione = new Point();
		selected = isSelected;
		return;
	}

	public String getPath()
	{
		return finalPath;
	}

	public String getColor()
	{
		return color;
	}

	public Point getPos()
	{
		return posizione;
	}

	public void setPos(Point x)
	{
		posizione = x;
	}

	public Image getImmagine()
	{
		URL urlPedina = ClassLoader.getSystemResource(finalPath);
		ImageIcon imgIconCard= new ImageIcon(urlPedina);
		Image graphicSymbol = imgIconCard.getImage();

		return graphicSymbol;
	}

	public void setID(int iD)
	{
		this.iD = iD;
	}

	public int getID()
	{
		return iD;
	}

	public boolean isSelected()
	{

		return selected;
	}

}