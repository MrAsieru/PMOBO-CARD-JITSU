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
		Karta karta = (Karta) new KartaNormala(ElementuMota.ELURRA,-1,KoloreMota.BERDEA);
		float randomness = new Random().nextFloat();
		float randomness1 = new Random().nextFloat();
		
		//Elementu berdina edo derberdina eta kolore desberdineko bi karta izatean, beste karta batekin irabazi ahala badu, hori bota
		if(randomness1<=0.75 & (sortuMatrizeKartakElemDes().size()!=0 || sortuMatrizeKartakElemBer().size()!=0)) 
		{
			kartaErabaki(sortuMatrizeKartakElemDes());
			kartaErabaki(sortuMatrizeKartakElemBer());
		}
		//Basic counter
		if(randomness<=0.5  & karta.getBalioa()==-1 & irabaziNahiDu().size()==1 || irabaziNahiDu().size()==2) 
		{
			if(irabaziNahiDu().contains(ElementuMota.SUA)) 
			{
				for(int i=0;i<5;i++) 
				{
					if(lortuJolastekoKartaPosz(i).getElementua()==ElementuMota.SUA & karta.getBalioa()<=lortuJolastekoKartaPosz(i).getBalioa()) 
					{
						karta=lortuJolastekoKartaPosz(i);
					}
				}
			}
			else if(irabaziNahiDu().contains(ElementuMota.URA)) 
			{
				for(int i=0;i<5;i++) 
				{
					if(lortuJolastekoKartaPosz(i).getElementua()==ElementuMota.URA & karta.getBalioa()<=lortuJolastekoKartaPosz(i).getBalioa()) 
					{
						karta=lortuJolastekoKartaPosz(i);
					}
				}
			}
			else if(irabaziNahiDu().contains(ElementuMota.ELURRA)) 
			{
				for(int i=0;i<5;i++) 
				{
					if(lortuJolastekoKartaPosz(i).getElementua()==ElementuMota.ELURRA & karta.getBalioa()<=lortuJolastekoKartaPosz(i).getBalioa()) 
					{
						karta=lortuJolastekoKartaPosz(i);
					}
				}
			}
		}
		// Ausazko karta
		if(karta.getBalioa()==-1)
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
	
	private ArrayList<ElementuMota> irabaziNahiDu() 
	{
		Jokalaria j = getNireJokalaria(); 
		ArrayList<ElementuMota> elem = new  ArrayList<ElementuMota>();
		ArrayList<ArrayList<Karta>> LisKartak = new ArrayList<ArrayList<Karta>>();
		for(int i=0;i<j.gordetakoKartenKantitatea();i++) 
		{
			for(int x=0;x<j.gordetakoKartenKantitatea();x++) 
			{
				if(j.lortuGordetakoKartaPosz(i).getElementua()!=j.lortuGordetakoKartaPosz(x).getElementua() & x!=i) 
				{
					ArrayList<Karta> kartak = new ArrayList<Karta>();
					kartak.add(lortuGordetakoKartaPosz(i));
					kartak.add(lortuGordetakoKartaPosz(x));
					LisKartak.add(kartak);
				}
			}	
		}
		
		for(int i=0;i<LisKartak.size();i++) 
		{
			if(LisKartak.get(i).get(0).getElementua()==ElementuMota.SUA & LisKartak.get(i).get(1).getElementua()==ElementuMota.URA 
					|| LisKartak.get(i).get(0).getElementua()==ElementuMota.URA & LisKartak.get(i).get(1).getElementua()==ElementuMota.SUA) 
			{
				if(!elem.contains(ElementuMota.ELURRA)) {elem.add(ElementuMota.ELURRA);}
			}
			if(LisKartak.get(i).get(0).getElementua()==ElementuMota.SUA & LisKartak.get(i).get(1).getElementua()==ElementuMota.ELURRA 
					|| LisKartak.get(i).get(0).getElementua()==ElementuMota.ELURRA & LisKartak.get(i).get(1).getElementua()==ElementuMota.SUA) 
			{
				if(!elem.contains(ElementuMota.URA)) {elem.add(ElementuMota.URA);}
			}
			if(LisKartak.get(i).get(0).getElementua()==ElementuMota.ELURRA & LisKartak.get(i).get(1).getElementua()==ElementuMota.URA 
					|| LisKartak.get(i).get(0).getElementua()==ElementuMota.URA & LisKartak.get(i).get(1).getElementua()==ElementuMota.ELURRA) 
			{
				if(!elem.contains(ElementuMota.SUA)) {elem.add(ElementuMota.SUA);}
			}
		}
		return elem;
	}
}
