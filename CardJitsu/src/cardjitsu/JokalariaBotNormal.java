package cardjitsu;

import java.util.ArrayList;
import java.util.Random;

public class JokalariaBotNormal extends JokalariaBot {
	
	public JokalariaBotNormal(String pIzena)
	{
		super(pIzena);
	}
	
	public Karta kartaAukeratu(ListaKartak pGordetaKartak) 
	{
		boolean aukeratuta = false;
		Karta karta = (Karta) new KartaNormala(ElementuMota.ELURRA,0,KoloreMota.BERDEA);
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
	
	private boolean logikoa(Karta p1,ListaKartak pGordetaKartak) 
	{
		boolean logikoa = true;
		if(p1 instanceof KartaBerezia) 
		{
			if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUBERDEBAT || ((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUBERDEGUZTIAK && !this.koloreHauDuenKartaDu(KoloreMota.BERDEA,pGordetaKartak)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUGORRIBAT || ((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUGORRIGUZTIAK && !this.koloreHauDuenKartaDu(KoloreMota.BERDEA,pGordetaKartak)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUURDINBAT || ((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUURDINGUZTIAK && !this.koloreHauDuenKartaDu(KoloreMota.BERDEA,pGordetaKartak)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUHORIBAT || ((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUHORIGUZTIAK && !this.koloreHauDuenKartaDu(KoloreMota.BERDEA,pGordetaKartak)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUMOREBAT || ((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUMOREGUZTIAK && !this.koloreHauDuenKartaDu(KoloreMota.BERDEA,pGordetaKartak)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDULARANJABAT || ((KartaBerezia)p1).getEfektua()==EfektuMota.KENDULARANJAGUZTIAK && !this.koloreHauDuenKartaDu(KoloreMota.BERDEA,pGordetaKartak)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDULARANJABAT || ((KartaBerezia)p1).getEfektua()==EfektuMota.KENDULARANJAGUZTIAK && !this.koloreHauDuenKartaDu(KoloreMota.BERDEA,pGordetaKartak)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUSUA && !this.elementuHauDuenKartaDu(ElementuMota.SUA,pGordetaKartak)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUURA && !this.elementuHauDuenKartaDu(ElementuMota.URA,pGordetaKartak)) 
			{
				logikoa = false;
			}
			else if(((KartaBerezia)p1).getEfektua()==EfektuMota.KENDUELURRA && !this.elementuHauDuenKartaDu(ElementuMota.ELURRA,pGordetaKartak)) 
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
