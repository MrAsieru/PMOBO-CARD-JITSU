package cardJitsu;

import java.util.Random;

public class JokalariaBotEasy extends JokalariaBot{

	public JokalariaBotEasy() 
	{
		super();
	}
	
	public Karta kartaAukeratu() 
	{
		return lortuJolastekoKartaPOZs(new Random().nextInt(5));
	}
}
