import java.awt.*;
import java.awt.event.*;
public class WindowCheck implements WindowListener
{
	private Menu menu;

	public WindowCheck(Menu m)
	{
		menu = m;
	}

	public WindowCheck()
	{}

	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}

	public void windowOpened(WindowEvent e)
	{

	}

	public void windowClosed(WindowEvent e)
	{
		//e.getWindow().dispose();
	}


	// Assegno all'evento "chiusura finestra" l'azione di terminazione del programma

	public void windowClosing(WindowEvent e)
	{
		if (e.getWindow() instanceof Credits || e.getWindow() instanceof SceltaGiocatore || e.getWindow() instanceof Help)
		{
			e.getWindow().dispose();
			menu.setEnabled(true);
		}
		else
			System.exit(0);
	}
}