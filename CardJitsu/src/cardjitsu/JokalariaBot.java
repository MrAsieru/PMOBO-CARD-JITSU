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
		if(randomness1<=0.8 & (sortuMatrizeKartakElemDes().size()!=0 || sortuMatrizeKartakElemBer().size()!=0)) 
		{
			kartaErabaki(sortuMatrizeKartakElemDes());
			kartaErabaki(sortuMatrizeKartakElemBer());
		}
		//Basic counter
		if(randomness<=0.6  & karta.getBalioa()==-1 & irabaziNahiDu().size()==1 || irabaziNahiDu().size()==2) 
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
				if(karta.getErabilgarria() & logikoa(karta)) {
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
	
	private boolean logikoa(Karta p1) 
	{
		boolean logikoa = true;
		if(p1 instanceof KartaBerezia) 
		{
			if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUBERDEBAT || ((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUBERDEGUZTIAK & !koloreHauDuenKartaDu(KoloreMota.BERDEA)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUGORRIBAT || ((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUGORRIGUZTIAK & !koloreHauDuenKartaDu(KoloreMota.BERDEA)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUURDINBAT || ((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUURDINGUZTIAK & !koloreHauDuenKartaDu(KoloreMota.BERDEA)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUHORIBAT || ((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUHORIGUZTIAK & !koloreHauDuenKartaDu(KoloreMota.BERDEA)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUMOREBAT || ((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUMOREGUZTIAK & !koloreHauDuenKartaDu(KoloreMota.BERDEA)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDULARANJABAT || ((KartaBerezia)p1).getEfektua()==EfektuMota.KENDULARANJAGUZTIAK & !koloreHauDuenKartaDu(KoloreMota.BERDEA)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDULARANJABAT || ((KartaBerezia)p1).getEfektua()==EfektuMota.KENDULARANJAGUZTIAK & !koloreHauDuenKartaDu(KoloreMota.BERDEA)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUSUA & !elementuHauDuenKartaDu(ElementuMota.SUA)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUURA & !elementuHauDuenKartaDu(ElementuMota.URA)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUELURRA & !elementuHauDuenKartaDu(ElementuMota.ELURRA)) 
			{
				logikoa = false;
			}
		}
		if(logikoa==false) 
		{
			float randomness = new Random().nextFloat();
			if(randomness<=0.8) 
			{
				logikoa = true;
			}
		}
		return logikoa;
	}
	
	private boolean koloreHauDuenKartaDu(KoloreMota p) 
	{
		boolean du = false;
		JokalariaLokala j = JokalariaLokala.getNireJokalaria();
		for(int i=0;i<j.gordetakoKartenKantitatea();i++) 
		{
			if(j.lortuGordetakoKartaPosz(i).getKolorea()==p) 
			{
				du=true;
			}
		}
		return du;
	}
	private boolean elementuHauDuenKartaDu(ElementuMota p) 
	{
		boolean du = false;
		JokalariaLokala j = JokalariaLokala.getNireJokalaria();
		for(int i=0;i<j.gordetakoKartenKantitatea();i++) 
		{
			if(j.lortuGordetakoKartaPosz(i).getElementua()==p) 
			{
				du=true;
			}
		}
		return du;
	}
}
