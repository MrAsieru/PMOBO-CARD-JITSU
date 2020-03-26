package cardJitsu;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class KartaGuztiak {
	private static ArrayList<Karta> lista = null;
	
	public KartaGuztiak() {
	}
	
	public static void jsonetikKartetara() {
		if (lista == null) {
			JSONParser parser = new JSONParser();
			
			try {
				Object obj = parser.parse(new FileReader("/src/cards_eu.json"));
				
				JSONObject jsonObj = (JSONObject) obj;
				
				JSONArray kartaLista = (JSONArray) jsonObj.get("KartenLista");
				
				Iterator<JSONObject> itr = kartaLista.iterator();
				
				while(itr.hasNext()) {
					JSONObject karta = itr.next();
					ElementuMota elementua;
					int balioa;
					KoloreMota kolorea;
					boolean erabilgarria;
					EfektuMota efektua;
					String deskripzioa;
					boolean kartaBereziaDa = true;
					
					//Elementua
					switch ((String) jsonObj.get("element")) {
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
						System.out.println("Ez da aurikitu elementua");
						break;
					}
					
					//Balioa
					balioa = (int) jsonObj.get("value");
					
					//Kolorea
					switch ((String) jsonObj.get("color")) {
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
					switch((int) jsonObj.get("color")) {
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
					deskripzioa = (String) jsonObj.get("description");
					
					//Karta sartu
					if(kartaBereziaDa) {
						
					} else {
						
					}
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static Karta getKarta(int pPos) {
		
	}
}
