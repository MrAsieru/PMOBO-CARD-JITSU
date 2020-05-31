package cardjitsu;

import java.util.Random;

public class JokalariaBotEasy extends JokalariaBot {
	
	public JokalariaBotEasy(String pIzena)
	{
		super(pIzena);
	}
	
	public Karta kartaAukeratu(ListaKartak pGordetaKartak) //Beste jokalariaren kartak ez dira erabiltzen bot honekin
	{
		boolean aukeratuta = false;
		Karta karta = (Karta) new KartaNormala(ElementuMota.ELURRA,0,KoloreMota.BERDEA);
		if(karta.getBalioa()==0)
		{
			while(!aukeratuta) {
				karta = this.lortuJolastekoKartaPosz(new Random().nextInt(5));
				if(karta.getErabilgarria()) {
					aukeratuta = true;
				}
			}
		}
		this.kenduJolastekoKarta(karta);
		return karta;
	}
}
