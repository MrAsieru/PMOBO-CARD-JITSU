package cardjitsu;

import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class KartaSorta {
	private ListaKartak lista = null;
	private static KartaSorta nireKartaSorta;
	
	private KartaSorta() {
		lista = new ListaKartak();
	}
	
	public static KartaSorta getKartaSorta(){
		if(nireKartaSorta == null) {
			nireKartaSorta = new KartaSorta();
		}
		return nireKartaSorta;
	}
	
	public void jsonetikKartetara() {
		if (this.getTamaina() == 0) {
			JSONParser parser = new JSONParser();
			
			try {				
				//JSONObject jsonObj = (JSONObject) parser.parse(new FileReader("/cards_eu.json"));
				JSONObject jsonObj = (JSONObject) parser.parse(new InputStreamReader(getClass().getResourceAsStream("/cards_eu.json"), "UTF-8"));
				
				JSONArray kartaLista = (JSONArray) jsonObj.get("KartenLista");
				
				/* BESTE ERA BAT
				Iterator<JSONObject> itr = kartaLista.iterator();
				while(itr.hasNext()) {
					JSONObject karta = itr.next();
					...
				}*/				
				
				for(int i = 0; i < kartaLista.size(); i++) {
					JSONObject karta = (JSONObject) kartaLista.get(i);
					ElementuMota elementua = null;
					int balioa;
					KoloreMota kolorea = null;
					boolean erabilgarria;
					EfektuMota efektua = null;
					String deskripzioa;
					boolean kartaBereziaDa = true;
					
					//Elementua
					switch ((String) karta.get("element")) {
					case "f":
						elementua = ElementuMota.SUA;
						break;

					case "s":
						elementua = ElementuMota.ELURRA;
						break;
						
					case "w":
						elementua = ElementuMota.URA;
						break;
					default:
						Kontsola.getKontsola().imprimatu("Ez da aurkitu elementua");
						break;
					}
					
					//Balioa
					balioa = Integer.parseInt((String) karta.get("value"));
					
					//Kolorea
					switch ((String) karta.get("color")) {
					case "r":
						kolorea = KoloreMota.GORRIA;
						break;
					case "b":
						kolorea = KoloreMota.URDINA;
						break;
					case "y":
						kolorea = KoloreMota.HORIA;
						break;
					case "g":
						kolorea = KoloreMota.BERDEA;
						break;
					case "o":
						kolorea = KoloreMota.LARANJA;
						break;
					case "p":
						kolorea = KoloreMota.MOREA;
						break;
					default:
						break;
					}
					
					//Erabilgarria
					erabilgarria = true;
					
					//Efektu mota
					switch(Integer.parseInt((String) karta.get("power_id"))) {
					case 0:
						kartaBereziaDa = false;
						break;
					case 1:
						efektua = EfektuMota.ZENBAKIALDAKETA;
						break;
					case 2:
						efektua = EfektuMota.BIGEHITU;
						break;
					case 3:
						efektua = EfektuMota.BIKENDU;
						break;
					case 4:
						efektua = EfektuMota.KENDUELURRA;
						break;
					case 5:
						efektua = EfektuMota.KENDUURA;
						break;
					case 6:
						efektua = EfektuMota.KENDUSUA;
						break;
					case 7:
						efektua = EfektuMota.KENDUGORRIBAT;
						break;
					case 8:
						efektua = EfektuMota.KENDUURDINBAT;
						break;
					case 9:
						efektua = EfektuMota.KENDUBERDEBAT;
						break;
					case 10:
						efektua = EfektuMota.KENDUHORIBAT;
						break;
					case 11:
						efektua = EfektuMota.KENDULARANJABAT;
						break;
					case 12:
						efektua = EfektuMota.KENDUMOREBAT;
						break;
					case 13:
						efektua = EfektuMota.ELURRABLOKEATU;
						break;
					case 14:
						efektua = EfektuMota.SUABLOKEATU;
						break;
					case 15:
						efektua = EfektuMota.URABLOKEATU;
						break;
					case 16:
						efektua = EfektuMota.URATIKSURA;
						break;
					case 17:
						efektua = EfektuMota.ELURRATIKURARA;
						break;
					case 18:
						efektua = EfektuMota.SUTIKELURRARA;
						break;
					case 19:
						efektua = EfektuMota.KENDUGORRIGUZTIAK;
						break;
					case 20:
						efektua = EfektuMota.KENDUURDINGUZTIAK;
						break;
					case 21:
						efektua = EfektuMota.KENDUHORIGUZTIAK;
						break;
					case 22:
						efektua = EfektuMota.KENDUBERDEGUZTIAK;
						break;
					case 23:
						efektua = EfektuMota.KENDULARANJAGUZTIAK;
						break;
					case 24:
						efektua = EfektuMota.KENDUMOREGUZTIAK;
						break;
					}
					
					//Deskripzioa
					deskripzioa = (String) karta.get("description");
					
					//Karta sartu
					Karta kartaB;
					if(kartaBereziaDa) {
						kartaB = new KartaBerezia(elementua,balioa,kolorea,efektua,deskripzioa);
					} else {
						kartaB = (Karta) new KartaNormala(elementua,balioa,kolorea);
					}
					lista.gehituKarta(kartaB);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
	}
	
	public Karta getKarta(int pPos) {
		return lista.lortuKartaPosz(pPos);
	}
	
	public Karta getKarta() {
		return getKarta(new Random().nextInt(this.getTamaina()));
	}
	
	public int getTamaina() {
		return lista.getTamaina();
	}
}
