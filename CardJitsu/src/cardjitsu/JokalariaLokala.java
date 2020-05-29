package cardjitsu;

public class JokalariaLokala extends Jokalaria {	
	public JokalariaLokala(String izena) {
		super(izena);
	}
	
	public Karta kartaAukeratu() {
		Karta aukeraketa = null;
		boolean lortuta = false;
		while(!lortuta) {
			int sarrera = Kontsola.getKontsola().zenbakiaIrakurri();
			aukeraketa = this.lortuJolastekoKartaPosz(sarrera-1);
			if(1<= sarrera && sarrera <= 5) {
				if (aukeraketa.getErabilgarria()) {
					lortuta = true;
				} else {
					Kontsola.getKontsola().inprimatuLinea("Erabili ahal den karta bat aukeratu mesedez.","","hor","");
				}
			} else {
				Kontsola.getKontsola().inprimatuLinea("1-etik 5-era den zenbaki bat sartu mesedez.","","hor","");
			}
		}
		this.kenduJolastekoKarta(aukeraketa);
		return aukeraketa;
	}
	
	public ListaKartak getGordetakoKartak() 
	{
		return this.gordetakoKartak;
	}
}
