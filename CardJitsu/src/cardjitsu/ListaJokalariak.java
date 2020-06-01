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
		this.aurrekoTxandakoEfektuaZenbakia = false;
		this.aurrekoTxandakoIrabazlea = null;
		this.jokalariLokalaKarta = null;
		this.jokalariBotKarta = null;		
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
		try {
			getNireListaJokalariak().partidaBerriaHasi();
		} catch (KartakEzAurkitutaException keae) {
			Kontsola.getKontsola().inprimatuLinea("Ez da aurkitu karten lista", "", "gor", "");
			Kontsola.getKontsola().enterTekla();
			System.exit(0);
		}
	}
	
	public void partidaBerriaHasi() throws KartakEzAurkitutaException {
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
		this.jokalariak[0] = new JokalariaLokala(izenaLokala);
		this.jokalariak[1] = null;
		switch (zailtasunaZenb) {
			case 1:
				this.jokalariak[1] = new JokalariaBotEasy(izenaBot);
				break;
			case 2:
				this.jokalariak[1] = new JokalariaBotNormal(izenaBot);
				break;
			case 3:
				this.jokalariak[1] = new JokalariaBotHard(izenaBot);
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
		Kontsola.getKontsola().inprimatuLinea("   Gordetako kartak   ","","bel","zur");
		Kontsola.getKontsola().inprimatuLinea(this.jokalariak[0].getIzena()+":","","zia","");
		gordetakoKartakImprimatu(this.jokalariak[0]);
		Kontsola.getKontsola().inprimatuLinea(" ");
		
		Kontsola.getKontsola().inprimatuLinea(this.jokalariak[1].getIzena()+":","","mag","");
		gordetakoKartakImprimatu(this.jokalariak[1]);
		Kontsola.getKontsola().inprimatuLinea(" ");
		Kontsola.getKontsola().inprimatuLinea("Zorionak "+irabazlea.getIzena()+", partida irabazi duzu!","","bel","zur");
		
		Kontsola.getKontsola().inprimatuLinea("Partida amaitu egin da, beste bat jokatu nahi duzu? [b/e]","","hor","");
		if("b".equals(Kontsola.getKontsola().testuaIrakurri())) {
			//Atributuak berriro hasieratu
			this.jokalariak = new Jokalaria[2];
			this.aurrekoTxandakoEfektua = null;
			this.aurrekoTxandakoEfektuaZenbakia = false;
			this.aurrekoTxandakoIrabazlea = null;
			this.jokalariLokalaKarta = null;
			this.jokalariBotKarta = null;		
			this.partidaBerriaHasi(); //Partida berria hasi
		}
	}

	private Jokalaria txandaBerria() {
		Kontsola.getKontsola().kontsolaGarbitu();
		Jokalaria irabazlea;
		//Kartak banatu
		this.kartakBanatu();
		
		//Aplikatu aurrreko efektua aurreko txandan galdu duenari
		if(this.aurrekoTxandakoIrabazlea != null && this.aurrekoTxandakoIrabazlea.equals(this.jokalariak[0])) 
		{
			this.aplikatuAurrekoEfektua(this.jokalariak[1]);
		}
		else if(this.aurrekoTxandakoIrabazlea != null && this.aurrekoTxandakoIrabazlea.equals(this.jokalariak[1])) 
		{
			this.aplikatuAurrekoEfektua(this.jokalariak[0]);
		}
		
		//Txanda imprimatu
		Kontsola.getKontsola().inprimatuLinea(this.jokalariak[0].getIzena()+":","","zia","");
		gordetakoKartakImprimatu(this.jokalariak[0]);
		Kontsola.getKontsola().inprimatuLinea(" ");
		
		Kontsola.getKontsola().inprimatuLinea(this.jokalariak[1].getIzena()+":","","mag","");
		gordetakoKartakImprimatu(this.jokalariak[1]);
		Kontsola.getKontsola().inprimatuLinea(" ");
		Kontsola.getKontsola().inprimatu("Zure txanda ");
		Kontsola.getKontsola().inprimatuLinea(this.jokalariak[0].getIzena(),"","zia","");
		for(int i=1;i<6;i++) {
			Karta karta = null;
			karta = this.jokalariak[0].lortuJolastekoKartaPosz(i-1);
			
			Kontsola.getKontsola().inprimatu("["+((karta.getErabilgarria()) ? i:"#")+"] E:");
			
			elementuKoloreduna(karta.getElementua());
			Kontsola.getKontsola().inprimatu("\tB:"+karta.getBalioa()+"\tK:");

			kartaKoloreduna(karta.getKolorea());
			
			Kontsola.getKontsola().inprimatu("  ");
			
			if(karta instanceof KartaBerezia) {
				if(((KartaBerezia) karta).getDeskribapena().split("#")[0].contains("<jokalariaL>")) {
					efektuaKoloreztatu("\tEf:"+((KartaBerezia) karta).getDeskribapena().split("#")[0].split("<jokalariaL>")[0]);
					Kontsola.getKontsola().inprimatu(this.jokalariak[1].getIzena(),"","mag","");
					efektuaKoloreztatu(((KartaBerezia) karta).getDeskribapena().split("#")[0].split("<jokalariaL>")[1]);			
				} else {
					Kontsola.getKontsola().inprimatu("\tEf:"+((KartaBerezia) karta).getDeskribapena().split("#")[0]);
				}
				
			}
			Kontsola.getKontsola().inprimatuLinea("");
		}
		
		Kontsola.getKontsola().inprimatuLinea("Gogoratu: E = Elementua. B = Balioa. K = Kolorea. Ef = Efektua.");
		
		//Karta erabilgarriak daudela konprobatu
		if(!erabilgarritasunaKonprobatu(this.jokalariak[0])) {
			Kontsola.getKontsola().inprimatu(this.jokalariak[0].getIzena(),"","zia","");
			Kontsola.getKontsola().inprimatu(" ezin du kartak erabili, ");
			Kontsola.getKontsola().inprimatu(this.jokalariak[1].getIzena(),"","mag","");
			Kontsola.getKontsola().inprimatuLinea(" irabazi du partida.");
			return this.jokalariak[1];
		}
		if(!erabilgarritasunaKonprobatu(this.jokalariak[1])) {
			Kontsola.getKontsola().inprimatu(this.jokalariak[1].getIzena(),"","mag","");
			Kontsola.getKontsola().inprimatu(" ezin du kartak erabili, ");
			Kontsola.getKontsola().inprimatu(this.jokalariak[0].getIzena(),"","zia","");
			Kontsola.getKontsola().inprimatuLinea(" irabazi du partida.");
			return this.jokalariak[0];
		}
		
		//Jokalariak karta bat aukeratu hau jokatzeko
		this.jokalariLokalaKarta = ((JokalariaLokala)this.jokalariak[0]).kartaAukeratu();
		this.jokalariBotKarta = ((JokalariaBot)this.jokalariak[1]).kartaAukeratu(((JokalariaLokala)this.jokalariak[0]).getGordetakoKartak());
		
		//Txandan jokatutako kartak konprobatu eta karten emaitza imprimatu
		JokalariMota txJok = this.kartakKonprobatu();
		Kontsola.getKontsola().inprimatuLinea("");
		
		if(txJok == JokalariMota.LOKALA) {
			txandarenIrabazleaInprimatu(this.jokalariLokalaKarta, 0);
			
		} else if (txJok == JokalariMota.BOT) {
			txandarenIrabazleaInprimatu(this.jokalariBotKarta, 1);
		} else {
			this.aurrekoTxandakoEfektua = null;
			this.aurrekoTxandakoIrabazlea = null;
			Kontsola.getKontsola().inprimatuLinea("Berdinketa bat egon da.");
		}
		
		//Erabilgarri ez diren kartak erabilgarri izatera bueltatu
		erabilgarriBueltatu(this.jokalariak[0]);
		erabilgarriBueltatu(this.jokalariak[1]);
		
		//Partida norbaitek irabazi badu konprobatu
		if (this.partidarenIrabazleaKonprobatu(this.jokalariak[0])) {
			irabazlea = this.jokalariak[0];
		} else if(this.partidarenIrabazleaKonprobatu(this.jokalariak[1])) {
			irabazlea = this.jokalariak[1];
		} else {
			//Errekurtsibitatea
			Kontsola.getKontsola().enterTekla();
			irabazlea = this.txandaBerria();
		}
		
		return irabazlea;
	}
	
	private void txandarenIrabazleaInprimatu(Karta pJok, int pZen) {
		this.aurrekoTxandakoEfektua = (pJok instanceof KartaBerezia) ? ((KartaBerezia) pJok).getEfektua():null;
		this.aurrekoTxandakoIrabazlea = this.jokalariak[pZen];
		this.jokalariak[pZen].gehituGordetakoKarta(pJok);
		Kontsola.getKontsola().inprimatu(this.jokalariak[pZen].getIzena(),"",(pZen==0)?"zia":"mag","");
		Kontsola.getKontsola().inprimatu(" irabazi du");
		if(pJok instanceof KartaBerezia) {	
			Kontsola.getKontsola().inprimatu(", ");
			if(((KartaBerezia) pJok).getDeskribapena().split("#")[1].contains("<jokalaria>")) {
				efektuaKoloreztatu(((KartaBerezia) pJok).getDeskribapena().split("#")[1].split("<")[0]);
				Kontsola.getKontsola().inprimatu(this.jokalariak[pZen].getIzena(),"",(pZen==0)?"zia":"mag","");
				efektuaKoloreztatu(((KartaBerezia) pJok).getDeskribapena().split("#")[1].split(">")[1]);					
			} else if(((KartaBerezia) pJok).getDeskribapena().split("#")[1].contains("<jokalariaL>")){
				efektuaKoloreztatu(((KartaBerezia) pJok).getDeskribapena().split("#")[1].split("<")[0]);
				Kontsola.getKontsola().inprimatu(this.jokalariak[(pZen==1) ? 0:1].getIzena(),"",(pZen==0)?"mag":"zia","");
				efektuaKoloreztatu(((KartaBerezia) pJok).getDeskribapena().split("#")[1].split(">")[1]);	
			} else {
				Kontsola.getKontsola().inprimatu(((KartaBerezia) pJok).getDeskribapena().split("#")[1]);
			}
			Kontsola.getKontsola().inprimatuLinea("");
		} else {
			Kontsola.getKontsola().inprimatuLinea(".");
		}
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
		this.jokalariak[0].gehituJolastekoKarta(KartaSorta.getKartaSorta().getKarta());
		this.jokalariak[1].gehituJolastekoKarta(KartaSorta.getKartaSorta().getKarta());
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
		
		if(this.aurrekoTxandakoEfektua != null) {
			switch(this.aurrekoTxandakoEfektua) 
			{
			case KENDUURA:
			case KENDUSUA:
			case KENDUELURRA:
				
				if(this.aurrekoTxandakoEfektua==EfektuMota.KENDUURA){mota = ElementuMota.URA;}
				else if(this.aurrekoTxandakoEfektua==EfektuMota.KENDUSUA){mota = ElementuMota.SUA;}
				else if(this.aurrekoTxandakoEfektua==EfektuMota.KENDUELURRA){mota = ElementuMota.ELURRA;}
				
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
	
				if(this.aurrekoTxandakoEfektua==EfektuMota.KENDUGORRIBAT || this.aurrekoTxandakoEfektua==EfektuMota.KENDUGORRIGUZTIAK){kolorea = KoloreMota.GORRIA;}
				else if(this.aurrekoTxandakoEfektua==EfektuMota.KENDUURDINBAT || this.aurrekoTxandakoEfektua==EfektuMota.KENDUURDINGUZTIAK){kolorea = KoloreMota.URDINA;}
				else if(this.aurrekoTxandakoEfektua==EfektuMota.KENDUHORIBAT || this.aurrekoTxandakoEfektua==EfektuMota.KENDUHORIGUZTIAK){kolorea = KoloreMota.HORIA;}
				else if(this.aurrekoTxandakoEfektua==EfektuMota.KENDUBERDEBAT || this.aurrekoTxandakoEfektua==EfektuMota.KENDUBERDEGUZTIAK){kolorea = KoloreMota.BERDEA;}
				else if(this.aurrekoTxandakoEfektua==EfektuMota.KENDULARANJABAT || this.aurrekoTxandakoEfektua==EfektuMota.KENDULARANJAGUZTIAK){kolorea = KoloreMota.LARANJA;}
				else if(this.aurrekoTxandakoEfektua==EfektuMota.KENDUMOREBAT || this.aurrekoTxandakoEfektua==EfektuMota.KENDUMOREGUZTIAK){kolorea = KoloreMota.MOREA;}
				
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
				
				if(this.aurrekoTxandakoEfektua==EfektuMota.SUABLOKEATU){mota = ElementuMota.SUA;}
				else if(this.aurrekoTxandakoEfektua==EfektuMota.URABLOKEATU){mota = ElementuMota.URA;}
				else if(this.aurrekoTxandakoEfektua==EfektuMota.ELURRABLOKEATU){mota = ElementuMota.ELURRA;}
				
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
		JokalariMota AurrekoIrabazlea = (this.aurrekoTxandakoIrabazlea instanceof JokalariaLokala) ? JokalariMota.LOKALA : JokalariMota.BOT;;
		JokalariMota IrabazleFinala = null;
		ElementuMota LokalaKartaElementua = this.jokalariLokalaKarta.getElementua();
		ElementuMota BotKartaElementua = this.jokalariBotKarta.getElementua();
		int LokalaKartaBalioa =  this.jokalariLokalaKarta.getBalioa();
		int BotKartaBalioa =  this.jokalariBotKarta.getBalioa();
		
		// Zenbaki txikiaren efektua aplikatu txanda honetarako
		boolean ZenbakiTxikienaIrabazi = this.aurrekoTxandakoEfektuaZenbakia;
		this.aurrekoTxandakoEfektuaZenbakia = false;
		
		//Aurreko irabazlearen arabera aplikatu aurreko efektua
		if(this.aurrekoTxandakoEfektua != null) {
			switch(AurrekoIrabazlea) 
			{
			case LOKALA:
				switch(this.aurrekoTxandakoEfektua) 
				{
				case BIGEHITU:
					LokalaKartaBalioa = LokalaKartaBalioa+2;
					break;
				case BIKENDU:
					BotKartaBalioa = BotKartaBalioa-2;
					break;
				default:
					//beste efektuak ez dira kontuan izan behar
					break;
				}
				break;
			case BOT:
				switch(this.aurrekoTxandakoEfektua) 
				{
				case BIGEHITU:
					BotKartaBalioa = BotKartaBalioa+2;
					break;
				case BIKENDU:
					LokalaKartaBalioa = LokalaKartaBalioa-2;
					break;
				default:
					//beste efektuak ez dira kontuan izan behar
					break;
				}
				break;
			default:	
				break;
			}
		}
		
		//Karta batek elementua aldatzen badu
		if(this.jokalariLokalaKarta instanceof KartaBerezia) {
			if(((KartaBerezia) this.jokalariLokalaKarta).getEfektua()==EfektuMota.ELURRATIKURARA
					|| ((KartaBerezia) this.jokalariLokalaKarta).getEfektua()==EfektuMota.URATIKSURA
					|| ((KartaBerezia) this.jokalariLokalaKarta).getEfektua()==EfektuMota.SUTIKELURRARA) 
			{
				EfektuMota efektua = ((KartaBerezia) this.jokalariLokalaKarta).getEfektua();
				switch (efektua) 
				{
				case ELURRATIKURARA:
					if(BotKartaElementua==ElementuMota.ELURRA) {BotKartaElementua=ElementuMota.URA;}
					break;
				case URATIKSURA:
					if(BotKartaElementua==ElementuMota.URA) {BotKartaElementua=ElementuMota.SUA;}
					break;
				case SUTIKELURRARA:
					if(BotKartaElementua==ElementuMota.SUA) {BotKartaElementua=ElementuMota.ELURRA;}
					break;
				default:
					//beste efektuak ez dira kontuan izan behar
					break;
				}
			}
		}
		if(this.jokalariBotKarta instanceof KartaBerezia) {
			if(((KartaBerezia) this.jokalariBotKarta).getEfektua()==EfektuMota.ELURRATIKURARA
					|| ((KartaBerezia) this.jokalariBotKarta).getEfektua()==EfektuMota.URATIKSURA
					|| ((KartaBerezia) this.jokalariBotKarta).getEfektua()==EfektuMota.SUTIKELURRARA) 
			{
				EfektuMota efektua = ((KartaBerezia) this.jokalariBotKarta).getEfektua();
				switch (efektua) 
				{
				case ELURRATIKURARA:
					if(LokalaKartaElementua==ElementuMota.ELURRA) {LokalaKartaElementua=ElementuMota.URA;}
					break;
				case URATIKSURA:
					if(LokalaKartaElementua==ElementuMota.URA) {LokalaKartaElementua=ElementuMota.SUA;}
					break;
				case SUTIKELURRARA:
					if(LokalaKartaElementua==ElementuMota.SUA) {LokalaKartaElementua=ElementuMota.ELURRA;}
					break;
				default:
					//beste efektuak ez dira kontuan izan behar
					break;
				}
			}
		}
		
		//Nork irabazten du?
		if(LokalaKartaElementua==BotKartaElementua) 
		{
			if(LokalaKartaBalioa>BotKartaBalioa) 
			{
				if(!ZenbakiTxikienaIrabazi) {IrabazleFinala = JokalariMota.LOKALA;} else {IrabazleFinala = JokalariMota.BOT;}
			}
			else if(LokalaKartaBalioa<BotKartaBalioa) 
			{
				if(!ZenbakiTxikienaIrabazi) {IrabazleFinala = JokalariMota.BOT;} else {IrabazleFinala = JokalariMota.LOKALA;}
			}
			else {IrabazleFinala = JokalariMota.BERDINKETA;}
		}
		else if(elementuaIrabazi(LokalaKartaElementua,BotKartaElementua)) {IrabazleFinala = JokalariMota.LOKALA;}
		else if(elementuaIrabazi(BotKartaElementua,LokalaKartaElementua)) {IrabazleFinala = JokalariMota.BOT;}
		
		//Zenbaki txikiena aplikatzen den begiratu
		this.aurrekoTxandakoEfektuaZenbakia = this.jokalariLokalaKarta instanceof KartaBerezia && ((KartaBerezia)this.jokalariLokalaKarta).getEfektua()==EfektuMota.ZENBAKIALDAKETA ? true :
											  this.jokalariBotKarta instanceof KartaBerezia && ((KartaBerezia)this.jokalariBotKarta).getEfektua()==EfektuMota.ZENBAKIALDAKETA ? true : false;

		//Inprimaketa
		txandarenKartakInprimatu(0,this.jokalariLokalaKarta,LokalaKartaElementua,LokalaKartaBalioa);
		txandarenKartakInprimatu(1,this.jokalariBotKarta,BotKartaElementua,BotKartaBalioa);
		
		return IrabazleFinala;
	}
	
	private void txandarenKartakInprimatu(int pZenb, Karta pKar, ElementuMota pElem, int pBalioa) {
		Kontsola.getKontsola().inprimatu(this.jokalariak[pZenb].getIzena(),"",(pZenb==0)?"zia":"mag","");
		Kontsola.getKontsola().inprimatu(": E:");
		elementuKoloreduna(pElem);
		Kontsola.getKontsola().inprimatu(" B:"+pBalioa+" K:");
		kartaKoloreduna(pKar.getKolorea());
		
		if(pKar instanceof KartaBerezia) {	
			Kontsola.getKontsola().inprimatu(" Ef:");
			if(((KartaBerezia) pKar).getDeskribapena().split("#")[0].contains("<jokalaria>")) {
				efektuaKoloreztatu(((KartaBerezia) pKar).getDeskribapena().split("#")[0].split("<")[0]);
				Kontsola.getKontsola().inprimatu(this.jokalariak[pZenb].getIzena(),"",(pZenb==0)?"zia":"mag","");
				efektuaKoloreztatu(((KartaBerezia) pKar).getDeskribapena().split("#")[0].split(">")[1]);					
			} else if(((KartaBerezia) pKar).getDeskribapena().split("#")[0].contains("<jokalariaL>")){
				efektuaKoloreztatu(((KartaBerezia) pKar).getDeskribapena().split("#")[0].split("<")[0]);
				Kontsola.getKontsola().inprimatu(this.jokalariak[(pZenb==0)?1:0].getIzena(),"",(pZenb==0)?"mag":"zia","");
				efektuaKoloreztatu(((KartaBerezia) pKar).getDeskribapena().split("#")[0].split(">")[1]);	
			} else {
				Kontsola.getKontsola().inprimatu(((KartaBerezia) pKar).getDeskribapena().split("#")[0]);
			}
		}
		Kontsola.getKontsola().inprimatuLinea("");
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
}