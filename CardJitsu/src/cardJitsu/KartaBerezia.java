package cardJitsu;

public class KartaBerezia extends Karta {
	
	//atributuak
	private EfektuMota efektua;
	private String deskripzioa;
	
	//metodo eraikitzailea
	public KartaBerezia(ElementuMota pElementua, int pBalioa, KoloreMota pKolorea, EfektuMota pEfektua, String pDeskripzioa) {
		// TODO Auto-generated constructor stub
		super(pElementua, pBalioa, pKolorea);
		this.efektua=pEfektua;
		this.deskripzioa=pDeskripzioa;
	}
	
	public EfektuMota getEfektua() {
		return this.efektua;
	}
	
	public String getDeskripzioa() {
		return this.deskripzioa;
	}
	
	public void imprimatu() {
		Kontsola k = Kontsola.getKontsola();
		k.imprimatu(String.format("E: %s B: %s K: %s Ef: %s D: %s", this.getElementua(), this.getBalioa(), this.getKolorea(), this.getEfektua(), this.getDeskripzioa()));
	}
}
