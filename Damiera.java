// ----- //
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
// ----- //

/*
CLASSE DAMIERA:
Rappresenta il campo di gioco ( la damiera ).

*/
public class Damiera extends Canvas
{
	/*
	Matrice di 64 oggetti di classe Pedina.
	*/

	private Pedina[][] game = new Pedina[8][8];

	/*
	-------------------
	void setPositions()
	-------------------
	Inizializzo le posizioni sullo schermo di ogni oggetto Pedina.

	*/
	public void setPositions()
	{
		int gap = 50;
		int posX=0;
		int posY=0;

		for (int i=0;i<8;i++)
		{
			posX=0;
			for (int j=0;j<8;j++)
			{
				game[i][j].setPos(new Point(posX,posY));
				posX+=gap;
			}
			posY+=gap;
		}
	}

	/*
	-----------------
	void setDefault()
	-----------------
	Inizializzazione Damiera di partenza.
	( sopra i bianchi, pedine vuote in mezzo e sotto i neri )

	*/
	public void setDefault()
	{
		int ctrl;
		int count=1;

		// Posizioni del bianco

		for (int i=0;i<3;i++)
		{
			ctrl=0;
			if (i%2==0)
				ctrl = 0;
			else
				ctrl = 1;

			for (int j=0;j<8;j++)
			{
				if (ctrl%2==0)
				{
					game[i][j] = new Pedina("Bianca","",false);
					game[i][j].setID(count);
					count++;
				}
				else
					game[i][j] = new PedinaVuota("Bianca");
				ctrl++;
			}
		}

		// Posizioni libere
		ctrl=0;

		for (int i=3;i<5;i++)
		{
			ctrl=0;
			if (i%2==0)
				ctrl = 0;
			else
				ctrl = 1;

			for (int j=0;j<8;j++)
			{
				if (ctrl%2==0)
				{
					game[i][j] = new PedinaVuota("Nera");
					game[i][j].setID(count);
					count++;
				}
				else
					game[i][j] = new PedinaVuota("Bianca");
				ctrl++;
			}
		}

		// Posizioni del nero
		ctrl=0;

		for (int i=5;i<8;i++)
		{
			ctrl=0;
			if (i%2==0)
				ctrl = 0;
			else
				ctrl = 1;

			for (int j=0;j<8;j++)
			{
				if (ctrl%2==0)
				{
					game[i][j] = new Pedina("Nera","",false);
					game[i][j].setID(count);
					count++;
				}
				else
					game[i][j] = new PedinaVuota("Bianca");
				ctrl++;
			}
		}
	}

	/*
	-----------------
	paint(Graphics g)
	-----------------
	Disegna la situazione della damiera descritta in game[][]
	Scrive inoltre il numero della pedina [1..32].
	*/
	public void paint(Graphics g)
	{
		int offsetCasella=8;// a che distanza viene scritto il numero pedina
		int ctrl=0;

		for (int i=0;i<8;i++)
		{
			if (i%2==0)
				ctrl=0;
			else
				ctrl=1;

			for (int j=0;j<8;j++)
			{
				if (ctrl%2==0)
				{
					g.drawImage(game[i][j].getImmagine(),game[i][j].getPos().x,game[i][j].getPos().y,this);
					g.setColor(Color.white);
					g.setFont(new Font("TimesRoman",Font.PLAIN,9));
					g.drawString(String.valueOf(game[i][j].getID()),game[i][j].getPos().x+offsetCasella-5,game[i][j].getPos().y+offsetCasella);
				}
				else
					g.drawImage(game[i][j].getImmagine(),game[i][j].getPos().x,game[i][j].getPos().y,this);
				ctrl++;
			}
		}
		g.setColor(Color.black);
		g.drawLine(0,0,400,0);
		g.drawLine(0,400,400,400);

	}
	/*
	---------------------
	costruttore Damiera()
	---------------------
	Richiamo setDefault e setPositions per
	inizializzare la Damiera di default.

	*/
	public Damiera()
	{
		setDefault();
		setPositions();
	}
	/*
	------------------
	void setPedina(..)
	------------------
	Modifico la pedina contenuta in game[x,y] con la pedina A.
	*/
	public void setPedina(Pedina A,int x,int y)
	{
		game[x][y] = A;
	}

	/*
	--------------------
	Pedina getPedina(..)
	--------------------
	Ritorna la pedina in posizione game[x,y]
	*/
	public Pedina getPedina(int x,int y)
	{
		return game[x][y];
	}
}