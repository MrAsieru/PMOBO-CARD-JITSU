package cardjitsu;

import java.io.IOException;
import java.util.Scanner;

public class Kontsola {
	private static Kontsola nireKontsola;
	Scanner sc = new Scanner(System.in);
	
	private Kontsola(){}
	//Hecho
	
	public static Kontsola getKontsola() {
		if(nireKontsola == null) {
			nireKontsola = new Kontsola();
		}
		return nireKontsola;
	}
	
	public String testuaIrakurri() {
		String sarrera = sc.nextLine();
		return sarrera;
	}
	//Hecho
	
	public int zenbakiaIrakurri() {
		int sarrera = 0;
		boolean datuOna = false;
		while(!datuOna) {
			try {
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
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			}
			else {
			    Runtime.getRuntime().exec("clear");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//Hecho
	
	public void enterTekla()
	 { 
	        System.out.println("Pultsatu ENTER tekla jarraitzeko...");
	        try
	        {
	            System.in.read();
	        }  
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        }  
	 }
}