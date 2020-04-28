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
		public void gehituJolastekoKarta (Karta pKarta) {
			if (jolastekoKartak.getTamaina() <= 5) {
				this.jolastekoKartak.gehituKarta(pKarta);
			} else {
				Kontsola.getKontsola().imprimatu("Ezin dira izan 5 karta baino gehiago!");
			}			
		}
		
		public void kenduJolastekoKarta (Karta pKarta) {
			this.jolastekoKartak.kenduKarta (pKarta);
		}
		public void gehituGordetakoKarta (Karta pKarta) {
			this.gordetakoKartak.gehituKarta(pKarta);
		}
		
		public void kenduGordetakoKarta (Karta pKarta) {
			this.gordetakoKartak.kenduKarta(pKarta);
		}
		public Karta lortuJolastekoKartaPosz(int pPos) throws TartetikKanpoException {
			return this.jolastekoKartak.lortuKartaPosz(pPos);
		}
		public Karta lortuGordetakoKartaPosz(int pPos){
			Karta karta = null;
			try {
				karta = this.gordetakoKartak.lortuKartaPosz(pPos);
			} catch (TartetikKanpoException e) {
				//Ez da ezer egin behar
			}
			return karta;
		}
		public int gordetakoKartenKantitatea() {
			return this.gordetakoKartak.getTamaina();
		}
		
		public String getIzena() {
			return this.izena;
		}
}
