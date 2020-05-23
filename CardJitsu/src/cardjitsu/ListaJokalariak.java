package cardjitsu;

import java.util.ArrayList;
import java.util.Iterator;

public class ListaJokalariak {
	
	private Jokalaria[] jokalariak;
	private EfektuMota aurrekoTxandakoEfektua;
	private boolean aurrekoTxandakoEfektuaZenbakia;
	private Jokalaria aurrekoTxandakoIrabazlea;
	private Karta jokalariLokalaKarta;
	private Karta jokalariBotKarta;
	private static ListaJokalariak nireListaJokalariak;
	
	private ListaJokalariak() 
	{
		this.jokalariak = new Jokalaria[2];
		this.aurrekoTxandakoEfektua = null;
		this.jokalariBotKarta = null;
		this.jokalariLokalaKarta = null;
		this.aurrekoTxandakoEfektuaZenbakia = false;
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
		Kontsola.getKontsola().kontsolaGarbitu();
		Kontsola.getKontsola().inprimatuLinea("   ____              _           _ _ _             ","","lar","");
		Kontsola.getKontsola().inprimatuLinea("  / ___|__ _ _ __ __| |         | (_) |_ ___ _   _ ","","lar","");
		Kontsola.getKontsola().inprimatuLinea(" | |   / _` | '__/ _` |_____ _  | | | __/ __| | | |","","lar","");
		Kontsola.getKontsola().inprimatuLinea(" | |__| (_| | | | (_| |_____| |_| | | |_\\__ \\ |_| |","","lar","");
		Kontsola.getKontsola().inprimatuLinea("  \\____\\__,_|_|  \\__,_|      \\___/|_|\\__|___/\\__,_|","","lar","");
		Kontsola.getKontsola().inprimatuLinea("","","lar","");
		
		
		//Izena eskatu
		Kontsola.getKontsola().inprimatuLinea("Sartu izenak");
		Kontsola.getKontsola().inprimatu("Jokalaria: ");
		String izenaLokala = Kontsola.getKontsola().testuaIrakurri();
		if (izenaLokala.isEmpty()) izenaLokala = "Jokalaria";
		Kontsola.getKontsola().inprimatu("Aurkaria: ");
		String izenaBot = Kontsola.getKontsola().testuaIrakurri();
		if (izenaBot.isEmpty()) izenaBot = "Sensei";
		
		
		//zailtasuna eskatu
		boolean zailtasunalortu = false;
		int zailtasunaZenb = 0;
		while(!zailtasunalortu) 
		{
			Kontsola.getKontsola().kontsolaGarbitu();
			Kontsola.getKontsola().inprimatuLinea("Erabaki zailtasuna zenbakiaren arabera:");
			Kontsola.getKontsola().inprimatu("[1]: ");
			Kontsola.getKontsola().inprimatuLinea("Erraza", "","ber","");
			Kontsola.getKontsola().inprimatu("[2]: ");
			Kontsola.getKontsola().inprimatuLinea("Normala", "","hor","");
			Kontsola.getKontsola().inprimatu("[3]: ");
			Kontsola.getKontsola().inprimatuLinea("Zaila", "","gor","");
			Kontsola.getKontsola().inprimatuLinea("Sartu nahi duzun zailtasuna:");
			zailtasunaZenb = Kontsola.getKontsola().zenbakiaIrakurri();
			if(zailtasunaZenb==1 || zailtasunaZenb==2 || zailtasunaZenb==3) 
			{
				zailtasunalortu = true;
				Kontsola.getKontsola().inprimatu("Zailtasun ");
				switch(zailtasunaZenb) {
				case 1:
					Kontsola.getKontsola().inprimatu("ERRAZA","","ber","");
					break;
				case 2:
					Kontsola.getKontsola().inprimatu("NORMALA","","hor","");
					break;
				case 3:
					Kontsola.getKontsola().inprimatu("ZAILA","","gor","");
					break;
				}
				Kontsola.getKontsola().inprimatuLinea(" aukeratu da.");
				
			}
			else
			{
				Kontsola.getKontsola().inprimatuLinea("Ez da zailtasunik aukeratu, honetarako 1, 2 edo 3 zenbakiak sartu.");
				Kontsola.getKontsola().enterTekla();
			}
		}
		
		//Jokalariak sortu
		jokalariak[0] = new JokalariaLokala(izenaLokala);
		jokalariak[1] = null;
		switch (zailtasunaZenb) {
			case 1:
				jokalariak[1] = new JokalariaBotEasy(izenaBot);
				break;
			case 2:
				jokalariak[1] = new JokalariaBotNormal(izenaBot);
				break;
			case 3:
				jokalariak[1] = new JokalariaBotHard(izenaBot);
				break;
		}
		
		//KartaSorta hasieratu
		KartaSorta.getKartaSorta().jsonetikKartetara();
		
		//Kartakbanatu
		for (int i = 0; i<4;i++) {
			this.kartakBanatu();
		}
		
		Jokalaria irabazlea = this.txandaBerria();
		
		//Irabazleari zorionak eman 
		Kontsola.getKontsola().inprimatuLinea("----------------------","","","zur");
		Kontsola.getKontsola().inprimatuLinea(jokalariak[0].getIzena()+":","","zia","");
		gordetakoKartakImprimatu(jokalariak[0]);
		Kontsola.getKontsola().inprimatuLinea(" ");
		
		Kontsola.getKontsola().inprimatuLinea(jokalariak[1].getIzena()+":","","mag","");
		gordetakoKartakImprimatu(jokalariak[1]);
		Kontsola.getKontsola().inprimatuLinea(" ");
		Kontsola.getKontsola().inprimatuLinea("Zorionak "+irabazlea.getIzena()+", partida irabazi duzu!","","bel","zur");
		
		Kontsola.getKontsola().inprimatuLinea("Partida amaitu egin da, beste bat jokatu nahi duzu? [b/e]","","hor","");
		if("b".equals(Kontsola.getKontsola().testuaIrakurri())) {
			new ListaJokalariak().partidaBerriaHasi();
		}
	}

