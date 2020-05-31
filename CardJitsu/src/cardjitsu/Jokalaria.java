package cardjitsu;

public abstract class Jokalaria {

	private String izena;
	protected ListaKartak gordetakoKartak;
	private ListaKartak jolastekoKartak;

	public Jokalaria (String pIzena) {
		this.izena = pIzena;
		this.gordetakoKartak = new ListaKartak();
		this.jolastekoKartak = new ListaKartak();
	}
	
	public void gehituJolastekoKarta (Karta pKarta) {
		if (jolastekoKartak.getTamaina() < 5) {
			this.jolastekoKartak.gehituKarta(pKarta);
		} else {
			Kontsola.getKontsola().inprimatuLinea("Ezin dira izan 5 karta baino gehiago! "+this.izena,"","gor","");
		}			
	}
	
	public void kenduJolastekoKarta (Karta pKarta) {
		this.jolastekoKartak.kenduKarta(pKarta);
	}
	
	public void gehituGordetakoKarta (Karta pKarta) {
		this.gordetakoKartak.gehituKarta(pKarta);
	}
	
	public void kenduGordetakoKarta (Karta pKarta) {
		this.gordetakoKartak.kenduKarta(pKarta);
	}
	
	public Karta lortuJolastekoKartaPosz(int pPos) {
		return this.jolastekoKartak.lortuKartaPosz(pPos);
	}
	
	public Karta lortuGordetakoKartaPosz(int pPos){
		return this.gordetakoKartak.lortuKartaPosz(pPos);
	}
	
	public int gordetakoKartenKantitatea() {
		return this.gordetakoKartak.getTamaina();
	}
	
	public int jolastekoKartenKantitatea() {
		return this.jolastekoKartak.getTamaina();
	}
	
	public String getIzena() {
		return this.izena;
	}
}
