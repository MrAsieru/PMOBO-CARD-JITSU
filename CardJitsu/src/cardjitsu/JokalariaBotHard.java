package cardjitsu;

import java.util.ArrayList;
import java.util.Random;

public class JokalariaBotHard extends Jokalaria {

	private static JokalariaBotHard nireJokalariaBot;
	
	protected JokalariaBotHard(String pIzena)
	{
		super(pIzena);
	}
	
	public static JokalariaBotHard getNireJokalaria(String pIzena) 
	{
		if(nireJokalariaBot==null) 
		{
			nireJokalariaBot = new JokalariaBotHard(pIzena);
		}
		return nireJokalariaBot;
	}
	
	public Karta kartaAukeratu(JokalariaLokala j) 
	{
		boolean aukeratuta = false;
		Karta karta = (Karta) new KartaNormala(ElementuMota.ELURRA,0,KoloreMota.BERDEA);
		float randomness = new Random().nextFloat();
		
		//Elementu berdina edo derberdina eta kolore desberdineko bi karta izatean, beste karta batekin irabazi ahala badu, hori bota
		if(randomness<=0.7 && (sortuMatrizeKartakElemDes().size()!=0 || sortuMatrizeKartakElemBer().size()!=0)) 
		{
			Karta karta1 = kartaErabaki(sortuMatrizeKartakElemDes());
			Karta karta2 = kartaErabaki(sortuMatrizeKartakElemBer());
			if(karta1.getBalioa()>karta2.getBalioa()) 
			{
				karta = karta1;
			}
			else if (karta1.getBalioa()<karta2.getBalioa()) 
			{
				karta = karta2;
			}
			//TODO //Batzuetan karta honen elementua aldatu beste jokalariak zuk aterako duzun karta pentsatzen duen karta countereatzeko
		}
		
		//TODO //Lortu irabazi ahalduen karta eta beste jokalariak nahi duen karta eta hauek erabili estrategikoki
		//Basic counter
		if(irabaziNahiDu(j).size()==1 || irabaziNahiDu(j).size()==2) 
		{
			if(irabaziNahiDu(j).contains(ElementuMota.SUA)) 
			{
				for(int i=0;i<5;i++) 
				{
					if(lortuJolastekoKartaPosz(i).getElementua()==ElementuMota.URA & karta.getBalioa()<=lortuJolastekoKartaPosz(i).getBalioa()) 
					{
						karta=lortuJolastekoKartaPosz(i);
					}
				}
			}
			else if(irabaziNahiDu(j).contains(ElementuMota.URA) ) 
			{
				for(int i=0;i<5;i++) 
				{
					if(lortuJolastekoKartaPosz(i).getElementua()==ElementuMota.ELURRA & karta.getBalioa()<=lortuJolastekoKartaPosz(i).getBalioa()) 
					{
						karta=lortuJolastekoKartaPosz(i);
					}
				}
			}
			else if(irabaziNahiDu(j).contains(ElementuMota.ELURRA)) 
			{
				for(int i=0;i<5;i++) 
				{
					if(lortuJolastekoKartaPosz(i).getElementua()==ElementuMota.SUA & karta.getBalioa()<=lortuJolastekoKartaPosz(i).getBalioa()) 
					{
						karta=lortuJolastekoKartaPosz(i);
					}
				}
			}
		}
		// Ausazko karta
		if(karta.getBalioa()==0)
		{
			while(!aukeratuta) {
				karta = this.lortuJolastekoKartaPosz(new Random().nextInt(5));
				if(karta.getErabilgarria() & logikoa(karta,j)) {
					aukeratuta = true;
				}
			}
		}
		this.kenduJolastekoKarta(karta);
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
						lortuJolastekoKartaPosz(x).getBalioa()>karta.getBalioa() &
						lortuJolastekoKartaPosz(x).getErabilgarria()) 
				{
					karta = lortuJolastekoKartaPosz(x);
				}
				if(p1.get(i).get(0).getElementua()==p1.get(i).get(1).getElementua() &
						p1.get(i).get(0).getElementua()==lortuJolastekoKartaPosz(x).getElementua() &
						p1.get(i).get(1).getElementua()==lortuJolastekoKartaPosz(x).getElementua() &
						p1.get(i).get(0).getKolorea()!=lortuJolastekoKartaPosz(x).getKolorea() &
						p1.get(i).get(1).getKolorea()!=lortuJolastekoKartaPosz(x).getKolorea() &
						lortuJolastekoKartaPosz(x).getBalioa()>karta.getBalioa() &
						lortuJolastekoKartaPosz(x).getErabilgarria()) 
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
	
