package cardJitsu;

import java.io.IOException;
import java.util.Scanner;

public class Kontsola {
	private static Kontsola nireKontsola;
	
	private Kontsola(){}
	//Hecho
	
	public static Kontsola getKontsola() {
		if(nireKontsola == null) {
			nireKontsola = new Kontsola();
		}
		return nireKontsola;
	}
	
	public String testuaIrakurri() {
		Scanner sc = new Scanner(System.in);
		String sarrera = sc.nextLine();

		return sarrera;
	}
	//Hecho
	
	public int zenbakiaIrakurri() {
		int sarrera = 0;
		boolean datuOna = false;
		while(!datuOna) {
			try {
				Scanner sc = new Scanner(System.in);
				sarrera = Integer.parseInt(sc.nextLine());
				datuOna = true;
			} catch (NumberFormatException e) {
				imprimatu("Zenbaki bat sartu");
			}
		}
		
		return sarrera;
	}
	//Hecho
	
	public void imprimatu(String pTestua) {
		System.out.println(pTestua);
	}
	//Hecho
	
	public  void kontsolaGarbitu() {
		try {
			if (System.getProperty("os.name").contains("Windows")) {
			    Runtime.getRuntime().exec("cls");
			}
			else {
			    Runtime.getRuntime().exec("clear");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//Hecho
	
	private void edozeinTekla()
	 { 
	        System.out.println("Edozein tekla pultsatu jarraitzeko...");
	        try
	        {
	            System.in.read();
	        }  
	        catch(Exception e)
	        {}  
	 }
}