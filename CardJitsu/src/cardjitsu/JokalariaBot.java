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
		if(randomness<=0.75 & (sortuMatrizeKartakElemDes().size()!=0 || sortuMatrizeKartakElemBer().size()!=0)) 
		{
			kartaErabaki(sortuMatrizeKartakElemDes());
			kartaErabaki(sortuMatrizeKartakElemBer());
		}
		// Ausazko karta
		if(karta==null)
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
	
	private Karta kartaErabaki(ArrayList<ArrayList<Karta>> p1) 
	{
		Karta karta = (Karta) new KartaNormala(ElementuMota.ELURRA,0,KoloreMota.BERDEA);
		for(int i=0;i<p1.size();i++) 
		{
			for(int x=0;x<5;x++) 
			{
				if(p1.get(i).get(0).getElementua()!=p1.get(i).get(1).getElementua() &
						p1.get(i).get(0).getElementua()!=lortuJolastekoKartaPosz(x).getElementua() &
						p1.get(i).get(1).getElementua()!=lortuJolastekoKartaPosz(x).getElementua() &
						p1.get(i).get(0).getKolorea()!=lortuJolastekoKartaPosz(x).getKolorea() &
						p1.get(i).get(1).getKolorea()!=lortuJolastekoKartaPosz(x).getKolorea() &
						lortuJolastekoKartaPosz(x).getBalioa()>karta.getBalioa()) 
				{
					karta = lortuJolastekoKartaPosz(x);
				}
				if(p1.get(i).get(0).getElementua()==p1.get(i).get(1).getElementua() &
						p1.get(i).get(0).getElementua()==lortuJolastekoKartaPosz(x).getElementua() &
						p1.get(i).get(1).getElementua()==lortuJolastekoKartaPosz(x).getElementua() &
						p1.get(i).get(0).getKolorea()!=lortuJolastekoKartaPosz(x).getKolorea() &
						p1.get(i).get(1).getKolorea()!=lortuJolastekoKartaPosz(x).getKolorea() &
						lortuJolastekoKartaPosz(x).getBalioa()>karta.getBalioa()) 
				{
					karta = lortuJolastekoKartaPosz(x);
				}
			}
		}
		return karta;
	}
	
	private ArrayList<ArrayList<Karta>> sortuMatrizeKartakElemDes()
	{
		ArrayList<ArrayList<Karta>> LisKartak = new ArrayList<ArrayList<Karta>>();
		for(int i=0;i<gordetakoKartenKantitatea();i++) 
		{
			for(int x=0;x<gordetakoKartenKantitatea();x++) 
			{
				if(lortuGordetakoKartaPosz(i).getElementua()!=lortuGordetakoKartaPosz(x).getElementua() &
						lortuGordetakoKartaPosz(i).getKolorea()!=lortuGordetakoKartaPosz(x).getKolorea() &
						x!=i) 
				{
					ArrayList<Karta> kartak = new ArrayList<Karta>();
					kartak.add(lortuGordetakoKartaPosz(i));
					kartak.add(lortuGordetakoKartaPosz(x));
					LisKartak.add(kartak);
				}
			}
		}
		return LisKartak;
	}
	
	private ArrayList<ArrayList<Karta>> sortuMatrizeKartakElemBer()
	{
		ArrayList<ArrayList<Karta>> LisKartak = new ArrayList<ArrayList<Karta>>();
		for(int i=0;i<gordetakoKartenKantitatea();i++) 
		{
			for(int x=0;x<gordetakoKartenKantitatea();x++) 
			{
				if(lortuGordetakoKartaPosz(i).getElementua()==lortuGordetakoKartaPosz(x).getElementua() &
						lortuGordetakoKartaPosz(i).getKolorea()!=lortuGordetakoKartaPosz(x).getKolorea() &
						x!=i) 
				{
					ArrayList<Karta> kartak = new ArrayList<Karta>();
					kartak.add(lortuGordetakoKartaPosz(i));
					kartak.add(lortuGordetakoKartaPosz(x));
					LisKartak.add(kartak);
				}
			}
		}
		return LisKartak;
	}
}
