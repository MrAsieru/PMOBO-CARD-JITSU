package cardjitsu;

public abstract class JokalariaBot extends Jokalaria {

	public JokalariaBot(String pIzena)
	{
		super(pIzena);
	}
	public abstract Karta kartaAukeratu(ListaKartak gordetaKartak);
}
