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
	
	private JokalariMota kartakKonprobatu() 
	{
		this.JokalariBotKarta;
		this.JokalariLokalaKarta;
	}

}
