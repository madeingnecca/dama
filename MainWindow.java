/*---------------------------------------------
.ITIS C.ZUCCANTE

.Autori:Seno Damiano - Doria Luca
.Classe 4ISE

DESCRIZIONE:
Gioco della dama per 1 / 2 giocatori.

VERSIONE BETA MANCANTE DELLA MODALITA 1 VS CPU

---------------------------------------------*/

//-----//
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.net.URL;
//-----//

/*-----//

Classe d'avvio del programma:
rappresenta la finestra principale
in cui il gioco si evolve.

//-----*/


public class MainWindow extends Frame
{
	private MenuBar menuPlayer;
	private Menu gioco;
	private Menu mode;
	private Menu credits;
	private Menu help;
	private Menu options;

	private Punteggio casellaPunteggio;
	private	Canvas damiera;
	private ElencoMosse elencoM;

	private Color sfondoFinestra = new Color(203,220,238);

	/* Parametri di caricamento immagini */

	private URL indirizzoImg = ClassLoader.getSystemResource("images/icona.gif");

	private ImageIcon imgIconCard = new ImageIcon(indirizzoImg);

	/* ------------ */
	private Image iconaFinestra = imgIconCard.getImage();
	// icona della finestra

/*

MainWindow():
Creazione di una finestra principale di default

*/
	public MainWindow()
	{
		super("°D@MA° - versione Beta 1.0");
		setDefault();
	}

/*
void setDefault():
FINESTRA DI DEFAULT
1)Rimozione di tutti i componenti per garantire la possibilità di cominciare un nuovo gioco.
2)Aggiunta di una menubar e della gestione degli eventi per la stessa
NB: La finestra di default contiene solo la menubar e una immagine di presentazione

*/
	public void setDefault()
	{
		removeAll();
		setIconImage(iconaFinestra);
		setLayout(new BorderLayout());
		damiera = new Presentazione();

		menuPlayer = new MenuBar();

		gioco = new Menu("Gioco");
		gioco.add("Nuovo");
		gioco.add("Esci");

		mode = new Menu("Modalità");
		mode.add("1 giocatore");
		mode.add("2 giocatori");
		mode.setEnabled(false);

		credits = new Menu("Credits");
		credits.add("Visualizza i crediti");

		help = new Menu("?");
		help.add("HELP");

		options = new Menu("Opzioni");
		options.add("Salva");
		options.add("Carica");

		menuPlayer.add(gioco);
		menuPlayer.add(mode);
		menuPlayer.add(options);
		menuPlayer.add(credits);
		menuPlayer.add(help);

		gioco.addActionListener(new BarCheck(this,mode,damiera));
		mode.addActionListener(new BarCheck(this,mode,damiera));
		credits.addActionListener(new BarCheck(this,credits,damiera));
		help.addActionListener(new BarCheck(this,help,damiera));

		add(damiera,"Center");

		setMenuBar(menuPlayer);

		setBackground(sfondoFinestra);
		setSize(405,425);
		setLocation(200,200);
		setResizable(false);
		addWindowListener(new WindowCheck());
		setVisible(true);
		setLayout(new BorderLayout());
		setBackground(sfondoFinestra);
		setSize(405,425);

	}

/*

void setToPlay(....):
PREPARAZIONE FINESTRA PER IL GIOCO:
1) La finestra si allunga per poter contenere
	il segna punteggio, la damiera e la descrizione
	della partita.
2) Alla damiera viene assegnato un ascoltatore del mouse

	[vedi classi Damiera, Punteggio ,ElencoMosse e Giocatore]

*/
	public void setToPlay(String p1,String p2,String cP1)
	{
		setSize(405,600);
		setLayout(new BorderLayout());
		elencoM.setMossa("Iniziato un nuovo gioco.");
		add(damiera,"Center");
		add(casellaPunteggio,"North");
		add(elencoM,"South");

		Giocatore x = new Giocatore(this,p1,p2,cP1,(Damiera)damiera,casellaPunteggio,elencoM,casellaPunteggio.getTempo(),casellaPunteggio.getMilli());

		damiera.addMouseListener(x);
		setVisible(true);
	}

	public void setPunteggio(Punteggio A)
	{
		casellaPunteggio = A;
	}

	public void setDamiera(Canvas A)
	{
		remove(damiera);
		damiera = A;
		A.paint(getGraphics());

	}

	public void setElencoMosse(ElencoMosse A)
	{
		elencoM = A;
	}

	public Menu getMode()
	{
		return mode;
	}


	public static void main(String[] dax)
	{
		new MainWindow();
	}


}