	private Jokalaria txandaBerria() {
		Kontsola.getKontsola().kontsolaGarbitu();
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
		Kontsola.getKontsola().inprimatuLinea(jokalariak[0].getIzena()+":","","zia","");
		gordetakoKartakImprimatu(jokalariak[0]);
		Kontsola.getKontsola().inprimatuLinea(" ");
		
		Kontsola.getKontsola().inprimatuLinea(jokalariak[1].getIzena()+":","","mag","");
		gordetakoKartakImprimatu(jokalariak[1]);
		Kontsola.getKontsola().inprimatuLinea(" ");
		Kontsola.getKontsola().inprimatu("Zure txanda ");
		Kontsola.getKontsola().inprimatuLinea(jokalariak[0].getIzena(),"","zia","");
		for(int i=1;i<6;i++) {
			Karta karta = null;
			karta = jokalariak[0].lortuJolastekoKartaPosz(i-1);
			//Kontsola.getKontsola().imprimatu("["+((karta.getErabilgarria()) ? i:"#")+"] E: "+karta.getElementua().name()+"\tB: "+karta.getBalioa()+"\tK: "+karta.getKolorea().name()+"\t"+((karta instanceof KartaBerezia) ? "Ef: "+((KartaBerezia) karta).getDeskripzioa().split("#")[0].replaceFirst("<jokalariaL>",  jokalariak[1].getIzena()):""));
			
			Kontsola.getKontsola().inprimatu("["+((karta.getErabilgarria()) ? i:"#")+"] E:");
			
			elementuKoloreduna(karta.getElementua());
			Kontsola.getKontsola().inprimatu("\tB:"+karta.getBalioa()+"\tK:");

			kartaKoloreduna(karta.getKolorea());
			
			Kontsola.getKontsola().inprimatu("  ");//Tabuladorea ondo funtzionatzeko
			
			if(karta instanceof KartaBerezia) {
				if(((KartaBerezia) karta).getDeskripzioa().split("#")[0].contains("<jokalariaL>")) {
					efektuaKoloreztatu("\tEf:"+((KartaBerezia) karta).getDeskripzioa().split("#")[0].split("<jokalariaL>")[0]);
					Kontsola.getKontsola().inprimatu(jokalariak[1].getIzena(),"","mag","");
					efektuaKoloreztatu(((KartaBerezia) karta).getDeskripzioa().split("#")[0].split("<jokalariaL>")[1]);			
				} else {
					Kontsola.getKontsola().inprimatu("\tEf:"+((KartaBerezia) karta).getDeskripzioa().split("#")[0]);
				}
				
			}
			Kontsola.getKontsola().inprimatuLinea("");
		}
		
		//TODO Sensei-ren kartak ez aurkeztu
		for(int i=1;i<jokalariak[1].jolastekoKartenKantitatea()+1;i++) {
			Karta karta = null;
			karta = jokalariak[1].lortuJolastekoKartaPosz(i-1);
			//Kontsola.getKontsola().imprimatu("["+((karta.getErabilgarria()) ? i:"#")+"] E: "+karta.getElementua().name()+"\tB: "+karta.getBalioa()+"\tK: "+karta.getKolorea().name()+"\t"+((karta instanceof KartaBerezia) ? "Ef: "+((KartaBerezia) karta).getDeskripzioa().split("#")[0].replaceFirst("<jokalariaL>",  jokalariak[1].getIzena()):""));
			
			Kontsola.getKontsola().inprimatu("["+((karta.getErabilgarria()) ? i:"#")+"] E:","","gor","");
			
			elementuKoloreduna(karta.getElementua());
			Kontsola.getKontsola().inprimatu("\tB:"+karta.getBalioa()+"\tK:","","gor","");

			kartaKoloreduna(karta.getKolorea());
			
			Kontsola.getKontsola().inprimatu("  ");//Tabuladorea ondo funtzionatzeko
			
			if(karta instanceof KartaBerezia) {
				if(((KartaBerezia) karta).getDeskripzioa().split("#")[0].contains("<jokalariaL>")) {
					efektuaKoloreztatu("\tEf:"+((KartaBerezia) karta).getDeskripzioa().split("#")[0].split("<jokalariaL>")[0]);
					Kontsola.getKontsola().inprimatu(jokalariak[1].getIzena(),"","mag","");
					efektuaKoloreztatu(((KartaBerezia) karta).getDeskripzioa().split("#")[0].split("<jokalariaL>")[1]);			
				} else {
					Kontsola.getKontsola().inprimatu("\tEf:"+((KartaBerezia) karta).getDeskripzioa().split("#")[0],"","gor","");
				}
				
			}
			Kontsola.getKontsola().inprimatuLinea("");
		}
		
		Kontsola.getKontsola().inprimatuLinea("Gogoratu: E = Elementua. B = Balioa. K = Kolorea. Ef = Efektua.");
		
		//Karta erabilgarriak daudela konprobatu
		if(!erabilgarritasunaKonprobatu(jokalariak[0])) {
			Kontsola.getKontsola().inprimatu(jokalariak[0].getIzena(),"","zia","");
			Kontsola.getKontsola().inprimatu(" ezin du kartak erabili, ");
			Kontsola.getKontsola().inprimatu(jokalariak[1].getIzena(),"","mag","");
			Kontsola.getKontsola().inprimatuLinea(" irabazi du partida.");
			return jokalariak[1];
		}
		if(!erabilgarritasunaKonprobatu(jokalariak[1])) {
			Kontsola.getKontsola().inprimatu(jokalariak[1].getIzena(),"","mag","");
			Kontsola.getKontsola().inprimatu(" ezin du kartak erabili, ");
			Kontsola.getKontsola().inprimatu(jokalariak[0].getIzena(),"","zia","");
			Kontsola.getKontsola().inprimatuLinea(" irabazi du partida.");
			return jokalariak[0];
		}
		
		//Jokalariak karta bat aukeratu hau jokatzeko
		
		jokalariLokalaKarta = ((JokalariaLokala)jokalariak[0]).kartaAukeratu();
		if(jokalariak[1] instanceof JokalariaBotEasy) 
		{
			jokalariBotKarta = ((JokalariaBotEasy)jokalariak[1]).kartaAukeratu((JokalariaLokala)jokalariak[0]);
		}
		else if(jokalariak[1] instanceof JokalariaBotNormal) 
		{
			jokalariBotKarta = ((JokalariaBotNormal)jokalariak[1]).kartaAukeratu((JokalariaLokala)jokalariak[0]);
		}
		else if(jokalariak[1] instanceof JokalariaBotHard) 
		{
			jokalariBotKarta = ((JokalariaBotHard)jokalariak[1]).kartaAukeratu((JokalariaLokala)jokalariak[0]);
		}
		
		//TODO kendu inprimatu hau
		Kontsola.getKontsola().inprimatuLinea("[INFO-Sensei]: E:"+jokalariBotKarta.getElementua()+" B:"+jokalariBotKarta.getBalioa()+" K:"+jokalariBotKarta.getKolorea(),"","gor","");
		
		//Txandan jokatutako kartak konprobatu eta karten emaitza imprimatu
		JokalariMota txJok = this.kartakKonprobatu();
		Kontsola.getKontsola().inprimatuLinea("");
		
		if(txJok == JokalariMota.LOKALA) {
			aurrekoTxandakoEfektua = (jokalariLokalaKarta instanceof KartaBerezia) ? ((KartaBerezia) jokalariLokalaKarta).getEfektua():null;
			aurrekoTxandakoIrabazlea = jokalariak[0];
			jokalariak[0].gehituGordetakoKarta(jokalariLokalaKarta);
			Kontsola.getKontsola().inprimatu(jokalariak[0].getIzena(),"","zia","");
			Kontsola.getKontsola().inprimatu(" irabazi du");
			if(jokalariLokalaKarta instanceof KartaBerezia) {	
				Kontsola.getKontsola().inprimatu(", ");
				if(((KartaBerezia) jokalariLokalaKarta).getDeskripzioa().split("#")[1].contains("<jokalaria>")) {
					efektuaKoloreztatu(((KartaBerezia) jokalariLokalaKarta).getDeskripzioa().split("#")[1].split("<")[0]);
					Kontsola.getKontsola().inprimatu(jokalariak[0].getIzena(),"","zia","");
					efektuaKoloreztatu(((KartaBerezia) jokalariLokalaKarta).getDeskripzioa().split("#")[1].split(">")[1]);					
				} else if(((KartaBerezia) jokalariLokalaKarta).getDeskripzioa().split("#")[1].contains("<jokalariaL>")){
					efektuaKoloreztatu(((KartaBerezia) jokalariLokalaKarta).getDeskripzioa().split("#")[1].split("<")[0]);
					Kontsola.getKontsola().inprimatu(jokalariak[1].getIzena(),"","mag","");
					efektuaKoloreztatu(((KartaBerezia) jokalariLokalaKarta).getDeskripzioa().split("#")[1].split(">")[1]);	
				} else {
					Kontsola.getKontsola().inprimatu(((KartaBerezia) jokalariLokalaKarta).getDeskripzioa().split("#")[1]);
				}
				Kontsola.getKontsola().inprimatuLinea("");
			} else {
				Kontsola.getKontsola().inprimatuLinea(".");
			}
			
		} else if (txJok == JokalariMota.BOT) {
			aurrekoTxandakoEfektua = (jokalariBotKarta instanceof KartaBerezia) ? ((KartaBerezia) jokalariBotKarta).getEfektua():null;
			aurrekoTxandakoIrabazlea = jokalariak[1];
			jokalariak[1].gehituGordetakoKarta(jokalariBotKarta);
			Kontsola.getKontsola().inprimatu(jokalariak[1].getIzena(),"","mag","");
			Kontsola.getKontsola().inprimatu(" irabazi du");
			if(jokalariBotKarta instanceof KartaBerezia) {	
				Kontsola.getKontsola().inprimatu(", ");
				if(((KartaBerezia) jokalariBotKarta).getDeskripzioa().split("#")[1].contains("<jokalaria>")) {
					efektuaKoloreztatu(((KartaBerezia) jokalariBotKarta).getDeskripzioa().split("#")[1].split("<")[0]);
					Kontsola.getKontsola().inprimatu(jokalariak[1].getIzena(),"","mag","");
					efektuaKoloreztatu(((KartaBerezia) jokalariBotKarta).getDeskripzioa().split("#")[1].split(">")[1]);					
				} else if(((KartaBerezia) jokalariBotKarta).getDeskripzioa().split("#")[1].contains("<jokalariaL>")){
					efektuaKoloreztatu(((KartaBerezia) jokalariBotKarta).getDeskripzioa().split("#")[1].split("<")[0]);
					Kontsola.getKontsola().inprimatu(jokalariak[0].getIzena(),"","zia","");
					efektuaKoloreztatu(((KartaBerezia) jokalariBotKarta).getDeskripzioa().split("#")[1].split(">")[1]);	
				} else {
					Kontsola.getKontsola().inprimatu(((KartaBerezia) jokalariBotKarta).getDeskripzioa().split("#")[1]);
				}
				Kontsola.getKontsola().inprimatuLinea("");
			} else {
				Kontsola.getKontsola().inprimatuLinea(".");
			}
		} else {
			aurrekoTxandakoEfektua = null;
			aurrekoTxandakoIrabazlea = null;
			Kontsola.getKontsola().inprimatuLinea("Berdinketa bat egon da.");
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
			Kontsola.getKontsola().enterTekla();
			irabazlea = this.txandaBerria();
		}
		
		return irabazlea;
	}

