
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class ButtonCheck implements ActionListener
{
	private Frame f;
	private MainWindow f2;
	private Menu giocatore;
	private Canvas areaGioco;
	private TextField nomeG1;
	private TextField nomeG2;
	private Choice coloreG1;
	private TextField tempo;
	private boolean dueGiocatori;

	public ButtonCheck(Frame f,MainWindow main,Menu g,Canvas a,boolean dueGiocatori,TextField G1,TextField G2,Choice c,TextField tempo)
	{
		this.f=f;
		f2 = main;
		giocatore = g;
		areaGioco = a;
		nomeG1 = G1;
		nomeG2 = G2;
		this.coloreG1 = c;
		this.dueGiocatori = dueGiocatori;
		this.tempo = tempo;
	}

	public void actionPerformed(ActionEvent e)
	{
		String bottone = e.getActionCommand();
		String colorP1=null;
		boolean isNero = coloreG1.getSelectedIndex() == 0;
		boolean isBianco = coloreG1.getSelectedIndex() == 1;

		if (bottone.equals("Accetta e gioca!") &&  isNero ||  isBianco )
		{
			int tempoPartita=0;
			try
			{
				tempoPartita = Integer.valueOf(tempo.getText()).intValue();
			}
			catch (NumberFormatException xxx)
			{
				tempo.setText("ERRORE");
				return;
			}

			areaGioco.setEnabled(false);
			f.dispose();

			if (isNero)
				colorP1="Nera";
			else
				colorP1="Bianca";

			if (dueGiocatori)
			{
				giocatore.setEnabled(false);
				if (colorP1.equals("Bianca"))
					f2.setPunteggio(new Punteggio(0,0,nomeG1.getText(),nomeG2.getText(),tempoPartita));
				else
					f2.setPunteggio(new Punteggio(0,0,nomeG2.getText(),nomeG1.getText(),tempoPartita));

				Damiera x = new Damiera();
				f2.setDamiera(x);
				f2.setElencoMosse(new ElencoMosse());
				f2.setToPlay(nomeG1.getText(),nomeG2.getText(),colorP1);
			}
			else
			{
				UnderCostruction x = new UnderCostruction();
				f2.setDamiera(x);
				f2.add(x);
				giocatore.setEnabled(true);
				f2.setVisible(true);
			}
		}
	}
}
