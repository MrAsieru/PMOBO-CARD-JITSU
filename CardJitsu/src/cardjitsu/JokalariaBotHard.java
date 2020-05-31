package cardjitsu;

import java.util.ArrayList;
import java.util.Random;

public class JokalariaBotHard extends JokalariaBot {
	
	public JokalariaBotHard(String pIzena)
	{
		super(pIzena);
	}
	
	public Karta kartaAukeratu(ListaKartak pGordetaKartak) 
	{
		boolean aukeratuta = false;
		Karta karta = (Karta) new KartaNormala(ElementuMota.ELURRA,0,KoloreMota.BERDEA);
		
		//Elementu berdina edo derberdina eta kolore desberdineko bi karta izatean, beste karta batekin irabazi ahala badu, hori bota
		if((sortuMatrizeKartakElemDes().size()!=0 || sortuMatrizeKartakElemBer().size()!=0)) 
		{
			Karta karta1 = this.kartaErabaki(this.sortuMatrizeKartakElemDes());
			Karta karta2 = this.kartaErabaki(this.sortuMatrizeKartakElemBer());
			if(karta1.getBalioa()>=karta2.getBalioa()) 
			{
				karta = karta1;
			}
			else if (karta1.getBalioa()<karta2.getBalioa()) 
			{
				karta = karta2;
			}
		}
		//Basic counter
		ArrayList<ElementuMota> irabazinahidu = this.irabaziNahiDu(pGordetaKartak);
		if(irabazinahidu.size()==1 || irabazinahidu.size()==2) 
		{
			if(irabazinahidu.contains(ElementuMota.SUA)) 
			{
				for(int i=0;i<5;i++) 
				{
					if(this.lortuJolastekoKartaPosz(i).getElementua()==ElementuMota.URA && karta.getBalioa()<=this.lortuJolastekoKartaPosz(i).getBalioa() && this.lortuJolastekoKartaPosz(i).getErabilgarria()) 
					{
						karta=this.lortuJolastekoKartaPosz(i);
					}
				}
			}
			else if(irabazinahidu.contains(ElementuMota.URA) ) 
			{
				for(int i=0;i<5;i++) 
				{
					if(this.lortuJolastekoKartaPosz(i).getElementua()==ElementuMota.ELURRA && karta.getBalioa()<=this.lortuJolastekoKartaPosz(i).getBalioa() && this.lortuJolastekoKartaPosz(i).getErabilgarria()) 
					{
						karta=this.lortuJolastekoKartaPosz(i);
					}
				}
			}
			else if(irabazinahidu.contains(ElementuMota.ELURRA)) 
			{
				for(int i=0;i<5;i++) 
				{
					if(this.lortuJolastekoKartaPosz(i).getElementua()==ElementuMota.SUA && karta.getBalioa()<=this.lortuJolastekoKartaPosz(i).getBalioa() && this.lortuJolastekoKartaPosz(i).getErabilgarria()) 
					{
						karta=this.lortuJolastekoKartaPosz(i);
					}
				}
			}
		}
		// Ausazko karta
		if(karta.getBalioa()==0)
		{
			while(!aukeratuta) {
				karta = this.lortuJolastekoKartaPosz(new Random().nextInt(5));
				if(karta.getErabilgarria() && this.logikoa(karta,pGordetaKartak)) {
					aukeratuta = true;
				}
			}
		}
		this.kenduJolastekoKarta(karta);
		return karta;
	}
	
	//Strategic metodos, elementu berdinak kolore desberdinak irabazi sahiatu
	private Karta kartaErabaki(ArrayList<ArrayList<Karta>> pMatrizea) 
	{
		Karta karta = (Karta) new KartaNormala(ElementuMota.ELURRA,0,KoloreMota.BERDEA);
		for(int i=0;i<pMatrizea.size();i++) 
		{
			for(int x=0;x<5;x++) 
			{
				if(pMatrizea.get(i).get(0).getElementua()!=pMatrizea.get(i).get(1).getElementua() &&
						pMatrizea.get(i).get(0).getElementua()!=this.lortuJolastekoKartaPosz(x).getElementua() &&
						pMatrizea.get(i).get(1).getElementua()!=this.lortuJolastekoKartaPosz(x).getElementua() &&
						pMatrizea.get(i).get(0).getKolorea()!=this.lortuJolastekoKartaPosz(x).getKolorea() &&
						pMatrizea.get(i).get(1).getKolorea()!=this.lortuJolastekoKartaPosz(x).getKolorea() &&
						this.lortuJolastekoKartaPosz(x).getBalioa()>karta.getBalioa() &&
						this.lortuJolastekoKartaPosz(x).getErabilgarria()) 
				{
					karta = lortuJolastekoKartaPosz(x);
				}
				if(pMatrizea.get(i).get(0).getElementua()==pMatrizea.get(i).get(1).getElementua() &&
						pMatrizea.get(i).get(0).getElementua()==this.lortuJolastekoKartaPosz(x).getElementua() &&
						pMatrizea.get(i).get(1).getElementua()==this.lortuJolastekoKartaPosz(x).getElementua() &&
						pMatrizea.get(i).get(0).getKolorea()!=this.lortuJolastekoKartaPosz(x).getKolorea() &&
						pMatrizea.get(i).get(1).getKolorea()!=this.lortuJolastekoKartaPosz(x).getKolorea() &&
						this.lortuJolastekoKartaPosz(x).getBalioa()>karta.getBalioa() &&
						this.lortuJolastekoKartaPosz(x).getErabilgarria()) 
				{
					karta = this.lortuJolastekoKartaPosz(x);
				}
			}
		}
		return karta;
	}
	
