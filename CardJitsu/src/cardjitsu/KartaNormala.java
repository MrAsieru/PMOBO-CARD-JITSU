package cardjitsu;

public class KartaNormala extends Karta {

	//metodo eraikitzailea
	public KartaNormala(ElementuMota pElementua, int pBalioa, KoloreMota pKolorea) {
		super(pElementua, pBalioa, pKolorea);
	}
	
	public void imprimatu() {
		Kontsola k = Kontsola.getKontsola();
		k.imprimatu(String.format("E: %s B: %s K: %s", this.getElementua(), this.getBalioa(), this.getKolorea()));
	}
}
