package cardjitsu;

public class JokalariaBot extends Jokalaria {
	
	public static JokalariaBot nireJokalariaBot;

	private JokalariaBot() 
	{
		super();
	}
	
	public static JokalariaBot getNireJokalariaBotEasy() 
	{
		if(nireJokalariaBot==null) 
		{
			nireJokalariaBot = new JokalariaBot();
		}
		return nireJokalariaBot;
	}
	
	public Karta kartaAukeratu() 
	{
		return null;
	}
}
