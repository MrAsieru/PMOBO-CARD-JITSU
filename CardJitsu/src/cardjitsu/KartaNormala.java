package cardjitsu;

public class KartaNormala extends Karta {

	//metodo eraikitzailea
	public KartaNormala(ElementuMota pElementua, int pBalioa, KoloreMota pKolorea) {
		super(pElementua, pBalioa, pKolorea);
	}
	
	public void inprimatu() {
		Kontsola k = Kontsola.getKontsola();
		k.inprimatuLinea(String.format("E: %s B: %s K: %s", this.getElementua(), this.getBalioa(), this.getKolorea()));
	}
}
