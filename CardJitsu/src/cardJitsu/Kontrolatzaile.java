package cardJitsu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class Kontrolatzaile {
	
	private JokalariaLokala jokalari1;
	private JokalariaBot jokalari2;
	private EfektuMota aurrekoTxandakoEfektua;
	private boolean aurrekoTxandakoEfektuaZenbakia;
	private Jokalaria aurrekoTxandakoIrabazlea;
	private Karta jokalariLokalaKarta;
	private Karta jokalariBotKarta;
	private static Kontrolatzaile nireKontrolatzaile;
	
	private Kontrolatzaile() 
	{
		this.jokalari1 = null;
		this.jokalari2 = null;
		this.aurrekoTxandakoEfektua = null;
		this.jokalariBotKarta = null;
		this.jokalariLokalaKarta = null;
	}
	//Necesita revision
	
	public static Kontrolatzaile getNireKontrolatzailea() 
	{
		if(nireKontrolatzaile==null) 
		{
			nireKontrolatzaile = new Kontrolatzaile();
		}
		return nireKontrolatzaile;
	} 
	//Necesita revision
	
	public void partidaBerriaHasi() 
	{
		
	}
	//Sin hacer
	
	private Jokalaria txandaBerria(Jokalaria pJokalaria) {
		//Kartak banatu
		this.kartaBatEman(jokalari1);
		this.kartaBatEman(jokalari2);
		
		//Aplikatu aurrreko efektua
		this.aplikatuAurrekoEfektua(jokalari1);
		this.aplikatuAurrekoEfektua(jokalari2);
		
		//Txanda imprimatu
		KontsolaK.imprimatu(jokalari1.getIzena()+":");
		for(int i=0;i<jokalari1.gordetakoKartenKantitatea();i++) {
			Karta karta = jokalari1.lortuGordetakoKartaPOSz(i);
			KontsolaK.imprimatu("E: "+karta.getElementua.name()+"\tK: "+karta.getKolorea());
		}
		KontsolaK.imprimatu(" ");
		KontsolaK.imprimatu(jokalari2.getIzena()+":");
		for(int i=0;i<jokalari2.gordetakoKartenKantitatea();i++) {
			Karta karta = jokalari2.lortuGordetakoKartaPOSz(i);
			KontsolaK.imprimatu("E: "+karta.getElementua.name()+"\tK: "+karta.getKolorea());
		}
		
		KontsolaK.imprimatu("Zure txanda "+jokalari1.getIzena());
		for(int i=1;i<5;i++) {
			Karta karta = jokalari2.lortuJolastekoKartaPOSz(i-1);
			KontsolaK.imprimatu("["+((karta.getErabilgarria()) ? "#":i)+"] E: "+karta.getElementua().name()+"\tB: "+karta.getBalioa()+"\tK: "+karta.getKolorea().name()+"\t"+((karta instanceof KartaBerezia) ? "Ef: "+karta.getDeskripzioa().split("#")[0]:""));
		}
		
		//TODO
		
		//Jokalariak karta bat aukeratu hau jokatzeko
		jokalariLokalaKarta = jokalari1.kartaAukeratu();
		jokalariBotKarta = jokalari2.kartaAukeratu();
		
		//Txandan jokatutako kartak konprobatu eta karten emaitza imprimatu
		JokalariMota txJok = this.kartakKonprobatu();
		if(txJok == JokalariMota.LOKALA) {
			aurrekoTxandakoEfektua = (jokalariLokalaKarta instanceof KartaBerezia) ? jokalariLokalaKarta.getEfektua():null;
			aurrekoTxandakoIrabazlea = jokalari1;
			jokalari1.gehituGordetakoKarta(jokalariLokalaKarta);
			KontsolaK.imprimatu(jokalari1.getIzena()+" irabazi du"+(jokalariLokalaKarta instanceof KartaBerezia) ? ", "+jokalariLokalaKarta.getDeskripzioa().split("#")[1]:".");
		} else if (txJok == JokalariMota.BOT) {
			aurrekoTxandakoEfektua = (jokalariBotKarta instanceof KartaBerezia) ? jokalariBotKarta.getEfektua():null;
			aurrekoTxandakoIrabazlea = jokalari2;
			jokalari2.gehituGordetakoKarta(jokalariBotKarta);
			KontsolaK.imprimatu(jokalari2.getIzena()+" irabazi du"+(jokalariBotKarta instanceof KartaBerezia) ? ", "+jokalariBotKarta.getDeskripzioa().split("#")[1]:".");
		} else {
			aurrekoTxandakoEfektua = null;
			aurrekoTxandakoIrabazlea = null;
			KontsolaK.imprimatu("Berdinketa bat egon da.");
		}
		
		//Txandaren amaiera imprimatu
		
	}
	//Hecho
	
	private boolean partidarenIrabazleaKonprobatu(Jokalaria pJokalaria) 
	{
		boolean irabaziDu = false;
		
		ArrayList<Karta> sua = new ArrayList<Karta>();
		Iterator<Karta> itrSua = sua.iterator();
		ArrayList<Karta> elurra = new ArrayList<Karta>();
		Iterator<Karta> itrElurra = sua.iterator();
		ArrayList<Karta> ura = new ArrayList<Karta>();
		Iterator<Karta> itrUra = sua.iterator();
		ArrayList<ArrayList<Karta>> elementuak = new ArrayList<ArrayList<Karta>>();
		elementuak.add(sua);
		elementuak.add(elurra);
		elementuak.add(ura);
		
		//Karta guztiak dagozkien listetan sartu
		for(int i=0;i<pJokalaria.gordetakoKartenKantitatea();i++) {
			Karta karta = pJokalaria.lortuGordetakoKartaPOSz();
			
			switch(karta.getElementua()) {
			case ElementuMota.SUA:
				sua.add(karta);
				break;
			case ElementuMota.ELURRA:
				elurra.add(karta);
				break;
			case ElementuMota.URA:
				ura.add(karta);
				break;
			}
		}
		
		//Konprobatu elementu bereko kartak
		for(int i=0;i<elementuak.size();i++) {
			ArrayList<KoloreMota> koloreak = new ArrayList<KoloreMota>();
			for(int j=0;j<elementuak.get(i).size();j++) {
				KoloreMota kKolorea = elementuak.get(i).get(j).getKolorea();
				if(!koloreak.contains(kKolorea)) {
					koloreak.add(kKolorea);
				}
			}
			if(koloreak.size() >= 3) {
				irabaziDu = true;
				break;
			}
		}
		
		//Konprobatu elementu desberdineko kartak
		if(!irabaziDu) {
			
			int s = 0;
			int e = 0;
			int u = 0;
			
			while(itrSua.hasNext() && !irabaziDu) {
				ArrayList<KoloreMota> koloreak = new ArrayList<KoloreMota>();
				KoloreMota suKolorea = itrSua.next().getKolorea();
				koloreak.add(suKolorea);
				
				while(itrElurra.hasNext() && !irabaziDu) {
					KoloreMota elKolorea = itrElurra.next().getKolorea();
					if(!koloreak.contains(elKolorea)) {
						koloreak.add(elKolorea);
						
						while(itrUra.hasNext() && !irabaziDu) {
							KoloreMota urKolorea = itrUra.next().getKolorea();
							if(!koloreak.contains(urKolorea)) {
								irabaziDu = true;								
							}
						}
					}
				}
			}
		}
		
		return irabaziDu;
	}
	//Hecho
	
	private void kartaBatEman(Jokalaria pJokalaria) 
	{
		pJokalaria.gehituJolastekoKarta(KartaGuztiak.getKarta(new Random().nextInt(KartaGuztiak.getTamaina())));
	}
	//Hecho
	
	private void aplikatuAurrekoEfektua(Jokalaria pJokalaria) 
	{
		
		int listaTam = pJokalaria.gordetakoKartenKantitatea(); 
		int i = 0;
		boolean kendua = false;
		switch(aurrekoTxandakoEfektua) 
		{
		case KENDUURA:
			while(listaTam>=i && !kendua) 
			{
				if(pJokalaria.lortuGordetakoKarta(i).getElementua==ElementuMota.URA) 
				{
					pJokalaria.kenduGordetakoKarta(pJokalaria.lortuGordetakoKarta(i));
					kendua = true;
				}
				i++;
			}
			break;
		case KENDUSUA:
			while(listaTam>=i && !kendua) 
			{
				if(pJokalaria.lortuGordetakoKarta(i).getElementua==ElementuMota.SUA) 
				{
					pJokalaria.kenduGordetakoKarta(pJokalaria.lortuGordetakoKarta(i));
					kendua = true;
				}
				i++;
			}
			break;
		case KENDUELURRA:
			while(listaTam>=i && !kendua) 
			{
				if(pJokalaria.lortuGordetakoKarta(i).getElementua==ElementuMota.ELURRA) 
				{
					pJokalaria.kenduGordetakoKarta(pJokalaria.lortuGordetakoKarta(i));
					kendua = true;
				}
				i++;
			}
			break;
		case KENDUGORRIBAT:
			while(listaTam>=i && !kendua) 
			{
				if(pJokalaria.lortuGordetakoKarta(i).getKolorea==KoloreMota.GORRIA) 
				{
					pJokalaria.kenduGordetakoKarta(pJokalaria.lortuGordetakoKarta(i));
					kendua = true;
				}
				i++;
			}
		case KENDUURDINBAT:
			while(listaTam>=i && !kendua) 
			{
				if(pJokalaria.lortuGordetakoKarta(i).getKolorea==KoloreMota.URDINA) 
				{
					pJokalaria.kenduGordetakoKarta(pJokalaria.lortuGordetakoKarta(i));
					kendua = true;
				}
				i++;
			}
			break;
		case KENDUHORIBAT:
			while(listaTam>=i && !kendua) 
			{
				if(pJokalaria.lortuGordetakoKarta(i).getKolorea==KoloreMota.HORIA) 
				{
					jokalari1.kenduGordetakoKarta(pJokalaria.lortuGordetakoKarta(i));
					kendua = true;
				}
				i++;
			}
			break;
		case KENDUBERDEBAT:
			while(listaTam>=i && !kendua) 
			{
				if(pJokalaria.lortuGordetakoKarta(i).getKolorea==KoloreMota.BERDEA) 
				{
					pJokalaria.kenduGordetakoKarta(pJokalaria.lortuGordetakoKarta(i));
					kendua = true;
				}
				i++;
			}
			break;
		case KENDULARANJABAT:
			while(listaTam>=i && !kendua) 
			{
				if(pJokalaria.lortuGordetakoKarta(i).getKolorea==KoloreMota.LARANJA) 
				{
					pJokalaria.kenduGordetakoKarta(pJokalaria.lortuGordetakoKarta(i));
					kendua = true;
				}
				i++;
			}
			break;
		case KENDUMOREBAT:
			while(listaTam>=i && !kendua) 
			{
				if(pJokalaria.lortuGordetakoKarta(i).getKolorea==KoloreMota.MOREA) 
				{
					pJokalaria.kenduGordetakoKarta(pJokalaria.lortuGordetakoKarta(i));
					kendua = true;
				}
				i++;
			}
			break;
		case KENDUGORRIGUZTIAK:
			while(listaTam>=i) 
			{
				if(pJokalaria.lortuGordetakoKarta(i).getKolorea==KoloreMota.GORRIA) 
				{
					pJokalaria.kenduGordetakoKarta(pJokalaria.lortuGordetakoKarta(i));
				}
				i++;
			}
			break;
		case KENDUURDINGUZTIAK:
			while(listaTam>=i) 
			{
				if(pJokalaria.lortuGordetakoKarta(i).getKolorea==KoloreMota.URDINA) 
				{
					pJokalaria.kenduGordetakoKarta(pJokalaria.lortuGordetakoKarta(i));
				}
				i++;
			}
			break;
		case KENDUHORIGUZTIAK:
			while(listaTam>=i) 
			{
				if(pJokalaria.lortuGordetakoKarta(i).getKolorea==KoloreMota.HORIA) 
				{
					pJokalaria.kenduGordetakoKarta(pJokalaria.lortuGordetakoKarta(i));
				}
				i++;
			}
			break;
		case KENDUBERDEGUZTIAK:
			while(listaTam>=i) 
			{
				if(pJokalaria.lortuGordetakoKarta(i).getKolorea==KoloreMota.BERDEA) 
				{
					pJokalaria.kenduGordetakoKarta(pJokalaria.lortuGordetakoKarta(i));
				}
				i++;
			}
			break;
		case KENDULARANJAGUZTIAK:
			while(listaTam>=i) 
			{
				if(pJokalaria.lortuGordetakoKarta(i).getKolorea==KoloreMota.LARANJA) 
				{
					pJokalaria.kenduGordetakoKarta(pJokalaria.lortuGordetakoKarta(i));
				}
				i++;
			}
			break;
		case KENDUMOREGUZTIAK:
			while(listaTam>=i) 
			{
				if(pJokalaria.lortuGordetakoKarta(i).getKolorea==KoloreMota.MOREA) 
				{
					pJokalaria.kenduGordetakoKarta(pJokalaria.lortuJolastekoKarta(i));
				}
				i++;
			}
			break;
		case SUABLOKEATU:
			while(listaTam>=i) 
			{
				if(pJokalaria.lortuJolastekoKarta(i).getElementua==ElementuMota.SUA) 
				{
					pJokalaria.lortuJolastekoKarta(i).setErabilgarritazuna(false);
				}
				i++;
			}
			break;
		case URABLOKEATU:
			while(listaTam>=i) 
			{
				if(pJokalaria.lortuJolastekoKarta(i).getElementua==ElementuMota.URA) 
				{
					pJokalaria.lortuJolastekoKarta(i).setErabilgarritazuna(false);
				}
				i++;
			}
			break;
		case ELURRABLOKEATU:
			while(listaTam>=i) 
			{
				if(pJokalaria.lortuJolastekoKarta(i).getElementua==ElementuMota.ELURRA) 
				{
					pJokalaria.lortuJolastekoKarta(i).setErabilgarritazuna(false);
				}
				i++;
			}
			break;
		}
	}
	//Hecho
	
	private JokalariMota kartakKonprobatu() 
	{
		//Baloreak gorde
		
		int aurIrabazlea = -1;
		ElementuMota elementuaL = jokalariLokalaKarta.getElementua();;
		ElementuMota elementuaB = jokalariBotKarta.getElementua();
		int balioaL =  jokalariLokalaKarta.getBalioa();
		int balioaB =  jokalariBotKarta.getBalioa();
		boolean minwin = false;
		int finala = -1;
		
		//Aurreko jokaldia nork irabazi duen jakin
		
		if(this.aurrekoTxandakoIrabazlea instanceof JokalariaLokala) {aurIrabazlea = 1;}else{aurIrabazlea = 0;}
		
		//Irabazlearen arabera balioak aldatu
		
		switch(aurIrabazlea) 
		{
		case 1:
			switch(aurrekoTxandakoEfektua) 
			{
			case BIGEHITU:
				balioaL = balioaL + 2;
				break;
			case BIKENDU:
				balioaB = balioaB-2;
				break;
			}
			break;
		case 0:
			switch(aurrekoTxandakoEfektua) 
			{
			case BIGEHITU:
				balioaB = balioaB + 2;
				break;
			case BIKENDU:
				balioaL = balioaL-2;
				break;
			}
			break;
		}
		
		if(aurrekoTxandakoEfektuaZenbakia) 
		{
			minwin=true;
			aurrekoTxandakoEfektuaZenbakia = false;
		}
		
		//Nork irabazten du?
		
		if(elementuaL==elementuaB) 
		{
			if(balioaL>balioaB) 
			{
				if(!minwin) 
				{
					finala = 1;
				}
				else 
				{
					finala = 2;
				}
			}
			else if(balioaL<balioaB) 
			{
				if(!minwin) 
				{
					finala = 2;
				}
				else 
				{
					finala = 1;
				}
			}
			else if(balioaL==balioaB) 
			{
				finala = 0;
			}
		}
		else if(elementuaIrabazi(elementuaL,elementuaB)) 
		{
			finala = 1;
		}
		else if(elementuaIrabazi(elementuaB,elementuaL)) 
		{
			finala = 2;
		}

		
		//Balioak heman
		
		JokalariMota irabazlea;
		
		if(finala==1) 
		{
			irabazlea = JokalariMota.BOT;
		}
		else if(finala==2) 
		{
			irabazlea = JokalariMota.LOKALA;
		}
		else if(finala==0) 
		{
			irabazlea = JokalariMota.BERDINKETA;
		}
		
		if((jokalariLokalaKarta instanceof KartaBerezia && jokalariLokalaKarta.getEfektua==EfektuMota.ZENBAKIALDAKETA) 
				|| (jokalariBotKarta instanceof KartaBerezia && jokalariLokalaKarta.getEfektua==EfektuMota.ZENBAKIALDAKETA))
		{
			aurrekoTxandakoEfektuaZenbakia = true;
		}
		
		// Imprimaketa
		KontsolaK.imprimatu(jokalari1.getIzena()+": E:"+elementuaL+" B:"+balioaL+" K: "+jokalariLokalaKarta.getKolorea()+(jokalariLokalaKarta instanceof KartaBerezia) ? "Ef: "+jokalariLokalaKarta.getDeskripzioa().split("#")[0]:"");
		KontsolaK.imprimatu(jokalari2.getIzena()+": E:"+elementuaB+" B:"+balioaB+" K: "+jokalariBotKarta.getKolorea()+(jokalariBotKarta instanceof KartaBerezia) ? "Ef: "+jokalariBotKarta.getDeskripzioa().split("#")[0]:"");
		
		return irabazlea;
	}
	//Hecho
	
	private boolean elementuaIrabazi(ElementuMota pElementua1,ElementuMota pElementua2) 
	{
		if(pElementua1==ElementuMota.SUA && pElementua2==ElementuMota.ELURRA) 
		{
			return true;
		}
		else if(pElementua1==ElementuMota.SUA && pElementua2==ElementuMota.URA) 
		{
			return false;
		}
		else if(pElementua1==ElementuMota.URA && pElementua2==ElementuMota.SUA) 
		{
			return true;
		}
		else if(pElementua1==ElementuMota.URA && pElementua2==ElementuMota.ELURRA) 
		{
			return false;
		}
		else if(pElementua1==ElementuMota.ELURRA && pElementua2==ElementuMota.URA) 
		{
			return true;
		}
		else if(pElementua1==ElementuMota.ELURRA && pElementua2==ElementuMota.SUA) 
		{
			return false;
		}
	}
	//Hecho
	
}

