package cardjitsu;

public abstract class Karta {

	private ElementuMota elementua;
	private int balioa;
	private KoloreMota kolorea;
	private boolean erabilgarria;
	
	public Karta (ElementuMota pElementua, int pBalioa, KoloreMota pKolorea) {
		this.balioa=pBalioa;
		this.elementua=pElementua;
		this.kolorea=pKolorea;
		this.erabilgarria=true;
	}
	
	public ElementuMota getElementua() {
		return this.elementua;
	}

	public int getBalioa() {
		return this.balioa;
	}
	 
	public KoloreMota getKolorea() {
		return this.kolorea;
	}
	
	public void setErabilgarria(boolean pErabilgarria) {
		this.erabilgarria=pErabilgarria;
	}
	
	public boolean getErabilgarria() {
		return this.erabilgarria;
	}
	
	public abstract void inprimatu();
}
