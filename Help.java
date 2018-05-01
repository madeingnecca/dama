//--------------------//
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.net.URL;
import java.io.*;
//--------------------//

public class Help extends Frame
{

	private Menu menu;

	private URL indirizzoImg = ClassLoader.getSystemResource("images/punto.gif");

	private ImageIcon imgIconCard = new ImageIcon(indirizzoImg);

	private Image iconaFinestra = imgIconCard.getImage();

	private String leggiFileTxt(BufferedReader fIN)
	throws Exception
	{
		String temp="";
		String contenuto="";
		temp = fIN.readLine();
		while (temp != null)
		{
			contenuto+=temp+"\n";
			temp = fIN.readLine();
		}
		return contenuto;
	}

	public Help(Menu m)
	throws Exception
	{

		super("Guida");
		menu = m;
		setIconImage(iconaFinestra);

		setLayout(new FlowLayout());
		setBackground(new Color(238,238,238));

		FileReader f = new FileReader("docs/help.txt");
		BufferedReader fIN = new BufferedReader(f);

		Panel p = new Panel();
		Panel p2 = new Panel();


		TextArea contenutoHelp = new TextArea(20,66);
		contenutoHelp.setText(leggiFileTxt(fIN));
		contenutoHelp.setEditable(false);
		contenutoHelp.setBackground(Color.white);

		p2.add(new Label("GUIDA AL GIOCO:"));
		p.add(contenutoHelp);

		add(p2);
		add(p);
		setSize(500,400);
		setLocation(690,400);
		setResizable(false);
		addWindowListener(new WindowCheck(menu));
		setVisible(true);
	}
}