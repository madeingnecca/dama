//-----//
import java.awt.*;
//-----//
/*

CLASSE ELENCOMOSSE
Gestisce i messaggi vari durante la partita.

*/

public class ElencoMosse extends Panel
{
	/*
	I messaggi vengono scritti in un'area di testo
	*/
	private TextArea elenco = new TextArea(6,50);
	private Color sfondo = new Color(203,220,238);

	public ElencoMosse()
	{
		add(elenco);
		setBackground(sfondo);
		elenco.setBackground(Color.white);
		elenco.setEditable(false);
		elenco.append("~~~ Dama by Dax & Doc ~~~ \n");
	}

	/*
	Scrive la mossa eseguita ( messaggi vari in generale )
	*/
	public void setMossa(String mossaEseguita)
	{
		elenco.append(mossaEseguita+"\n");
	}
}