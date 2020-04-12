package cardjitsu;

import java.util.Random;

public class JokalariaBot extends Jokalaria {
	
	private static JokalariaBot nireJokalariaBot;

	private JokalariaBot(String pIzena) 
	{
		super(pIzena);
	}
	
	public static JokalariaBot getNireJokalaria(String pIzena) 
	{
		if(nireJokalariaBot==null) 
		{
			nireJokalariaBot = new JokalariaBot(pIzena);
		}
		return nireJokalariaBot;
	}
	
	public static JokalariaBot getNireJokalaria() 
	{
		if(nireJokalariaBot==null) 
		{
			nireJokalariaBot = new JokalariaBot("Sensei");
		}
		return nireJokalariaBot;
	}
	
	public Karta kartaAukeratu() 
	{
		boolean aukeratuta = false;
		Karta karta = null;
		int saiakerak = 0;
		while(!aukeratuta) {
			karta = this.lortuJolastekoKartaPosz(new Random().nextInt(5));
			saiakerak++;
			if(karta.getErabilgarria()) {
				aukeratuta = true;
			} else {
				if(saiakerak > 4) {
					//TODO exception
				}
			}
		}
		return karta;
	}
}