	private ArrayList<ArrayList<Karta>> sortuMatrizeKartakElemDes()
	{
		ArrayList<ArrayList<Karta>> LisKartak = new ArrayList<ArrayList<Karta>>();
		for(int i=0;i<this.gordetakoKartenKantitatea();i++) 
		{
			for(int x=0;x<this.gordetakoKartenKantitatea();x++) 
			{
				if(this.lortuGordetakoKartaPosz(i).getElementua()!=this.lortuGordetakoKartaPosz(x).getElementua() &&
				   this.lortuGordetakoKartaPosz(i).getKolorea()!=this.lortuGordetakoKartaPosz(x).getKolorea() &&
						x!=i) 
				{
					ArrayList<Karta> kartak = new ArrayList<Karta>();
					kartak.add(this.lortuGordetakoKartaPosz(i));
					kartak.add(this.lortuGordetakoKartaPosz(x));
					LisKartak.add(kartak);
				}
			}
		}
		return LisKartak;
	}
	
	private ArrayList<ArrayList<Karta>> sortuMatrizeKartakElemBer()
	{
		ArrayList<ArrayList<Karta>> LisKartak = new ArrayList<ArrayList<Karta>>();
		for(int i=0;i<this.gordetakoKartenKantitatea();i++) 
		{
			for(int x=0;x<this.gordetakoKartenKantitatea();x++) 
			{
				if(this.lortuGordetakoKartaPosz(i).getElementua()==this.lortuGordetakoKartaPosz(x).getElementua() &&
				   this.lortuGordetakoKartaPosz(i).getKolorea()!=this.lortuGordetakoKartaPosz(x).getKolorea() &&
						x!=i) 
				{
					ArrayList<Karta> kartak = new ArrayList<Karta>();
					kartak.add(this.lortuGordetakoKartaPosz(i));
					kartak.add(this.lortuGordetakoKartaPosz(x));
					LisKartak.add(kartak);
				}
			}
		}
		return LisKartak;
	}
	
	private ArrayList<ElementuMota> irabaziNahiDu(ListaKartak pGordetaKartak) 
	{
		ArrayList<ElementuMota> elem = new  ArrayList<ElementuMota>();
		for(int i=0;i<pGordetaKartak.getTamaina();i++)
		{
			for(int x=0;x<pGordetaKartak.getTamaina();x++)
			{
				if(pGordetaKartak.lortuKartaPosz(i).getElementua()!=pGordetaKartak.lortuKartaPosz(x).getElementua() & x!=i) 
				{
					if((pGordetaKartak.lortuKartaPosz(i).getElementua()==ElementuMota.SUA && pGordetaKartak.lortuKartaPosz(x).getElementua()==ElementuMota.URA)
							|| (pGordetaKartak.lortuKartaPosz(i).getElementua()==ElementuMota.URA && pGordetaKartak.lortuKartaPosz(x).getElementua()==ElementuMota.SUA)) 
					{
						if(!elem.contains(ElementuMota.ELURRA)) {elem.add(ElementuMota.ELURRA);}
					}
					else if((pGordetaKartak.lortuKartaPosz(i).getElementua()==ElementuMota.SUA && pGordetaKartak.lortuKartaPosz(x).getElementua()==ElementuMota.ELURRA) 
							|| (pGordetaKartak.lortuKartaPosz(i).getElementua()==ElementuMota.ELURRA && pGordetaKartak.lortuKartaPosz(x).getElementua()==ElementuMota.SUA)) 
					{
						if(!elem.contains(ElementuMota.URA)) {elem.add(ElementuMota.URA);}
					} 
					else if((pGordetaKartak.lortuKartaPosz(i).getElementua()==ElementuMota.ELURRA && pGordetaKartak.lortuKartaPosz(x).getElementua()==ElementuMota.URA) 
							|| (pGordetaKartak.lortuKartaPosz(i).getElementua()==ElementuMota.URA && pGordetaKartak.lortuKartaPosz(x).getElementua()==ElementuMota.ELURRA)) 
					{
						if(!elem.contains(ElementuMota.SUA)) {elem.add(ElementuMota.SUA);}
					}
				}
				if(pGordetaKartak.lortuKartaPosz(i).getElementua()==pGordetaKartak.lortuKartaPosz(x).getElementua() && pGordetaKartak.lortuKartaPosz(i).getKolorea()!=pGordetaKartak.lortuKartaPosz(x).getKolorea())
				{
					elem.add(pGordetaKartak.lortuKartaPosz(i).getElementua());
				}
			}	
		}
		return elem;
	}
	
