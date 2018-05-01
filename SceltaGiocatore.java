//--------------------//
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.net.URL;
//--------------------//

public class SceltaGiocatore extends Frame
{
	private Choice color;

	public SceltaGiocatore(int nPlayers,MainWindow principale, Menu m,Canvas c)
	{
		super("Scelta giocatore:");
		boolean doppio = false;
		Panel p1 = new Panel();
		Panel p2 = new Panel();
		Panel p3 = new Panel();
		Panel p4 = new Panel();

		setLayout(new FlowLayout());
		Label play1 = new Label("Giocatore 1");
		Label play2 = new Label("Giocatore 2");
		TextField name1 = new TextField("",4);
		TextField name2 = new TextField("",4);
		TextField tempo = new TextField("10");
		Label time = new Label("Durata in minuti:");
		color = new Choice();


		Button accetta = new Button("Accetta e gioca!");


		p1.add(play1);
		p1.add(name1);
		p1.add(new Label("Colore G1"));
		color.addItem("Nero");
		color.addItem("Bianco");
		p1.add(color);

		if (nPlayers == 2)
		{
			p2.add(play2);
			p2.add(name2);
			doppio = true;
		}
		else
			name2.setText("CPU");

		p3.add(time);
		p3.add(tempo);
		p4.add(accetta);


		add(p1);
		add(p2);
		add(p3);
		add(p4);

		accetta.addActionListener(new ButtonCheck(this,principale,m,c,doppio,name1,name2,color,tempo));

		setBackground(new Color(238,238,238));
		setSize(300,150);
		setLocation(650,200);
		setResizable(false);
		addWindowListener(new WindowCheck(m));
		setVisible(true);
	}
}