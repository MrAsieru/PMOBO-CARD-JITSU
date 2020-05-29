package cardjitsu;

import java.util.ArrayList;
import java.util.Random;

public class JokalariaBotHard extends JokalariaBot {
	
	public JokalariaBotHard(String pIzena)
	{
		super(pIzena);
	}
	
	public Karta kartaAukeratu(ListaKartak gordetaKartak) 
	{
		boolean aukeratuta = false;
		Karta karta = (Karta) new KartaNormala(ElementuMota.ELURRA,0,KoloreMota.BERDEA);
		
		//Elementu berdina edo derberdina eta kolore desberdineko bi karta izatean, beste karta batekin irabazi ahala badu, hori bota
		if((sortuMatrizeKartakElemDes().size()!=0 || sortuMatrizeKartakElemBer().size()!=0)) 
		{
			Karta karta1 = kartaErabaki(sortuMatrizeKartakElemDes());
			Karta karta2 = kartaErabaki(sortuMatrizeKartakElemBer());
			if(karta1.getBalioa()>=karta2.getBalioa()) 
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
		ArrayList<ElementuMota> irabazinahidu = irabaziNahiDu(gordetaKartak);
		if(irabazinahidu.size()==1 || irabazinahidu.size()==2) 
		{
			if(irabazinahidu.contains(ElementuMota.SUA)) 
			{
				for(int i=0;i<5;i++) 
				{
					if(lortuJolastekoKartaPosz(i).getElementua()==ElementuMota.URA && karta.getBalioa()<=lortuJolastekoKartaPosz(i).getBalioa() && lortuJolastekoKartaPosz(i).getErabilgarria()) 
					{
						karta=lortuJolastekoKartaPosz(i);
					}
				}
			}
			else if(irabazinahidu.contains(ElementuMota.URA) ) 
			{
				for(int i=0;i<5;i++) 
				{
					if(lortuJolastekoKartaPosz(i).getElementua()==ElementuMota.ELURRA && karta.getBalioa()<=lortuJolastekoKartaPosz(i).getBalioa() && lortuJolastekoKartaPosz(i).getErabilgarria()) 
					{
						karta=lortuJolastekoKartaPosz(i);
					}
				}
			}
			else if(irabazinahidu.contains(ElementuMota.ELURRA)) 
			{
				for(int i=0;i<5;i++) 
				{
					if(lortuJolastekoKartaPosz(i).getElementua()==ElementuMota.SUA && karta.getBalioa()<=lortuJolastekoKartaPosz(i).getBalioa() && lortuJolastekoKartaPosz(i).getErabilgarria()) 
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
				if(karta.getErabilgarria() && logikoa(karta,gordetaKartak)) {
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
				if(p1.get(i).get(0).getElementua()!=p1.get(i).get(1).getElementua() &&
						p1.get(i).get(0).getElementua()!=lortuJolastekoKartaPosz(x).getElementua() &&
						p1.get(i).get(1).getElementua()!=lortuJolastekoKartaPosz(x).getElementua() &&
						p1.get(i).get(0).getKolorea()!=lortuJolastekoKartaPosz(x).getKolorea() &&
						p1.get(i).get(1).getKolorea()!=lortuJolastekoKartaPosz(x).getKolorea() &&
						lortuJolastekoKartaPosz(x).getBalioa()>karta.getBalioa() &&
						lortuJolastekoKartaPosz(x).getErabilgarria()) 
				{
					karta = lortuJolastekoKartaPosz(x);
				}
				if(p1.get(i).get(0).getElementua()==p1.get(i).get(1).getElementua() &&
						p1.get(i).get(0).getElementua()==lortuJolastekoKartaPosz(x).getElementua() &&
						p1.get(i).get(1).getElementua()==lortuJolastekoKartaPosz(x).getElementua() &&
						p1.get(i).get(0).getKolorea()!=lortuJolastekoKartaPosz(x).getKolorea() &&
						p1.get(i).get(1).getKolorea()!=lortuJolastekoKartaPosz(x).getKolorea() &&
						lortuJolastekoKartaPosz(x).getBalioa()>karta.getBalioa() &&
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
				if(lortuGordetakoKartaPosz(i).getElementua()!=lortuGordetakoKartaPosz(x).getElementua() &&
						lortuGordetakoKartaPosz(i).getKolorea()!=lortuGordetakoKartaPosz(x).getKolorea() &&
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
				if(lortuGordetakoKartaPosz(i).getElementua()==lortuGordetakoKartaPosz(x).getElementua() &&
						lortuGordetakoKartaPosz(i).getKolorea()!=lortuGordetakoKartaPosz(x).getKolorea() &&
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
	
	private ArrayList<ElementuMota> irabaziNahiDu(ListaKartak gordetaKartak) 
	{
		ArrayList<ElementuMota> elem = new  ArrayList<ElementuMota>();
		for(int i=0;i<gordetaKartak.getTamaina();i++)
		{
			for(int x=0;x<gordetaKartak.getTamaina();x++)
			{
				if(gordetaKartak.lortuKartaPosz(i).getElementua()!=gordetaKartak.lortuKartaPosz(x).getElementua() & x!=i) 
				{
					if((gordetaKartak.lortuKartaPosz(i).getElementua()==ElementuMota.SUA && gordetaKartak.lortuKartaPosz(x).getElementua()==ElementuMota.URA)
							|| (gordetaKartak.lortuKartaPosz(i).getElementua()==ElementuMota.URA && gordetaKartak.lortuKartaPosz(x).getElementua()==ElementuMota.SUA)) 
					{
						if(!elem.contains(ElementuMota.ELURRA)) {elem.add(ElementuMota.ELURRA);}
					}
					else if((gordetaKartak.lortuKartaPosz(i).getElementua()==ElementuMota.SUA && gordetaKartak.lortuKartaPosz(x).getElementua()==ElementuMota.ELURRA) 
							|| (gordetaKartak.lortuKartaPosz(i).getElementua()==ElementuMota.ELURRA && gordetaKartak.lortuKartaPosz(x).getElementua()==ElementuMota.SUA)) 
					{
						if(!elem.contains(ElementuMota.URA)) {elem.add(ElementuMota.URA);}
					} 
					else if((gordetaKartak.lortuKartaPosz(i).getElementua()==ElementuMota.ELURRA && gordetaKartak.lortuKartaPosz(x).getElementua()==ElementuMota.URA) 
							|| (gordetaKartak.lortuKartaPosz(i).getElementua()==ElementuMota.URA && gordetaKartak.lortuKartaPosz(x).getElementua()==ElementuMota.ELURRA)) 
					{
						if(!elem.contains(ElementuMota.SUA)) {elem.add(ElementuMota.SUA);}
					}
				}
				if(gordetaKartak.lortuKartaPosz(i).getElementua()==gordetaKartak.lortuKartaPosz(x).getElementua() && gordetaKartak.lortuKartaPosz(i).getKolorea()!=gordetaKartak.lortuKartaPosz(x).getKolorea())
				{
					elem.add(gordetaKartak.lortuKartaPosz(i).getElementua());
				}
			}	
		}
		return elem;
	}
	
	private boolean logikoa(Karta p1,ListaKartak gordetaKartak) 
	{
		boolean logikoa = true;
		if(p1 instanceof KartaBerezia) 
		{
			if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUBERDEBAT || ((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUBERDEGUZTIAK && !koloreHauDuenKartaDu(KoloreMota.BERDEA,gordetaKartak)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUGORRIBAT || ((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUGORRIGUZTIAK && !koloreHauDuenKartaDu(KoloreMota.BERDEA,gordetaKartak)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUURDINBAT || ((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUURDINGUZTIAK && !koloreHauDuenKartaDu(KoloreMota.BERDEA,gordetaKartak)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUHORIBAT || ((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUHORIGUZTIAK && !koloreHauDuenKartaDu(KoloreMota.BERDEA,gordetaKartak)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUMOREBAT || ((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUMOREGUZTIAK && !koloreHauDuenKartaDu(KoloreMota.BERDEA,gordetaKartak)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDULARANJABAT || ((KartaBerezia)p1).getEfektua()==EfektuMota.KENDULARANJAGUZTIAK && !koloreHauDuenKartaDu(KoloreMota.BERDEA,gordetaKartak)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDULARANJABAT || ((KartaBerezia)p1).getEfektua()==EfektuMota.KENDULARANJAGUZTIAK && !koloreHauDuenKartaDu(KoloreMota.BERDEA,gordetaKartak)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUSUA && !elementuHauDuenKartaDu(ElementuMota.SUA,gordetaKartak)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUURA && !elementuHauDuenKartaDu(ElementuMota.URA,gordetaKartak)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUELURRA && !elementuHauDuenKartaDu(ElementuMota.ELURRA,gordetaKartak)) 
			{
				logikoa = false;
			}
		}
		if(logikoa==false) 
		{
			float randomness = new Random().nextFloat();
			if(randomness<=0.7) 
			{
				logikoa = true;
			}
		}
		return logikoa;
	}
	
	private boolean koloreHauDuenKartaDu(KoloreMota p,ListaKartak gordetaKartak) 
	{
		boolean du = false;
		for(int i=0;i<gordetaKartak.getTamaina();i++) 
		{
			if(gordetaKartak.lortuKartaPosz(i).getKolorea()==p) 
			{
				du=true;
			}
		}
		return du;
	}
	private boolean elementuHauDuenKartaDu(ElementuMota p,ListaKartak gordetaKartak) 
	{
		boolean du = false;
		for(int i=0;i<gordetaKartak.getTamaina();i++) 
		{
			if(gordetaKartak.lortuKartaPosz(i).getElementua()==p) 
			{
				du=true;
			}
		}
		return du;
	}
}
