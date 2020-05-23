package test;

import java.awt.SystemColor;
import java.util.Random;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi.*;

import cardjitsu.*;

public class mainC {
	
	public static void main(String[] args) {
		ColoredPrinter cp = new ColoredPrinter.Builder(1, false).build();
		//System.out.println("[48;2;255;165;0mYess[0m");
		/*
		 * GORRIA,
	URDINA,
	HORIA,
	BERDEA,
	LARANJA,
	MOREA
		 */
		/*cp.println("GORRIA", Attribute.BOLD, FColor.RED, BColor.BLACK);
		cp.println("URDINA", Attribute.BOLD, FColor.BLUE, BColor.BLACK);
		cp.println("HORIA", Attribute.BOLD, FColor.YELLOW, BColor.BLACK);
		cp.println("BERDEA", Attribute.BOLD, FColor.GREEN, BColor.BLACK);
		cp.println("LARANJA", Attribute.BOLD, FColor.WHITE, BColor.BLACK);
		cp.println("MOREA", Attribute.BOLD, FColor.MAGENTA, BColor.BLACK);*/
		
		/*
		 * 1-ez hasi, bold
		 * 38-z hasi foreground; 2 rgb 24bit izateko; r;g;b
		 * 48-z hasi background; 2 rgb 24bit izateko; r;g;b
		 */
		/*String kol[] = new String[] {"ber","gor","hor","lar","mor","urd","bel","zur"};
		for(int i = 0;i<kol.length;i++) {
			for(int j = 0;j<kol.length;j++) {
				Kontsola.getKontsola().inprimatuLinea("LOL","", kol[i], kol[j]);
			}
		}*/
		Kontsola.getKontsola().inprimatuLinea("URDINA", "", "urd", "");
		Kontsola.getKontsola().inprimatuLinea("URA", "", "ura", "");
		Kontsola.getKontsola().inprimatuLinea("ZIAN", "", "zia", "");
	}

}