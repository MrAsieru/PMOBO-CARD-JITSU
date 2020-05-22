package cardjitsu;

import java.util.ArrayList;
import java.util.Random;

public class JokalariaBotEasy extends Jokalaria {

	private static JokalariaBotEasy nireJokalariaBot;
	
	protected JokalariaBotEasy(String pIzena)
	{
		super(pIzena);
	}
	
	public static JokalariaBotEasy getNireJokalaria(String pIzena) 
	{
		if(nireJokalariaBot==null) 
		{
			nireJokalariaBot = new JokalariaBotEasy(pIzena);
		}
		return nireJokalariaBot;
	}
	
	public Karta kartaAukeratu(JokalariaLokala j) 
	{
		boolean aukeratuta = false;
		Karta karta = (Karta) new KartaNormala(ElementuMota.ELURRA,0,KoloreMota.BERDEA);
		if(karta.getBalioa()==0)
		{
			while(!aukeratuta) {
				karta = this.lortuJolastekoKartaPosz(new Random().nextInt(5));
				if(karta.getErabilgarria()) {
					aukeratuta = true;
				}
			}
		}
		this.kenduJolastekoKarta(karta);
		return karta;
	}
}
