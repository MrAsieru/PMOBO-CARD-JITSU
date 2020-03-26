package cardJitsu;

import java.io.IOException;
import java.util.Scanner;

public class KontsolaKontrolatzailea {
	
	public KontsolaKontrolatzailea(){}
	//Sin hacer
	
	public static String stringIrakurri() {
		Scanner sc = new Scanner(System.in);
		String sarrera = sc.nextLine();

		return sarrera;
	}
	//Hecho
	
	public static int zenbakiaIrakurri() {
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
	
	public static void imprimatu(String pTestua) {
		System.out.println(pTestua);
	}
	//Hecho
	
	public static void kontsolaGarbitu() {
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
}
