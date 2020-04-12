package cardjitsu;

import java.util.ArrayList;
import java.util.Iterator;


public class ListaJokalariak {
	
	private JokalariaLokala jokalari1;
	private JokalariaBot jokalari2;
	private EfektuMota aurrekoTxandakoEfektua;
	private boolean aurrekoTxandakoEfektuaZenbakia;
	private Jokalaria aurrekoTxandakoIrabazlea;
	private Karta jokalariLokalaKarta;
	private Karta jokalariBotKarta;
	private static ListaJokalariak nireListaJokalariak;
	private Kontsola kontsola;
	private KartaSorta kSorta;
	
	private ListaJokalariak() 
	{
		this.jokalari1 = null;
		this.jokalari2 = null;
		this.aurrekoTxandakoEfektua = null;
		this.jokalariBotKarta = null;
		this.jokalariLokalaKarta = null;
		this.aurrekoTxandakoEfektuaZenbakia = false;
		kontsola = Kontsola.getKontsola();
		kSorta = KartaSorta.getKartaSorta();
	}
	//Necesita revision
	
	public static ListaJokalariak getNireListaJokalariak() 
	{
		if(nireListaJokalariak==null) 
		{
			nireListaJokalariak = new ListaJokalariak();
		}
		return nireListaJokalariak;
	} 
	//Necesita revision
	
	public void partidaBerriaHasi() {
		//Titulua imprimatu
		//TODO
		
		//Izena eskatu
		kontsola.imprimatu("Sartu izena:");
		String izena = kontsola.testuaIrakurri();
		
		//Jokalariak sortu
		jokalari1 = JokalariaLokala.getNireJokalaria(izena);
		jokalari2 = JokalariaBot.getNireJokalaria("Sensei");
		
		//KartaSorta hasieratu
		kSorta.jsonetikKartetara();
		
		//Kartak banatu
		for (int i = 0; i<5;i++) {
			this.kartakBanatu();
		}
		
		Jokalaria irabazlea = this.txandaBerria();
		
		//Irabazleari zorionak eman
		//TODO
	}
	//Sin hacer
	
