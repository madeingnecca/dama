// ----- //
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
// ----- //

public class UnderCostruction extends Canvas
{
	private URL indirizzoImg = ClassLoader.getSystemResource("images/under.gif");

	private ImageIcon imgIconCard = new ImageIcon(indirizzoImg);

	private Image presentation = imgIconCard.getImage();

	public void paint(Graphics g)
	{
		g.drawImage(presentation,0,0,this);
	}
}