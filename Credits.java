//--------------------//
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.net.URL;
//--------------------//

/*
CLASSE CREDITS:
Rappresenta la finestra contenente i crediti
degli autori del gioco.
*/

public class Credits extends Frame
{
	private Menu menu;

	/*
	-----------------------
	costruttore Credits(..)
	-----------------------
	Crea la finestra.
	*/

	public Credits(Menu m)
	{
		super(": CRETIS :");
		menu = m;
		setLayout(new FlowLayout());

		URL url = ClassLoader.getSystemResource("images/dax.gif");

		ImageIcon icon = new ImageIcon(url);
		JLabel img = new JLabel(icon);

		Panel p1 = new Panel();
		Panel p2 = new Panel();
		Panel p3 = new Panel();

		p1.add(img);

		Label l = new Label("Creato da Seno Damiano & Doria Luca, Copyright 2004");
		l.setForeground(Color.black);
		p2.add(l);
		Label email = new Label("damy_belthazor86@yahoo.it // el_doria_xe_grosso@libero.it");
		p3.setForeground(Color.black);
		p3.add(email);

		add(p1);
		add(p2);
		add(p3);
		setBackground(new Color(238,238,238));
		setSize(380,180);
		setLocation(650,200);
		setResizable(false);
		addWindowListener(new WindowCheck(menu));
		setVisible(true);
	}
}