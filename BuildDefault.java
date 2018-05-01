import java.io.*;
import java.awt.*;

public class BuildDefault
{
	private String nomeFile;

	private FileWriter nuovoFile;
	private FileOutputStream f;
	private ObjectOutputStream fOUT;
	private Pedina[][] game = new Pedina[8][8];

	public BuildDefault() throws IOException
	{
		nomeFile="saves/default.dat";
		nuovoFile = new FileWriter(nomeFile);
		f = new FileOutputStream(nomeFile);
		fOUT = new ObjectOutputStream(f);

		/*

		*/

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

		// posizioni nello schermo
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

		/*
		scrivo nel file le 64 pedine
		*/

		for (int i=0;i<8;i++)
			for (int j=0;j<8;j++)
				fOUT.writeObject(game[i][j]);

	}

	public static void main(String[] args) throws IOException
	{
		new BuildDefault();
	}

}