	private boolean partidarenIrabazleaKonprobatu(Jokalaria pJokalaria) 
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
		jokalariak[0].gehituJolastekoKarta(KartaSorta.getKartaSorta().getKarta());
		jokalariak[1].gehituJolastekoKarta(KartaSorta.getKartaSorta().getKarta());
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

	private void aplikatuAurrekoEfektua(Jokalaria pJokalaria) 
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
			default:
				//beste efektuak ez dira kontuan izan behar
				break;
			}
		}
	}

	private JokalariMota kartakKonprobatu() 
	{
		//Baloreak gorde
		
		int aurIrabazlea = -1;
		ElementuMota elementuaL = jokalariLokalaKarta.getElementua();
		ElementuMota elementuaB = jokalariBotKarta.getElementua();
		int balioaL =  jokalariLokalaKarta.getBalioa();
		int balioaB =  jokalariBotKarta.getBalioa();
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
				default:
					//beste efektuak ez dira kontuan izan behar
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
				default:
					//beste efektuak ez dira kontuan izan behar
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
				default:
					//beste efektuak ez dira kontuan izan behar
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
				default:
					//beste efektuak ez dira kontuan izan behar
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
		Kontsola.getKontsola().inprimatu(jokalariak[0].getIzena(),"","zia","");
		Kontsola.getKontsola().inprimatu(": E:");
		elementuKoloreduna(elementuaL);
		Kontsola.getKontsola().inprimatu(" B:"+balioaL+" K:");
		kartaKoloreduna(jokalariLokalaKarta.getKolorea());
		
		if(jokalariLokalaKarta instanceof KartaBerezia) {	
			Kontsola.getKontsola().inprimatu(" Ef:");
			if(((KartaBerezia) jokalariLokalaKarta).getDeskripzioa().split("#")[0].contains("<jokalaria>")) {
				efektuaKoloreztatu(((KartaBerezia) jokalariLokalaKarta).getDeskripzioa().split("#")[0].split("<")[0]);
				Kontsola.getKontsola().inprimatu(jokalariak[0].getIzena(),"","zia","");
				efektuaKoloreztatu(((KartaBerezia) jokalariLokalaKarta).getDeskripzioa().split("#")[0].split(">")[1]);					
			} else if(((KartaBerezia) jokalariLokalaKarta).getDeskripzioa().split("#")[0].contains("<jokalariaL>")){
				efektuaKoloreztatu(((KartaBerezia) jokalariLokalaKarta).getDeskripzioa().split("#")[0].split("<")[0]);
				Kontsola.getKontsola().inprimatu(jokalariak[1].getIzena(),"","mag","");
				efektuaKoloreztatu(((KartaBerezia) jokalariLokalaKarta).getDeskripzioa().split("#")[0].split(">")[1]);	
			} else {
				Kontsola.getKontsola().inprimatu(((KartaBerezia) jokalariLokalaKarta).getDeskripzioa().split("#")[0]);
			}
		}
		Kontsola.getKontsola().inprimatuLinea("");
		
		Kontsola.getKontsola().inprimatu(jokalariak[1].getIzena(),"","mag","");
		Kontsola.getKontsola().inprimatu(": E:");
		elementuKoloreduna(elementuaB);
		Kontsola.getKontsola().inprimatu(" B:"+balioaB+" K:");
		kartaKoloreduna(jokalariBotKarta.getKolorea());
		
		
		if(jokalariBotKarta instanceof KartaBerezia) {		
			Kontsola.getKontsola().inprimatu(" Ef:");
			if(((KartaBerezia) jokalariBotKarta).getDeskripzioa().split("#")[0].contains("<jokalaria>")) {
				efektuaKoloreztatu(((KartaBerezia) jokalariBotKarta).getDeskripzioa().split("#")[0].split("<")[0]);
				Kontsola.getKontsola().inprimatu(jokalariak[1].getIzena(),"","mag","");
				efektuaKoloreztatu(((KartaBerezia) jokalariBotKarta).getDeskripzioa().split("#")[0].split(">")[1]);					
			} else if(((KartaBerezia) jokalariBotKarta).getDeskripzioa().split("#")[0].contains("<jokalariaL>")){
				efektuaKoloreztatu(((KartaBerezia) jokalariBotKarta).getDeskripzioa().split("#")[0].split("<")[0]);
				Kontsola.getKontsola().inprimatu(jokalariak[0].getIzena(),"","zia","");
				efektuaKoloreztatu(((KartaBerezia) jokalariBotKarta).getDeskripzioa().split("#")[0].split(">")[1]);	
			} else {
				Kontsola.getKontsola().inprimatu(((KartaBerezia) jokalariBotKarta).getDeskripzioa().split("#")[0]);
			}
		}
		Kontsola.getKontsola().inprimatuLinea("");
		
		return irabazlea;
	}

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
		
		Kontsola.getKontsola().inprimatu("SUA","azp","sua","");
		Kontsola.getKontsola().inprimatu("\t");
		Kontsola.getKontsola().inprimatu("URA","azp","ura","");
		Kontsola.getKontsola().inprimatu("\t");
		Kontsola.getKontsola().inprimatu("ELURRA","azp","elu","");
		Kontsola.getKontsola().inprimatuLinea("");
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
			
			//Kontsola.getKontsola().inprimatuLinea(sua+((sua.length()!=0)?("\t"+ura+((ura.length()!=0)?("\t"+elurra):("\t"+elurra))):("\t"+ura+((ura.length()!=0)?("\t"+elurra):("\t"+elurra)))));
			if(sua!=null)kartaKoloreduna(sua.getKolorea());
			Kontsola.getKontsola().inprimatu("\t");
			if(ura!=null)kartaKoloreduna(ura.getKolorea());
			Kontsola.getKontsola().inprimatu("\t");
			if(elurra!=null)kartaKoloreduna(elurra.getKolorea());
			Kontsola.getKontsola().inprimatuLinea("");
		}
		
	}
	
	private void kartaKoloreduna(KoloreMota pKolorea) {
		switch(pKolorea) {
		case BERDEA:
			Kontsola.getKontsola().inprimatu("BERDEA","","ber","");
			break;
		case GORRIA:
			Kontsola.getKontsola().inprimatu("GORRIA","","gor","");
			break;
		case HORIA:
			Kontsola.getKontsola().inprimatu("HORIA","","hor","");
			break;
		case LARANJA:
			Kontsola.getKontsola().inprimatu("LARANJA","","lar","");
			break;
		case MOREA:
			Kontsola.getKontsola().inprimatu("MOREA","","mor","");
			break;
		case URDINA:
			Kontsola.getKontsola().inprimatu("URDINA","","urd","");
			break;
		}
	}
	
	private void elementuKoloreduna(ElementuMota pElementua) {
		switch(pElementua) {
		case ELURRA:
			Kontsola.getKontsola().inprimatu("ELURRA","","elu","");
			break;
		case SUA:
			Kontsola.getKontsola().inprimatu("SUA","","sua","");
			break;
		case URA:
			Kontsola.getKontsola().inprimatu("URA","","ura","");
			break;			
		}
	}
	
	private void efektuaKoloreztatu(String pTestua) {
		if(pTestua.contains("gorria")) {
			efektuaKoloreztatu(pTestua.split("gorria")[0]);
			Kontsola.getKontsola().inprimatu("gorria","","gor","");
			efektuaKoloreztatu(pTestua.split("gorria")[1]);
		} else if (pTestua.contains("urdina")) {
			efektuaKoloreztatu(pTestua.split("urdina")[0]);
			Kontsola.getKontsola().inprimatu("urdina","","urd","");
			Kontsola.getKontsola().inprimatu(pTestua.split("urdina")[1]);
		} else if (pTestua.contains("horia")) {
			efektuaKoloreztatu(pTestua.split("horia")[0]);
			Kontsola.getKontsola().inprimatu("horia","","hor","");
			efektuaKoloreztatu(pTestua.split("horia")[1]);
		} else if (pTestua.contains("berdea")) {
			efektuaKoloreztatu(pTestua.split("berdea")[0]);
			Kontsola.getKontsola().inprimatu("berdea","","ber","");
			efektuaKoloreztatu(pTestua.split("berdea")[1]);
		} else if (pTestua.contains("laranja")) {
			efektuaKoloreztatu(pTestua.split("laranja")[0]);
			Kontsola.getKontsola().inprimatu("laranja","","lar","");
			efektuaKoloreztatu(pTestua.split("laranja")[1]);
		} else if (pTestua.contains("morea")) {
			efektuaKoloreztatu(pTestua.split("morea")[0]);
			Kontsola.getKontsola().inprimatu("morea","","mor","");
			efektuaKoloreztatu(pTestua.split("morea")[1]);
		} else if (pTestua.contains("sua")) {
			efektuaKoloreztatu(pTestua.split("sua")[0]);
			Kontsola.getKontsola().inprimatu("sua","","sua","");
			efektuaKoloreztatu(pTestua.split("sua")[1]);
		} else if (pTestua.contains("ura")) {
			efektuaKoloreztatu(pTestua.split("ura")[0]);
			Kontsola.getKontsola().inprimatu("ura","","ura","");
			efektuaKoloreztatu(pTestua.split("ura")[1]);
		} else if (pTestua.contains("elurra")) {
			efektuaKoloreztatu(pTestua.split("elurra")[0]);
			Kontsola.getKontsola().inprimatu("elurra","","elu","");
			efektuaKoloreztatu(pTestua.split("elurra")[1]);
		} else {
			Kontsola.getKontsola().inprimatu(pTestua);
		}
	}
	
	//Junitak erabiltzen duten metodoak
	
	private void aldatuEfektua(EfektuMota pEfektua) 
	{
		aurrekoTxandakoEfektua = pEfektua;
	}
	
	private void kartakAldatu(Karta p1,Karta p2) 
	{
		jokalariLokalaKarta = p1;
		jokalariBotKarta = p2;
	}
	
//	void aldatuAurrekoIrabazlea(int p1) 
//	{
//		if(p1==0) {aurrekoTxandakoIrabazlea= (JokalariaLokala) jokalariak[0];}
//		if(p1==1) {aurrekoTxandakoIrabazlea= (JokalariaBot) jokalariak[1];}
//	}
	
	private void aldatuZenbakiTxikiarenEfektua(boolean p1) 
	{aurrekoTxandakoEfektuaZenbakia=p1;}
	
	private void aldatuZenbakiEfektua (boolean p1) 
	{
		aurrekoTxandakoEfektuaZenbakia = p1;
	}
}
//Que leches pasa que no me deja subir >:v