	private Jokalaria txandaBerria() {
		Jokalaria irabazlea;
		//Kartak banatu
		this.kartakBanatu();
		
		//Aplikatu aurrreko efektua aurreko txandan galdu duenari
		if(aurrekoTxandakoIrabazlea.equals(jokalari1)) 
		{
			this.aplikatuAurrekoEfektua(jokalari2);
		}
		else if(aurrekoTxandakoIrabazlea.equals(jokalari2)) 
		{
			this.aplikatuAurrekoEfektua(jokalari1);
		}
		
		//Txanda imprimatu
		kontsola.imprimatu(jokalari1.getIzena()+":");
		for(int i=0;i<jokalari1.gordetakoKartenKantitatea();i++) {
			Karta karta = jokalari1.lortuGordetakoKartaPosz(i);
			kontsola.imprimatu("E: "+karta.getElementua().name()+"\tK: "+karta.getKolorea());
		}
		kontsola.imprimatu(" ");
		kontsola.imprimatu(jokalari2.getIzena()+":");
		for(int i=0;i<jokalari2.gordetakoKartenKantitatea();i++) {
			Karta karta = jokalari2.lortuGordetakoKartaPosz(i);
			kontsola.imprimatu("E: "+karta.getElementua().name()+"\tK: "+karta.getKolorea());
		}
		
		kontsola.imprimatu("Zure txanda "+jokalari1.getIzena());
		for(int i=1;i<6;i++) {
			Karta karta = jokalari1.lortuJolastekoKartaPosz(i-1);
			kontsola.imprimatu("["+((karta.getErabilgarria()) ? "#":i)+"] E: "+karta.getElementua().name()+"\tB: "+karta.getBalioa()+"\tK: "+karta.getKolorea().name()+"\t"+((karta instanceof KartaBerezia) ? "Ef: "+((KartaBerezia) karta).getDeskripzioa().split("#")[0]:""));
		}
		
		//TODO
		
		//Jokalariak karta bat aukeratu hau jokatzeko
		jokalariLokalaKarta = jokalari1.kartaAukeratu();
		jokalariBotKarta = jokalari2.kartaAukeratu();
		
		//Txandan jokatutako kartak konprobatu eta karten emaitza imprimatu
		JokalariMota txJok = this.kartakKonprobatu();
		if(txJok == JokalariMota.LOKALA) {
			aurrekoTxandakoEfektua = (jokalariLokalaKarta instanceof KartaBerezia) ? ((KartaBerezia) jokalariLokalaKarta).getEfektua():null;
			aurrekoTxandakoIrabazlea = jokalari1;
			jokalari1.gehituGordetakoKarta(jokalariLokalaKarta);
			kontsola.imprimatu(jokalari1.getIzena()+" irabazi du"+((jokalariLokalaKarta instanceof KartaBerezia) ? ", "+((KartaBerezia) jokalariLokalaKarta).getDeskripzioa().split("#")[1]:"."));
		} else if (txJok == JokalariMota.BOT) {
			aurrekoTxandakoEfektua = (jokalariBotKarta instanceof KartaBerezia) ? ((KartaBerezia) jokalariBotKarta).getEfektua():null;
			aurrekoTxandakoIrabazlea = jokalari2;
			jokalari2.gehituGordetakoKarta(jokalariBotKarta);
			kontsola.imprimatu(jokalari2.getIzena()+" irabazi du"+((jokalariBotKarta instanceof KartaBerezia) ? ", "+((KartaBerezia) jokalariBotKarta).getDeskripzioa().split("#")[1]:"."));
		} else {
			aurrekoTxandakoEfektua = null;
			aurrekoTxandakoIrabazlea = null;
			kontsola.imprimatu("Berdinketa bat egon da.");
		}
		
		//Partida norbaitek irabazi badu konprobatu
		if (this.partidarenIrabazleaKonprobatu(jokalari1)) {
			irabazlea = jokalari1;
		} else if(this.partidarenIrabazleaKonprobatu(jokalari2)) {
			irabazlea = jokalari2;
		} else {
			//Errekurtsibitatea
			irabazlea = this.txandaBerria();
		}
		
		return irabazlea;
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
			Karta karta = pJokalaria.lortuGordetakoKartaPosz(i);
			
			switch(karta.getElementua()) {
			case SUA:
				sua.add(karta);
				break;
			case ELURRA:
				elurra.add(karta);
				break;
			case URA:
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
	
	private void kartakBanatu() 
	{
		jokalari1.gehituJolastekoKarta(kSorta.getKarta());
		jokalari2.gehituJolastekoKarta(kSorta.getKarta());
	}
	//Hecho
	
	private void aplikatuAurrekoEfektua(Jokalaria pJokalaria) 
	{
		
		int listaTam = pJokalaria.gordetakoKartenKantitatea(); 
		int i = 0;
		boolean kendua = false;
		KoloreMota kolorea = null;
		ElementuMota mota = null;
		
		switch(aurrekoTxandakoEfektua) 
		{
		case KENDUURA:
		case KENDUSUA:
		case KENDUELURRA:
			
			if(aurrekoTxandakoEfektua==EfektuMota.KENDUURA){mota = ElementuMota.URA;}
			else if(aurrekoTxandakoEfektua==EfektuMota.KENDUSUA){mota = ElementuMota.SUA;}
			else if(aurrekoTxandakoEfektua==EfektuMota.KENDUELURRA){mota = ElementuMota.ELURRA;}
			
			while(listaTam>i && !kendua) 
			{
				if(pJokalaria.lortuGordetakoKartaPosz(i).getElementua()==mota) 
				{
					pJokalaria.kenduGordetakoKarta(pJokalaria.lortuGordetakoKartaPosz(i));
					kendua = true;
				}
				i++;
			}
			break;
			
		case KENDUGORRIBAT:
		case KENDUURDINBAT:
		case KENDUHORIBAT:
		case KENDUBERDEBAT:
		case KENDULARANJABAT:
		case KENDUMOREBAT:
			
			if(aurrekoTxandakoEfektua==EfektuMota.KENDUGORRIBAT){kolorea = KoloreMota.GORRIA;}
			else if(aurrekoTxandakoEfektua==EfektuMota.KENDUURDINBAT){kolorea = KoloreMota.URDINA;}
			else if(aurrekoTxandakoEfektua==EfektuMota.KENDUHORIBAT){kolorea = KoloreMota.HORIA;}
			else if(aurrekoTxandakoEfektua==EfektuMota.KENDUBERDEBAT){kolorea = KoloreMota.BERDEA;}
			else if(aurrekoTxandakoEfektua==EfektuMota.KENDULARANJABAT){kolorea = KoloreMota.LARANJA;}
			else if(aurrekoTxandakoEfektua==EfektuMota.KENDUMOREBAT){kolorea = KoloreMota.MOREA;}
			
			while(listaTam>i && !kendua) 
			{
				if(pJokalaria.lortuGordetakoKartaPosz(i).getKolorea()==kolorea) 
				{
					pJokalaria.kenduGordetakoKarta(pJokalaria.lortuGordetakoKartaPosz(i));
					kendua = true;
				}
				i++;
			}
			break;
			
		case KENDUGORRIGUZTIAK:
		case KENDUURDINGUZTIAK:
		case KENDUHORIGUZTIAK:
		case KENDUBERDEGUZTIAK:
		case KENDULARANJAGUZTIAK:
		case KENDUMOREGUZTIAK:

			if(aurrekoTxandakoEfektua==EfektuMota.KENDUGORRIGUZTIAK){kolorea = KoloreMota.GORRIA;}
			else if(aurrekoTxandakoEfektua==EfektuMota.KENDUURDINGUZTIAK){kolorea = KoloreMota.URDINA;}
			else if(aurrekoTxandakoEfektua==EfektuMota.KENDUHORIGUZTIAK){kolorea = KoloreMota.HORIA;}
			else if(aurrekoTxandakoEfektua==EfektuMota.KENDUBERDEGUZTIAK){kolorea = KoloreMota.BERDEA;}
			else if(aurrekoTxandakoEfektua==EfektuMota.KENDULARANJAGUZTIAK){kolorea = KoloreMota.LARANJA;}
			else if(aurrekoTxandakoEfektua==EfektuMota.KENDUMOREGUZTIAK){kolorea = KoloreMota.MOREA;}
			
			while(listaTam>i) 
			{
				if(pJokalaria.lortuGordetakoKartaPosz(i).getKolorea()==kolorea) 
				{
					pJokalaria.kenduGordetakoKarta(pJokalaria.lortuJolastekoKartaPosz(i));
				}
				i++;
			}
			break;
			
		case SUABLOKEATU:
		case URABLOKEATU:
		case ELURRABLOKEATU:
			
			if(aurrekoTxandakoEfektua==EfektuMota.SUABLOKEATU){mota = ElementuMota.SUA;}
			else if(aurrekoTxandakoEfektua==EfektuMota.URABLOKEATU){mota = ElementuMota.URA;}
			else if(aurrekoTxandakoEfektua==EfektuMota.ELURRABLOKEATU){mota = ElementuMota.ELURRA;}
			
			while(listaTam>i) 
			{
				if(pJokalaria.lortuJolastekoKartaPosz(i).getElementua()==mota) 
				{
					pJokalaria.lortuJolastekoKartaPosz(i).setErabilgarria(false);
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
		ElementuMota elementuaL = jokalariLokalaKarta.getElementua();
		ElementuMota elementuaB = jokalariBotKarta.getElementua();
		int balioaL =  jokalariLokalaKarta.getBalioa();
		int balioaB =  jokalariBotKarta.getBalioa();
		EfektuMota efektuL = ((KartaBerezia) jokalariLokalaKarta).getEfektua();
		EfektuMota efektuB = ((KartaBerezia) jokalariBotKarta).getEfektua();
		boolean minwin = false;
		int finala = -1;
		
		//Aurreko jokaldia nork irabazi duen jakin
		
		if(this.aurrekoTxandakoIrabazlea instanceof JokalariaLokala) {aurIrabazlea = 1;}else{aurIrabazlea = 0;}
		
		//Aurreko irabazlearen arabera aplikatu aurreko efektua
		
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
		
		//aurrekoTxandakoEfektuaZenbakia erabili eta efektua kendu
		
		if(aurrekoTxandakoEfektuaZenbakia) 
		{
			minwin=true;
			aurrekoTxandakoEfektuaZenbakia = false;
		}
		
		//Karta batek elementua aldatzen badu
		if(((KartaBerezia) jokalariLokalaKarta).getEfektua()==EfektuMota.ELURRATIKURARA
				|| ((KartaBerezia) jokalariLokalaKarta).getEfektua()==EfektuMota.URATIKSURA
				|| ((KartaBerezia) jokalariLokalaKarta).getEfektua()==EfektuMota.SUTIKELURRARA) 
		{
			EfektuMota efektua = ((KartaBerezia) jokalariLokalaKarta).getEfektua();
			switch (efektua) 
			{
			case ELURRATIKURARA:
				if(elementuaB==ElementuMota.ELURRA) {elementuaB=ElementuMota.URA;}
				break;
			case URATIKSURA:
				if(elementuaB==ElementuMota.URA) {elementuaB=ElementuMota.SUA;}
				break;
			case SUTIKELURRARA:
				if(elementuaB==ElementuMota.SUA) {elementuaB=ElementuMota.ELURRA;}
				break;
			}
		}
		
		if(((KartaBerezia) jokalariBotKarta).getEfektua()==EfektuMota.ELURRATIKURARA
				|| ((KartaBerezia) jokalariBotKarta).getEfektua()==EfektuMota.URATIKSURA
				|| ((KartaBerezia) jokalariBotKarta).getEfektua()==EfektuMota.SUTIKELURRARA) 
		{
			EfektuMota efektua = ((KartaBerezia) jokalariBotKarta).getEfektua();
			switch (efektua) 
			{
			case ELURRATIKURARA:
				if(elementuaL==ElementuMota.ELURRA) {elementuaB=ElementuMota.URA;}
				break;
			case URATIKSURA:
				if(elementuaL==ElementuMota.URA) {elementuaB=ElementuMota.SUA;}
				break;
			case SUTIKELURRARA:
				if(elementuaL==ElementuMota.SUA) {elementuaB=ElementuMota.ELURRA;}
				break;
			}
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

		//try {}catch{}
		
		//Balioak heman
		
		JokalariMota irabazlea = null;
		
		if(finala==1) 
		{
			irabazlea = JokalariMota.LOKALA;
		}
		else if(finala==2) 
		{
			irabazlea = JokalariMota.BOT;
		}
		else if(finala==0) 
		{
			irabazlea = JokalariMota.BERDINKETA;
		}
		
		if((jokalariLokalaKarta instanceof KartaBerezia && ((KartaBerezia) jokalariLokalaKarta).getEfektua()==EfektuMota.ZENBAKIALDAKETA) 
				|| (jokalariBotKarta instanceof KartaBerezia && ((KartaBerezia) jokalariLokalaKarta).getEfektua()==EfektuMota.ZENBAKIALDAKETA))
		{
			aurrekoTxandakoEfektuaZenbakia = true;
		}
		
		// Imprimaketa
		kontsola.imprimatu(jokalari1.getIzena()+": E:"+elementuaL+" B:"+balioaL+" K: "+jokalariLokalaKarta.getKolorea()+((jokalariLokalaKarta instanceof KartaBerezia) ? "Ef: "+((KartaBerezia) jokalariLokalaKarta).getDeskripzioa().split("#")[0]:""));
		kontsola.imprimatu(jokalari2.getIzena()+": E:"+elementuaB+" B:"+balioaB+" K: "+jokalariBotKarta.getKolorea()+((jokalariBotKarta instanceof KartaBerezia) ? "Ef: "+((KartaBerezia) jokalariBotKarta).getDeskripzioa().split("#")[0]:""));
		
		return irabazlea;
	}
	//Hecho
	

	private boolean elementuaIrabazi(ElementuMota pElementua1,ElementuMota pElementua2) 
	{	boolean irabazi=false;
		if(pElementua1==ElementuMota.SUA && pElementua2==ElementuMota.ELURRA) {
			irabazi=true;
		}else if(pElementua1==ElementuMota.URA && pElementua2==ElementuMota.SUA) {
			irabazi= true;
		}else if(pElementua1==ElementuMota.ELURRA && pElementua2==ElementuMota.URA) {
			irabazi=true;
		}
		
		//Elementu berdinak badira kartakKonprobatun begiratuko da
		//Irabazi egiten ez badu galdu egin du
		
		return irabazi;
	}
	//Hecho
	
}