	private boolean logikoa(Karta pKarta,ListaKartak pGordetaKartak) 
	{
		boolean logikoa = true;
		if(pKarta instanceof KartaBerezia) 
		{
			if(((KartaBerezia)pKarta).getEfektua()==EfektuMota.KENDUBERDEBAT || ((KartaBerezia)pKarta).getEfektua()==EfektuMota.KENDUBERDEGUZTIAK && !this.koloreHauDuenKartaDu(KoloreMota.BERDEA,pGordetaKartak)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)pKarta).getEfektua()==EfektuMota.KENDUGORRIBAT || ((KartaBerezia)pKarta).getEfektua()==EfektuMota.KENDUGORRIGUZTIAK && !this.koloreHauDuenKartaDu(KoloreMota.BERDEA,pGordetaKartak)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)pKarta).getEfektua()==EfektuMota.KENDUURDINBAT || ((KartaBerezia)pKarta).getEfektua()==EfektuMota.KENDUURDINGUZTIAK && !this.koloreHauDuenKartaDu(KoloreMota.BERDEA,pGordetaKartak)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)pKarta).getEfektua()==EfektuMota.KENDUHORIBAT || ((KartaBerezia)pKarta).getEfektua()==EfektuMota.KENDUHORIGUZTIAK && !this.koloreHauDuenKartaDu(KoloreMota.BERDEA,pGordetaKartak)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)pKarta).getEfektua()==EfektuMota.KENDUMOREBAT || ((KartaBerezia)pKarta).getEfektua()==EfektuMota.KENDUMOREGUZTIAK && !this.koloreHauDuenKartaDu(KoloreMota.BERDEA,pGordetaKartak)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)pKarta).getEfektua()==EfektuMota.KENDULARANJABAT || ((KartaBerezia)pKarta).getEfektua()==EfektuMota.KENDULARANJAGUZTIAK && !this.koloreHauDuenKartaDu(KoloreMota.BERDEA,pGordetaKartak)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)pKarta).getEfektua()==EfektuMota.KENDULARANJABAT || ((KartaBerezia)pKarta).getEfektua()==EfektuMota.KENDULARANJAGUZTIAK && !this.koloreHauDuenKartaDu(KoloreMota.BERDEA,pGordetaKartak)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)pKarta).getEfektua()==EfektuMota.KENDUSUA && !this.elementuHauDuenKartaDu(ElementuMota.SUA,pGordetaKartak)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)pKarta).getEfektua()==EfektuMota.KENDUURA && !this.elementuHauDuenKartaDu(ElementuMota.URA,pGordetaKartak)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)pKarta).getEfektua()==EfektuMota.KENDUELURRA && !this.elementuHauDuenKartaDu(ElementuMota.ELURRA,pGordetaKartak)) 
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
	
	private boolean koloreHauDuenKartaDu(KoloreMota pKolorea,ListaKartak pGordetaKartak) 
	{
		boolean du = false;
		for(int i=0;i<pGordetaKartak.getTamaina();i++) 
		{
			if(pGordetaKartak.lortuKartaPosz(i).getKolorea()==pKolorea) 
			{
				du=true;
			}
		}
		return du;
	}
	private boolean elementuHauDuenKartaDu(ElementuMota pElementua,ListaKartak pGordetaKartak) 
	{
		boolean du = false;
		for(int i=0;i<pGordetaKartak.getTamaina();i++) 
		{
			if(pGordetaKartak.lortuKartaPosz(i).getElementua()==pElementua) 
			{
				du=true;
			}
		}
		return du;
	}
}
