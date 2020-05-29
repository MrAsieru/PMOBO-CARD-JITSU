package cardjitsu;

public class KartaBerezia extends Karta {
	
	//atributuak
	private EfektuMota efektua;
	private String deskribapena;
	
	//metodo eraikitzailea
	public KartaBerezia(ElementuMota pElementua, int pBalioa, KoloreMota pKolorea, EfektuMota pEfektua, String pDeskribapena) {
		super(pElementua, pBalioa, pKolorea);
		this.efektua=pEfektua;
		this.deskribapena=pDeskribapena;
	}
	
	public EfektuMota getEfektua() {
		return this.efektua;
	}
	
	public String getDeskribapena() {
		return this.deskribapena;
	}
	
	public void inprimatu() {
		Kontsola k = Kontsola.getKontsola();
		k.inprimatuLinea(String.format("E: %s B: %s K: %s Ef: %s D: %s", this.getElementua(), this.getBalioa(), this.getKolorea(), this.getEfektua(), this.getDeskribapena()));
	}
}
