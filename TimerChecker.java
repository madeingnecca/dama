import java.awt.*;
import java.awt.event.*;
import java.util.TimerTask.*;
import java.util.*;

public class TimerChecker extends TimerTask implements Runnable
{
	private Label location;
	private long time;
	private Date data;

	private ElencoMosse elenco;
	private Damiera damiera;
	private Giocatore giocatore;

	public TimerChecker(Label location,long time,ElencoMosse elenco,Damiera damiera,Giocatore giocatore)
	{
		this.time = time;
		this.location = location;
		data = new Date();
		this.elenco = elenco;
		this.damiera = damiera;
		this.giocatore=giocatore;
	}

	public void run()
	{
		if (time>=0)
		{
			if (time <= 10000)
				location.setForeground(Color.red);

			data.setTime(time);
			String ore = String.valueOf(data.getHours()-1);
			if (ore.length()<2)
				ore="0"+ore;
			String minuti = String.valueOf(data.getMinutes());
			if (minuti.length()<2)
				minuti="0"+minuti;
			String secondi = String.valueOf(data.getSeconds());
			if (secondi.length()<2)
				secondi="0"+secondi;

			location.setText(ore+":"+minuti+":"+secondi+"");
			time-=1000;
		}
		else
		{
			damiera.removeMouseListener(giocatore);
			elenco.setMossa("Tempo scaduto. Risultato: "+giocatore.chiVinceAlTermine());
			cancel();
		}
	}
}