package cardJitsu;

public class JokalariaBot extends Jokalaria {
	
	public static JokalariaBot nireJokalariaBotEasy;

	private JokalariaBot() 
	{
		super();
	}
	
	public static JokalariaBot nireJokalariaBotEasy() 
	{
		if(nireJokalariaBotEasy==null) 
		{
			nireJokalariaBotEasy = new JokalariaBot();
		}
		return nireJokalariaBotEasy;
	}
	
	public void kartaAukeratu() 
	{
		
	}
}
