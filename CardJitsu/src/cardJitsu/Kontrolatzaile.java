package cardJitsu;

public class Kontrolatzaile {
	private JokalariaLOKALA jokalari1;
	private JokalariaBot jokalari2;
	private EfektuMota aurrekoTxandakoEfektua;
	private Jokalaria aurrekoTxandakoIrabazlea;
	private Karta JokalariLokalaKarta;
	private Karta JokalariBotKarta;
	public static Kontrolatzaile nireKontrolatzaile;
	
	private Kontrolatzailea() 
	{
		this.jokalari1=null;
		this.jokalari2=null;
		this.aurrekoTxandakoEfektua=null;
		this.JokalariBotKarta=null;
		this.JokalariLokalaKarta=null;
	}
	
	public static Kontrolatzaile getNireKontrolatzailea() 
	{
		if(nireKontrolatzaile==null) 
		{
			nireKontrolatzaile = new Kontrolatzaile();
		}
		return nireKontrolatzaile;
	} 
	
	public void partidaBerriaHasi() 
	{
		
	}
	
	private Jokalaria txandaBerria(Jokalaria pJokalaria) 
	{
		
	}
	
	private Jokalaria partidarenIrabazleaKonprobatu(Jokalaria pJokalaria) 
	{
		
	}
	
	private Karta kartaBatEman(Jokalaria pJokalaria) 
	{
		
	}
	
	private void konprobatuAurrekoEfektua() 
	{
		
	}
	
	private boolean elementuaIrabazi(ElementuMota pElementua1,ElementuMota pElementua2) 
	{
		if(pElementua1==ElementuMota.SUA && pElementua2==ElementuMota.ELURRA) 
		{
			return true;
		}
		else if(pElementua1==ElementuMota.SUA && pElementua2==ElementuMota.URA) 
		{
			return false;
		}
		else if(pElementua1==ElementuMota.URA && pElementua2==ElementuMota.SUA) 
		{
			return true;
		}
		else if(pElementua1==ElementuMota.URA && pElementua2==ElementuMota.ELURRA) 
		{
			return false;
		}
		else if(pElementua1==ElementuMota.ELURRA && pElementua2==ElementuMota.URA) 
		{
			return true;
		}
		else if(pElementua1==ElementuMota.ELURRA && pElementua2==ElementuMota.SUA) 
		{
			return false;
		}
	}
	
	private JokalariMota kartakKonprobatu() 
	{
		//Baloreak gorde
		
		int irabazlea = -1;
		ElementuMota elementuaL = JokalariLokalaKarta.getElementua();;
		ElementuMota elementuaB = JokalariBotKarta.getElementua();
		int balioaL =  JokalariLokalaKarta.getBalioa();
		int balioaB =  JokalariBotKarta.getBalioa();
		boolean minwin = false;
		int finala = -1;
		
		//Aurreko jokaldia nork irabazi duen jakin
		
		if(this.aurrekoTxandakoIrabazlea instanceof (JokalariaLOKALA)Jokalaria) {irabazlea = 1;}else{irabazlea = 0;}
		
		//Irabazlearen arabera balioak aldatu
		
		switch(irabazlea) 
		{
		case 1:
			switch(aurrekoTxandakoEfektua) 
			{
			case BIGEHITU:
				balioaL = balioaL + 2;
			case BIKENDU:
				balioaB = balioaB-2;
			case ZENBAKIALDAKETA:
				minwin = true;
			}
		case 0:
			switch(aurrekoTxandakoEfektua) 
			{
			case BIGEHITU:
				balioaB = balioaB + 2;
			case BIKENDU:
				balioaL = balioaL-2;
			case ZENBAKIALDAKETA:
				minwin = true;
			}
		}
		
		//Nork irabazten du?
		
		if(elementuaL==elementuaB) 
		{
			if(balioaL>balioaB) 
			{
				if(!minwin) 
				{
					finala = 2;
				}
				else 
				{
					finala = 1;
				}
			}
			else if(balioaL<balioaB) 
			{
				if(!minwin) 
				{
					finala = 1;
				}
				else 
				{
					finala = 2;
				}
			}
			else if(balioaL==balioaB) 
			{
				finala = 0;
			}
		}
		else if(elementuaIrabazi(elementuaL,elementuaB)) 
		{
			finala = 2;
		}
		else if(elementuaIrabazi(elementuaB,elementuaL)) 
		{
			finala = 1;
		}
		
		// Imprimaketa
		
		//Balioak heman
		if(finala==1) 
		{
			return JokalariMota.BOT;
		}
		else if(finala==2) 
		{
			return JokalariMota.LOKALA;
		}
		else if(finala==0) 
		{
			return JokalariMota.BERDINKETA;
		}
	}
}
	
