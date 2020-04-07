package test;

import cardJitsu.*;

public class mainC {
	
	public static void main(String[] args) {
		KartaSorta sorta = KartaSorta.getKartaSorta();
		sorta.jsonetikKartetara();
		for(int i = 0; i < sorta.getTamaina(); i++) {
			sorta.getKarta(i).imprimatu();
		}
	}

}
