package cardjitsu;

import java.util.ArrayList;
import java.util.Random;

public class JokalariaBot extends Jokalaria {
	
	private static JokalariaBot nireJokalariaBot;

	protected JokalariaBot(String pIzena) 
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
		float randomness = new Random().nextFloat();
		//Elementu berdina kolore desberdina irabazi sahiatu
		if(randomness<=0.65 & (elementuBerdinak(ElementuMota.ELURRA).size()>=2 || elementuBerdinak(ElementuMota.SUA).size()>=2 || elementuBerdinak(ElementuMota.URA).size()>=2)) 
		{
			ArrayList<Karta> elem = elementuBerdinak(ElementuMota.ELURRA);
			if(elem.size()>=2 & koloreDesberdinak(elem).size()>=2) 
			{
				bilatuAukeratzekKarta_ElementuBerdinak_KoloreDesberdinak(elem, koloreDesberdinak(elem));
			}
			
			elem = elementuBerdinak(ElementuMota.SUA);
			if(elem.size()>=2 & koloreDesberdinak(elem).size()>=2) 
			{
				bilatuAukeratzekKarta_ElementuBerdinak_KoloreDesberdinak(elem, koloreDesberdinak(elem));
			}
			
			elem = elementuBerdinak(ElementuMota.URA);
			if(elem.size()>=2 & koloreDesberdinak(elem).size()>=2) 
			{
				bilatuAukeratzekKarta_ElementuBerdinak_KoloreDesberdinak(elem, koloreDesberdinak(elem));
			}
		}
		// Ausazko karta
		else if(karta==null)
		{
			while(!aukeratuta) {
				karta = this.lortuJolastekoKartaPosz(new Random().nextInt(5));
				if(karta.getErabilgarria()) {
					aukeratuta = true;
				}
			}
			this.kenduJolastekoKarta(karta);
		}
		return karta;
	}
	
	//Strategic metodos, elementu berdinak kolore desberdinak irabazi sahiatu
	
	private ArrayList<Karta> elementuBerdinak(ElementuMota pElemetua) 
	{
		ArrayList<Karta> elementuKartak = new ArrayList<Karta>();
		for(int i=0;i<this.gordetakoKartenKantitatea();i++) 
		{
			if(this.lortuGordetakoKartaPosz(i).getElementua()==pElemetua) 
			{
				elementuKartak.add(this.lortuGordetakoKartaPosz(i));
			}
		}
		return elementuKartak;
	}
	
	private ArrayList<KoloreMota> koloreDesberdinak(ArrayList<Karta> p1) 
	{
		ArrayList<KoloreMota> koloreak = new ArrayList<KoloreMota>();
		for(int i=0;i<p1.size();i++) 
		{
			if(!koloreak.contains(p1.get(i).getKolorea()))
			{
				koloreak.add(p1.get(i).getKolorea());
			}
		}
		return koloreak;
	}
	
	private Karta bilatuAukeratzekKarta_ElementuBerdinak_KoloreDesberdinak(ArrayList<Karta> p1, ArrayList<KoloreMota> p2) 
	{
		Karta karta = null;
		for(int i=0;i<5;i++) 
		{
			if(p1.get(1).getElementua().equals(this.lortuJolastekoKartaPosz(i).getElementua())) 
			{
				for(int z=0;z<p2.size();z++) 
				{
					if(p2.get(z)!=this.lortuJolastekoKartaPosz(i).getKolorea()) 
					{
						if(karta.getBalioa()<this.lortuJolastekoKartaPosz(i).getBalioa()) 
						{
							karta = this.lortuJolastekoKartaPosz(i);
						}
					}
				}
			}
		}
		return karta;
	}
}
