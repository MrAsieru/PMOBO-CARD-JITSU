package cardjitsu;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Kontsola {
	private static Kontsola nireKontsola;
	private Scanner sc;
	
	private Kontsola(){
		this.sc = new Scanner(System.in);
	}
	
	public static Kontsola getKontsola() {
		if(nireKontsola == null) {
			nireKontsola = new Kontsola();
		}
		return nireKontsola;
	}
	
	public String testuaIrakurri() {
		String sarrera = this.sc.nextLine();
		return sarrera;
	}
	
	public int zenbakiaIrakurri() {
		int sarrera = 0;
		boolean datuOna = false;
		while(!datuOna) {
			try {
				sarrera = Integer.parseInt(this.sc.nextLine());
				datuOna = true;
			} catch (NumberFormatException e) {
				inprimatuLinea("Zenbaki bat sartu","","hor","");
			} catch (NoSuchElementException e) {
				inprimatuLinea("Zenbaki bat sartu","","hor","");
			}
		}
		return sarrera;
	}
	
	public void inprimatuLinea(String pTestua) {
		System.out.println(pTestua);
	}
	public void inprimatuLinea(String pTestua, String pTestuMota, String pKolAur, String pKolAtz) {
		String config = "[";
		switch(pTestuMota) {
		case "azp"://Azpimarratu
			config = config+"4;";
			break;
		}
		//Aurreko kolorea 38;2;r;g;b
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
		//Atzeko kolorea 48;2;r;g;b
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
			config = (config.endsWith(";"))?config.substring(0,config.length()-1):config;
			break;
		}
		
		this.inprimatuLinea(config+"m"+pTestua+"[0m");
	}
	
	public void inprimatu(String pTestua) {
		System.out.print(pTestua);
	}
	
	public void inprimatu(String pTestua, String pTestuMota, String pKolAur, String pKolAtz) {
		String config = "[";
		switch(pTestuMota) {
		case "azp"://Azpimarratu
			config = config+"4;";
			break;
		}
		//Aurreko kolorea 38;2;r;g;b
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
		
		//Atzeko kolorea 48;2;r;g;b
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
			config = (config.endsWith(";"))?config.substring(0,config.length()-1):config;
			break;
		}
		
		this.inprimatu(config+"m"+pTestua+"[0m");
	}
	
	public  void kontsolaGarbitu() {
		try {
			if (System.getProperty("os.name").contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
			    Runtime.getRuntime().exec("clear");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void enterTekla() { 
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