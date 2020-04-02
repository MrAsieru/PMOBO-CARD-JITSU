package cardJitsu;

public abstract class Karta {

	public Karta() {
		//atributuak
		private ElementuMota elementua;
		private int balioa;
		private KoloreMota kolorea;
		private boolean erabilgarria;
		
		//metodo eraikitzailea
		public Karta (int pBalioa, boolean pErabilgarria, ElementuMota pElementua, KoloreMota pKolorea) {
			this.balioa=pBalioa;
			this.erabilgarria=pErabilgarria;
			this.elementua=pElementua;
			this.kolorea=pKolorea;
		}
		
		 //gainerako metodoa
		
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
	}
}