	private ArrayList<ElementuMota> irabaziNahiDu(JokalariaLokala j) 
	{
		ArrayList<ElementuMota> elem = new  ArrayList<ElementuMota>();
		for(int i=0;i<j.gordetakoKartenKantitatea();i++)
		{
			for(int x=0;x<j.gordetakoKartenKantitatea();x++)
			{
				if(j.lortuGordetakoKartaPosz(i).getElementua()!=j.lortuGordetakoKartaPosz(x).getElementua() & x!=i) 
				{
					if((j.lortuGordetakoKartaPosz(i).getElementua()==ElementuMota.SUA & j.lortuGordetakoKartaPosz(x).getElementua()==ElementuMota.URA)
							|| (j.lortuGordetakoKartaPosz(i).getElementua()==ElementuMota.URA & j.lortuGordetakoKartaPosz(x).getElementua()==ElementuMota.SUA)) 
					{
						if(!elem.contains(ElementuMota.ELURRA)) {elem.add(ElementuMota.ELURRA);}
					}
					else if((j.lortuGordetakoKartaPosz(i).getElementua()==ElementuMota.SUA & j.lortuGordetakoKartaPosz(x).getElementua()==ElementuMota.ELURRA) 
							|| (j.lortuGordetakoKartaPosz(i).getElementua()==ElementuMota.ELURRA & j.lortuGordetakoKartaPosz(x).getElementua()==ElementuMota.SUA)) 
					{
						if(!elem.contains(ElementuMota.URA)) {elem.add(ElementuMota.URA);}
					} 
					else if((j.lortuGordetakoKartaPosz(i).getElementua()==ElementuMota.ELURRA & j.lortuGordetakoKartaPosz(i).getElementua()==ElementuMota.URA) 
							|| (j.lortuGordetakoKartaPosz(i).getElementua()==ElementuMota.URA & j.lortuGordetakoKartaPosz(i).getElementua()==ElementuMota.ELURRA)) 
					{
						if(!elem.contains(ElementuMota.SUA)) {elem.add(ElementuMota.SUA);}
					}
				}
			}	
		}
		return elem;
	}
	
	private boolean logikoa(Karta p1,JokalariaLokala j) 
	{
		boolean logikoa = true;
		if(p1 instanceof KartaBerezia) 
		{
			if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUBERDEBAT || ((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUBERDEGUZTIAK & !koloreHauDuenKartaDu(KoloreMota.BERDEA,j)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUGORRIBAT || ((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUGORRIGUZTIAK & !koloreHauDuenKartaDu(KoloreMota.BERDEA,j)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUURDINBAT || ((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUURDINGUZTIAK & !koloreHauDuenKartaDu(KoloreMota.BERDEA,j)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUHORIBAT || ((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUHORIGUZTIAK & !koloreHauDuenKartaDu(KoloreMota.BERDEA,j)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUMOREBAT || ((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUMOREGUZTIAK & !koloreHauDuenKartaDu(KoloreMota.BERDEA,j)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDULARANJABAT || ((KartaBerezia)p1).getEfektua()==EfektuMota.KENDULARANJAGUZTIAK & !koloreHauDuenKartaDu(KoloreMota.BERDEA,j)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDULARANJABAT || ((KartaBerezia)p1).getEfektua()==EfektuMota.KENDULARANJAGUZTIAK & !koloreHauDuenKartaDu(KoloreMota.BERDEA,j)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUSUA & !elementuHauDuenKartaDu(ElementuMota.SUA,j)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUURA & !elementuHauDuenKartaDu(ElementuMota.URA,j)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUELURRA & !elementuHauDuenKartaDu(ElementuMota.ELURRA,j)) 
			{
				logikoa = false;
			}
		}
		if(logikoa==false) 
		{
			float randomness = new Random().nextFloat();
			if(randomness<=0.6) 
			{
				logikoa = true;
			}
		}
		return logikoa;
	}
	
	private boolean koloreHauDuenKartaDu(KoloreMota p,JokalariaLokala j) 
	{
		boolean du = false;
		for(int i=0;i<j.gordetakoKartenKantitatea();i++) 
		{
			if(j.lortuGordetakoKartaPosz(i).getKolorea()==p) 
			{
				du=true;
			}
		}
		return du;
	}
	private boolean elementuHauDuenKartaDu(ElementuMota p,JokalariaLokala j) 
	{
		boolean du = false;
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
