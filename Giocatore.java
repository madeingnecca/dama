// ----- //
//import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.*;
import java.util.TimerTask.*;
import java.util.*;
// ----- //

/*
CLASSE GIOCATORE:
Rappresenta il giocatore e le sue mosse.
*/
public class Giocatore implements MouseListener,Runnable
{
	private MainWindow frameP;
	private Punteggio elPunteggio;
	private ElencoMosse elMosse;
	private String color;
	private int chiTocca;
	private int faseTurno;
	private Damiera damiera;
	private int posX;
	private int posY;
	private int oldX;
	private int oldY;
	private Pedina temp=null;
	private Pedina old=null;
	private int pedineNere=12;
	private int pedineBianche=12;
	private String player1;
	private String player2;
	private String playerToDisplay;
	private int presaMultipla;
	private int pedineTemp;
	private Timer superTimer;
	private Label count_downDisplay;
	private long millisecs;

	public String chiVinceAlTermine()
	{
		String risultato;

		if (pedineNere == pedineBianche)
			risultato = "Pari, grossi tutti do";
		else
		{
			if (pedineNere > pedineBianche)
				risultato = "Vince "+player2;
			else
				risultato = "Vince "+player1;
		}
		return risultato;
	}

	public void run()
	{
		TimerChecker x = new TimerChecker(count_downDisplay,millisecs,elMosse,damiera,this);
		superTimer.schedule(x,0,1000);
	}

	private boolean canMove(Pedina A)
	{
		boolean altoSx,bassoSx,altoDx,bassoDx;
		Pedina temp2;
		// controllo in alto a sinistra (pedine nere e damoni)
		try
		{
			temp2 =(damiera.getPedina((A.getPos().y-50)/50,(A.getPos().x-50)/50));
			altoSx = (temp2 instanceof PedinaVuota) && temp2.getColor().equals("Nera");
		}
		catch (IndexOutOfBoundsException e)
		{
			altoSx = false;
		}
		// controllo in alto a destra (pedine nere e damoni)
		try
		{
			temp2 =(damiera.getPedina((A.getPos().y-50)/50,(A.getPos().x+50)/50));
			altoDx = (temp2 instanceof PedinaVuota) && temp2.getColor().equals("Nera");
		}
		catch (IndexOutOfBoundsException e)
		{
			altoDx = false;
		}
		// controllo in basso a sinistra (pedine bianche e damoni)
		try
		{
			temp2 =(damiera.getPedina((A.getPos().y+50)/50,(A.getPos().x-50)/50));
			bassoSx =(temp2 instanceof PedinaVuota) && temp2.getColor().equals("Nera");
		}
		catch (IndexOutOfBoundsException e)
		{
			bassoSx = false;
		}
		// controllo in basso a destra (pedine bianche e damoni)
		try
		{
			temp2 =(damiera.getPedina((A.getPos().y+50)/50,(A.getPos().x+50)/50));
			bassoDx =(temp2 instanceof PedinaVuota) && temp2.getColor().equals("Nera");
		}
		catch (IndexOutOfBoundsException e)
		{
			bassoDx = false;
		}


		if (A instanceof Damone)
			return  (altoSx || bassoSx || altoDx || bassoDx);
		else
		{
			if (A.getColor().equals("Nera"))
				return altoSx || altoDx;
			else
				return bassoSx || bassoDx;
		}

	}

	private int pedineInTrappola(String colore)
	{
		int count=0;
		Pedina temporary;

		for (int i=0;i<8;i++)
		{
			for (int j=0;j<8;j++)
			{
				temporary = damiera.getPedina(i,j);
				if (!(temporary instanceof PedinaVuota) && !canMove(temporary) && !puoMangiareAncora(temporary) && temporary.getColor().equals(colore))
					count++;
			}
		}
		return count;
	}

	/*
	Il metodo restituisce "true" se la pedina può effettivamente agire,
	in quanto controlla se ci sono pedine che possono mangiare
	( visto che la mangiata è obbligatoria )
	*/
	private boolean puoAgire(Pedina A)
	{
		boolean esito = false;
		boolean trovato = false;
		Pedina temporary;

		for (int i=0;i<8 && !trovato;i++)
		{
			for (int j=0;j<8 && !trovato;j++)
			{
				temporary = damiera.getPedina(i,j);
				if (!(temporary instanceof PedinaVuota) && puoMangiareAncora(temporary) && A.getColor().equals(temporary.getColor()))
					trovato = true;
			}
		}

		esito = !trovato;
		return esito;
	}

