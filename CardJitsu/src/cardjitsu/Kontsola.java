package cardjitsu;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Kontsola {
	private static Kontsola nireKontsola;
	private Scanner sc;
	
	private Kontsola(){
		sc = new Scanner(System.in);
	}
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
				inprimatuLinea("Zenbaki bat sartu","","hor","");
			} catch (NoSuchElementException e) {
				inprimatuLinea("Zenbaki bat sartu","","hor","");
			}
		}
		return sarrera;
	}
	//Hecho
	
	public void inprimatuLinea(String pTestua) {
		System.out.println(pTestua);
	}
	public void inprimatuLinea(String pTestua, String pTestuMota, String pKolAur, String pKolAtz) {
		String config = "[";
		//Ansi nola erabili https://www.ecma-international.org/publications/files/ECMA-ST/Ecma-048.pdf
		switch(pTestuMota) {
		case "azp"://Azpimarratu
			config = config+"4;";
			break;
		}
		
		switch (pKolAur) {
		case "ber"://Berdea
			config = config+"38;2;98;184;71;";
			break;
		case "gor"://Gorria
			config = config+"38;2;227;60;37;";
			break;
		case "hor"://Horia
			config = config+"38;2;251;234;44;";
			break;
		case "lar"://Laranja
			config = config+"38;2;248;149;43;";
			break;
		case "mor"://Morea
			config = config+"38;2;163;153;202;";
			break;
		case "urd"://Urdina
			config = config+"38;2;30;144;255;";
			break;
		case "mag"://Magenta
			config = config+"38;2;255;0;255;";
			break;
		case "zia"://Zian
			config = config+"38;2;0;255;255;";
			break;
		case "bel"://Beltza
			config = config+"38;2;0;0;0;";
			break;
		case "sua"://Sua
			config = config+"38;2;255;87;0;";
			break;
		case "ura"://Ura
			config = config+"38;2;65;105;255;";
			break;
		case "elu"://Elurra
			config = config+"38;2;255;255;255;";
			break;
		case "zur":
			config = config+"38;2;255;255;255;";
			break;
		default:
			break;
		}
		
		switch (pKolAtz) {
		case "ber"://Berdea
			config = config+"48;2;98;184;71";
			break;
		case "gor"://Gorria
			config = config+"48;2;227;60;37";
			break;
		case "hor"://Horia
			config = config+"48;2;251;234;44";
			break;
		case "lar"://Laranja
			config = config+"48;2;248;149;43";
			break;
		case "mor"://Morea
			config = config+"48;2;163;153;202";
			break;
		case "urd"://Urdina
			config = config+"48;2;30;144;255;";
			break;
		case "mag":
			config = config+"48;2;255;0;255";
			break;
		case "zia":
			config = config+"48;2;0;255;255";
			break;
		case "zur"://Beltza
			config = config+"48;2;255;255;255";
			break;
		case "sua"://Sua
			config = config+"48;2;255;87;0";
			break;
		case "ura"://Ura
			config = config+"48;2;65;105;255";
			break;
		case "elu"://Elurra
			config = config+"48;2;255;255;255";
			break;
		case "bel":
			config = config+"48;2;0;0;0";
			break;
		default:
			break;
		}
		
		this.inprimatuLinea(config+"m"+pTestua+"[0m");
	}
	
	public void inprimatu(String pTestua) {
		System.out.print(pTestua);
	}
	
	public void inprimatu(String pTestua, String pTestuMota, String pKolAur, String pKolAtz) {
		String config = "[";
		//Ansi nola erabili https://www.ecma-international.org/publications/files/ECMA-ST/Ecma-048.pdf
		switch(pTestuMota) {
		case "azp"://Azpimarratu
			config = config+"4;";
			break;
		}
		
		switch (pKolAur) {
		case "ber"://Berdea
			config = config+"38;2;98;184;71;";
			break;
		case "gor"://Gorria
			config = config+"38;2;227;60;37;";
			break;
		case "hor"://Horia
			config = config+"38;2;251;234;44;";
			break;
		case "lar"://Laranja
			config = config+"38;2;248;149;43;";
			break;
		case "mor"://Morea
			config = config+"38;2;163;153;202;";
			break;
		case "urd"://Urdina
			config = config+"38;2;30;144;255;";
			break;
		case "mag":
			config = config+"38;2;255;0;255;";
			break;
		case "zia":
			config = config+"38;2;0;255;255;";
			break;
		case "bel"://Beltza
			config = config+"38;2;0;0;0;";
			break;
		case "sua"://Sua
			config = config+"38;2;255;87;0;";
			break;
		case "ura"://Ura
			config = config+"38;2;65;105;255;";
			break;
		case "elu"://Elurra
			config = config+"38;2;255;255;255;";
			break;
		case "zur":
			config = config+"38;2;255;255;255;";
			break;
		default:
			break;
		}
		
		switch (pKolAtz) {
		case "ber"://Berdea
			config = config+"48;2;98;184;71";
			break;
		case "gor"://Gorria
			config = config+"48;2;227;60;37";
			break;
		case "hor"://Horia
			config = config+"48;2;251;234;44";
			break;
		case "lar"://Laranja
			config = config+"48;2;248;149;43";
			break;
		case "mor"://Morea
			config = config+"48;2;163;153;202";
			break;
		case "urd"://Urdina
			config = config+"48;2;30;144;255";
			break;
		case "mag":
			config = config+"48;2;255;0;255";
			break;
		case "zia":
			config = config+"48;2;0;255;255";
			break;
		case "zur"://Beltza
			config = config+"48;2;255;255;255";
			break;
		case "sua"://Sua
			config = config+"48;2;255;87;0";
			break;
		case "ura"://Ura
			config = config+"48;2;65;105;255";
			break;
		case "elu"://Elurra
			config = config+"48;2;255;255;255";
			break;
		case "bel":
			config = config+"48;2;0;0;0";
			break;
		default:
			break;
		}
		
		this.inprimatu(config+"m"+pTestua+"[0m");
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