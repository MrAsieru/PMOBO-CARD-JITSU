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
		int irabazlea = -1;
		ElementuMota elementuaW = null;
		ElementuMota elementuaL = null;
		int balioaW = -1;
		int balioaL = -1;
		boolean minwin = false;
		int finala = -1;
		
		if(this.aurrekoTxandakoIrabazlea instanceof (JokalariaLOKALA)Jokalaria) 
		{
			irabazlea = 1;
			elementuaW = JokalariLokalaKarta.getElementua();
			elementuaL = JokalariBotKarta.getElementua();
			balioaW = JokalariLokalaKarta.getBalioa();
			balioaL = JokalariBotKarta.getBalioa();
		}
		else 
		{
			irabazlea = 0;
			elementuaW = JokalariBotKarta.getElementua();
			elementuaL = JokalariLokalaKarta.getElementua();
			balioaW = JokalariBotKarta.getBalioa();
			balioaL = JokalariLokalaKarta.getBalioa();
		}
		
		
		switch(aurrekoTxandakoEfektua) 
		{
		case BIGEHITU:
			balioaW = balioaW + 2;
		case BIKENDU:
			balioaL = balioaL-2;
		case ZENBAKIALDAKETA:
			minwin = true;
		}
		
		switch(irabazlea) 
		{
		
		case 0: //Bot-aren PowerCard
			
			if(elementuaW==elementuaL) 
			{
				if(balioaW>balioaL) 
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
				else if(balioaW<balioaL) 
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
				else if(balioaW==balioaL) 
				{
					finala = 0;
				}
			}
			else if(elementuaIrabazi(elementuaW,elementuaL)) 
			{
				finala = 2;
			}
			else if(elementuaIrabazi(elementuaL,elementuaW)) 
			{
				finala = 1;
			} 
			
		case 1: //Jokalariaren PoweCard
			
			if(elementuaW==elementuaL) 
			{
				if(balioaW>balioaL) 
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
				else if(balioaW<balioaL) 
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
				else if(balioaW==balioaL) 
				{
					finala = 0;
				}
			}
			else if(elementuaIrabazi(elementuaW,elementuaL)) 
			{
				finala = 1;
			}
			else if(elementuaIrabazi(elementuaL,elementuaW)) 
			{
				finala = 2;
			} 
		}
		
		// Imprimaketa
		
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
	
