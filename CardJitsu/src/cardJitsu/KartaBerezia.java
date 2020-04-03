package cardJitsu;

public class KartaBerezia extends Karta {
	
	//atributuak
	private EfektuMota efektua;
	private string deskripzioa;
	
	//metodo eraikitzailea
	public KartaBerezia(ElementuMota pElementua, int pBalioa, KoloreMota pKolorea, EfektuMota pEfektua, String pDeskripzioa) {
		// TODO Auto-generated constructor stub
		super (pElementua, pBalioa, pKolorea);
		this.efektua=pEfektua;
		this.deskripzioa=pDeskripzioa;
	}
	
	//gainerako metodoak
	public void efektuaGauzatu() {
		if (EfektuMota.BIGEHITU) {
			this.balioa=this.balioa+2;
		}if (EfektuMota.BIKENDU) {
			this.balioa=this.balioa-2;
		}
	}
	
}
