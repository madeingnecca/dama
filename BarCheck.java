// ----- //
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
// ----- //

public class BarCheck implements ActionListener
{
	private MainWindow f;
	private Menu menu;
	private Canvas areaG;

	public BarCheck(MainWindow f,Menu m,Canvas c)
	{
		this.f = f;
		menu = m;
		areaG = c;
	}

	public void actionPerformed(ActionEvent e)
	{
		String choice = e.getActionCommand();
		if (choice.equals("Visualizza i crediti"))
		{
			new Credits(menu);
			menu.setEnabled(false);
		}

		else if (choice.equals("Esci"))
			System.exit(0);

		else if (choice.equals("1 giocatore"))
		{
			new SceltaGiocatore(1,f,menu,areaG);
			menu.setEnabled(false);
		}

		else if (choice.equals("2 giocatori"))
		{
			new SceltaGiocatore(2,f,menu,areaG);
			menu.setEnabled(false);
		}

		else if (choice.equals("HELP"))
		{
			try
			{
				new Help(menu);
				menu.setEnabled(false);
			}
			catch (Exception ex)
			{
				return;
			}
		}
		else if (choice.equals("Nuovo"))
		{
			f.setDefault();
			f.getMode().setEnabled(true);
		}

	}


}