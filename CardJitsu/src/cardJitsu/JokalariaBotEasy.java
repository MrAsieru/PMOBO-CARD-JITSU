package cardJitsu;

import java.util.Random;

public class JokalariaBotEasy extends JokalariaBot{

	public JokalariaBotEasy() 
	{
		super();
	}
	
	public Karta kartaAukeratu() 
	{
		return lortuJolastekoKarta(new Random().nextInt(5));
	}
}
