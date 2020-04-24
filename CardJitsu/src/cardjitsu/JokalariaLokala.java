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
		Karta aukeraketa = null;
		boolean lortuta = false;
		while(!lortuta) {
			int sarrera = Kontsola.getKontsola().zenbakiaIrakurri();
			if (1 <= sarrera && sarrera <= 5) {
				aukeraketa = this.lortuJolastekoKartaPosz(sarrera-1);
				
				if(aukeraketa.getErabilgarria()) {
					lortuta = true;
				}
			} else {
				Kontsola.getKontsola().imprimatu("1-etik 5-era den zenbaki bat sartu mesedez.");
			}
		}
		this.kenduJolastekoKarta(aukeraketa);
		return aukeraketa;
	}
}
