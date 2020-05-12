package cardjitsu;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.naming.PartialResultException;


public class ListaJokalariak {
	
	private Jokalaria[] jokalariak;
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
		this.jokalariak = new Jokalaria[2];
		this.aurrekoTxandakoEfektua = null;
		this.jokalariBotKarta = null;
		this.jokalariLokalaKarta = null;
		this.aurrekoTxandakoEfektuaZenbakia = false;
		kontsola = Kontsola.getKontsola();
		kSorta = KartaSorta.getKartaSorta();
	}
	
	public static ListaJokalariak getNireListaJokalariak() 
	{
		if(nireListaJokalariak==null) 
		{
			nireListaJokalariak = new ListaJokalariak();
		}
		return nireListaJokalariak;
	}
	
	public static void main(String[] args) {
		new ListaJokalariak().partidaBerriaHasi();
	}
	
	public void partidaBerriaHasi() {
		//Titulua inprimatu
		kontsola.inprimatuLinea("   ____              _           _ _ _             ","","lar","");
		kontsola.inprimatuLinea("  / ___|__ _ _ __ __| |         | (_) |_ ___ _   _ ","","lar","");
		kontsola.inprimatuLinea(" | |   / _` | '__/ _` |_____ _  | | | __/ __| | | |","","lar","");
		kontsola.inprimatuLinea(" | |__| (_| | | | (_| |_____| |_| | | |_\\__ \\ |_| |","","lar","");
		kontsola.inprimatuLinea("  \\____\\__,_|_|  \\__,_|      \\___/|_|\\__|___/\\__,_|","","lar","");
		
		//Izena eskatu
		kontsola.inprimatuLinea("Sartu izena:");
		String izena = kontsola.testuaIrakurri();
		
		//Jokalariak sortu
		jokalariak[0] = JokalariaLokala.getNireJokalaria(izena);
		jokalariak[1] = JokalariaBot.getNireJokalaria("Sensei");
		
		//KartaSorta hasieratu
		kSorta.jsonetikKartetara();
		
		//Kartakbanatu
		for (int i = 0; i<4;i++) {
			this.kartakBanatu();
		}
		
		Jokalaria irabazlea = this.txandaBerria();
		
		//Irabazleari zorionak eman 
		kontsola.inprimatuLinea("----------------------","","","zur");
		kontsola.inprimatuLinea(jokalariak[0].getIzena()+":","","zia","");
		gordetakoKartakImprimatu(jokalariak[0]);
		kontsola.inprimatuLinea(" ");
		
		kontsola.inprimatuLinea(jokalariak[1].getIzena()+":","","mag","");
		gordetakoKartakImprimatu(jokalariak[1]);
		kontsola.inprimatuLinea(" ");
		kontsola.inprimatuLinea("Zorionak "+irabazlea.getIzena()+", partida irabazi duzu!","","bel","zur");
		kontsola.enterTekla();
	}

	private Jokalaria txandaBerria() { 
		kontsola.kontsolaGarbitu();
		Jokalaria irabazlea;
		//Kartak banatu
		this.kartakBanatu();
		
		//Aplikatu aurrreko efektua aurreko txandan galdu duenari
		if(aurrekoTxandakoIrabazlea != null && aurrekoTxandakoIrabazlea.equals(jokalariak[0])) 
		{
			this.aplikatuAurrekoEfektua(jokalariak[1]);
		}
		else if(aurrekoTxandakoIrabazlea != null && aurrekoTxandakoIrabazlea.equals(jokalariak[1])) 
		{
			this.aplikatuAurrekoEfektua(jokalariak[0]);
		}
		
		//Txanda imprimatu
		kontsola.inprimatuLinea(jokalariak[0].getIzena()+":","","zia","");
		gordetakoKartakImprimatu(jokalariak[0]);
		kontsola.inprimatuLinea(" ");
		
		kontsola.inprimatuLinea(jokalariak[1].getIzena()+":","","mag","");
		gordetakoKartakImprimatu(jokalariak[1]);
		kontsola.inprimatuLinea(" ");
		kontsola.inprimatu("Zure txanda ");
		kontsola.inprimatuLinea(jokalariak[0].getIzena(),"","zia","");
		for(int i=1;i<6;i++) {
			Karta karta = null;
			karta = jokalariak[0].lortuJolastekoKartaPosz(i-1);
			//kontsola.imprimatu("["+((karta.getErabilgarria()) ? i:"#")+"] E: "+karta.getElementua().name()+"\tB: "+karta.getBalioa()+"\tK: "+karta.getKolorea().name()+"\t"+((karta instanceof KartaBerezia) ? "Ef: "+((KartaBerezia) karta).getDeskripzioa().split("#")[0].replaceFirst("<jokalariaL>",  jokalariak[1].getIzena()):""));
			
			kontsola.inprimatu("["+((karta.getErabilgarria()) ? i:"#")+"] E:");
			
			elementuKoloreduna(karta.getElementua());
			kontsola.inprimatu("\tB:"+karta.getBalioa()+"\tK:");

			kartaKoloreduna(karta.getKolorea());
			
			kontsola.inprimatu("  ");//Tabuladorea ondo funtzionatzeko
			
			if(karta instanceof KartaBerezia) {
				if(((KartaBerezia) karta).getDeskripzioa().split("#")[0].contains("<jokalariaL>")) {
					efektuaKoloreztatu("\tEf:"+((KartaBerezia) karta).getDeskripzioa().split("#")[0].split("<jokalariaL>")[0]);
					kontsola.inprimatu(jokalariak[1].getIzena(),"","mag","");
					efektuaKoloreztatu(((KartaBerezia) karta).getDeskripzioa().split("#")[0].split("<jokalariaL>")[1]);			
				} else {
					kontsola.inprimatu("\tEf:"+((KartaBerezia) karta).getDeskripzioa().split("#")[0]);
				}
				
			}
			kontsola.inprimatuLinea("");
		}
		
		//TODO Sensei-ren kartak ez aurkeztu
		for(int i=1;i<6;i++) {
			Karta karta = null;
			karta = jokalariak[1].lortuJolastekoKartaPosz(i-1);
			//kontsola.imprimatu("["+((karta.getErabilgarria()) ? i:"#")+"] E: "+karta.getElementua().name()+"\tB: "+karta.getBalioa()+"\tK: "+karta.getKolorea().name()+"\t"+((karta instanceof KartaBerezia) ? "Ef: "+((KartaBerezia) karta).getDeskripzioa().split("#")[0].replaceFirst("<jokalariaL>",  jokalariak[1].getIzena()):""));
			
			kontsola.inprimatu("["+((karta.getErabilgarria()) ? i:"#")+"] E:","","gor","");
			
			elementuKoloreduna(karta.getElementua());
			kontsola.inprimatu("\tB:"+karta.getBalioa()+"\tK:","","gor","");

			kartaKoloreduna(karta.getKolorea());
			
			kontsola.inprimatu("  ");//Tabuladorea ondo funtzionatzeko
			
			if(karta instanceof KartaBerezia) {
				if(((KartaBerezia) karta).getDeskripzioa().split("#")[0].contains("<jokalariaL>")) {
					efektuaKoloreztatu("\tEf:"+((KartaBerezia) karta).getDeskripzioa().split("#")[0].split("<jokalariaL>")[0]);
					kontsola.inprimatu(jokalariak[1].getIzena(),"","mag","");
					efektuaKoloreztatu(((KartaBerezia) karta).getDeskripzioa().split("#")[0].split("<jokalariaL>")[1]);			
				} else {
					kontsola.inprimatu("\tEf:"+((KartaBerezia) karta).getDeskripzioa().split("#")[0],"","gor","");
				}
				
			}
			kontsola.inprimatuLinea("");
		}
		
		kontsola.inprimatuLinea("Gogoratu: E = Elementua. B = Balioa. K = Kolorea. Ef = Efektua.");
		
		//Karta erabilgarriak daudela konprobatu
		if(!erabilgarritasunaKonprobatu(jokalariak[0])) {
			kontsola.inprimatu(jokalariak[0].getIzena(),"","zia","");
			kontsola.inprimatu(" ezin du kartak erabili, ");
			kontsola.inprimatu(jokalariak[1].getIzena(),"","mag","");
			kontsola.inprimatuLinea(" irabazi du partida.");
			return jokalariak[1];
		}
		if(!erabilgarritasunaKonprobatu(jokalariak[1])) {
			kontsola.inprimatu(jokalariak[1].getIzena(),"","mag","");
			kontsola.inprimatu(" ezin du kartak erabili, ");
			kontsola.inprimatu(jokalariak[0].getIzena(),"","zia","");
			kontsola.inprimatuLinea(" irabazi du partida.");
			return jokalariak[0];
		}
		
		//Jokalariak karta bat aukeratu hau jokatzeko
		
		jokalariLokalaKarta = jokalariak[0].kartaAukeratu();
		jokalariBotKarta = jokalariak[1].kartaAukeratu();
		//TODO kendu inprimatu hau
		Kontsola.getKontsola().inprimatuLinea("[INFO-Sensei]: E:"+jokalariBotKarta.getElementua()+" B:"+jokalariBotKarta.getBalioa()+" K:"+jokalariBotKarta.getKolorea(),"","gor","");
		
		//Txandan jokatutako kartak konprobatu eta karten emaitza imprimatu
		JokalariMota txJok = this.kartakKonprobatu();
		kontsola.inprimatuLinea("");
		
		if(txJok == JokalariMota.LOKALA) {
			aurrekoTxandakoEfektua = (jokalariLokalaKarta instanceof KartaBerezia) ? ((KartaBerezia) jokalariLokalaKarta).getEfektua():null;
			aurrekoTxandakoIrabazlea = jokalariak[0];
			jokalariak[0].gehituGordetakoKarta(jokalariLokalaKarta);
			kontsola.inprimatu(jokalariak[0].getIzena(),"","zia","");
			kontsola.inprimatu(" irabazi du");
			if(jokalariLokalaKarta instanceof KartaBerezia) {	
				kontsola.inprimatu(", ");
				if(((KartaBerezia) jokalariLokalaKarta).getDeskripzioa().split("#")[1].contains("<jokalaria>")) {
					efektuaKoloreztatu(((KartaBerezia) jokalariLokalaKarta).getDeskripzioa().split("#")[1].split("<")[0]);
					kontsola.inprimatu(jokalariak[0].getIzena(),"","zia","");
					efektuaKoloreztatu(((KartaBerezia) jokalariLokalaKarta).getDeskripzioa().split("#")[1].split(">")[1]);					
				} else if(((KartaBerezia) jokalariLokalaKarta).getDeskripzioa().split("#")[1].contains("<jokalariaL>")){
					efektuaKoloreztatu(((KartaBerezia) jokalariLokalaKarta).getDeskripzioa().split("#")[1].split("<")[0]);
					kontsola.inprimatu(jokalariak[1].getIzena(),"","mag","");
					efektuaKoloreztatu(((KartaBerezia) jokalariLokalaKarta).getDeskripzioa().split("#")[1].split(">")[1]);	
				} else {
					kontsola.inprimatu(((KartaBerezia) jokalariLokalaKarta).getDeskripzioa().split("#")[1]);
				}
				kontsola.inprimatuLinea("");
			} else {
				kontsola.inprimatuLinea(".");
			}
			
		} else if (txJok == JokalariMota.BOT) {
			aurrekoTxandakoEfektua = (jokalariBotKarta instanceof KartaBerezia) ? ((KartaBerezia) jokalariBotKarta).getEfektua():null;
			aurrekoTxandakoIrabazlea = jokalariak[1];
			jokalariak[1].gehituGordetakoKarta(jokalariBotKarta);
			kontsola.inprimatu(jokalariak[1].getIzena(),"","mag","");
			kontsola.inprimatu(" irabazi du");
			if(jokalariBotKarta instanceof KartaBerezia) {	
				kontsola.inprimatu(", ");
				if(((KartaBerezia) jokalariBotKarta).getDeskripzioa().split("#")[1].contains("<jokalaria>")) {
					efektuaKoloreztatu(((KartaBerezia) jokalariBotKarta).getDeskripzioa().split("#")[1].split("<")[0]);
					kontsola.inprimatu(jokalariak[1].getIzena(),"","mag","");
					efektuaKoloreztatu(((KartaBerezia) jokalariBotKarta).getDeskripzioa().split("#")[1].split(">")[1]);					
				} else if(((KartaBerezia) jokalariBotKarta).getDeskripzioa().split("#")[1].contains("<jokalariaL>")){
					efektuaKoloreztatu(((KartaBerezia) jokalariBotKarta).getDeskripzioa().split("#")[1].split("<")[0]);
					kontsola.inprimatu(jokalariak[0].getIzena(),"","zia","");
					efektuaKoloreztatu(((KartaBerezia) jokalariBotKarta).getDeskripzioa().split("#")[1].split(">")[1]);	
				} else {
					kontsola.inprimatu(((KartaBerezia) jokalariBotKarta).getDeskripzioa().split("#")[1]);
				}
				kontsola.inprimatuLinea("");
			} else {
				kontsola.inprimatuLinea(".");
			}
		} else {
			aurrekoTxandakoEfektua = null;
			aurrekoTxandakoIrabazlea = null;
			kontsola.inprimatuLinea("Berdinketa bat egon da.");
		}
		
		//Erabilgarri ez diren kartak erabilgarri izatera bueltatu
		erabilgarriBueltatu(jokalariak[0]);
		erabilgarriBueltatu(jokalariak[1]);
		
		//Partida norbaitek irabazi badu konprobatu
		if (this.partidarenIrabazleaKonprobatu(jokalariak[0])) {
			irabazlea = jokalariak[0];
		} else if(this.partidarenIrabazleaKonprobatu(jokalariak[1])) {
			irabazlea = jokalariak[1];
		} else {
			//Errekurtsibitatea
			kontsola.enterTekla();
			irabazlea = this.txandaBerria();
		}
		
		return irabazlea;
	}

	boolean partidarenIrabazleaKonprobatu(Jokalaria pJokalaria) 
	{
		boolean irabaziDu = false;
		
		ArrayList<Karta> sua = new ArrayList<Karta>();
		ArrayList<Karta> elurra = new ArrayList<Karta>();
		ArrayList<Karta> ura = new ArrayList<Karta>();
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
			KoloreMota suKolorea;
			KoloreMota elKolorea;
			KoloreMota urKolorea;
			
			Iterator<Karta> itrSua = sua.iterator();
			while(itrSua.hasNext() && !irabaziDu) {
				Karta suKarta = itrSua.next();
				suKolorea = suKarta.getKolorea();
				Iterator<Karta> itrElurra = elurra.iterator();
				while(itrElurra.hasNext() && !irabaziDu) {
					Karta elKarta = itrElurra.next();
					elKolorea = elKarta.getKolorea();
					if(elKolorea != suKolorea) {
						Iterator<Karta> itrUra = ura.iterator();
						while(itrUra.hasNext() && !irabaziDu) {
							Karta urKarta = itrUra.next();
							urKolorea = urKarta.getKolorea();
							if(urKolorea != suKolorea && urKolorea != elKolorea) {
								irabaziDu = true;								
							}
						}
					}
				}
			}
		}
		
		return irabaziDu;
	}

	private void kartakBanatu() 
	{
		jokalariak[0].gehituJolastekoKarta(kSorta.getKarta());
		jokalariak[1].gehituJolastekoKarta(kSorta.getKarta());
	}
	
	private boolean erabilgarritasunaKonprobatu(Jokalaria pJokalaria) {
		int i = 0;
		boolean erabilgarriDago = false;
		while (i<5 && !erabilgarriDago) {
			erabilgarriDago = pJokalaria.lortuJolastekoKartaPosz(i).getErabilgarria();
			i++;
		}
		return erabilgarriDago;
			
	}
	
	private void erabilgarriBueltatu(Jokalaria pJokalaria) {
		for (int i = 0; i<4; i++) {
			pJokalaria.lortuJolastekoKartaPosz(i).setErabilgarria(true);
		}		
	}

	void aplikatuAurrekoEfektua(Jokalaria pJokalaria) 
	{
		
		int listaTam = pJokalaria.gordetakoKartenKantitatea(); 
		int i = 0;
		boolean kendua = false;
		KoloreMota kolorea = null;
		ElementuMota mota = null;
		boolean guztia = false;
		
		if(aurrekoTxandakoEfektua != null) {
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
				
			case KENDUGORRIGUZTIAK: 
			case KENDUURDINGUZTIAK:
			case KENDUHORIGUZTIAK:
			case KENDUBERDEGUZTIAK:
			case KENDULARANJAGUZTIAK:
			case KENDUMOREGUZTIAK:
				guztia = true;
			case KENDUGORRIBAT:
			case KENDUURDINBAT:
			case KENDUHORIBAT:
			case KENDUBERDEBAT:
			case KENDULARANJABAT:
			case KENDUMOREBAT:
	
				if(aurrekoTxandakoEfektua==EfektuMota.KENDUGORRIBAT || aurrekoTxandakoEfektua==EfektuMota.KENDUGORRIGUZTIAK){kolorea = KoloreMota.GORRIA;}
				else if(aurrekoTxandakoEfektua==EfektuMota.KENDUURDINBAT || aurrekoTxandakoEfektua==EfektuMota.KENDUURDINGUZTIAK){kolorea = KoloreMota.URDINA;}
				else if(aurrekoTxandakoEfektua==EfektuMota.KENDUHORIBAT || aurrekoTxandakoEfektua==EfektuMota.KENDUHORIGUZTIAK){kolorea = KoloreMota.HORIA;}
				else if(aurrekoTxandakoEfektua==EfektuMota.KENDUBERDEBAT || aurrekoTxandakoEfektua==EfektuMota.KENDUBERDEGUZTIAK){kolorea = KoloreMota.BERDEA;}
				else if(aurrekoTxandakoEfektua==EfektuMota.KENDULARANJABAT || aurrekoTxandakoEfektua==EfektuMota.KENDULARANJAGUZTIAK){kolorea = KoloreMota.LARANJA;}
				else if(aurrekoTxandakoEfektua==EfektuMota.KENDUMOREBAT || aurrekoTxandakoEfektua==EfektuMota.KENDUMOREGUZTIAK){kolorea = KoloreMota.MOREA;}
				
				while(listaTam>i && !kendua) 
				{
					if(pJokalaria.lortuGordetakoKartaPosz(i).getKolorea()==kolorea)
					{
						pJokalaria.kenduGordetakoKarta(pJokalaria.lortuGordetakoKartaPosz(i));
						if(!guztia) {kendua = true;}
						else {listaTam = listaTam-1;}
					}
					else 
					{
						i++;
					}
					
				}
				break;
				
			case SUABLOKEATU:
			case URABLOKEATU:
			case ELURRABLOKEATU:
				
				if(aurrekoTxandakoEfektua==EfektuMota.SUABLOKEATU){mota = ElementuMota.SUA;}
				else if(aurrekoTxandakoEfektua==EfektuMota.URABLOKEATU){mota = ElementuMota.URA;}
				else if(aurrekoTxandakoEfektua==EfektuMota.ELURRABLOKEATU){mota = ElementuMota.ELURRA;}
				
				while(i<5) 
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
	}

	JokalariMota kartakKonprobatu() 
	{
		//Baloreak gorde
		
		int aurIrabazlea = -1;
		ElementuMota elementuaL = jokalariLokalaKarta.getElementua();
		ElementuMota elementuaB = jokalariBotKarta.getElementua();
		int balioaL =  jokalariLokalaKarta.getBalioa();
		int balioaB =  jokalariBotKarta.getBalioa();
		EfektuMota efektuL = (jokalariLokalaKarta instanceof KartaBerezia) ? ((KartaBerezia) jokalariLokalaKarta).getEfektua() : null;
		EfektuMota efektuB = (jokalariBotKarta instanceof KartaBerezia) ? ((KartaBerezia) jokalariBotKarta).getEfektua() : null;
		boolean minwin = false;
		int finala = -1;
		
		//Aurreko jokaldia nork irabazi duen jakin
		
		aurIrabazlea = (aurrekoTxandakoIrabazlea instanceof JokalariaLokala) ? 1 : 0;
		
		//Aurreko irabazlearen arabera aplikatu aurreko efektua
		if(aurrekoTxandakoEfektua != null) {
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
		}
		//aurrekoTxandakoEfektuaZenbakia erabili eta efektua kendu
		
		if(aurrekoTxandakoEfektuaZenbakia) 
		{
			minwin=true;
			aurrekoTxandakoEfektuaZenbakia = false;
		}
		
		//Karta batek elementua aldatzen badu
		if(jokalariLokalaKarta instanceof KartaBerezia) {
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
		}
		if(jokalariBotKarta instanceof KartaBerezia) {
			if(((KartaBerezia) jokalariBotKarta).getEfektua()==EfektuMota.ELURRATIKURARA
					|| ((KartaBerezia) jokalariBotKarta).getEfektua()==EfektuMota.URATIKSURA
					|| ((KartaBerezia) jokalariBotKarta).getEfektua()==EfektuMota.SUTIKELURRARA) 
			{
				EfektuMota efektua = ((KartaBerezia) jokalariBotKarta).getEfektua();
				switch (efektua) 
				{
				case ELURRATIKURARA:
					if(elementuaL==ElementuMota.ELURRA) {elementuaL=ElementuMota.URA;}
					break;
				case URATIKSURA:
					if(elementuaL==ElementuMota.URA) {elementuaL=ElementuMota.SUA;}
					break;
				case SUTIKELURRARA:
					if(elementuaL==ElementuMota.SUA) {elementuaL=ElementuMota.ELURRA;}
					break;
				}
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
			else 
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
				|| (jokalariBotKarta instanceof KartaBerezia && ((KartaBerezia) jokalariBotKarta).getEfektua()==EfektuMota.ZENBAKIALDAKETA))
		{
			aurrekoTxandakoEfektuaZenbakia = true;
		}
		
		//Imprimaketa
		kontsola.inprimatu(jokalariak[0].getIzena(),"","zia","");
		kontsola.inprimatu(": E:");
		elementuKoloreduna(elementuaL);
		kontsola.inprimatu(" B:"+balioaL+" K:");
		kartaKoloreduna(jokalariLokalaKarta.getKolorea());
		
		if(jokalariLokalaKarta instanceof KartaBerezia) {	
			kontsola.inprimatu(" Ef:");
			if(((KartaBerezia) jokalariLokalaKarta).getDeskripzioa().split("#")[0].contains("<jokalaria>")) {
				efektuaKoloreztatu(((KartaBerezia) jokalariLokalaKarta).getDeskripzioa().split("#")[0].split("<")[0]);
				kontsola.inprimatu(jokalariak[0].getIzena(),"","zia","");
				efektuaKoloreztatu(((KartaBerezia) jokalariLokalaKarta).getDeskripzioa().split("#")[0].split(">")[1]);					
			} else if(((KartaBerezia) jokalariLokalaKarta).getDeskripzioa().split("#")[0].contains("<jokalariaL>")){
				efektuaKoloreztatu(((KartaBerezia) jokalariLokalaKarta).getDeskripzioa().split("#")[0].split("<")[0]);
				kontsola.inprimatu(jokalariak[1].getIzena(),"","mag","");
				efektuaKoloreztatu(((KartaBerezia) jokalariLokalaKarta).getDeskripzioa().split("#")[0].split(">")[1]);	
			} else {
				kontsola.inprimatu(((KartaBerezia) jokalariLokalaKarta).getDeskripzioa().split("#")[0]);
			}
		}
		kontsola.inprimatuLinea("");
		
		kontsola.inprimatu(jokalariak[1].getIzena(),"","mag","");
		kontsola.inprimatu(": E:");
		elementuKoloreduna(elementuaB);
		kontsola.inprimatu(" B:"+balioaB+" K:");
		kartaKoloreduna(jokalariBotKarta.getKolorea());
		
		
		if(jokalariBotKarta instanceof KartaBerezia) {		
			kontsola.inprimatu(" Ef:");
			if(((KartaBerezia) jokalariBotKarta).getDeskripzioa().split("#")[0].contains("<jokalaria>")) {
				efektuaKoloreztatu(((KartaBerezia) jokalariBotKarta).getDeskripzioa().split("#")[0].split("<")[0]);
				kontsola.inprimatu(jokalariak[1].getIzena(),"","mag","");
				efektuaKoloreztatu(((KartaBerezia) jokalariBotKarta).getDeskripzioa().split("#")[0].split(">")[1]);					
			} else if(((KartaBerezia) jokalariBotKarta).getDeskripzioa().split("#")[0].contains("<jokalariaL>")){
				efektuaKoloreztatu(((KartaBerezia) jokalariBotKarta).getDeskripzioa().split("#")[0].split("<")[0]);
				kontsola.inprimatu(jokalariak[0].getIzena(),"","zia","");
				efektuaKoloreztatu(((KartaBerezia) jokalariBotKarta).getDeskripzioa().split("#")[0].split(">")[1]);	
			} else {
				kontsola.inprimatu(((KartaBerezia) jokalariBotKarta).getDeskripzioa().split("#")[0]);
			}
		}
		kontsola.inprimatuLinea("");
		
		return irabazlea;
	}

	boolean elementuaIrabazi(ElementuMota pElementua1,ElementuMota pElementua2) 
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
	
	private void gordetakoKartakImprimatu(Jokalaria pJok) {
		ArrayList<Karta> kartaGuztiak = new ArrayList<Karta>();
		ArrayList<Karta> suKartak = new ArrayList<Karta>();
		ArrayList<Karta> urKartak = new ArrayList<Karta>();
		ArrayList<Karta> elurKartak = new ArrayList<Karta>();
		
		for(int i=0;i<pJok.gordetakoKartenKantitatea();i++) {
			kartaGuztiak.add(pJok.lortuGordetakoKartaPosz(i));
		}
		
		for(int i=0;i<kartaGuztiak.size();i++) {
			Karta karta = kartaGuztiak.get(i);
			
			switch(karta.getElementua()) {
			case SUA:
				suKartak.add(karta);
				break;
			case URA:
				urKartak.add(karta);
				break;
			case ELURRA:
				elurKartak.add(karta);
				break;
			}
		}
		Iterator<Karta> itrSu = suKartak.iterator();
		Iterator<Karta> itrUr = urKartak.iterator();
		Iterator<Karta> itrElur = elurKartak.iterator();
		
		kontsola.inprimatu("SUA","azp","sua","");
		kontsola.inprimatu("\t");
		kontsola.inprimatu("URA","azp","ura","");
		kontsola.inprimatu("\t");
		kontsola.inprimatu("ELURRA","azp","elu","");
		kontsola.inprimatuLinea("");
		while(itrSu.hasNext() || itrUr.hasNext() || itrElur.hasNext()) {
			Karta karta;
			Karta sua;
			Karta ura;
			Karta elurra;
			if(itrSu.hasNext()) {
				karta = itrSu.next();
				sua = karta;
			} else {
				sua = null;
			}
			
			if(itrUr.hasNext()) {
				karta = itrUr.next();
				ura = karta;
			} else {
				ura = null;
			}
			
			if(itrElur.hasNext()) {
				karta = itrElur.next();
				elurra = karta;
			} else {
				elurra = null;
			}
			
			//kontsola.inprimatuLinea(sua+((sua.length()!=0)?("\t"+ura+((ura.length()!=0)?("\t"+elurra):("\t"+elurra))):("\t"+ura+((ura.length()!=0)?("\t"+elurra):("\t"+elurra)))));
			if(sua!=null)kartaKoloreduna(sua.getKolorea());
			kontsola.inprimatu("\t");
			if(ura!=null)kartaKoloreduna(ura.getKolorea());
			kontsola.inprimatu("\t");
			if(elurra!=null)kartaKoloreduna(elurra.getKolorea());
			kontsola.inprimatuLinea("");
		}
		
	}
	
	private void kartaKoloreduna(KoloreMota pKolorea) {
		switch(pKolorea) {
		case BERDEA:
			kontsola.inprimatu("BERDEA","","ber","");
			break;
		case GORRIA:
			kontsola.inprimatu("GORRIA","","gor","");
			break;
		case HORIA:
			kontsola.inprimatu("HORIA","","hor","");
			break;
		case LARANJA:
			kontsola.inprimatu("LARANJA","","lar","");
			break;
		case MOREA:
			kontsola.inprimatu("MOREA","","mor","");
			break;
		case URDINA:
			kontsola.inprimatu("URDINA","","urd","");
			break;
		}
	}
	
	private void elementuKoloreduna(ElementuMota pElementua) {
		switch(pElementua) {
		case ELURRA:
			kontsola.inprimatu("ELURRA","","elu","");
			break;
		case SUA:
			kontsola.inprimatu("SUA","","sua","");
			break;
		case URA:
			kontsola.inprimatu("URA","","ura","");
			break;			
		}
	}
	
	private void efektuaKoloreztatu(String pTestua) {
		if(pTestua.contains("gorria")) {
			efektuaKoloreztatu(pTestua.split("gorria")[0]);
			kontsola.inprimatu("gorria","","gor","");
			efektuaKoloreztatu(pTestua.split("gorria")[1]);
		} else if (pTestua.contains("urdina")) {
			efektuaKoloreztatu(pTestua.split("urdina")[0]);
			kontsola.inprimatu("urdina","","urd","");
			kontsola.inprimatu(pTestua.split("urdina")[1]);
		} else if (pTestua.contains("horia")) {
			efektuaKoloreztatu(pTestua.split("horia")[0]);
			kontsola.inprimatu("horia","","hor","");
			efektuaKoloreztatu(pTestua.split("horia")[1]);
		} else if (pTestua.contains("berdea")) {
			efektuaKoloreztatu(pTestua.split("berdea")[0]);
			kontsola.inprimatu("berdea","","ber","");
			efektuaKoloreztatu(pTestua.split("berdea")[1]);
		} else if (pTestua.contains("laranja")) {
			efektuaKoloreztatu(pTestua.split("laranja")[0]);
			kontsola.inprimatu("laranja","","lar","");
			efektuaKoloreztatu(pTestua.split("laranja")[1]);
		} else if (pTestua.contains("morea")) {
			efektuaKoloreztatu(pTestua.split("morea")[0]);
			kontsola.inprimatu("morea","","mor","");
			efektuaKoloreztatu(pTestua.split("morea")[1]);
		} else if (pTestua.contains("sua")) {
			efektuaKoloreztatu(pTestua.split("sua")[0]);
			kontsola.inprimatu("sua","","sua","");
			efektuaKoloreztatu(pTestua.split("sua")[1]);
		} else if (pTestua.contains("ura")) {
			efektuaKoloreztatu(pTestua.split("ura")[0]);
			kontsola.inprimatu("ura","","ura","");
			efektuaKoloreztatu(pTestua.split("ura")[1]);
		} else if (pTestua.contains("elurra")) {
			efektuaKoloreztatu(pTestua.split("elurra")[0]);
			kontsola.inprimatu("elurra","","elu","");
			efektuaKoloreztatu(pTestua.split("elurra")[1]);
		} else {
			kontsola.inprimatu(pTestua);
		}
	}
	
	//Junitak erabiltzen duten metodoak
	
	void aldatuEfektua(EfektuMota pEfektua) 
	{
		aurrekoTxandakoEfektua = pEfektua;
	}
	
	void kartakAldatu(Karta p1,Karta p2) 
	{
		jokalariLokalaKarta = p1;
		jokalariBotKarta = p2;
	}
	
	void aldatuAurrekoIrabazlea(int p1) 
	{
		if(p1==0) {aurrekoTxandakoIrabazlea=JokalariaLokala.getNireJokalaria("");}
		if(p1==1) {aurrekoTxandakoIrabazlea=JokalariaBot.getNireJokalaria("Sensei");}
	}
	
	void aldatuZenbakiTxikiarenEfektua(boolean p1) 
	{aurrekoTxandakoEfektuaZenbakia=p1;}
	
	void aldatuZenbakiEfektua (boolean p1) 
	{
		aurrekoTxandakoEfektuaZenbakia = p1;
	}
}

