package cardjitsu;

public class JokalariaLokala extends Jokalaria {
	private static JokalariaLokala nireJokalaria;
	
	private JokalariaLokala(String izena) {
		super(izena);
	}
	
	public static JokalariaLokala getNireJokalaria(String pIzena) {
		if (nireJokalaria == null) {
			nireJokalaria = new JokalariaLokala(pIzena);
		}
		return nireJokalaria;
	}
	
	public static JokalariaLokala getNireJokalaria() {
		if (nireJokalaria == null) {
			nireJokalaria = new JokalariaLokala("Jokalaria1");
		}
		return nireJokalaria;
	}
	
	public Karta kartaAukeratu() {
		
	}
}
