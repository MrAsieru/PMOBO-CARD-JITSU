package cardjitsu;

public abstract class Jokalaria {
		//atributuak
		private String izena;
		private ListaKartak gordetakoKartak;
		private ListaKartak jolastekoKartak;

		//metodo eraikitzailea
		public Jokalaria (String pIzena) {
			this.izena=pIzena;
			this.gordetakoKartak= new ListaKartak();
			this.jolastekoKartak= new ListaKartak();
		}
		
		//gainerako metodoak
		public void gehituJolastekoKarta (Karta pkarta) {
			this.jolastekoKartak.gehituKarta(pKarta);
		}
		
		public void kenduJolastekoKarta (Karta pKarta) {
			this.jolastekoKartak.kenduKarta (pKarta);
		}
		public void gehituGordetakoKarta (Karta pkarta) {
			this.gordetakoKartak.gehituKarta(pKarta);
		}
		
		public void kenduGordetakoKarta (Karta pKarta) {
			this.gordetakoKartak.kenduKarta (pKarta);
		}
		public Karta lortuJolastekoKartaPosz(int pPos) {
			return this.jolastekoKartak.lortuKartaPosz(pPos);
		}
		public Karta lortuGordetakoKartaPosz(int pPos) {
			return this.gordetakoKartak.lortuKartaPosz(pPos);
		}
		public int gordetakoKartenKantitatea() {
			return this.gordetakoKartak.getTamaina();
		}
		
		public string getIzena() {
			return this.izena;
		}
		

}
