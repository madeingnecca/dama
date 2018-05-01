import java.awt.*;
import java.awt.event.*;
import java.util.TimerTask.*;
import java.util.*;



public class Punteggio extends Panel
{
	private String primoGiocatore;
	private String secondoGiocatore;

	private Label l1;
	private Label l2;

	private TextField puntiG1;
	private TextField puntiG2;
	private Color sfondo = new Color(203,220,238);
	private long millisecs;

	private ElencoMosse elenco;
	private Damiera damiera;
	private Giocatore giocatore;
	private Label count_downDisplay;


	public void setPrimo(String s)
	{
		primoGiocatore = s;
	}

	public void setSecondo(String s)
	{
		secondoGiocatore = s;
	}

	public void setPuntiPrimo(int score)
	{
		puntiG1.setText(String.valueOf(score));
	}

	public void setPuntiSecondo(int score)
	{
		puntiG2.setText(String.valueOf(score));
	}

	public Punteggio(int p1,int p2,String primo,String secondo,int minuti)
	{
		this.elenco = elenco;
		this.damiera = damiera;
		this.giocatore=giocatore;
		millisecs = 60000*minuti;
		count_downDisplay = new Label(String.valueOf(millisecs));
		count_downDisplay.setForeground(Color.gray);

		l1 = new Label(primo);
		l2 = new Label(secondo);

		puntiG1 = new TextField(String.valueOf(p1),1);
		puntiG1.setEnabled(false);

		puntiG2 = new TextField(String.valueOf(p2),1);
		puntiG2.setEnabled(false);

		add(l1);
		add(puntiG1);
		add(count_downDisplay);
		add(l2);
		add(puntiG2);
		setBackground(sfondo);
	}

	public Punteggio()
	{
		setBackground(sfondo);
	}

	public Label getTempo()
	{
		return count_downDisplay;
	}

	public long getMilli()
	{
		return millisecs;
	}
}