	/*
	Scrive la data di quando è stata eseguita la mossa nel formato hh:mm:ss
	*/
	private String makeDate()
	{
		Date data = new Date();
		return String.valueOf(data.getHours())+":"+String.valueOf(data.getMinutes())+":"+String.valueOf(data.getSeconds())+"   ";
	}

	/*
	Restituisce vero se la partita è finita.
	*/

	private boolean finePartita()
	{
		return (pedineNere == 0 || pedineBianche == 0);
	}

	/*
	Aggiorna la damiera
	*/
	private void updateDamiera()
	{
		frameP.setDamiera(damiera);
		frameP.add(damiera);
	}

	private void gestoreTurno()
	{
		if (finePartita())
		{
			elMosse.setMossa(makeDate()+playerToDisplay+" ( "+color+" ) xe grosso e vince! ");
			damiera.removeMouseListener(this);
			elMosse.setMossa("Vai su gioco / nuovo  per cominciare una nuova partita");
		}
		else
		{
			if (chiTocca==0)
			{
				color ="Nera";
				chiTocca++;
				playerToDisplay = player2;
				pedineTemp = pedineNere;
			}
			else
			{
				color = "Bianca";
				chiTocca = 0;
				playerToDisplay = player1;
				pedineTemp = pedineBianche;
			}
		}
	}



	/*
		Il metodo puoMuoversi(..) indica se la pedina "to" è distante n caselle dalla pedina "from".
		true se è vero, false altrimenti.
	*/

	private boolean puoMuoversi(Pedina from , Pedina to,int n)
	{
		boolean esito = false;
		boolean bianco = (from.getPos().x + n*50 == to.getPos().x && from.getPos().y + n*50 == to.getPos().y
						 || from.getPos().x - n*50 == to.getPos().x && from.getPos().y + n*50 == to.getPos().y);
		boolean nero = (from.getPos().x - n*50 == to.getPos().x && from.getPos().y - n*50 == to.getPos().y
						 || from.getPos().x + n*50 == to.getPos().x && from.getPos().y - n*50 == to.getPos().y);

		if (!(from instanceof Damone))
		{
			if (from.getColor().equals("Bianca"))
				esito = bianco;
			else
				esito = nero;
		}
		else
			esito = bianco || nero;

		return esito;
	}

	/*
		Restituisce vero se la Pedina predator è un tipo di pedina che puo'
		mangiare la pedina victim.
	*/

	private boolean isMangiabile(Pedina predator,Pedina victim)
	{
		boolean esito = false;
		if (!(victim instanceof PedinaVuota))
		{
			esito = (!(victim.getColor().equals(color)) &&(
					predator instanceof Damone && !(victim instanceof Damone)
					|| (predator instanceof Damone && victim instanceof Damone)
					|| !(predator instanceof Damone) && !(victim instanceof Damone)));
		}
		return esito;
	}

	private boolean puoMangiareAncora(Pedina A)
	{
		boolean altoSx,bassoSx,altoDx,bassoDx;
		Pedina temp;
		Pedina temp2;
		// controllo in alto a sinistra (pedine nere e damoni)
		try
		{
			temp = (damiera.getPedina((A.getPos().y-100)/50,(A.getPos().x-100)/50));
			temp2 =(damiera.getPedina((A.getPos().y-50)/50,(A.getPos().x-50)/50));
			altoSx = temp instanceof PedinaVuota && temp.getColor().equals("Nera") && isMangiabile(A,temp2);
		}
		catch (IndexOutOfBoundsException e)
		{
			altoSx = false;
		}
		// controllo in alto a destra (pedine nere e damoni)
		try
		{
			temp = (damiera.getPedina((A.getPos().y-100)/50,(A.getPos().x+100)/50));
			temp2 =(damiera.getPedina((A.getPos().y-50)/50,(A.getPos().x+50)/50));
			altoDx = temp instanceof PedinaVuota && temp.getColor().equals("Nera") && isMangiabile(A,temp2);
		}
		catch (IndexOutOfBoundsException e)
		{
			altoDx = false;
		}
		// controllo in basso a sinistra (pedine bianche e damoni)
		try
		{
			temp = (damiera.getPedina((A.getPos().y+100)/50,(A.getPos().x-100)/50));
			temp2 =(damiera.getPedina((A.getPos().y+50)/50,(A.getPos().x-50)/50));
			bassoSx = temp instanceof PedinaVuota && temp.getColor().equals("Nera") && isMangiabile(A,temp2);
		}
		catch (IndexOutOfBoundsException e)
		{
			bassoSx = false;
		}
		// controllo in basso a destra (pedine bianche e damoni)
		try
		{
			temp = (damiera.getPedina((A.getPos().y+100)/50,(A.getPos().x+100)/50));
			temp2 =(damiera.getPedina((A.getPos().y+50)/50,(A.getPos().x+50)/50));
			bassoDx = temp instanceof PedinaVuota && temp.getColor().equals("Nera") && isMangiabile(A,temp2);
		}
		catch (IndexOutOfBoundsException e)
		{
			bassoDx = false;
		}


		if (A instanceof Damone)
			return  (altoSx || bassoSx || altoDx || bassoDx);
		else
		{
			if (A.getColor().equals("Nera"))
				return altoSx || altoDx;
			else
				return bassoSx || bassoDx;
		}

	}

	public Giocatore(MainWindow frameP,String p1,String p2,String cP1,Damiera damiera,Punteggio elPunteggio,ElencoMosse elMosse,Label count_downDisplay,long millisecs)
	{
		superTimer = new Timer();
		this.frameP = frameP;
		color = "Bianca";
		chiTocca = 0;
		faseTurno = 0;
		presaMultipla=0;
		pedineTemp = pedineBianche;
		this.damiera=damiera;
		this.elPunteggio=elPunteggio;
		this.elMosse = elMosse;
		this.count_downDisplay = count_downDisplay;
		this.millisecs = millisecs;

		player1 = p1;
		if (cP1.equals("Bianca"))
		{
			player2 = p2;
		}
		else
		{
			player1 = p2;
			player2 = p1;
		}
		playerToDisplay = player1;
		elMosse.setMossa(makeDate()+"Inizia "+playerToDisplay+" ( "+color+" )");
		run();
	}

	public void mouseClicked(MouseEvent e)
	{
		String tempColor;
		faseTurno++;
		posX = e.getX()/50;
		posY = e.getY()/50;
		// correzione bug //
		try
		{
			temp = damiera.getPedina(posY,posX);
		}
		catch (IndexOutOfBoundsException out)
		{
			faseTurno--;
			return;
		}
		tempColor = temp.getColor();


		/*
			Il turno si divide in due fasi: selezione e azione.
			Quando la variabile faseTurno assume il valore 1, significa che
			il giocatore (bianco o nero che sia) è nella fase di selezione
		*/



		if (faseTurno == 1)
		{
			/*
				S E L E Z I O N E
			*/
			if (!(temp instanceof PedinaVuota) && tempColor.equals(color))
			{
				old = temp;
				oldX = posX;
				oldY = posY;
				elMosse.setMossa(makeDate()+playerToDisplay+" ( "+color+" ) seleziona pedina in "+temp.getID());
				if (!(temp instanceof Damone))
					temp = new Pedina(color,"",true);
				else
					temp = new Damone(color,true);
				temp.setID(old.getID());
				temp.setPos(new Point(posX*50,posY*50));
				damiera.setPedina(temp,posY,posX);
				updateDamiera();
			}
			else
			{
				faseTurno--;
				elMosse.setMossa("Attenzione, mossa non consentita!");
			}
		}
		/*
			Nello stesso modo quando faseTurno vale 2, il giocatore può muoversi o mangiare,
			a seconda del tipo di pedina e della situazione della damiera.
		*/

		else if (faseTurno == 2)
		{
			/*
				M O V I M E N T O
			*/
			if (temp instanceof PedinaVuota && tempColor.equals("Nera") && puoMuoversi(old,temp,1) && presaMultipla==0 && puoAgire(old))
			{
				elMosse.setMossa(makeDate()+playerToDisplay+" ( "+color+" ) sposta "+old.getID()+" in "+temp.getID());
				int t = temp.getID();
				temp = new PedinaVuota("Nera");
				temp.setID(old.getID());
				temp.setPos(new Point(oldX*50,oldY*50));
				damiera.setPedina(temp,oldY,oldX);
				faseTurno=0;

				if (old.getColor().equals("Nera"))
				{
					if (posY==0)
						old = new Damone("Nera",false);
				}
				else
				{
					if (posY==7)
						old = new Damone("Bianca",false);
				}

				old.setID(t);
				old.setPos(new Point(posX*50,posY*50));
				damiera.setPedina(old,posY,posX);
				updateDamiera();
				gestoreTurno();
			}
			else
			{
				/*
					P R E S E   S I N G O L E
				*/
				if (temp instanceof PedinaVuota && tempColor.equals("Nera") && puoMuoversi(old,temp,2))
				{
					Pedina backUp=null;
					int multy;
					if (old.getPos().x > temp.getPos().x)
						multy=1;
					else
						multy=-1;

					if ((!(old instanceof Damone) && old.getColor().equals("Nera")) || (old instanceof Damone && old.getPos().y > temp.getPos().y))
						backUp = damiera.getPedina((temp.getPos().y+50)/50,(temp.getPos().x+multy*50)/50);

					else if ((!(old instanceof Damone) && old.getColor().equals("Bianca")) || (old instanceof Damone && old.getPos().y < temp.getPos().y))
						backUp = damiera.getPedina((temp.getPos().y-50)/50,(temp.getPos().x+multy*50)/50);

					if (!(backUp instanceof PedinaVuota) && isMangiabile(old,backUp))
					{
						int t = old.getID();
						// la mia avanza
						if (old.getColor().equals("Nera"))
						{
							if (posY==0)
								old = new Damone("Nera",false);
						}
						else
						{
							if (posY==7)
								old = new Damone("Bianca",false);
						}
						old.setPos(new Point(posX*50,posY*50));
						old.setID(temp.getID());
						damiera.setPedina(old,posY,posX);
						int x = posX;
						int y = posY;

						// La mia pedina viene sostituita da una pedina nera
						Pedina temp2 = new PedinaVuota("Nera");
						temp2.setID(t);
						temp2.setPos(new Point(oldX*50,oldY*50));
						damiera.setPedina(temp2,oldY,oldX);

						// Elimino quella avversaria
						temp = new PedinaVuota("Nera");
						posX = backUp.getPos().x;
						posY = backUp.getPos().y;
						temp.setID(backUp.getID());
						temp.setPos(new Point(posX,posY));
						damiera.setPedina(temp,posY/50,posX/50);

						if (backUp.getColor().equals("Nera"))
							pedineNere--;
						else
							pedineBianche--;

						elPunteggio.setPuntiPrimo(12-pedineNere);
						elPunteggio.setPuntiSecondo(12-pedineBianche);

						elMosse.setMossa(makeDate()+playerToDisplay+" ( "+color+" ) mangia pedina in "+backUp.getID()+" con "+t);

						updateDamiera();
						presaMultipla = 0;
						/*

						P R E S E   M U L T I P L E

						*/
						// se la mia puo' mangiare un' altra volta
						if (puoMangiareAncora(old))
						{
							oldX = old.getPos().x/50;
							oldY = old.getPos().y/50;
							presaMultipla = 1;
							faseTurno--;
							elMosse.setMossa(makeDate()+playerToDisplay+" ( "+color+" ) può mangiare ancora con pedina in "+old.getID()+"!");
						}
						else
						{
							gestoreTurno();
							faseTurno=0;
						}

						if (pedineInTrappola(color) == pedineTemp)
						{
							elMosse.setMossa(makeDate()+playerToDisplay+" ( "+color+" ) è in trappola e perde!");
							damiera.removeMouseListener(this);
							return;
						}

					}
					else
					{
						faseTurno--;

					}

				}
				else
				{
					if (!puoAgire(temp))
						elMosse.setMossa("La presa è obbligatoria!");

					faseTurno--;
					if (temp.isSelected())
					{
						elMosse.setMossa(makeDate()+playerToDisplay+" ( "+color+" ) deseleziona pedina in "+old.getID());
						if (!(temp instanceof Damone))
							temp = new Pedina(color,"",false);
						else
							temp = new Damone(color,false);
						temp.setID(old.getID());
						temp.setPos(new Point(posX*50,posY*50));
						damiera.setPedina(temp,posY,posX);
						updateDamiera();
						faseTurno--;
					}
				}
			}

			if (!(finePartita()) && presaMultipla==0)
				elMosse.setMossa(makeDate()+"Tocca a "+playerToDisplay+" ( "+color+" )");
		}
	}

	public void mousePressed(MouseEvent e)
	{}

	public void mouseReleased(MouseEvent e)
	{}

	public void mouseEntered(MouseEvent e)
	{}

	public void mouseExited(MouseEvent e)
	{}

}

